plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.space.navigator"
}

dependencies {
    implementation(project(":shared"))
}