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
class ActivityBundleLazyTest {

  @Test
  fun bundleLazyTest() {
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

    val boolean by activity.bundle("boolean", false)
    assertThat(boolean, `is`(true))

    val byte: Byte by activity.bundle("byte", 0.toByte())
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char by activity.bundle("char", 'b')
    assertThat(char, `is`('a'))

    val double: Double by activity.bundle("double", 0.0)
    assertThat(double, `is`(1.23))

    val float: Float by activity.bundle("float", 0.0f)
    assertThat(float, `is`(1.23f))

    val int: Int by activity.bundle("int", 0)
    assertThat(int, `is`(123))

    val long: Long by activity.bundle("long", 0L)
    assertThat(long, `is`(123L))

    val short: Short by activity.bundle("short", 0.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String by activity.bundle("charSequence", "")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test
  fun bundleLazyDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val boolean by activity.bundle("boolean", true)
    assertThat(boolean, `is`(true))

    val byte: Byte by activity.bundle("byte", Byte.MAX_VALUE)
    assertThat(byte, `is`(Byte.MAX_VALUE))

    val char: Char by activity.bundle("char", 'a')
    assertThat(char, `is`('a'))

    val double: Double by activity.bundle("double", 1.23)
    assertThat(double, `is`(1.23))

    val float: Float by activity.bundle("float", 1.23f)
    assertThat(float, `is`(1.23f))

    val int: Int by activity.bundle("int", 123)
    assertThat(int, `is`(123))

    val long: Long by activity.bundle("long", 123L)
    assertThat(long, `is`(123L))

    val short: Short by activity.bundle("short", 123.toShort())
    assertThat(short, `is`(123.toShort()))

    val charSequence: String by activity.bundle("charSequence", "skydoves")
    assertThat(charSequence, `is`("skydoves"))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleLazyWrongTypeExceptionTest() {
    val mock = Poster.create()
    val activity = TestActivity()
    activity.intent = Intent().apply {
      putExtra("poster", mock)
    }

    val poster: Poster by activity.bundle("poster", mock)
    poster.id
  }

  @Test
  fun bundleReferenceLazyTest() {
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

    val bundle: Bundle? by activity.bundle("bundle")
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? by activity.bundle("charSequence")
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? by activity.bundle("parcelable")
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? by activity.bundle("serializable")
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? by activity.bundle("booleanArray")
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? by activity.bundle("byteArray")
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? by activity.bundle("doubleArray")
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? by activity.bundle("floatArray")
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? by activity.bundle("intArray")
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? by activity.bundle("longArray")
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? by activity.bundle("shortArray")
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test
  fun bundleReferenceLazyDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()
    val poster = Poster.create()
    val serializablePoster = PosterSerializable.create()

    val bundle: Bundle? by activity.bundle("bundle") { Bundle().apply { putString("bundleString", "skydoves") } }
    assertThat(bundle?.getString("bundleString"), `is`("skydoves"))

    val charSequence: CharSequence? by activity.bundle("charSequence") { "skydoves" }
    assertThat(charSequence, `is`("skydoves"))

    val parcelable: Poster? by activity.bundle("parcelable") { poster }
    assertThat(parcelable, `is`(Poster.create()))

    val serializable: PosterSerializable? by activity.bundle("serializable") { serializablePoster }
    assertThat(serializable, `is`(PosterSerializable.create()))

    val booleanArray: BooleanArray? by activity.bundle("booleanArray") { booleanArrayOf(true, false, true) }
    assertThat(booleanArray, `is`(booleanArrayOf(true, false, true)))

    val byteArray: ByteArray? by activity.bundle("byteArray") { byteArrayOf(0.toByte(), 1.toByte(), 2.toByte()) }
    assertThat(byteArray, `is`(byteArrayOf(0.toByte(), 1.toByte(), 2.toByte())))

    val doubleArray: DoubleArray? by activity.bundle("doubleArray") { doubleArrayOf(0.0, 1.0, 2.0) }
    assertThat(doubleArray, `is`(doubleArrayOf(0.0, 1.0, 2.0)))

    val floatArray: FloatArray? by activity.bundle("floatArray") { floatArrayOf(0f, 1f, 2f) }
    assertThat(floatArray, `is`(floatArrayOf(0f, 1f, 2f)))

    val intArray: IntArray? by activity.bundle("intArray") { intArrayOf(0, 1, 2) }
    assertThat(intArray, `is`(intArrayOf(0, 1, 2)))

    val longArray: LongArray? by activity.bundle("longArray") { longArrayOf(0L, 1L, 2L) }
    assertThat(longArray, `is`(longArrayOf(0L, 1L, 2L)))

    val shortArray: ShortArray? by activity.bundle("shortArray") { shortArrayOf(0, 1, 2) }
    assertThat(shortArray, `is`(shortArrayOf(0, 1, 2)))
  }

  @Test(expected = IllegalArgumentException::class)
  fun bundleReferenceLazyWrongTypeExceptionTest() {
    val activity = TestActivity()

    val userInfo: UserInfo? by activity.bundle("userInfo") { UserInfo.create() }
    userInfo?.nickname
  }
}
