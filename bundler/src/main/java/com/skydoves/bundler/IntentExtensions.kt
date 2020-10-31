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

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlin.reflect.KClass

@DslMarker
internal annotation class InlineIntentOnly

/** creates an instance of the intent. */
@JvmSynthetic
@InlineIntentOnly
inline fun intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler().apply(block).intent
}

/** creates an instance of the intent from an intent. */
@JvmSynthetic
@InlineIntentOnly
inline fun Intent.intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/** creates an instance of the intent with an action. */
@JvmSynthetic
@InlineIntentOnly
inline fun String.intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/** creates an instance of the intent with action and URI. */
@JvmSynthetic
@InlineIntentOnly
inline fun String.intent(uri: Uri, crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this, uri)).apply(block).intent
}

/** creates an instance of the intent with packageContext and a target class. */
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intent(
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(this, T::class.java)).apply(block).intent
}

/** creates an instance of the intent with packageContext, a target class, an action and URI. */
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intent(
  action: String,
  uri: Uri,
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(action, uri, this, T::class.java)).apply(block).intent
}

/** creates a new bundle and put it into the [intent] with the given key/value pairs as elements. */
@JvmSynthetic
@InlineIntentOnly
fun Intent.bundleOf(vararg pairs: Pair<String, Any?>) = apply {
  putExtras(com.skydoves.bundler.bundleOf(*pairs))
}
