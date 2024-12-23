import java.util.Properties

plugins {
    kotlin("android")
    id("com.android.application")
    id("com.google.dagger.hilt.android")
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    id("kotlin-kapt")
    id("com.google.android.gms.oss-licenses-plugin")
    id("com.google.gms.google-services")
    id("com.google.firebase.crashlytics")
    id("com.google.firebase.firebase-perf")
}

android {
    namespace = "com.space.biblemon"
    val localProps = Properties()
    localProps.load(project.rootProject.file("local.properties").inputStream())

    defaultConfig {
        applicationId = "com.space.biblemon"
        versionCode = 122
        versionName = "1.2.2"
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
            manifestPlaceholders["FIREBASE_REPORT_STATUS"] = false
            isDebuggable = true
        }
        release {
            manifestPlaceholders["FIREBASE_REPORT_STATUS"] = true
            isMinifyEnabled = true
            isDebuggable = false
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
    implementation(project(":features:class-room"))
    implementation(project(":features:course"))
    implementation(project(":features:pdf-viewer"))
    implementation(project(":features:web-view"))

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

    implementation("com.google.android.gms:play-services-oss-licenses:17.1.0")
    implementation(platform(Dev.FireBase.firebaseBom))
    implementation(Dev.FireBase.firebaseAnalytics)
    implementation(Dev.FireBase.firebaseCrashlytics)
    implementation(Dev.FireBase.firebasePerformance)
}