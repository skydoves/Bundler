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

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.verifyNoMoreInteractions
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.Is.`is`
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@Config(sdk = [21])
@RunWith(RobolectricTestRunner::class)
class SingleShotLiveDataTest {

  @get:Rule
  var instantExecutorRule = InstantTaskExecutorRule()

  @Test
  fun observeTest() {
    val oneShotLiveData = SingleShotLiveData("one")

    val observer = mock<Observer<String>>()
    oneShotLiveData.observeForever(observer)

    verify(observer).onChanged("one")

    oneShotLiveData.setValue("two")

    verifyNoMoreInteractions(observer)
  }

  @Test
  fun observeWithMultipleObserversTest() {
    val oneShotLiveData = SingleShotLiveData("one")

    val observer0 = mock<Observer<String>>()
    oneShotLiveData.observeForever(observer0)

    val observer1 = mock<Observer<String>>()
    oneShotLiveData.observeForever(observer1)

    verify(observer0).onChanged("one")
    verifyNoMoreInteractions(observer1)

    oneShotLiveData.setValue("two")

    verifyNoMoreInteractions(observer0)
    verifyNoMoreInteractions(observer1)
  }

  @Test
  fun setValueTest() {
    val oneShotLiveData = SingleShotLiveData("one")
    assertThat(oneShotLiveData.value, `is`("one"))

    oneShotLiveData.setValue("two")
    assertThat(oneShotLiveData.value, `is`("two"))

    oneShotLiveData.setValue("three")
    assertThat(oneShotLiveData.value, `is`("three"))

    val observer = mock<Observer<String>>()
    oneShotLiveData.observeForever(observer)

    verify(observer).onChanged("three")

    oneShotLiveData.setValue("four")
    verifyNoMoreInteractions(observer)
  }

  @Test
  fun postValueTest() {
    val oneShotLiveData = SingleShotLiveData("one")
    assertThat(oneShotLiveData.value, `is`("one"))

    oneShotLiveData.postValue("two")
    assertThat(oneShotLiveData.value, `is`("two"))

    oneShotLiveData.postValue("three")
    assertThat(oneShotLiveData.value, `is`("three"))

    val observer = mock<Observer<String>>()
    oneShotLiveData.observeForever(observer)

    verify(observer).onChanged("three")

    oneShotLiveData.postValue("four")
    verifyNoMoreInteractions(observer)
  }
}
