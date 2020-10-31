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

package com.skydoves.bundlerdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.bundler.intentOf

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    // generate random instances.
    val poster = Poster.create()
    val userInfo = UserInfo.create()

    // / start the SecondActivity with intent data.
    intentOf<SecondActivity> {
      +("id" to userInfo.id)
      +("name" to userInfo.nickname)
      putExtra("poster", poster)
      putExtra("posterArray", arrayOf(poster))
      putExtra("posterArrayList", arrayListOf(poster))
      startActivity(this@MainActivity)
    }

    // / start the MainFragment with intent data.
    supportFragmentManager.beginTransaction()
      .replace(R.id.container, MainFragment.create(userInfo, poster))
      .addToBackStack(null)
      .commit()
  }
}
