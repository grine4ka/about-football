plugins {
    id("com.bykov.kotlin-library")
}

dependencies {

    // Rx
    implementation("io.reactivex.rxjava2:rxjava")

    // TODO Remove Gson cause it is a detail, not an abstraction
    implementation("com.google.code.gson:gson")
}
