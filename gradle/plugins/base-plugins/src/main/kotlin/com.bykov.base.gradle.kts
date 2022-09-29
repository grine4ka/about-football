plugins {
    id("base")
}

// Set the group (some components will be published)
group = "com.bykov.aboutfootball"

// Set the version from 'version.txt'
version = providers.fileContents(
    rootProject.layout.projectDirectory.file("gradle/version.txt")).asText.getOrElse("")
