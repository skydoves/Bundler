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
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.skydoves.bundler.bundle
import com.skydoves.bundler.intent

class MainFragment : Fragment() {

  private val id: Long by bundle("id", -1)
  private val name: String by bundle("name", "")
  private val poster: Poster? by bundle("poster")

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.activity_main, container, false)
    return view.rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)

    Log.d(TAG, "id: $id")
    Log.d(TAG, "name: $name")
    Log.d(TAG, "poster: $poster")
  }

  companion object {

    private val TAG = MainFragment::class.java.simpleName

    fun create(userInfo: UserInfo, poster: Poster): MainFragment {
      return MainFragment().apply {
        arguments = intent {
          +("id" to userInfo.id)
          +("name" to userInfo.nickname)
          +("poster" to poster)
        }.extras
      }
    }
  }
}
