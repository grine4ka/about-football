plugins {
    id("java-platform")
}

// Declare versions of the plugins here
dependencies.constraints {
    api("com.android.tools.build:gradle:7.2.0")
    api("com.autonomousapps:dependency-analysis-gradle-plugin:1.2.0")
    api("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.21")
    api("de.mannodermaus.gradle.plugins:android-junit5:1.8.2.0")
    api("com.klaxit.hiddensecrets:HiddenSecretsPlugin:0.2.0")
}
