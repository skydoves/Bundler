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

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Creates an instance of the intent.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun intentOf(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler().apply(block).intent
}

/**
 * Creates an instance of the intent from an intent.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun Intent.intentOf(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/**
 * Creates an instance of the intent with an action.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun String.intentOf(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/**
 * Creates an instance of the intent with action and URI.
 *
 * @param uri The Intent data URI.
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun String.intentOf(uri: Uri, crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this, uri)).apply(block).intent
}

/**
 * Creates an instance of the intent with packageContext and a target class.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intentOf(
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(this, T::class.java)).apply(block).intent
}

/**
 * Creates an instance of the intent with packageContext, a target class, an action and URI.
 *
 * @param action The Intent action, such as ACTION_VIEW.
 * @param uri The Intent data URI.
 * @param block A lambda domain scope of the [Bundler].
 */
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intentOf(
  action: String,
  uri: Uri,
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(action, uri, this, T::class.java)).apply(block).intent
}

/**
 * Creates an instance of the intent.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf() instead",
  replaceWith = ReplaceWith(
    "intentOf(block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler().apply(block).intent
}

/**
 * Creates an instance of the intent from an intent.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf() instead",
  replaceWith = ReplaceWith(
    "intentOf(block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun Intent.intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/**
 * Creates an instance of the intent with action and URI.
 *
 * @param uri The Intent data URI.
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf() instead",
  replaceWith = ReplaceWith(
    "intentOf(uri, block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun String.intent(uri: Uri, crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this, uri)).apply(block).intent
}

/**
 * Creates an instance of the intent with an action.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf() instead",
  replaceWith = ReplaceWith(
    "intentOf(block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun String.intent(crossinline block: Bundler.() -> Unit): Intent {
  return Bundler(Intent(this)).apply(block).intent
}

/**
 * Creates an instance of the intent with packageContext and a target class.
 *
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf<T>() instead",
  replaceWith = ReplaceWith(
    "intentOf<T>(block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intent(
  clazz: KClass<T>,
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(this, clazz.java)).apply(block).intent
}

/**
 * Creates an instance of the intent with packageContext, a target class, an action and URI.
 *
 * @param clazz The component class that is to be used for the intent.
 * @param action The Intent action, such as ACTION_VIEW.
 * @param uri The Intent data URI.
 * @param block A lambda domain scope of the [Bundler].
 */
@Deprecated(
  message = "use intentOf<T>() instead",
  replaceWith = ReplaceWith(
    "intentOf<T>(action, uri, block)",
    imports = ["com.skydoves.bundler.intentOf"]
  )
)
@JvmSynthetic
@InlineIntentOnly
inline fun <reified T : Any> Context.intent(
  clazz: KClass<T>,
  action: String,
  uri: Uri,
  crossinline block: Bundler.() -> Unit
): Intent {
  return Bundler(Intent(action, uri, this, clazz.java)).apply(block).intent
}

/**
 * Creates a new bundle and put it into the [intent] with the given key/value pairs as elements.
 *
 * @param pairs key/value pairs.
 */
@JvmSynthetic
@InlineIntentOnly
fun Intent.bundleOf(vararg pairs: Pair<String, Any?>) = apply {
  putExtras(com.skydoves.bundler.bundleOf(*pairs))
}
