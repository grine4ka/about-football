plugins {
    id("java-platform")
}

dependencies.constraints {
    api("com.android.tools.build:gradle:7.2.0")
    api("com.autonomousapps:dependency-analysis-gradle-plugin:1.2.0")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    api("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
}
