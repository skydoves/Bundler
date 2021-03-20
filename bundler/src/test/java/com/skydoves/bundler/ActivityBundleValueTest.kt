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

import android.content.Intent
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
class ActivityBundleValueTest {

  @Test
  fun bundleValueTest() {
    val activity = TestActivity()
    activity.intent = Intent().apply {
      putExtra("boolean", true)
      putExtra("byte", Byte.MAX_VALUE)
      putExtra("char", 'a')
      putExtra("double", 1.23)
      putExtra("float", 1.23f)
      putExtra("int", 123)
      putExtra("long", 123L)
      putExtra("short", 123.toShort())
      putExtra("charSequence", "skydoves")
    }

    val boolean = activity.bundleValue("boolean", false)
    assertThat(boolean, `is`(true))

    val byte: Byte = activity.bundleValue("byte", 0.toByte())
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char = activity.bundleValue("char", 'b')
    assertThat(char, `is`('a'))

    val double: Double = activity.bundleValue("double", 0.0)
    assertThat(double, `is`(1.23))

    val float: Float = activity.bundleValue("float", 0.0f)
    assertThat(float, `is`(1.23f))

    val int: Int = activity.bundleValue("int", 0)
    assertThat(int, `is`(123))

    val long: Long = activity.bundleValue("long", 0L)
    assertThat(long, `is`(123L))

    val short: Short = activity.bundleValue("short", 0.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String = activity.bundleValue("charSequence", "")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test
  fun bundleValueDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val boolean = activity.bundleValue("boolean", true)
    assertThat(boolean, `is`(true))

    val byte: Byte = activity.bundleValue("byte", Byte.MAX_VALUE)
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char = activity.bundleValue("char", 'a')
    assertThat(char, `is`('a'))

    val double: Double = activity.bundleValue("double", 1.23)
    assertThat(double, `is`(1.23))

    val float: Float = activity.bundleValue("float", 1.23f)
    assertThat(float, `is`(1.23f))

    val int: Int = activity.bundleValue("int", 123)
    assertThat(int, `is`(123))

    val long: Long = activity.bundleValue("long", 123L)
    assertThat(long, `is`(123L))

    val short: Short = activity.bundleValue("short", 123.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String = activity.bundleValue("charSequence", "skydoves")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleValueWrongTypeExceptionTest() {
    val mock = Poster.create()
    val activity = TestActivity()
    activity.intent = Intent().apply {
      putExtra("poster", mock)
    }

    val poster: Poster = activity.bundleValue("poster", mock)
    poster.id
  }

  @Test
  fun bundleReferenceValueTest() {
    val activity = TestActivity()
    activity.intent = Intent().apply {
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
    }

    val bundle: Bundle? = activity.bundleValue("bundle")
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? = activity.bundleValue("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? = activity.bundleValue("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? = activity.bundleValue("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? = activity.bundleValue("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? = activity.bundleValue("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? = activity.bundleValue("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? = activity.bundleValue("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? = activity.bundleValue("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? = activity.bundleValue("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? = activity.bundleValue("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test
  fun bundleReferenceValueDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()
    val poster = Poster.create()
    val serializablePoster = PosterSerializable.create()

    val bundle: Bundle? =
      activity.bundleValue("bundle") { Bundle().apply { putString("bundleString", "skydoves") } }
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? = activity.bundleValue("charSequence") { "skydoves" }
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? = activity.bundleValue("parcelable") { poster }
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? =
      activity.bundleValue("serializable") { serializablePoster }
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? =
      activity.bundleValue("booleanArray") { booleanArrayOf(true, false, true) }
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? =
      activity.bundleValue("byteArray") { byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()) }
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? =
      activity.bundleValue("doubleArray") { doubleArrayOf(0.0, 1.0, 2.0) }
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? = activity.bundleValue("floatArray") { floatArrayOf(0f, 1f, 2f) }
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? = activity.bundleValue("intArray") { intArrayOf(0, 1, 2) }
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? = activity.bundleValue("longArray") { longArrayOf(0L, 1L, 2L) }
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? = activity.bundleValue("shortArray") { shortArrayOf(0, 1, 2) }
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleReferenceValueWrongTypeExceptionTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val userInfo: UserInfo? = activity.bundleValue("userInfo") { UserInfo.create() }
    userInfo?.nickname
  }

  @Test
  fun bundleNonNullReferenceValueTest() {
    val activity = TestActivity()
    activity.intent = Intent().apply {
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
    }

    val bundle: Bundle = activity.bundleNonNullValue("bundle")
    assertThat(bundle.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence = activity.bundleNonNullValue("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster = activity.bundleNonNullValue("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable = activity.bundleNonNullValue("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray = activity.bundleNonNullValue("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray = activity.bundleNonNullValue("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray = activity.bundleNonNullValue("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray = activity.bundleNonNullValue("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray = activity.bundleNonNullValue("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray = activity.bundleNonNullValue("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray = activity.bundleNonNullValue("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = java.lang.IllegalArgumentException::class)
  fun bundleNonNullReferenceValueWrongTypeExceptionTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val userInfo: UserInfo = activity.bundleNonNullValue("userInfo")
    userInfo.nickname
  }

  @Test(expected = NullPointerException::class)
  fun bundleNonNullReferenceValueNoValueIncludedExceptionTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val poster: Poster = activity.bundleNonNullValue("poster")
    poster.name
  }

  @Test
  fun bundleArrayValueTest() {
    val activity = TestActivity()
    val poster = Poster.create()
    activity.intent = Intent().apply {
      putExtra("stringArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArray", arrayOf(poster, poster, poster))
    }

    val stringArray = activity.bundleArrayValue<String>("stringArray")
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray = activity.bundleArrayValue<String>("charSequenceArray")
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray = activity.bundleArrayValue<Poster>("parcelableArray")
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayValueDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()
    val poster = Poster.create()

    val stringArray = activity.bundleArrayValue("stringArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray = activity.bundleArrayValue("charSequenceArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray = activity.bundleArrayValue("parcelableArray") {
      arrayOf(poster, poster, poster)
    }
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayValueWrongTypeExceptionTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val userInfo = activity.bundleArrayValue("userInfo") { arrayOf(UserInfo.create()) }
    userInfo?.get(0)
  }

  @Test
  fun bundleArrayListValueTest() {
    val activity = TestActivity()
    val poster = Poster.create()
    activity.intent = Intent().apply {
      putExtra("stringArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArrayList", arrayListOf(poster, poster, poster))
    }

    val stringArrayList = activity.bundleArrayListValue<String>("stringArrayList")
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList = activity.bundleArrayListValue<String>("charSequenceArrayList")
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList = activity.bundleArrayListValue<Poster>("parcelableArrayList")
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayLsitValueDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()
    val poster = Poster.create()

    val stringArrayList = activity.bundleArrayListValue("stringArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList = activity.bundleArrayListValue("charSequenceArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList = activity.bundleArrayListValue("parcelableArrayList") {
      arrayListOf(poster, poster, poster)
    }
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayListValueWrongTypeExceptionTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val userInfo = activity.bundleArrayListValue("userInfo") { arrayListOf(UserInfo.create()) }
    userInfo?.get(0)
  }
}
