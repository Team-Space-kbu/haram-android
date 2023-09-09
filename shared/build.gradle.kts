plugins {
    kotlin("android")
    id("com.android.library")
}

android {
    namespace = "com.space.shared"
}

dependencies {
    implementation (Libraries.javaxInject)
}