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
class FragmentBundleLazyTest {

  @Test
  fun bundleLazyTest() {
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

    val boolean by fragment.bundle("boolean", false)
    assertThat(boolean, `is`(true))

    val byte: Byte by fragment.bundle("byte", 0.toByte())
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char by fragment.bundle("char", 'b')
    assertThat(char, `is`('a'))

    val double: Double by fragment.bundle("double", 0.0)
    assertThat(double, `is`(1.23))

    val float: Float by fragment.bundle("float", 0.0f)
    assertThat(float, `is`(1.23f))

    val int: Int by fragment.bundle("int", 0)
    assertThat(int, `is`(123))

    val long: Long by fragment.bundle("long", 0L)
    assertThat(long, `is`(123L))

    val short: Short by fragment.bundle("short", 0.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String by fragment.bundle("charSequence", "")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test
  fun bundleLazyDefaultValueTest() {
    val fragment = TestFragment()

    val boolean by fragment.bundle("boolean", true)
    assertThat(boolean, `is`(true))

    val byte: Byte by fragment.bundle("byte", Byte.MAX_VALUE)
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char by fragment.bundle("char", 'a')
    assertThat(char, `is`('a'))

    val double: Double by fragment.bundle("double", 1.23)
    assertThat(double, `is`(1.23))

    val float: Float by fragment.bundle("float", 1.23f)
    assertThat(float, `is`(1.23f))

    val int: Int by fragment.bundle("int", 123)
    assertThat(int, `is`(123))

    val long: Long by fragment.bundle("long", 123L)
    assertThat(long, `is`(123L))

    val short: Short by fragment.bundle("short", 123.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String by fragment.bundle("charSequence", "skydoves")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleLazyWrongTypeExceptionTest() {
    val mock = Poster.create()
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", mock)
    }.extras

    val poster: Poster by fragment.bundle("poster", mock)
    poster.id
  }

  @Test
  fun bundleReferenceLazyTest() {
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

    val bundle: Bundle? by fragment.bundle("bundle")
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? by fragment.bundle("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? by fragment.bundle("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? by fragment.bundle("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? by fragment.bundle("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? by fragment.bundle("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? by fragment.bundle("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? by fragment.bundle("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? by fragment.bundle("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? by fragment.bundle("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? by fragment.bundle("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test
  fun bundleReferenceLazyDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    val serializablePoster = PosterSerializable.create()

    val bundle: Bundle? by fragment.bundle("bundle") { Bundle().apply { putString("bundleString", "skydoves") } }
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? by fragment.bundle("charSequence") { "skydoves" }
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? by fragment.bundle("parcelable") { poster }
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? by fragment.bundle("serializable") { serializablePoster }
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? by fragment.bundle("booleanArray") { booleanArrayOf(true, false, true) }
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? by fragment.bundle("byteArray") { byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()) }
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? by fragment.bundle("doubleArray") { doubleArrayOf(0.0, 1.0, 2.0) }
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? by fragment.bundle("floatArray") { floatArrayOf(0f, 1f, 2f) }
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? by fragment.bundle("intArray") { intArrayOf(0, 1, 2) }
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? by fragment.bundle("longArray") { longArrayOf(0L, 1L, 2L) }
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? by fragment.bundle("shortArray") { shortArrayOf(0, 1, 2) }
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleReferenceLazyWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo: UserInfo? by fragment.bundle("userInfo") { UserInfo.create() }
    userInfo?.nickname
  }

  @Test
  fun bundleNonNullReferenceLazyTest() {
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

    val bundle: Bundle by fragment.bundleNonNull("bundle")
    assertThat(bundle.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence by fragment.bundleNonNull("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster by fragment.bundleNonNull("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable by fragment.bundleNonNull("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray by fragment.bundleNonNull("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray by fragment.bundleNonNull("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray by fragment.bundleNonNull("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray by fragment.bundleNonNull("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray by fragment.bundleNonNull("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray by fragment.bundleNonNull("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray by fragment.bundleNonNull("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = java.lang.IllegalArgumentException::class)
  fun bundleNonNullReferenceLazyWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo: UserInfo by fragment.bundleNonNull("userInfo")
    userInfo.nickname
  }

  @Test(expected = NullPointerException::class)
  fun bundleNonNullReferenceLazyNoValueIncludedExceptionTest() {
    val fragment = TestFragment()

    val poster: Poster by fragment.bundleNonNull("poster")
    poster.name
  }

  @Test
  fun bundleArrayLazyTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    fragment.arguments = intentOf {
      putExtra("stringArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArray", arrayOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArray", arrayOf(poster, poster, poster))
    }.extras

    val stringArray by fragment.bundleArray<String>("stringArray")
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray by fragment.bundleArray<String>("charSequenceArray")
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray by fragment.bundleArray<Poster>("parcelableArray")
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayLazyDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    val stringArray by fragment.bundleArray("stringArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArray?.size, `is`(3))
    assertThat(stringArray?.get(0), `is`("skydoves0"))

    val charSequenceArray by fragment.bundleArray("charSequenceArray") {
      arrayOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArray?.size, `is`(3))
    assertThat(charSequenceArray?.get(0), `is`("skydoves0"))

    val parcelableArray by fragment.bundleArray("parcelableArray") {
      arrayOf(poster, poster, poster)
    }
    assertThat(parcelableArray?.size, `is`(3))
    assertThat(parcelableArray?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayLazyWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo by fragment.bundleArray("userInfo") { arrayOf(UserInfo.create()) }
    userInfo?.get(0)
  }

  @Test
  fun bundleArrayListLazyTest() {
    val fragment = TestFragment()
    val poster = Poster.create()
    fragment.arguments = intentOf {
      putExtra("stringArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("charSequenceArrayList", arrayListOf("skydoves0", "skydoves1", "skydoves2"))
      putExtra("parcelableArrayList", arrayListOf(poster, poster, poster))
    }.extras

    val stringArrayList by fragment.bundleArrayList<String>("stringArrayList")
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList by fragment.bundleArrayList<String>("charSequenceArrayList")
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList by fragment.bundleArrayList<Poster>("parcelableArrayList")
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test
  fun bundleArrayLsitLazyDefaultValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    val stringArrayList by fragment.bundleArrayList("stringArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(stringArrayList?.size, `is`(3))
    assertThat(stringArrayList?.get(0), `is`("skydoves0"))

    val charSequenceArrayList by fragment.bundleArrayList("charSequenceArrayList") {
      arrayListOf("skydoves0", "skydoves1", "skydoves2")
    }
    assertThat(charSequenceArrayList?.size, `is`(3))
    assertThat(charSequenceArrayList?.get(0), `is`("skydoves0"))

    val parcelableArrayList by fragment.bundleArrayList("parcelableArrayList") {
      arrayListOf(poster, poster, poster)
    }
    assertThat(parcelableArrayList?.size, `is`(3))
    assertThat(parcelableArrayList?.get(0), `is`(poster))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleArrayListLazyWrongTypeExceptionTest() {
    val fragment = TestFragment()

    val userInfo by fragment.bundleArrayList("userInfo") { arrayListOf(UserInfo.create()) }
    userInfo?.get(0)
  }
}
