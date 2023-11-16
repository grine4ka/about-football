plugins {
    id("java-platform")
}

// Declare versions of the plugins here
dependencies.constraints {
    api("com.android.tools.build:gradle:8.0.2")
    api("com.autonomousapps:dependency-analysis-gradle-plugin:1.2.0")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.20")
    api("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
    api("com.klaxit.hiddensecrets:HiddenSecretsPlugin:0.2.1")
    api("com.google.devtools.ksp:com.google.devtools.ksp.gradle.plugin:1.8.20-1.0.10")
}
