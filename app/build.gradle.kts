plugins {
    id("com.bykov.android-application")
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    // Base
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")

    // Rx
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

    // UI tests
    androidTestImplementation("androidx.test.ext:junit")
}