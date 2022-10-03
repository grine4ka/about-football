plugins {
    id("com.android.application")
    id("com.bykov.android")
    id("de.mannodermaus.android-junit5")
}

val versionString = version as String
val versionInt = versionString.split(".")[0].toInt() * 1000 + versionString.split(".")[1].toInt()

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "ru.bykov.footballteams"
        minSdk = 26
        targetSdk = 31
        versionCode = versionInt
        versionName = versionString

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes.getByName("release") {
        minifyEnabled(false)
        proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    packagingOptions {
        resources.excludes.add("META-INF/**")
    }

    buildFeatures {
        viewBinding = true
    }
}

// Configure common test runtime dependencies for *all* android projects
dependencies {
    // Can't declare dependency from the platform in com.bykov.android convention plugin
    // cause com.android.base doesn't support it
    implementation(platform("com.bykov.aboutfootball:platform"))
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")

    androidTestRuntimeOnly("androidx.test.espresso:espresso-core")
}
