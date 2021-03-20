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
class FragmentBundlerTest {

  @Test
  fun fragmentBundlerTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("name", "skydoves")
    }.extras

    val bundler: Bundler = fragment.fragmentBundler()
    assertThat(bundler.intent.getStringExtra("name"), `is`("skydoves"))
  }

  @Test
  fun fragmentVariableBundlerTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("name", "skydoves")
    }.extras

    val name by fragment.fragmentVariableBundler("skydoves") {
      intent.getStringExtra("name")
    }

    assertThat(name, `is`("skydoves"))
  }

  @Test
  fun fragmentVariableBundlerValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("name", "skydoves")
    }.extras

    val name = fragment.fragmentVariableBundlerValue("skydoves") {
      intent.getStringExtra("name")
    }

    assertThat(name, `is`("skydoves"))
  }

  @Test
  fun fragmentTypedBundlerTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", Poster.create())
    }.extras

    val poster by fragment.fragmentTypedBundler {
      intent.getParcelableExtra("poster") as? Poster
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun fragmentTypedBundlerValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", Poster.create())
    }.extras

    val poster = fragment.fragmentTypedBundlerValue {
      intent.getParcelableExtra("poster") as? Poster
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun fragmentNonNullTypedBundlerTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", Poster.create())
    }.extras

    val poster by fragment.fragmentNonNullTypedBundler {
      intent.getParcelableExtra("poster")!!
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun fragmentNonNullTypedBundlerValueTest() {
    val fragment = TestFragment()
    fragment.arguments = intentOf {
      putExtra("poster", Poster.create())
    }.extras

    val poster = fragment.fragmentNonNullTypedBundlerValue {
      intent.getParcelableExtra("poster")!!
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  @Suppress("UNCHECKED_CAST")
  fun fragmentArrayBundlerTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    fragment.arguments = intentOf {
      putExtra("posterArray", arrayOf(poster, poster, poster))
    }.extras

    val posterArray by fragment.fragmentArrayBundler {
      intent.getParcelableArrayExtra("posterArray") as Array<Poster>
    }

    assertThat(posterArray?.size, `is`(3))
    assertThat(posterArray?.get(0), `is`(poster))
  }

  @Test
  @Suppress("UNCHECKED_CAST")
  fun fragmentArrayBundlerValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    fragment.arguments = intentOf {
      putExtra("posterArray", arrayOf(poster, poster, poster))
    }.extras

    val posterArray = fragment.fragmentArrayBundlerValue {
      intent.getParcelableArrayExtra("posterArray") as Array<Poster>
    }

    assertThat(posterArray?.size, `is`(3))
    assertThat(posterArray?.get(0), `is`(poster))
  }

  @Test
  fun activityArrayListBundlerTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    fragment.arguments = intentOf {
      putExtra("posterArrayList", arrayListOf(poster, poster, poster))
    }.extras

    val posterArrayList by fragment.fragmentArrayListBundler<Poster> {
      intent.getParcelableArrayListExtra("posterArrayList")
    }

    assertThat(posterArrayList?.size, `is`(3))
    assertThat(posterArrayList?.get(0), `is`(poster))
  }

  @Test
  fun activityArrayListBundlerValueTest() {
    val fragment = TestFragment()
    val poster = Poster.create()

    fragment.arguments = intentOf {
      putExtra("posterArrayList", arrayListOf(poster, poster, poster))
    }.extras

    val posterArrayList = fragment.fragmentArrayListBundlerValue<Poster> {
      intent.getParcelableArrayListExtra("posterArrayList")
    }

    assertThat(posterArrayList?.size, `is`(3))
    assertThat(posterArrayList?.get(0), `is`(poster))
  }
}
