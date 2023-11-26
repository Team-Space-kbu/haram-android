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

    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidRecyclerview)
    implementation(Libraries.androidAppcompat)

    implementation(Libraries.glide)
    implementation(Libraries.glide_okhttp3)
    kapt(Libraries.glide_compiler)
}