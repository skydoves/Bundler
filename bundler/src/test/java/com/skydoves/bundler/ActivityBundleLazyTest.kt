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
import com.skydoves.bundler.data.Poster
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
}
