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

/**
 * Returns an instance of a bundler that has the intent of an Activity.
 *
 * @throws NullPointerException when the Activity does not contain any intent.
 */
fun Activity.activityBundler(): Bundler = Bundler(intent)

/**
 * Retrieves a primitive type of extended data from intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
inline fun <reified T : Any> Activity.activityVariableBundler(
  defaultValue: T,
  crossinline initializer: Bundler.() -> T?
): Lazy<T> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue
  }

/**
 * Retrieves a primitive type of extended data from intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
inline fun <reified T : Any> Activity.activityTypedBundler(
  crossinline defaultValue: () -> T? = { null },
  crossinline initializer: Bundler.() -> T?
): Lazy<T?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }

/**
 * Retrieves a primitive type of extended data from intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
inline fun <reified T : Any> Activity.activityArrayBundler(
  crossinline defaultValue: () -> Array<T>? = { null },
  crossinline initializer: Bundler.() -> Array<*>?
): Lazy<Array<*>?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }

/**
 * Retrieves a primitive type of extended data from intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
inline fun <reified T : Any> Activity.activityArrayListBundler(
  crossinline defaultValue: () -> ArrayList<T>? = { null },
  crossinline initializer: Bundler.() -> ArrayList<*>?
): Lazy<ArrayList<*>?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }
