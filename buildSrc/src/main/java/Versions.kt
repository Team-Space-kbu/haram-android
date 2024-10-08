object Versions {
    const val kotlinxCoroutines = "1.7.3"

    const val androidxCore = "1.13.1"
    const val androidxActivity = "1.9.2"
    const val androidxFragment = "1.8.3"
    const val androidxSecurity = "1.1.0-alpha06"
    const val androidxNavigation = "2.8.1"
    const val androidAppcompat = "1.7.0"
    const val androidLifecycle = "2.8.6"
    const val androidLegacy = "1.0.0"
    const val androidConstraintlayout = "2.1.4"
    const val androidAnnotation = "1.8.0"
    const val androidCardView = "1.0.0"
    const val androidRecyclerview = "1.3.2"
    const val androidMaterial = "1.12.0"

    const val hilt = "2.51.1"
    const val gson = "2.11.0"
    const val glide = "4.16.0"
    const val timber = "5.0.1"

    const val okhttp = "4.12.0"
    const val retrofit = "2.11.0"

    const val naverMap = "3.19.1"

    const val junit = "4.13.2"
    const val jsoup = "1.16.1"
    const val androidxTestJunit = "1.1.5"
    const val androidxTestEspresso = "3.5.1"
}


object Dev {

    object Android {

        object Core{
            const val androidxCore = "androidx.core:core-ktx:${Versions.androidxCore}"
            const val splashscreen = "androidx.core:core-splashscreen:1.0.1"
        }

        object Startup{
            const val AndroidStartup = "androidx.startup:startup-runtime:1.2.0-rc01"
        }

        object Annotation{
            const val androidAnnotation = "androidx.annotation:annotation:${Versions.androidAnnotation}"
        }

        object Security{
            const val androidxSecurity = "androidx.security:security-crypto-ktx:${Versions.androidxSecurity}"
        }

        object Activity{
            const val androidxActivity = "androidx.activity:activity-ktx:${Versions.androidxActivity}"
            const val androidxNavigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}"
        }

        object Fragment{
            const val androidxFragment = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
            const val androidxNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"
        }

        object Legacy{
            const val androidLegacy = "androidx.legacy:legacy-support-v4:${Versions.androidLegacy}"
        }

        object Appcompat{
            const val androidAppcompat = "androidx.appcompat:appcompat:${Versions.androidAppcompat}"
            const val androidAppcompatResources = "androidx.appcompat:appcompat-resources:${Versions.androidAppcompat}"
        }

        object Lifecycle{
            const val androidLifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidLifecycle}"
            const val androidLifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidLifecycle}"
        }

        object Ui{
            const val androidMaterial = "com.google.android.material:material:${Versions.androidMaterial}"
            const val androidCardView = "androidx.cardview:cardview:${Versions.androidCardView}"
            const val androidRecyclerview = "androidx.recyclerview:recyclerview:${Versions.androidRecyclerview}"
            const val androidConstraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidConstraintlayout}"
            const val androidFlexbox = "com.google.android.flexbox:flexbox:3.0.0"
        }

        object WebKit{
            const val webKit = "androidx.webkit:webkit:1.10.0"
        }
    }

    object FireBase{
        const val firebaseBom = "com.google.firebase:firebase-bom:33.3.0"
        const val firebaseAnalytics = "com.google.firebase:firebase-analytics-ktx"
        const val firebaseCrashlytics = "com.google.firebase:firebase-crashlytics"
        const val firebasePerformance = "com.google.firebase:firebase-perf"
    }

    object Kotlin {
        const val kotlinxSerializationJson = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0"
        const val kotlinxSerializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0"
        const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinxCoroutines}"
    }

    object Gson {
        const val gson = "com.google.code.gson:gson:${Versions.gson}"
    }

    object Dagger {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.hilt}"
        const val hiltAndroidGradle = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hilt}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.hilt}"
    }

    object Okhttp {
        const val okhttp3 = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
        const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    }

    object Retrofit {
        const val retrofit2 = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val retrofit2ConverterScalars = "com.squareup.retrofit2:converter-scalars:${Versions.retrofit}"
    }

    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide_okhttp3 = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"
    }

    object AndroidSvg{
        const val androidSvg = "com.caverock:androidsvg:1.4"
    }

    object Ui {
        const val AndroidSlidingUpPanel = "com.github.hannesa2:AndroidSlidingUpPanel:4.6.1"
    }

    object Naver{
        const val naverMaps = "com.naver.maps:map-sdk:${Versions.naverMap}"
    }

    object Timber{
        const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    }
    object Jsoup{
        const val JSOUP = "org.jsoup:jsoup:${Versions.jsoup}"
    }

    object Facebook{
        const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"
    }

    object Timetable{
        const val timetable ="com.github.islandparadise14:MinTimetable:1.5.1"
    }

    object Test{
        const val junit = "junit:junit:${Versions.junit}"
        const val androidxTestJunit = "androidx.test.ext:junit:${Versions.androidxTestJunit}"
        const val androidxTestEspresso = "androidx.test.espresso:espresso-core:${Versions.androidxTestEspresso}"
    }

}