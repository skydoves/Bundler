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

import com.skydoves.bundler.data.Poster
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
}
