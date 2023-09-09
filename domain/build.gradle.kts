plugins {
    kotlin("android")
    id("com.android.library")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.domain"
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":data"))
    //jetpack
    implementation(Libraries.androidxSecurity)
    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    implementation(Libraries.hiltAndroidGradle)
    kapt(Libraries.hiltCompiler)

    //Retrofit, okhttp3
    implementation(Libraries.gson)
    implementation(Libraries.okhttp3)
    implementation(Libraries.okhttp3Logging)
    implementation(Libraries.retrofit2)
    implementation(Libraries.retrofit2ConverterScalars)
    implementation(Libraries.retrofit2ConverterGson)

    //etc
    implementation(Libraries.jsoup)
    implementation(Libraries.timber)

}