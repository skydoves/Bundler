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

import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

/**
 * @author skydoves (Jaewoong Eum)
 *
 * SingleShotLiveData is an implementation of the [MutableLiveData] that emits [T] data
 * only a single time to a single observer. We can observe only once using one observer.
 * And the observer will be unregistered from the [SingleShotLiveData] after observing data at once.
 */
@PublishedApi
internal class SingleShotLiveData<T> constructor(
  initialValue: T? = null
) : MutableLiveData<T>() {

  private val emitted = AtomicBoolean(false)

  init {
    value = initialValue
  }

  @MainThread
  override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
    if (emitted.compareAndSet(false, true)) {
      super.observe(
        owner,
        {
          observer.onChanged(it)
          removeObserver(observer)
        }
      )
    } else {
      Log.i(TAG, "SingleShotLiveData already has been emitted data.")
    }
  }

  @MainThread
  override fun observeForever(observer: Observer<in T>) {
    if (emitted.compareAndSet(false, true)) {
      super.observeForever {
        observer.onChanged(it)
        removeObserver(observer)
      }
    } else {
      Log.i(TAG, "SingleShotLiveData already has been emitted data.")
    }
  }

  @MainThread
  override fun setValue(value: T?) {
    if (!emitted.get()) {
      super.setValue(value)
    } else {
      Log.i(TAG, "SingleShotLiveData already has been emitted data.")
    }
  }

  override fun postValue(value: T?) {
    if (!emitted.get()) {
      super.postValue(value)
    } else {
      Log.i(TAG, "SingleShotLiveData already has been emitted data.")
    }
  }

  companion object {
    private const val TAG = "SingleShotLiveData"
  }
}
