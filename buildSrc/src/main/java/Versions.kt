object LibrariesVersions {
    const val kotlinxCoroutines = "1.7.3"

    const val androidxCore = "1.12.0"
    const val androidxActivity = "1.7.2"
    const val androidxFragment = "1.6.1"
    const val androidxSecurity = "1.1.0-alpha06"
    const val androidxNavigation = "2.7.2"
    const val androidAppcompat = "1.7.0-alpha03"
    const val androidLifecycle = "2.6.2"
    const val androidLegacy = "1.0.0"
    const val androidConstraintlayout = "2.1.4"
    const val androidAnnotation = "1.7.0"
    const val androidCardview = "1.0.0"
    const val androidRecyclerview = "1.3.1"
    const val androidMaterial = "1.9.0"

    const val hilt = "2.48"
    const val gson = "2.10.1"
    const val glide = "4.16.0"
    const val timber = "5.0.1"

    const val okhttp = "5.0.0-alpha.11"
    const val retrofit = "2.9.0"

    const val naverMap= "3.17.0"

    const val junit = "4.13.2"
    const val jsoup = "1.16.1"
    const val androidxTestJunit = "1.1.5"
    const val androidxTestEspresso = "3.5.1"
}


object Libraries{
    //Jetbrain
    const val kotlinxCoroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${LibrariesVersions.kotlinxCoroutines}"

    //AndroidX
    const val androidxCore = "androidx.core:core-ktx:${LibrariesVersions.androidxCore}"
    const val androidxActivity = "androidx.activity:activity-ktx:${LibrariesVersions.androidxActivity}"
    const val androidxFragment = "androidx.fragment:fragment-ktx:${LibrariesVersions.androidxFragment}"
    const val androidxSecurity = "androidx.security:security-crypto-ktx:${LibrariesVersions.androidxSecurity}"
    const val androidxNavigationFragment = "androidx.navigation:navigation-fragment-ktx:${LibrariesVersions.androidxNavigation}"
    const val androidxNavigationUi = "androidx.navigation:navigation-ui-ktx:${LibrariesVersions.androidxNavigation}"

    //AndroidX Jetpack
    const val androidAppcompat = "androidx.appcompat:appcompat:${LibrariesVersions.androidAppcompat}"
    const val androidAppcompatResources = "androidx.appcompat:appcompat-resources:${LibrariesVersions.androidAppcompat}"
    const val androidLifecycleViewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${LibrariesVersions.androidLifecycle}"
    const val androidLifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${LibrariesVersions.androidLifecycle}"
    const val androidLegacy = "androidx.legacy:legacy-support-v4:${LibrariesVersions.androidLegacy}"
    const val androidConstraintlayout = "androidx.constraintlayout:constraintlayout:${LibrariesVersions.androidConstraintlayout}"
    const val androidAnnotation = "androidx.annotation:annotation:${LibrariesVersions.androidAnnotation}"
    const val androidCardview = "androidx.cardview:cardview:${LibrariesVersions.androidCardview}"
    const val androidRecyclerview = "androidx.recyclerview:recyclerview:${LibrariesVersions.androidRecyclerview}"
    const val androidMaterial = "com.google.android.material:material:${LibrariesVersions.androidMaterial}"

    //Hilt, dagger
    const val hiltAndroid = "com.google.dagger:hilt-android:${LibrariesVersions.hilt}"
    const val hiltAndroidGradle = "com.google.dagger:hilt-android-gradle-plugin:${LibrariesVersions.hilt}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${LibrariesVersions.hilt}"

    //Retrofit, okhttp3
    const val gson = "com.google.code.gson:gson:${LibrariesVersions.gson}"
    const val okhttp3 = "com.squareup.okhttp3:okhttp:${LibrariesVersions.okhttp}"
    const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:${LibrariesVersions.okhttp}"
    const val retrofit2 = "com.squareup.retrofit2:retrofit:${LibrariesVersions.retrofit}"
    const val retrofit2ConverterGson = "com.squareup.retrofit2:converter-gson:${LibrariesVersions.retrofit}"
    const val retrofit2ConverterScalars = "com.squareup.retrofit2:converter-scalars:${LibrariesVersions.retrofit}"


    //etc
    const val glide = "com.github.bumptech.glide:glide:${LibrariesVersions.glide}"
    const val glide_okhttp3 = "com.github.bumptech.glide:okhttp3-integration:${LibrariesVersions.glide}"
    const val glide_compiler = "com.github.bumptech.glide:compiler:${LibrariesVersions.glide}"
    const val timber = "com.jakewharton.timber:timber:${LibrariesVersions.timber}"
    const val jsoup = "org.jsoup:jsoup:${LibrariesVersions.jsoup}"
    const val javaxInject = "javax.inject:javax.inject:1"
    const val naverMaps = "com.naver.maps:map-sdk:${LibrariesVersions.naverMap}"
    const val shimmer = "com.facebook.shimmer:shimmer:0.5.0"



    //test
    const val junit = "junit:junit:${LibrariesVersions.junit}"
    const val androidxTestJunit = "androidx.test.ext:junit:${LibrariesVersions.androidxTestJunit}"
    const val androidxTestEspresso = "androidx.test.espresso:espresso-core:${LibrariesVersions.androidxTestEspresso}"
}