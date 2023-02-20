plugins {
    id("com.bykov.platform")
}

dependencies {
    api(platform("org.junit:junit-bom:5.7.2")) { (this as ExternalModuleDependency).version { reject("[5.8.0,)") } } // Do not Upgrade to 5.8: https://github.com/gradle/gradle/issues/18627
    api(platform("com.squareup.okhttp3:okhttp-bom:4.10.0"))
}

dependencies.constraints {

    // Base
    api("androidx.core:core-ktx:1.6.0")
    api("androidx.appcompat:appcompat:1.3.1")

    // Rx
    api("io.reactivex.rxjava2:rxjava:2.2.21")
    api("io.reactivex.rxjava2:rxandroid:2.1.1")

    // Network
    api("com.squareup.retrofit2:retrofit:2.9.0")
    api("com.squareup.retrofit2:adapter-rxjava2:2.9.0")
    api("com.squareup.retrofit2:converter-gson:2.9.0")
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")
    api("com.google.code.gson:gson:2.10")

    // UI
    api("androidx.constraintlayout:constraintlayout:2.1.1")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    api("com.google.android.material:material:1.4.0")
    api("androidx.palette:palette:1.0.0")

    // Images
    api("com.github.bumptech.glide:glide:4.12.0")

    // Unit tests
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    api("io.kotest:kotest-runner-junit5:4.6.3")
    api("io.kotest:kotest-assertions-core:4.6.3")

    // UI tests
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")
}