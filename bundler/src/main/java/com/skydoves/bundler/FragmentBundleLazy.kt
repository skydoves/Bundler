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

@file:Suppress("UNCHECKED_CAST", "unused")

package com.skydoves.bundler

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import java.io.Serializable

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a primitive type of extended data from arguments lazily.
 *
 * @param key The name of the desired item.
 * @param defaultValue The value to be returned if no value of the desired type is stored with the given name.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Fragment.bundle(key: String, defaultValue: T): Lazy<T> {
  return fragmentVariableBundler(defaultValue) {
    when (defaultValue) {
      is Boolean -> intent.getBooleanExtra(key, defaultValue)
      is Byte -> intent.getByteExtra(key, defaultValue)
      is Char -> intent.getCharExtra(key, defaultValue)
      is Double -> intent.getDoubleExtra(key, defaultValue)
      is Float -> intent.getFloatExtra(key, defaultValue)
      is Int -> intent.getIntExtra(key, defaultValue)
      is Long -> intent.getLongExtra(key, defaultValue)
      is Short -> intent.getShortExtra(key, defaultValue)
      is CharSequence -> intent.getStringExtra(key)

      else -> throw IllegalArgumentException(
        "Illegal value type ${defaultValue.javaClass} for key \"$key\""
      )
    } as? T
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from arguments lazily.
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
  return fragmentTypedBundler(defaultValue) {
    when {
      // references
      Bundle::class.java.isAssignableFrom(objectType) -> intent.getBundleExtra(key) as? T
      CharSequence::class.java.isAssignableFrom(objectType) -> intent.getCharSequenceExtra(key) as? T
      Parcelable::class.java.isAssignableFrom(objectType) -> intent.getParcelableExtra<Parcelable>(
        key
      ) as? T
      Serializable::class.java.isAssignableFrom(objectType) -> intent.getSerializableExtra(
        key
      ) as? T

      // scalar arrays
      BooleanArray::class.java.isAssignableFrom(objectType) -> intent.getBooleanArrayExtra(
        key
      ) as? T
      ByteArray::class.java.isAssignableFrom(objectType) -> intent.getByteArrayExtra(key) as? T
      CharArray::class.java.isAssignableFrom(objectType) -> intent.getCharArrayExtra(key) as? T
      DoubleArray::class.java.isAssignableFrom(objectType) -> intent.getDoubleArrayExtra(key) as? T
      FloatArray::class.java.isAssignableFrom(objectType) -> intent.getFloatArrayExtra(key) as? T
      IntArray::class.java.isAssignableFrom(objectType) -> intent.getIntArrayExtra(key) as? T
      LongArray::class.java.isAssignableFrom(objectType) -> intent.getLongArrayExtra(key) as? T
      ShortArray::class.java.isAssignableFrom(objectType) -> intent.getShortArrayExtra(key) as? T

      else -> throw IllegalArgumentException("Illegal value type $objectType for key \"$key\"")
    }
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references type of extended data from arguments lazily.
 *
 * @param key The name of the desired item.
 *
 * @throws IllegalArgumentException When a value is not a supported type of [Bundle].
 * @exception NullPointerException When there is no desired value from the arguments.
 */
@JvmSynthetic
@InlineBundleDsl
inline fun <reified T : Any> Fragment.bundleNonNull(
  key: String
): Lazy<T> {
  val objectType = T::class.javaObjectType
  return fragmentNonNullTypedBundler {
    when {
      // references
      Bundle::class.java.isAssignableFrom(objectType) -> intent.getBundleExtra(key) as T
      CharSequence::class.java.isAssignableFrom(objectType) -> intent.getCharSequenceExtra(key) as T
      Parcelable::class.java.isAssignableFrom(objectType) -> intent.getParcelableExtra<Parcelable>(
        key
      ) as T
      Serializable::class.java.isAssignableFrom(objectType) -> intent.getSerializableExtra(
        key
      ) as T

      // scalar arrays
      BooleanArray::class.java.isAssignableFrom(objectType) -> intent.getBooleanArrayExtra(
        key
      ) as T
      ByteArray::class.java.isAssignableFrom(objectType) -> intent.getByteArrayExtra(key) as T
      CharArray::class.java.isAssignableFrom(objectType) -> intent.getCharArrayExtra(key) as T
      DoubleArray::class.java.isAssignableFrom(objectType) -> intent.getDoubleArrayExtra(key) as T
      FloatArray::class.java.isAssignableFrom(objectType) -> intent.getFloatArrayExtra(key) as T
      IntArray::class.java.isAssignableFrom(objectType) -> intent.getIntArrayExtra(key) as T
      LongArray::class.java.isAssignableFrom(objectType) -> intent.getLongArrayExtra(key) as T
      ShortArray::class.java.isAssignableFrom(objectType) -> intent.getShortArrayExtra(key) as T

      else -> throw IllegalArgumentException("Illegal value type $objectType for key \"$key\"")
    }
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array type of extended data from arguments lazily.
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
): Lazy<Array<T>?> {
  val javaObjectType = T::class.javaObjectType
  return fragmentArrayBundler(defaultValue) {
    (
      when {
        String::class.java.isAssignableFrom(javaObjectType) -> intent.getStringArrayExtra(key)
        CharSequence::class.java.isAssignableFrom(javaObjectType) -> intent.getCharSequenceArrayExtra(key)
        Parcelable::class.java.isAssignableFrom(javaObjectType) -> intent.getParcelableArrayExtra(key)

        else -> throw IllegalArgumentException("Illegal value type $javaObjectType for key \"$key\"")
      }
      )?.filterIsInstance<T>()?.toTypedArray()
  }
}

/**
 * @author skydoves (Jaewoong Eum)
 *
 * Retrieves a references array list type of extended data from arguments lazily.
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
): Lazy<ArrayList<T>?> {
  val javaObjectType = T::class.javaObjectType
  return fragmentArrayListBundler(defaultValue) {
    when {
      String::class.java.isAssignableFrom(javaObjectType) -> intent.getStringArrayListExtra(key)
      CharSequence::class.java.isAssignableFrom(
        javaObjectType
      ) -> intent.getCharSequenceArrayListExtra(key)
      Parcelable::class.java.isAssignableFrom(
        javaObjectType
      ) -> intent.getParcelableArrayListExtra<Parcelable>(key)

      else -> throw IllegalArgumentException("Illegal value type $javaObjectType for key \"$key\"")
    } as? ArrayList<T>
  }
}
