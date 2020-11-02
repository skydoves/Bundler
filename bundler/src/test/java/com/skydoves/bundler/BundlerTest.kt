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
import com.skydoves.bundler.data.UserInfo
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [21])
@RunWith(RobolectricTestRunner::class)
class BundlerTest {

  private val poster = Poster.create()
  private val userInfo = UserInfo.create()

  @Test
  fun putExtraWithKeyValueArgumentsTest() {
    val bundler: Bundler = Bundler().apply {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    val intent = bundler.intent
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun putExtraWithKeyValuePairTest() {
    val bundler: Bundler = Bundler().apply {
      putExtra("id" to userInfo.id)
      putExtra("name" to userInfo.nickname)
      putExtra("position" to userInfo.position)
      putExtra("poster" to poster)
    }

    val intent = bundler.intent
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun putExtraWithUnaryPlusTest() {
    val bundler: Bundler = Bundler().apply {
      +("id" to userInfo.id)
      +("name" to userInfo.nickname)
      +("position" to userInfo.position)
      +("poster" to poster)
    }

    val intent = bundler.intent
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun removeExtraWithUnaryMinusTest() {
    val bundler: Bundler = Bundler().apply {
      +("id" to userInfo.id)
      -"id"
      +("name" to userInfo.nickname)
      -"name"
      +("position" to userInfo.position)
      -"position"
      +("poster" to poster)
      -"poster"
    }

    val intent = bundler.intent
    assertThat(intent.hasExtra("id"), `is`(false))
    assertThat(intent.hasExtra("name"), `is`(false))
    assertThat(intent.hasExtra("position"), `is`(false))
    assertThat(intent.hasExtra("poster"), `is`(false))
  }

  @Test
  fun putExtraWithInfixExtensionTest() {
    val bundler: Bundler = Bundler().apply {
      "id" eq userInfo.id
      "name" eq userInfo.nickname
      "position" eq userInfo.position
      "poster" eq poster
    }

    val intent = bundler.intent
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun putExtraWithBundleOfTest() {
    val bundler: Bundler = Bundler().apply {
      bundleOf(
        ("id" to userInfo.id),
        ("name" to userInfo.nickname),
        ("position" to userInfo.position),
        ("poster" to poster)
      )
    }

    val intent = bundler.intent
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun setIntentOptionsTest() {
    val bundler: Bundler = Bundler().apply {
      setAction(Intent.ACTION_MAIN)
      addCategory(Intent.CATEGORY_APP_MUSIC)
      setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val intent = bundler.intent
    assertThat(intent.action, `is`(Intent.ACTION_MAIN))
    assertThat(intent.hasCategory(Intent.CATEGORY_APP_MUSIC), `is`(true))
    assertThat(intent.flags, `is`(Intent.FLAG_ACTIVITY_NEW_TASK))
  }
}
