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
  fun replaceExtras(extras: Bundle) = apply {
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

  /** Launch a new activity. */
  fun startActivity(context: Context) {
    context.startActivity(intent)
  }

  /** Launch an activity for which you would like a result when it finished. */
  fun startActivityForResult(activity: Activity, requestCode: Int) {
    activity.startActivityForResult(intent, requestCode)
  }

  /** A special variation to launch an activity only if a new activity instance is needed to handle the given Intent. */
  fun startActivityIfNeeded(activity: Activity, requestCode: Int): Boolean {
    return activity.startActivityIfNeeded(intent, requestCode)
  }

  /** Special version of starting an activity, for use when you are replacing other activity components. */
  fun startNextMatchingActivity(activity: Activity): Boolean {
    return activity.startNextMatchingActivity(intent)
  }

  /** creates a new bundle and put it into the [intent] with the given key/value pairs as elements. */
  @JvmSynthetic
  @InlineBundleDsl
  fun bundleOf(vararg pairs: Pair<String, Any?>) = apply {
    intent.putExtras(com.skydoves.bundler.bundleOf(*pairs))
  }

  /** put a new extra data with the given key/value pairs as elements. */
  @InlineBundleDsl
  fun putExtra(pair: Pair<String, Any?>) = apply {
    bundleOf(pair)
  }

  /** put a new extra data with the given key/value pair as an element. */
  @InlineBundleDsl
  fun putExtra(key: String, value: Any) = apply {
    intent.putExtras(com.skydoves.bundler.bundleOf(key to value))
  }

  /** put a key/value pair as an extra element. */
  operator fun Pair<String, Any?>.unaryPlus() = intent.putExtras(
    com.skydoves.bundler.bundleOf(this)
  )

  /** removes a previous extra. */
  operator fun String.unaryMinus() = intent.removeExtra(this)
}
