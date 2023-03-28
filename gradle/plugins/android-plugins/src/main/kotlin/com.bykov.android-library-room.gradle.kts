@file:Suppress("UnstableApiUsage")

plugins {
    id("com.bykov.android-library")
    id("com.google.devtools.ksp")
}

ksp {
    arg("room.schemaLocation", "${projectDir}/schemas")
}

// Configure room dependencies
dependencies {
    ksp(platform("com.bykov.footea:platform"))

    // Room
    implementation("androidx.room:room-runtime")
    implementation("androidx.room:room-rxjava2")
    ksp("androidx.room:room-compiler")
}
