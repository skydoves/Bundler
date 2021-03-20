/*
 * Designed and developed by 2020 skydoves (Jaewoong Eum)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.skydoves.bundler

import android.os.Bundle
import com.skydoves.bundler.data.Poster
import com.skydoves.bundler.data.PosterSerializable
import com.skydoves.bundler.data.UserInfo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [21])
@RunWith(RobolectricTestRunner::class)
class FragmentBundleValueTest {

  @Test
  fun bundleValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("boolean", true)
      putExtra("byte", Byte.MAX_VALUE)
      putExtra("char", 'a')
      putExtra("double", 1.23)
      putExtra("float", 1.23f)
      putExtra("int", 123)
      putExtra("long", 123L)
      putExtra("short", 123.toShort())
      putExtra("charSequence", "skydoves")
    }.extras

    val boolean = fragment.bundleValue("boolean", false)
    assertThat(boolean, `is`(true))

    val byte: Byte = fragment.bundleValue("byte", 0.toByte())
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char = fragment.bundleValue("char", 'b')
    assertThat(char, `is`('a'))

    val double: Double = fragment.bundleValue("double", 0.0)
    assertThat(double, `is`(1.23))

    val float: Float = fragment.bundleValue("float", 0.0f)
    assertThat(float, `is`(1.23f))

    val int: Int = fragment.bundleValue("int", 0)
    assertThat(int, `is`(123))

    val long: Long = fragment.bundleValue("long", 0L)
    assertThat(long, `is`(123L))

    val short: Short = fragment.bundleValue("short", 0.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String = fragment.bundleValue("charSequence", "")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test
  fun bundleValueDefaultValueTest() {
    val fragment = TestFragment()

    val boolean = fragment.bundleValue("boolean", true)
    assertThat(boolean, `is`(true))

    val byte: Byte = fragment.bundleValue("byte", Byte.MAX_VALUE)
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char = fragment.bundleValue("char", 'a')
    assertThat(char, `is`('a'))

    val double: Double = fragment.bundleValue("double", 1.23)
    assertThat(double, `is`(1.23))

    val float: Float = fragment.bundleValue("float", 1.23f)
    assertThat(float, `is`(1.23f))

    val int: Int = fragment.bundleValue("int", 123)
    assertThat(int, `is`(123))

    val long: Long = fragment.bundleValue("long", 123L)
    assertThat(long, `is`(123L))

    val short: Short = fragment.bundleValue("short", 123.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String = fragment.bundleValue("charSequence", "skydoves")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleValueWrongTypeExceptionTest() {
    val mock = Poster.create()
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", mock)
    }.extras

    val poster: Poster = fragment.bundleValue("poster", mock)
    poster.id
  }

  @Test
  fun bundleReferenceValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      val poster = Poster.create()
      val serializablePoster = PosterSerializable.create()

      putExtra("bundle", Bundle().apply { putString("bundleString", "skydoves") })
      putExtra("charSequence", "skydoves")
      putExtra("parcelable", poster)
      putExtra("serializable", serializablePoster)
      putExtra("booleanArray", booleanArrayOf(true, false, true))
      putExtra("byteArray", byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()))
      putExtra("doubleArray", doubleArrayOf(0.0, 1.0, 2.0))
      putExtra("floatArray", floatArrayOf(0f, 1f, 2f))
      putExtra("intArray", intArrayOf(0, 1, 2))
      putExtra("longArray", longArrayOf(0L, 1L, 2L))
      putExtra("shortArray", shortArrayOf(0, 1, 2))
    }.extras

    val bundle: Bundle? = fragment.bundleValue("bundle")
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? = fragment.bundleValue("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? = fragment.bundleValue("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? = fragment.bundleValue("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? = fragment.bundleValue("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? = fragment.bundleValue("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? = fragment.bundleValue("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? = fragment.bundleValue("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? = fragment.bundleValue("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? = fragment.bundleValue("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? = fragment.bundleValue("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test
  fun bundleReferenceValueDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    val serializablePoster = PosterSerializable.create()

    val bundle: Bundle? =
      fragment.bundleValue("bundle") { Bundle().apply { putString("bundleString", "skydoves") } }
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? = fragment.bundleValue("charSequence") { "skydoves" }
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? = fragment.bundleValue("parcelable") { poster }
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? =
      fragment.bundleValue("serializable") { serializablePoster }
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? =
      fragment.bundleValue("booleanArray") { booleanArrayOf(true, false, true) }
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? =
      fragment.bundleValue("byteArray") { byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()) }
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? =
      fragment.bundleValue("doubleArray") { doubleArrayOf(0.0, 1.0, 2.0) }
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? = fragment.bundleValue("floatArray") { floatArrayOf(0f, 1f, 2f) }
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? = fragment.bundleValue("intArray") { intArrayOf(0, 1, 2) }
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? = fragment.bundleValue("longArray") { longArrayOf(0L, 1L, 2L) }
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? = fragment.bundleValue("shortArray") { shortArrayOf(0, 1, 2) }
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleReferenceValueWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo: UserInfo? = fragment.bundleValue("userInfo") { UserInfo.create() }
    userInfo?.nickname
  }

  @Test
  fun bundleNonNullReferenceValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      val poster = Poster.create()
      val serializablePoster = PosterSerializable.create()

      putExtra("bundle", Bundle().apply { putString("bundleString", "skydoves") })
      putExtra("charSequence", "skydoves")
      putExtra("parcelable", poster)
      putExtra("serializable", serializablePoster)
      putExtra("booleanArray", booleanArrayOf(true, false, true))
      putExtra("byteArray", byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()))
      putExtra("doubleArray", doubleArrayOf(0.0, 1.0, 2.0))
      putExtra("floatArray", floatArrayOf(0f, 1f, 2f))
      putExtra("intArray", intArrayOf(0, 1, 2))
      putExtra("longArray", longArrayOf(0L, 1L, 2L))
      putExtra("shortArray", shortArrayOf(0, 1, 2))
    }.extras

    val bundle: Bundle = fragment.bundleNonNullValue("bundle")
    assertThat(bundle.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence = fragment.bundleNonNullValue("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster = fragment.bundleNonNullValue("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable = fragment.bundleNonNullValue("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray = fragment.bundleNonNullValue("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray = fragment.bundleNonNullValue("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray = fragment.bundleNonNullValue("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray = fragment.bundleNonNullValue("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray = fragment.bundleNonNullValue("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray = fragment.bundleNonNullValue("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray = fragment.bundleNonNullValue("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = java.lang.IllegalArgumentException::class)
  fun bundleNonNullReferenceValueWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo: UserInfo = fragment.bundleNonNullValue("userInfo")
    userInfo.nickname
  }

  @Test(expected = NullPointerException::class)
  fun bundleNonNullReferenceValueNoValueIncludedExceptionTest() {
    val fragment = TestFragment()

    val poster: Poster = fragment.bundleNonNullValue("poster")
    poster.name
  }

  @Test
  fun bundleArrayValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    fragment.arguments = intentOf {
      putExtra("stringArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArray", arrayOf(poster, poster, poster))
    }.extras

    val stringArray = fragment.bundleArrayValue<String>("stringArray")
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray = fragment.bundleArrayValue<String>("charSequenceArray")
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray = fragment.bundleArrayValue<Poster>("parcelableArray")
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayValueDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    val stringArray = fragment.bundleArrayValue("stringArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray = fragment.bundleArrayValue("charSequenceArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray = fragment.bundleArrayValue("parcelableArray") {
      arrayOf(poster, poster, poster)
    }
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayValueWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo = fragment.bundleArrayValue("userInfo") { arrayOf(UserInfo.create()) }
    userInfo?.get(0)
  }

  @Test
  fun bundleArrayListValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    fragment.arguments = intentOf {
      putExtra("stringArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArrayList", arrayListOf(poster, poster, poster))
    }.extras

    val stringArrayList = fragment.bundleArrayListValue<String>("stringArrayList")
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList = fragment.bundleArrayListValue<String>("charSequenceArrayList")
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList = fragment.bundleArrayListValue<Poster>("parcelableArrayList")
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayLsitValueDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    val stringArrayList = fragment.bundleArrayListValue("stringArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList = fragment.bundleArrayListValue("charSequenceArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList = fragment.bundleArrayListValue("parcelableArrayList") {
      arrayListOf(poster, poster, poster)
    }
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayListValueWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo = fragment.bundleArrayListValue("userInfo") { arrayListOf(UserInfo.create()) }
    userInfo?.get(0)
  }
}
