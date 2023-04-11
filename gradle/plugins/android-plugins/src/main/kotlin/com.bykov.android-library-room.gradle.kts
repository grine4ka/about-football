@file:Suppress("UnstableApiUsage")

plugins {
    id("com.bykov.android-library-ksp")
}

ksp {
    arg("room.schemaLocation", "${projectDir}/schemas")
}

// Configure room dependencies
dependencies {
    // Room
    implementation("androidx.room:room-runtime")
    implementation("androidx.room:room-rxjava2")
    ksp("androidx.room:room-compiler")
}
