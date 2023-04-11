plugins {
    id("com.bykov.platform")
}

dependencies {
    api(platform("org.junit:junit-bom:5.8.2"))
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
    val retrofitVersion = "2.9.0"
    api("com.squareup.retrofit2:retrofit:$retrofitVersion")
    api("com.squareup.retrofit2:adapter-rxjava2:$retrofitVersion")
    api("com.squareup.retrofit2:converter-gson:$retrofitVersion")
    api("com.squareup.okhttp3:okhttp")
    api("com.squareup.okhttp3:logging-interceptor")
    api("com.google.code.gson:gson:2.10")

    // DB
    val roomVersion = "2.5.0"
    api("androidx.room:room-runtime:$roomVersion")
    api("androidx.room:room-rxjava2:$roomVersion")
    api("androidx.room:room-compiler:$roomVersion")

    // Datetime
    api("org.jetbrains.kotlinx:kotlinx-datetime:0.4.0")

    // UI
    api("androidx.constraintlayout:constraintlayout:2.1.1")
    api("androidx.swiperefreshlayout:swiperefreshlayout:1.1.0")
    api("com.google.android.material:material:1.4.0")
    api("androidx.palette:palette:1.0.0")

    // Images
    val glideVersion = "4.15.1"
    api("com.github.bumptech.glide:glide:$glideVersion")
    api("com.github.bumptech.glide:ksp:$glideVersion")

    // Unit tests
    // (Required) Writing and executing Unit Tests on the JUnit Platform
    api("io.kotest:kotest-runner-junit5:4.6.3")
    api("io.kotest:kotest-assertions-core:4.6.3")
    api("io.mockk:mockk:1.13.4")

    // UI tests
    api("androidx.test.ext:junit:1.1.3")
    api("androidx.test.espresso:espresso-core:3.4.0")
}