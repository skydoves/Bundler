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

@file:Suppress("unused")

package com.skydoves.bundler

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle

@DslMarker
internal annotation class InlineBundleDsl

/** create a new instance of the [Bundler] using kotlin dsl. */
@JvmSynthetic
@InlineBundleDsl
inline fun bundler(crossinline block: Bundler.() -> Unit) = Bundler(Intent()).apply(block)

/** Bundler is a wrapper class of the [Intent] for putting intent data elegantly. */
inline class Bundler(val intent: Intent = Intent()) {

  /** Add a new category to the intent. */
  fun addCategory(category: String) = apply {
    intent.addCategory(category)
  }

  /** Remove a category from an intent. */
  fun removeCategory(category: String) = apply {
    intent.removeCategory(category)
  }

  /** Add additional flags to the intent (or with existing flags value). */
  fun addFlags(flags: Int) = apply {
    intent.addFlags(flags)
  }

  /** Copy the contents of other in to this object, but only where fields are not defined by this object. */
  fun fillIn(other: Intent, flags: Int) = apply {
    intent.fillIn(other, flags)
  }

  /** Determine if two intents are the same for the purposes of intent resolution (filtering). */
  fun filterEquals(other: Intent) = apply {
    intent.filterEquals(other)
  }

  /** Completely replace the extras in the Intent with the extras in the given Intent. */
  fun replaceExtras(src: Intent) = apply {
    intent.replaceExtras(src)
  }

  /** Completely replace the extras in the Intent with the given Bundle of extras. */
  fun replaceExtras(extras: Bundle?) = apply {
    intent.replaceExtras(extras)
  }

  /** Set the general action to be performed. */
  fun setAction(action: String) = apply {
    intent.action = action
  }

  /** Set the data this intent is operating on. */
  fun setData(data: Uri) = apply {
    intent.data = data
  }

  /** Set special flags controlling how this intent is handled. */
  fun setFlags(flags: Int) = apply {
    intent.flags = flags
  }

  /**
   * Launch a new activity with no options specified.
   *
   * @param context A context for launching an activity.
   *
   * @throws android.content.ActivityNotFoundException
   */
  fun startActivity(context: Context) {
    context.startActivity(intent)
  }

  /**
   * Launch a new activity with options.
   *
   * @param context A context for launching an activity.
   * @param options Additional options for how the Activity should be started.
   *
   * @throws android.content.ActivityNotFoundException
   */
  fun startActivity(context: Context, options: Bundle) {
    context.startActivity(intent, options)
  }

  /**
   * Launch an activity for which you would like a result when it finished.
   *
   * @param activity An activity for receiving the result.
   * @param requestCode If >= 0, this code will be returned in
   *                    onActivityResult() when the activity exits.
   *
   * @throws android.content.ActivityNotFoundException
   */
  fun startActivityForResult(activity: Activity, requestCode: Int) {
    activity.startActivityForResult(intent, requestCode)
  }

  /**
   * Launch an activity for which you would like a result when it finished.
   *
   * @param activity An activity for receiving the result.
   * @param requestCode If >= 0, this code will be returned in
   *                    onActivityResult() when the activity exits.
   * @param options Additional options for how the Activity should be started.
   * See {@link android.content.Context#startActivity(Intent, Bundle)}
   * Context.startActivity(Intent, Bundle)} for more details.
   *
   * @throws android.content.ActivityNotFoundException
   */

  fun startActivityForResult(activity: Activity, requestCode: Int, options: Bundle) {
    activity.startActivityForResult(intent, requestCode, options)
  }

  /**
   * A special variation to launch an activity only if a new activity instance is needed to handle the given Intent.
   *
   * @param activity An activity.
   * @param requestCode If >= 0, this code will be returned in
   *                    onActivityResult() when the activity exits.
   */
  fun startActivityIfNeeded(activity: Activity, requestCode: Int): Boolean {
    return activity.startActivityIfNeeded(intent, requestCode)
  }

  /**
   * Special version of starting an activity, for use when you are replacing other activity components.
   *
   * @param activity An activity.
   */
  fun startNextMatchingActivity(activity: Activity): Boolean {
    return activity.startNextMatchingActivity(intent)
  }

  /**
   * Creates a new bundle and put it into the [intent] with the given key/value pairs as elements.
   *
   * @param pairs key/value pairs.
   */
  @JvmSynthetic
  @InlineBundleDsl
  fun bundleOf(vararg pairs: Pair<String, Any?>) = apply {
    intent.putExtras(com.skydoves.bundler.bundleOf(*pairs))
  }

  /**
   * Inserts a new extra data with the given key/value pairs as elements.
   *
   * @param pair A key/value pair. (key to value) A value that is supported type of [Bundle].
   *
   * ```
   * putExtra(key to value)
   * ```
   */
  @InlineBundleDsl
  fun putExtra(pair: Pair<String, Any?>) = apply {
    bundleOf(pair)
  }

  /**
   * Inserts a new extra data with the given key/value pair as an element.
   *
   * @param key A key String
   * @param value A value that is supported type of [Bundle].
   *
   * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
   */
  @InlineBundleDsl
  fun putExtra(key: String, value: Any?) = apply {
    intent.putExtras(com.skydoves.bundler.bundleOf(key to value))
  }

  /**
   * Inserts a key/value pair as an extra element.
   *
   * ```
   * key eq value
   * ```
   */
  infix fun String.eq(value: Any?) = intent.putExtras(
    com.skydoves.bundler.bundleOf(this to value)
  )

  /**
   * Inserts a key/value pair as an extra element.
   *
   * ```
   * +(key to value)
   * ```
   */
  operator fun Pair<String, Any?>.unaryPlus() = intent.putExtras(
    com.skydoves.bundler.bundleOf(this)
  )

  /**
   * Removes a previous extra.
   *
   * ```
   * -key
   * ```
   **/
  operator fun String.unaryMinus() = intent.removeExtra(this)
}
