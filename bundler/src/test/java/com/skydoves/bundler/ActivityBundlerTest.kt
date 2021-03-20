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
class ActivityBundlerTest {

  @Test
  fun activityBundlerTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val bundler: Bundler = activity.activityBundler()
    bundler.putExtra("name", "skydoves")

    assertThat(activity.intent.getStringExtra("name"), `is`("skydoves"))
  }

  @Test
  fun activityVariableBundlerTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("name", "skydoves")
    }

    val name by activity.activityVariableBundler("skydoves") {
      intent.getStringExtra("name")
    }

    assertThat(name, `is`("skydoves"))
  }

  @Test
  fun activityVariableBundlerValueTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("name", "skydoves")
    }

    val name = activity.activityVariableBundlerValue("skydoves") {
      intent.getStringExtra("name")
    }

    assertThat(name, `is`("skydoves"))
  }

  @Test
  fun activityTypedBundlerTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("poster", Poster.create())
    }

    val poster by activity.activityTypedBundler {
      intent.getParcelableExtra("poster") as? Poster
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun activityTypedBundlerValueTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("poster", Poster.create())
    }

    val poster = activity.activityTypedBundlerValue {
      intent.getParcelableExtra("poster") as? Poster
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun activityNonNullTypedBundlerTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("poster", Poster.create())
    }

    val poster by activity.activityNonNullTypedBundler {
      intent.getParcelableExtra("poster")!!
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  fun activityNonNullTypedBundlerValueTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("poster", Poster.create())
    }

    val poster = activity.activityNonNullTypedBundlerValue {
      intent.getParcelableExtra("poster")!!
    }

    assertThat(poster, `is`(Poster.create()))
  }

  @Test
  @Suppress("UNCHECKED_CAST")
  fun activityArrayBundlerTest() {
    val activity = TestActivity()
    val poster = Poster.create()

    activity.intent = intentOf {
      putExtra("posterArray", arrayOf(poster, poster, poster))
    }

    val posterArray by activity.activityArrayBundler {
      intent.getParcelableArrayExtra("posterArray") as? Array<Poster>
    }

    assertThat(posterArray?.size, `is`(3))
    assertThat(posterArray?.get(0), `is`(poster))
  }

  @Test
  @Suppress("UNCHECKED_CAST")
  fun activityArrayBundlerValueTest() {
    val activity = TestActivity()
    val poster = Poster.create()

    activity.intent = intentOf {
      putExtra("posterArray", arrayOf(poster, poster, poster))
    }

    val posterArray = activity.activityArrayBundlerValue {
      intent.getParcelableArrayExtra("posterArray") as? Array<Poster>
    }

    assertThat(posterArray?.size, `is`(3))
    assertThat(posterArray?.get(0), `is`(poster))
  }

  @Test
  fun activityArrayListBundlerTest() {
    val activity = TestActivity()
    val poster = Poster.create()

    activity.intent = intentOf {
      putExtra("posterArrayList", arrayListOf(poster, poster, poster))
    }

    val posterArrayList by activity.activityArrayListBundler<Poster> {
      intent.getParcelableArrayListExtra("posterArrayList")
    }

    assertThat(posterArrayList?.size, `is`(3))
    assertThat(posterArrayList?.get(0), `is`(poster))
  }

  @Test
  fun activityArrayListBundlerValueTest() {
    val activity = TestActivity()
    val poster = Poster.create()

    activity.intent = intentOf {
      putExtra("posterArrayList", arrayListOf(poster, poster, poster))
    }

    val posterArrayList = activity.activityArrayListBundlerValue<Poster> {
      intent.getParcelableArrayListExtra("posterArrayList")
    }

    assertThat(posterArrayList?.size, `is`(3))
    assertThat(posterArrayList?.get(0), `is`(poster))
  }
}
