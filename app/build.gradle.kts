import java.util.Properties

plugins {
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.gms.google-services")
}

android {
    namespace = "com.space.biblemon"
    val localProps = Properties()
    localProps.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.space.biblemon"
        versionCode = 1
        versionName = "0.9.0"
    }
    signingConfigs {
        create("configName") {
            keyAlias = localProps.getProperty("keyAlias") ?: error("Key alias not found in local.properties")
            keyPassword = localProps.getProperty("keyPassword") ?: error("Key alias not found in local.properties")
            storeFile = file(localProps.getProperty("storeFile") ?: error("Key alias not found in local.properties"))
            storePassword = localProps.getProperty("storePassword") ?: error("Key alias not found in local.properties")
        }

    }
    buildTypes {
        debug {
            isDebuggable = true
        }
        release {
            isMinifyEnabled = true
            isDebuggable = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
            proguardFile("proguard-retrofit2.pro")
            proguardFile("proguard-glide.pro")
            proguardFile("proguard-google.pro")
            signingConfig = signingConfigs.getByName("configName")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    kapt {
        correctErrorTypes = true
    }
}


dependencies {
    implementation(project(":shared"))
    implementation(project(":domain"))
    implementation(project(":features:core-ui"))
    implementation(project(":features:navigate"))

    implementation(project(":features:main"))
    implementation(project(":features:signin"))
    implementation(project(":features:signup"))
    implementation(project(":features:home"))
    implementation(project(":features:book"))
    implementation(project(":features:mileage"))
    implementation(project(":features:chapel"))
    implementation(project(":features:other"))
    implementation(project(":features:board"))
    implementation(project(":features:timetable"))
    implementation(project(":features:partners"))
    implementation(project(":features:bible"))
    implementation(project(":features:rothem"))
    implementation(project(":features:notice-bible"))
    implementation(project(":features:image"))
    implementation(project(":features:notice-space"))


    //KTX
    implementation(Dev.Android.Core.androidxCore)
    implementation(Dev.Android.Core.splashscreen)
    implementation(Dev.Android.Activity.androidxActivity)
    implementation(Dev.Android.Startup.AndroidStartup)
    implementation(Dev.Kotlin.kotlinxCoroutines)

    //dagger, hilt
    implementation(Dev.Dagger.hiltAndroid)
    kapt(Dev.Dagger.hiltCompiler)

    implementation(Dev.Timber.timber)
    implementation(Dev.Naver.naverMaps)

    implementation(Dev.Glide.glide)
    implementation(Dev.Glide.glide_okhttp3)
    implementation(Dev.AndroidSvg.androidSvg)
    kapt(Dev.Glide.glide_compiler)

    implementation("com.google.android.gms:play-services-oss-licenses:17.0.1")
    implementation(platform("com.google.firebase:firebase-bom:32.8.0"))
    implementation("com.google.firebase:firebase-analytics-ktx")

}