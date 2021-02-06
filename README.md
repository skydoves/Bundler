
<h1 align="center">Bundler</h1></br>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
  <a href="https://github.com/skydoves/Bundler/actions"><img alt="Build Status" src="https://github.com/skydoves/Bundler/workflows/Android%20CI/badge.svg"/></a>
  <a href="https://androidweekly.net/issues/issue-439"><img alt="Android Weekly" src="https://skydoves.github.io/badges/android-weekly.svg"/></a>
  <a href="https://mailchi.mp/kotlinweekly/kotlin-weekly-225"><img alt="KotlinWeekly" src="https://skydoves.github.io/badges/kotlin-weekly.svg"/></a>
  <a href="https://skydoves.medium.com/clean-ways-to-handle-android-bundles-40af734bab3"><img alt="Medium" src="https://skydoves.github.io/badges/Story-Medium.svg"/></a>
  <a href="https://skydoves.github.io/libraries/bundler/html/bundler/com.skydoves.bundler/index.html"><img alt="Javadoc" src="https://skydoves.github.io/badges/javadoc-bundler.svg"/></a>
</p>

<p align="center">
🎁 Android Intent & Bundle extensions that insert and retrieve values elegantly.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/97807630-3dfb6800-1ca5-11eb-9887-3e3c51aabb95.png" width="471" height="316"/>
</p>

## Including in your project
[![Maven Central](https://img.shields.io/maven-central/v/com.github.skydoves/bundler.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.skydoves%22%20AND%20a:%22bundler%22) [![Jitpack](https://jitpack.io/v/skydoves/bundler.svg)](https://jitpack.io/#skydoves/bundler)
### Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        mavenCentral()
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:bundler:1.0.3"
}
```

## Usage
### Intent
`intentOf` is an expression for creating an Intent using Kotlin DSL style and we can put extras using the `putExtra` method. Also, we can put extras using the `+` keyword in front of a key/value pair.
```kotlin
val intent = intentOf {
    putExtra("posterId", poster.id) // put a Long type 'posterId' value.
    putExtra("posterName" to poster.name) // put a String type 'posterName' value.
    putExtra("poster", poster) // put a Parcelable type 'poster' value.

    +("id" to userInfo.id) // put a Long type 'id' value.
    +("name" to userInfo.nickname) // put a String type 'name' value.
    
    -"name" // remove a String type 'name' value.
}
```
### StartActivity
We can start activities using the `intentOf` expression like below.
```kotlin
intentOf<SecondActivity> {
    putExtra("id" to userInfo.id)
    putExtra("name" to userInfo.nickname)
    putExtra("poster", poster)
    startActivity(this@MainActivity)
}
```
We can also use other options for creating an intent.
```kotlin
intentOf {
    setAction(Intent.ACTION_MAIN)
    addCategory(Intent.CATEGORY_APP_MUSIC)
    setFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    startActivity(this@MainActivity)
}
```
### bundle
`bundle` is an expression for initializing lazily extra values from the intent.
```kotlin
class SecondActivity : AppCompatActivity() {

  private val id: Long by bundle("id", -1) // initializes a Long extra value lazily.
  private val name: String by bundle("name", "") // initializes a String extra value lazily.
  private val poster: Poster? by bundle("poster") // initializes a Parcelable extra value lazily.

  // -- stubs -- //
```
We can initialize a Parcelable value with a defaut value.
```kotlin
private val poster: Poster? by bundle("poster") { Poster.create() }
```
Also, we can initialize type of Array and ArrayList using `bundleArray` and `bundleArrayList` expression.
```kotlin
// initialize lazily without default values.
private val posterArray by bundleArray<Poster>("posterArray")
private val posterListArray by bundleArrayList<Poster>("posterArrayList")

or

// initialize lazily with default values.
private val posterArray by bundleArray<Poster>("posterArray") { arrayOf() }
private val posterListArray by bundleArrayList<Poster>("posterArrayList") { arrayListOf() }
```
### Fragment
The below example shows setting arguments using the `intentOf` expression.
```kotlin
arguments = intentOf {
    +("id" to userInfo.id)
    +("name" to userInfo.nickname)
    +("poster" to poster)
}.extras
```
We can initialize argument values lazily in Fragments using the `bundle` expression like below.
```diff
- val id: Long = arguments?.getLong("id", -1) ?: -1
+ val id: Long by bundle("id", -1)
- val poster: Poster? = arguments?.getParcelable("poster")
+ val poster: Poster? by bundle("poster")
```

### bundleNonNull
The `bundle` expression for initializing objects (e.g. Bundle, CharSequence, Parcelable, Serializable, Arrays), the property type must be null-able. But If we want to initialize them non-nullable type, we can initialize them to non-nullable type using the `bundleNonNull` expression.
```diff
- val poster: Poster? by bundle("poster")
+ val poster: Poster by bundleNotNull("poster")
```

### observeBundle
We can observe the bundle data as LiveData using the `observeBundle` expression.<br>
If there are no extra & arguments in the Activity or Fragment, `null` will be passed to the observers.
```kotlin
private val id: LiveData<Long> by observeBundle("id", -1L)
private val poster: LiveData<Poster> by observeBundle("poster")

id.observe(this) {
  vm.id = it
}

poster.observe(this) {
  binding.name = it.name
}
```

## Find this library useful? :heart:
Support it by joining __[stargazers](https://github.com/skydoves/bundler/stargazers)__ for this repository. :star: <br>
And __[follow](https://github.com/skydoves)__ me for my next creations! 🤩

# License
```xml
Copyright 2020 skydoves (Jaewoong Eum)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
