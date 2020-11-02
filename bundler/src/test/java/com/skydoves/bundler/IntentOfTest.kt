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

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.test.core.app.ApplicationProvider
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
class IntentOfTest {

  private val poster = Poster.create()
  private val userInfo = UserInfo.create()

  @Test
  fun intentOfTest() {
    val intent = intentOf {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun intentOfFromIntentTest() {
    val initialIntent = Intent().apply {
      putExtra("id", userInfo.id)
    }

    val intent = initialIntent.intentOf {
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun intentOfFromActionTest() {
    val intent = Intent.ACTION_MAIN.intentOf {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    assertThat(intent.action, `is`(Intent.ACTION_MAIN))
    assertThat(intent.getLongExtra("id", -1), `is`(1000L))

    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun intentOfFromActionWithDataTest() {
    val intent = Intent.ACTION_MAIN.intentOf(Uri.parse("skydoves")) {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    assertThat(intent.action, `is`(Intent.ACTION_MAIN))
    assertThat(intent.data, `is`(Uri.parse("skydoves")))

    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun intentOfFromContextTest() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.intentOf<TestActivity> {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    val componentName = ComponentName(context, TestActivity::class.java)
    assertThat(intent.component?.className, `is`(componentName.className))
    assertThat(intent.component?.packageName, `is`(componentName.packageName))

    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }

  @Test
  fun intentOfFromContextWithActionAndUriTest() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val intent = context.intentOf<TestActivity>(
      action = Intent.ACTION_MAIN,
      uri = Uri.parse("skydoves")
    ) {
      putExtra("id", userInfo.id)
      putExtra("name", userInfo.nickname)
      putExtra("position", userInfo.position)
      putExtra("poster", poster)
    }

    val componentName = ComponentName(context, TestActivity::class.java)
    assertThat(intent.component?.className, `is`(componentName.className))
    assertThat(intent.component?.packageName, `is`(componentName.packageName))

    assertThat(intent.action, `is`(Intent.ACTION_MAIN))
    assertThat(intent.data, `is`(Uri.parse("skydoves")))

    assertThat(intent.getLongExtra("id", -1), `is`(1000L))
    assertThat(intent.getStringExtra("name"), `is`("skydoves"))
    assertThat(intent.getStringExtra("position"), `is`("Android"))
    assertThat(intent.getParcelableExtra("poster"), `is`(poster))
  }
}
