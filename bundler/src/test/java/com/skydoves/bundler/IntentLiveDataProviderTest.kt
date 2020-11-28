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

import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.skydoves.bundler.IntentLiveDataProvider.provideArrayIntentLiveData
import com.skydoves.bundler.IntentLiveDataProvider.provideArrayListIntentLiveData
import com.skydoves.bundler.IntentLiveDataProvider.provideIntentLiveData
import com.skydoves.bundler.data.Poster
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [21])
@RunWith(RobolectricTestRunner::class)
class IntentLiveDataProviderTest {

  @Test
  fun provideIntentLiveDataTest() {
    val intLiveData = provideIntentLiveData { 123 }

    val intObserver = mock<Observer<Int>>()
    intLiveData.observeForever(intObserver)

    verify(intObserver).onChanged(123)

    val poster = Poster.create()
    val posterLiveData = provideIntentLiveData { poster }

    val posterObserver = mock<Observer<Poster>>()
    posterLiveData.observeForever(posterObserver)

    verify(posterObserver).onChanged(poster)
  }

  @Test
  fun provideArrayIntentLiveDataTest() {
    val posterArrayLiveData = provideArrayIntentLiveData {
      arrayOf(Poster.create(), Poster.create())
    }

    assertThat(posterArrayLiveData.value?.size, `is`(2))

    val observer = mock<Observer<Array<Poster>>>()
    posterArrayLiveData.observeForever(observer)

    verify(observer).onChanged(arrayOf(Poster.create(), Poster.create()))
  }

  @Test
  fun provideArrayListIntentLiveDataTest() {
    val posterArrayListLiveData = provideArrayListIntentLiveData {
      arrayListOf(Poster.create(), Poster.create())
    }

    assertThat(posterArrayListLiveData.value?.size, `is`(2))

    val observer = mock<Observer<ArrayList<Poster>>>()
    posterArrayListLiveData.observeForever(observer)

    verify(observer).onChanged(arrayListOf(Poster.create(), Poster.create()))
  }
}
