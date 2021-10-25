package com.github.versionplugin

import com.github.versionplugin.Versions.kotlin_version
import com.github.versionplugin.Versions.lifecycle_version
import com.github.versionplugin.Versions.roomVersion
import com.github.versionplugin.Versions.supportVersion


object Versions {
    const val kotlin_version = "1.4.32"
    const val lifecycle_version = "1.1.0"
    const val supportVersion = "27.1.0"
    const val fragment_version = "1.3.3"
    const val activity_version = "1.2.2"
    const val roomVersion = "2.2.5"
}

object AndroidX {
    val androidx_appcompat = "androidx.appcompat:appcompat:1.2.0"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:1.1.0"
    val androidx_constraint = "androidx.constraintlayout:constraintlayout:2.0.4"
    val androidx_core_ktx = "androidx.core:core-ktx:1.3.2"
    val material = "com.google.android.material:material:1.3.0"
    val androidx_core_core = "androidx.core:core:1.3.1"
    val cardview = "androidx.cardview:cardview:1.0.0"
    val lifecycleCommon = "android.arch.lifecycle:common-java8:1.1.1"

    // Java language implementation
    val activity = "androidx.activity:activity:${Versions.activity_version}"

    // Kotlin
    val activity_ktx = "androidx.activity:activity-ktx:${Versions.activity_version}"

    val fragment = "androidx.fragment:fragment:${Versions.fragment_version}"

    // Kotlin
    val fragment_ktx = "androidx.fragment:fragment-ktx:${Versions.fragment_version}"
}

object Room {
    val Room = "androidx.room:room-ktx:$roomVersion"
    val RoomCompiler = "androidx.room:room-compiler:$roomVersion"
}

object Kotlin {
    val kotlin_jdk8 = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$kotlin_version"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
}

object Lifecycle {
    const val lifecycle_version = "2.3.1"
    const val arch_version = "2.1.0"

    // ViewModel
    const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"

    // LiveData
    const val livedataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycle_version"

    // Lifecycles only (without ViewModel or LiveData)
    const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:$lifecycle_version"

    // Saved state module for ViewModel
    const val savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$lifecycle_version"

    // Annotation processor
    const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycle_version"

    // alternately - if using Java8, use the following instead of lifecycle-compiler
    const val common = "androidx.lifecycle:lifecycle-common-java8:$lifecycle_version"

    // optional - helpers for implementing LifecycleOwner in a Service
    const val service = "androidx.lifecycle:lifecycle-service:$lifecycle_version"

    // optional - ProcessLifecycleOwner provides a lifecycle for the whole application process
    const val process = "androidx.lifecycle:lifecycle-process:$lifecycle_version"

    // optional - ReactiveStreams support for LiveData
    const val reactivestreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:$lifecycle_version"
}
