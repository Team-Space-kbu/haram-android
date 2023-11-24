plugins {
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
}

android {
    namespace = "com.space.biblemon"

    defaultConfig {
        applicationId = "com.space.biblemon"
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
    implementation(project(":features:home"))

    implementation(project(":shared"))

    //KTX
    implementation(Libraries.androidxCore)
    implementation(Libraries.androidxNavigationFragment)
    implementation(Libraries.androidxNavigationUi)

    //AndroidX
    implementation(Libraries.androidMaterial)
    implementation(Libraries.androidConstraintlayout)
    implementation(Libraries.androidAppcompat)
    implementation(Libraries.androidLegacy)
    implementation(Libraries.androidLifecycleViewmodel)
    implementation(Libraries.androidLifecycleLivedata)
    implementation(Libraries.androidRecyclerview)

    //dagger, hilt
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)

    //etc
    implementation(Libraries.glide)
    implementation(Libraries.glide_okhttp3)
    kapt(Libraries.glide_compiler)

    implementation(Libraries.timber)
    implementation(Libraries.naverMaps)
    implementation(Libraries.shimmer)

//    implementation("pub.devrel:easypermissions:3.0.0")
//    implementation("io.socket:socket.io-client:2.1.0")
//    implementation("com.google.firebase:firebase-firestore:21.4.3")
////    implementation("com.myhexaville:smart-image-picker:1.0.4")
//    implementation("io.reactivex.rxjava2:rxjava:2.0.5")
//    implementation("io.reactivex.rxjava2:rxandroid:2.0.1")
//    implementation("androidx.multidex:multidex:2.0.1")



    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    //test
    implementation(Libraries.junit)
    implementation(Libraries.androidxTestJunit)
    implementation(Libraries.androidxTestEspresso)
}