plugins {
    id("com.bykov.android-application")
}

android {
    namespace = "com.bykov.footea"
}

dependencies {

    implementation(project(":domain"))
    implementation(project(":data"))

    // Base
    implementation("androidx.core:core-ktx")
    implementation("androidx.appcompat:appcompat")

    // Rx
    implementation("io.reactivex.rxjava2:rxandroid")

    // UI
    implementation("androidx.constraintlayout:constraintlayout")
    implementation("androidx.swiperefreshlayout:swiperefreshlayout")
    implementation("com.google.android.material:material")
    implementation("androidx.palette:palette")

    // Images
    implementation("com.github.bumptech.glide:glide")

    // UI tests
    androidTestImplementation("androidx.test.ext:junit")
}