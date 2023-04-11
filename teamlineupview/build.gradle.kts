plugins {
    id("com.bykov.android-library-ksp")
}

android {
    namespace = "com.bykov.footea.teamlineupview"
}

androidComponents {
    // disable debug build type
    val debug = selector().withBuildType("debug")
    beforeVariants(debug) {
        it.enable = false
    }
}

dependencies {

    // Base
    implementation("androidx.core:core-ktx")

    // Images
    implementation("com.github.bumptech.glide:glide")
    ksp("com.github.bumptech.glide:ksp")
}