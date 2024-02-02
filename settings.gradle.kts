pluginManagement {
    includeBuild("gradle/settings")
}
plugins {
    id("com.bykov.settings")
}

dependencyResolutionManagement {
    // needed for declaring dependencies on other modules using groupId:artifactId notation
    includeBuild(".")
}

rootProject.name = "footea"
