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

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/**
 * @author skydoves (Jaewoong Eum)
 *
 * A provider for providing [LiveData] which has the desired intent data.
 */
@PublishedApi
internal object IntentLiveDataProvider {

  /**
   * Provide a [LiveData] which has a primitive and references type of extended data from intent.
   *
   * @param initialValue An initial value for a new [LiveData].
   */
  @MainThread
  @PublishedApi
  internal inline fun <reified T : Any?> provideIntentLiveData(
    crossinline initialValue: () -> T?
  ): LiveData<T> {
    return MutableLiveData<T>().apply {
      value = initialValue()
    }
  }

  /**
   * Provide a [LiveData] which has a references array type of extended data from intent.
   *
   * @param initialValue An initial value for a new [LiveData].
   */
  @MainThread
  @PublishedApi
  internal inline fun <reified T : Any> provideArrayIntentLiveData(
    crossinline initialValue: () -> Array<T>?
  ): LiveData<Array<T>> {
    return MutableLiveData<Array<T>>().apply {
      value = initialValue()
    }
  }

  /**
   * Provide a [LiveData] which has a references array list type of extended data from intent.
   *
   * @param initialValue An initial value for a new [LiveData].
   */
  @MainThread
  @PublishedApi
  internal inline fun <reified T : Any> provideArrayListIntentLiveData(
    crossinline initialValue: () -> ArrayList<T>?
  ): LiveData<ArrayList<T>> {
    return MutableLiveData<ArrayList<T>>().apply {
      value = initialValue()
    }
  }
}
