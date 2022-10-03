plugins {
    id("java-platform")
    id("com.bykov.base")
}

// Depend on other Platforms/BOMs to align versions for libraries that consist of multiple components (like Jackson)
javaPlatform.allowDependencies()
