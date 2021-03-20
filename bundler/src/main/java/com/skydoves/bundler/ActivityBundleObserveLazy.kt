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

import android.app.Activity
import androidx.lifecycle.LiveData
import com.skydoves.bundler.IntentLiveDataProvider.provideArrayIntentLiveData
import com.skydoves.bundler.IntentLiveDataProvider.provideArrayListIntentLiveData
import com.skydoves.bundler.IntentLiveDataProvider.provideIntentLiveData

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Returns a [LiveData] which has a retrieved primitive type of extended data from the Intent.
 * The implementation of the [LiveData] is a [SingleShotLiveData] that emits data only a single time to
 * a single observer. We can observe only once using one observer. And the observer will be unregistered
 * from the [SingleShotLiveData] after observing data at once.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Activity.observeBundle(
  key: String,
  defaultValue: T
): Lazy<LiveData<T>> {
  return lazy(LazyThreadSafetyMode.NONE) {
    val initialValue by bundle(key, defaultValue)
    provideIntentLiveData {
      initialValue
    }
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Returns a [LiveData] which has a references primitive type of extended data from the Intent.
 * The implementation of the [LiveData] is a [SingleShotLiveData] that emits data only a single time to
 * a single observer. We can observe only once using one observer. And the observer will be unregistered
 * from the [SingleShotLiveData] after observing data at once.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any?> Activity.observeBundle(
  key: String,
  crossinline defaultValue: () -> T? = { null }
): Lazy<LiveData<T>> {
  return lazy(LazyThreadSafetyMode.NONE) {
    val initialValue by bundle(key, defaultValue)
    provideIntentLiveData {
      initialValue
    }
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Returns a [LiveData] which has a references array type of extended data from the Intent.
 * The implementation of the [LiveData] is a [SingleShotLiveData] that emits data only a single time to
 * a single observer. We can observe only once using one observer. And the observer will be unregistered
 * from the [SingleShotLiveData] after observing data at once.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Activity.observeBundleArray(
  key: String,
  crossinline defaultValue: () -> Array<T>? = { null }
): Lazy<LiveData<Array<T>>> {
  return lazy(LazyThreadSafetyMode.NONE) {
    val initialValue by bundleArray(key, defaultValue)
    provideArrayIntentLiveData {
      initialValue
    }
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Returns a [LiveData] which has a references array list type of extended data from the Intent.
 * The implementation of the [LiveData] is a [SingleShotLiveData] that emits data only a single time to
 * a single observer. We can observe only once using one observer. And the observer will be unregistered
 * from the [SingleShotLiveData] after observing data at once.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Activity.observeBundleArrayList(
  key: String,
  crossinline defaultValue: () -> ArrayList<T>? = { null }
): Lazy<LiveData<ArrayList<T>>> {
  return lazy(LazyThreadSafetyMode.NONE) {
    val initialValue by bundleArrayList(key, defaultValue)
    provideArrayListIntentLiveData {
      initialValue
    }
  }
}
