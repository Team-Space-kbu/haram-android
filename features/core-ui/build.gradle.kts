plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.core_ui"
    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":shared"))


    implementation(Dev.androidxCore)
    implementation(Dev.androidxNavigationFragment)
    implementation(Dev.androidRecyclerview)
    implementation(Dev.androidAppcompat)
    implementation(Dev.androidMaterial)
    implementation(Dev.androidLifecycleViewmodel)
    implementation(Dev.androidLifecycleLivedata)
    implementation(Dev.androidRecyclerview)

    implementation(Dev.glide)
    implementation(Dev.glide_okhttp3)
    kapt(Dev.glide_compiler)
}