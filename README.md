
<h1 align="center">Bundler</h1></br>
<p align="center">
  <a href="https://opensource.org/licenses/Apache-2.0"><img alt="License" src="https://img.shields.io/badge/License-Apache%202.0-blue.svg"/></a>
  <a href="https://android-arsenal.com/api?level=16"><img alt="API" src="https://img.shields.io/badge/API-16%2B-brightgreen.svg?style=flat"/></a>
</p>

<p align="center">
🎁 Android Intent & Bundle extensions that insert and retrieve values elegantly.
</p>

<p align="center">
<img src="https://user-images.githubusercontent.com/24237865/97780897-e8528d00-1bca-11eb-85d1-4d177d65327b.png" width="471" height="316"/>
</p>

## Including in your project
[![Download](https://api.bintray.com/packages/devmagician/maven/bundler/images/download.svg) ](https://bintray.com/devmagician/maven/bundler/_latestVersion)<br>
[![Jitpack](https://jitpack.io/v/skydoves/bundler.svg)](https://jitpack.io/#skydoves/bundler)
### Gradle 
Add below codes to your **root** `build.gradle` file (not your module build.gradle file).
```gradle
allprojects {
    repositories {
        jcenter()
    }
}
```
And add a dependency code to your **module**'s `build.gradle` file.
```gradle
dependencies {
    implementation "com.github.skydoves:bundler:1.0.0"
}
```

## Usage
### Intent
`intent` is an expression for creating an Intent using Kotlin DSL style and we can put extras using the `putExtra` method. Also, we can put extras using the `+` keyword in front of a key/value pair.
```kotlin
val intent = intent {
    putExtra("posterId", poster.id) // put a Long type 'posterId' value.
    putExtra("posterName" to poster.name) // put a String type 'posterName' value.
    putExtra("poster", poster) // put a Parcelable type 'poster' value.

    +("id" to userInfo.id) // put a Long type 'id' value.
    +("name" to userInfo.nickname) // put a String type 'name' value.
}
```
### StartActivity
We can start activities using the `intent` expression like below.
```kotlin
intent(SecondActivity::class) {
    putExtra("id" to userInfo.id)
    putExtra("name" to userInfo.nickname)
    putExtra("poster", poster)
    startActivity(this@MainActivity)
}
```
We can also use other options for creating an intent.
```kotlin
intent {
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
The below example shows setting an argument using the `intent` expression.
```kotlin
arguments = intent {
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
