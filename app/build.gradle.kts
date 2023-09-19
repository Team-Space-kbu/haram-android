plugins {
    kotlin("android")
    id("com.android.application")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.space.haram_android"

    defaultConfig {
        applicationId = "com.space.haram_android"
        versionCode = 1
        versionName = "1.0"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isDebuggable = false
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
        getByName("debug") {
            isDebuggable = true
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kapt {
        correctErrorTypes = true
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":shared"))

    //KTX
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidxNavigationUi)

    //AndroidX
    implementation(Libraries.androidMaterial)
    implementation(Libraries.androidConstraintlayout)
    implementation(Libraries.androidAppcompat)
    implementation(Libraries.androidLifecycleViewmodel)
    implementation(Libraries.androidLifecycleLivedata)
    implementation(Libraries.androidRecyclerview)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    kapt(Libraries.hiltCompiler)

    //etc
    implementation(Libraries.glide)
    implementation(Libraries.kotlinxCoroutines)
    implementation(Libraries.timber)

    //test
    implementation(Libraries.junit)
    implementation(Libraries.androidxTestJunit)
    implementation(Libraries.androidxTestEspresso)
}