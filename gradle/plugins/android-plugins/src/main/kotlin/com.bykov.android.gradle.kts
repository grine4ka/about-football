import com.android.build.gradle.BaseExtension

plugins {
    id("com.bykov.base")
    id("com.android.base")
    id("org.jetbrains.kotlin.android")
}

// Configure Java/Kotlin compilation on java/kotlin {} extension
// https://kotl.in/gradle/jvm/toolchain
val javaLanguageVersion = JavaLanguageVersion.of(17)
kotlin {
    jvmToolchain {
        languageVersion.set(javaLanguageVersion)
    }
}

extensions.configure<BaseExtension>("android") {
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
}

// Configure details for *all* test executions direclty on 'Test' task
tasks.withType<Test>().configureEach {
    useJUnitPlatform() // Use JUnit 5 as test framework

    maxHeapSize = "1g"
    systemProperty("file.encoding", "UTF-8")
}

