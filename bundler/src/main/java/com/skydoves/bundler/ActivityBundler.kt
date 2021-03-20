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
 * @author skydoves (Jaewoong Eum)
 *
 * Returns an instance of a bundler that has the the Intent of an Activity.
 *
 * @throws NullPointerException when the Activity does not contain any Intent.
 */
fun Activity.activityBundler(): Bundler = Bundler(intent)

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a primitive type of extended data from the Intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityVariableBundler(
  defaultValue: T,
  crossinline initializer: Bundler.() -> T?
): Lazy<T> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue
  }

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a primitive type of extended data from the Intent immediately.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityVariableBundlerValue(
  defaultValue: T,
  crossinline initializer: Bundler.() -> T?
): T = activityBundler().initializer() ?: defaultValue

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from the Intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityTypedBundler(
  crossinline defaultValue: () -> T? = { null },
  crossinline initializer: Bundler.() -> T?
): Lazy<T?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from the Intent immediately.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityTypedBundlerValue(
  crossinline defaultValue: () -> T? = { null },
  crossinline initializer: Bundler.() -> T?
): T? = activityBundler().initializer() ?: defaultValue()

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from the Intent lazily.
 *
 * @param initializer The initializer for providing a non-null instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityNonNullTypedBundler(
  crossinline initializer: Bundler.() -> T
): Lazy<T> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer()
  }

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from the Intent immediately.
 *
 * @param initializer The initializer for providing a non-null instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityNonNullTypedBundlerValue(
  crossinline initializer: Bundler.() -> T
): T = activityBundler().initializer()

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array type of extended data from the Intent lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityArrayBundler(
  crossinline defaultValue: () -> Array<T>? = { null },
  crossinline initializer: Bundler.() -> Array<T>?
): Lazy<Array<T>?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array type of extended data from the Intent immediately.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityArrayBundlerValue(
  crossinline defaultValue: () -> Array<T>? = { null },
  crossinline initializer: Bundler.() -> Array<T>?
): Array<T>? = activityBundler().initializer() ?: defaultValue()

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array list type of extended data from the Intent  lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityArrayListBundler(
  crossinline defaultValue: () -> ArrayList<T>? = { null },
  crossinline initializer: Bundler.() -> ArrayList<T>?
): Lazy<ArrayList<T>?> =
  lazy(LazyThreadSafetyMode.NONE) {
    activityBundler().initializer() ?: defaultValue()
  }

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array list type of extended data from the Intent  lazily.
 *
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 * @param initializer The initializer for providing an instance of the type parameter.
 */
@PublishedApi
@JvmSynthetic
@InlineBundleDsl
internal inline fun <reified T : Any> Activity.activityArrayListBundlerValue(
  crossinline defaultValue: () -> ArrayList<T>? = { null },
  crossinline initializer: Bundler.() -> ArrayList<T>?
): ArrayList<T>? = activityBundler().initializer() ?: defaultValue()
