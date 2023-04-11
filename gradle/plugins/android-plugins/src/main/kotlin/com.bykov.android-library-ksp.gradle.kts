@file:Suppress("UnstableApiUsage")

plugins {
    id("com.bykov.android-library")
    id("com.google.devtools.ksp")
}

// Add platform version to ksp configuration
dependencies {
    ksp(platform("com.bykov.footea:platform"))
}
