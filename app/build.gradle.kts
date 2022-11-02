plugins {
    id("com.bykov.android-application")
}

dependencies {

    implementation(project(":domain"))
    // Base
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")

    // Rx
    implementation("io.reactivex.rxjava2:rxjava")
    implementation("io.reactivex.rxjava2:rxandroid")

    // Network
    implementation("com.squareup.retrofit2:retrofit")
    implementation("com.squareup.retrofit2:adapter-rxjava2")
    implementation("com.squareup.retrofit2:converter-gson")
    implementation("com.squareup.okhttp3:logging-interceptor")

    // UI
    implementation("androidx.constraintlayout:constraintlayout")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout")
    implementation("com.google.android.material:material")

    // Images
    implementation("com.github.bumptech.glide:glide")

    // Unit tests
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    testImplementation("io.kotest:kotest-runner-junit5")
    testImplementation("io.kotest:kotest-assertions-core")
    testImplementation("org.junit.jupiter:junit-jupiter-api")

    // UI tests
    androidTestImplementation("androidx.test.ext:junit")
}