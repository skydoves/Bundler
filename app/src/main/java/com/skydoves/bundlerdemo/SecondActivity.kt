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
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.skydoves.bundler.bundle
import com.skydoves.bundler.bundleArray
import com.skydoves.bundler.bundleArrayList

class SecondActivity : AppCompatActivity() {

  private val id: Long by bundle("id", -1)
  private val name: String by bundle("name", "")
  private val poster: Poster? by bundle("poster")

  private val posterArray by bundleArray("posterArray") { arrayOf(Poster.create()) }
  private val posterListArray by bundleArrayList("posterArrayList") { arrayListOf(Poster.create()) }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_second)

    Log.d(TAG, "id: $id")
    Log.d(TAG, "name: $name")
    Log.d(TAG, "poster: $poster")
    Log.d(TAG, "posterArray: ${posterArray?.get(0)}")
    Log.d(TAG, "posterListArray: ${posterListArray?.get(0)}")
  }

  companion object {

    private val TAG = SecondActivity::class.java.simpleName
  }
}
