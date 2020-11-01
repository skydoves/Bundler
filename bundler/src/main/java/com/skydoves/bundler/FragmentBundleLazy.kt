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

@file:Suppress("IMPLICIT_CAST_TO_ANY", "UNCHECKED_CAST", "unused")

package com.skydoves.bundler

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * Retrieves a primitive type of extended data from intent lazily.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 */
@JvmSynthetic
@InlineBundleDsl
fun <T : Any> Fragment.bundle(key: String, defaultValue: T): Lazy<T> =
  lazy {
    requireNotNull(
      when (val value: T = defaultValue) {
        is Boolean -> arguments?.getBoolean(key, value)
        is Byte -> arguments?.getByte(key, value)
        is Char -> arguments?.getChar(key, value)
        is Double -> arguments?.getDouble(key, value)
        is Float -> arguments?.getFloat(key, value)
        is Int -> arguments?.getInt(key, value)
        is Long -> arguments?.getLong(key, value)
        is Short -> arguments?.getShort(key, value)
        is CharSequence -> arguments?.getString(key)

        else -> IllegalArgumentException(
          "Illegal value type ${defaultValue.javaClass} for key \"$key\""
        )
      } as? T ?: defaultValue
    )
  }

/**
 * Retrieves a references type of extended data from intent lazily.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Fragment.bundle(
  key: String,
  crossinline defaultValue: () -> T? = { null }
): Lazy<T?> {
  val objectType = T::class.javaObjectType
  return lazy {
    @Suppress("UNCHECKED_CAST")
    when {
      // references
      Bundle::class.java.isAssignableFrom(objectType) -> arguments?.getBundle(key) as? T
      CharSequence::class.java.isAssignableFrom(objectType) -> arguments?.getChar(key) as? T
      Parcelable::class.java.isAssignableFrom(objectType) -> arguments?.getParcelable<Parcelable>(
        key
      ) as? T
      Serializable::class.java.isAssignableFrom(objectType) -> arguments?.getSerializable(key) as? T

      // scalar arrays
      BooleanArray::class.java.isAssignableFrom(objectType) -> arguments?.getBooleanArray(key) as? T
      ByteArray::class.java.isAssignableFrom(objectType) -> arguments?.getByteArray(key) as? T
      CharArray::class.java.isAssignableFrom(objectType) -> arguments?.getCharArray(key) as? T
      DoubleArray::class.java.isAssignableFrom(objectType) -> arguments?.getDoubleArray(key) as? T
      FloatArray::class.java.isAssignableFrom(objectType) -> arguments?.getFloatArray(key) as? T
      IntArray::class.java.isAssignableFrom(objectType) -> arguments?.getIntArray(key) as? T
      LongArray::class.java.isAssignableFrom(objectType) -> arguments?.getLongArray(key) as? T
      ShortArray::class.java.isAssignableFrom(objectType) -> arguments?.getShortArray(key) as? T

      else -> throw IllegalArgumentException("Illegal value type $objectType for key \"$key\"")
    } ?: defaultValue()
  }
}

/**
 * Retrieves a references array type of extended data from intent lazily.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Fragment.bundleArray(
  key: String,
  crossinline defaultValue: () -> Array<T>? = { null }
): Lazy<Array<*>?> {
  val javaObjectType = T::class.javaObjectType
  return lazy {
    @Suppress("UNCHECKED_CAST")
    when {
      String::class.java.isAssignableFrom(javaObjectType) -> arguments?.getStringArray(key)
      CharSequence::class.java.isAssignableFrom(javaObjectType) -> arguments?.getCharSequenceArray(key)
      Parcelable::class.java.isAssignableFrom(javaObjectType) -> arguments?.getParcelableArray(key)

      else -> throw IllegalArgumentException("Illegal value type $javaObjectType for key \"$key\"")
    } ?: defaultValue()
  }
}

/**
 * Retrieves a references array list type of extended data from intent lazily.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Fragment.bundleArrayList(
  key: String,
  crossinline defaultValue: () -> ArrayList<T>? = { null }
): Lazy<ArrayList<*>?> {
  val javaObjectType = T::class.javaObjectType
  return lazy {
    @Suppress("UNCHECKED_CAST")
    when {
      String::class.java.isAssignableFrom(javaObjectType) -> arguments?.getStringArrayList(key)
      CharSequence::class.java.isAssignableFrom(
        javaObjectType
      ) -> arguments?.getCharSequenceArrayList(key)
      Parcelable::class.java.isAssignableFrom(
        javaObjectType
      ) -> arguments?.getParcelableArrayList<Parcelable>(key)

      else -> throw IllegalArgumentException("Illegal value type $javaObjectType for key \"$key\"")
    } ?: defaultValue()
  }
}
