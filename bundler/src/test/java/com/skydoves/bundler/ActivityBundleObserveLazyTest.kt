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
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import com.skydoves.bundler.data.Poster
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [21])
@RunWith(RobolectricTestRunner::class)
class ActivityBundleObserveLazyTest {

  @Test
  fun observeBundleLazyTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("int", 123)
    }

    val intLiveData by activity.observeBundle("int", 0)

    val intObserver = mock<Observer<Int>>()
    intLiveData.observeForever(intObserver)

    verify(intObserver).onChanged(123)
  }

  @Test
  fun observeBundleLazyTwiceTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("int", 123)
    }

    val intLiveData by activity.observeBundle("int", 0)

    val intObserver0 = mock<Observer<Int>>()
    intLiveData.observeForever(intObserver0)

    verify(intObserver0).onChanged(123)

    val intObserver1 = mock<Observer<Int>>()
    intLiveData.observeForever(intObserver1)

    verifyNoMoreInteractions(intObserver1)
  }

  @Test
  fun observeBundleLazyDefaultValueTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val intLiveData by activity.observeBundle("int", 123)

    val observer = mock<Observer<Int>>()
    intLiveData.observeForever(observer)

    verify(observer).onChanged(123)
  }

  fun observeBundleReferenceLazyTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("poster", Poster.create())
    }

    val posterLiveData by activity.observeBundle<Poster>("poster")

    val posterObserver = mock<Observer<Poster>>()
    posterLiveData.observeForever(posterObserver)

    verify(posterObserver).onChanged(Poster.create())
  }

  @Test
  fun observeBundleLazyNullTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val posterLiveData by activity.observeBundle<Poster>("poster")

    val posterObserver = mock<Observer<Poster>>()
    posterLiveData.observeForever(posterObserver)

    verify(posterObserver).onChanged(null)
  }

  @Test
  fun observeBundleArrayLazyTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("posterArray", arrayOf(Poster.create(), Poster.create()))
    }
    val posterArrayLiveData by activity.observeBundleArray<Poster>("posterArray")

    MatcherAssert.assertThat(posterArrayLiveData.value?.size, CoreMatchers.`is`(2))

    val observer = mock<Observer<Array<Poster>>>()
    posterArrayLiveData.observeForever(observer)

    verify(observer).onChanged(arrayOf(Poster.create(), Poster.create()))
  }

  @Test
  fun observeBundleArrayLazyNullTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val posterArrayLiveData by activity.observeBundleArray<Poster>("posterArray")

    val observer = mock<Observer<Array<Poster>>>()
    posterArrayLiveData.observeForever(observer)

    verify(observer).onChanged(null)
  }

  @Test
  fun observeBundleArrayListLazyTest() {
    val activity = TestActivity()
    activity.intent = intentOf {
      putExtra("posterArrayList", arrayListOf(Poster.create(), Poster.create()))
    }
    val posterArrayListLiveData by activity.observeBundleArrayList<Poster>("posterArrayList")

    MatcherAssert.assertThat(posterArrayListLiveData.value?.size, CoreMatchers.`is`(2))

    val observer = mock<Observer<ArrayList<Poster>>>()
    posterArrayListLiveData.observeForever(observer)

    verify(observer).onChanged(arrayListOf(Poster.create(), Poster.create()))
  }

  @Test
  fun observeBundleArrayListLazyNullTest() {
    val activity = TestActivity()
    activity.intent = Intent()

    val posterArrayListLiveData by activity.observeBundleArrayList<Poster>("posterArrayList")

    val observer = mock<Observer<ArrayList<Poster>>>()
    posterArrayListLiveData.observeForever(observer)

    verify(observer).onChanged(null)
  }
}
