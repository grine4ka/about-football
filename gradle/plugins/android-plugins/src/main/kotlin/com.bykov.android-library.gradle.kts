plugins {
    id("com.android.library")
    id("com.bykov.android")
}

android {
    compileSdk = 30

    defaultConfig {
        minSdk = 26
        targetSdk = 31
    }
}

// Configure common test runtime dependencies for *all* android projects
dependencies {
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    androidTestRuntimeOnly("androidx.test.espresso:espresso-core")
}
