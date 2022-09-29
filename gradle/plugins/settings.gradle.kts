pluginManagement {
    includeBuild("../settings")
}
plugins {
    id("com.bykov.settings")
}

dependencyResolutionManagement {
    repositories.gradlePluginPortal()
    repositories.google()
}
