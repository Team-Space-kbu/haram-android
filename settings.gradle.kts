pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        mavenLocal()
        maven("https://jitpack.io")
        maven("https://naver.jfrog.io/artifactory/maven/")
    }
}
rootProject.name = "biblemon"
include(":app")
include(":domain")
include(":shared")
include(":data")
include(":security")
include(":features:home")
include(":features:core-ui")
include(":features:book")
include(":features:navigate")
include(":features:mileage")
include(":features:chapel")
include(":features:other")
include(":features:board")
include(":features:timetable")
include(":features:main")
include(":features:partners")
include(":features:signin")
include(":features:bible")
include(":features:rothem")
include(":features:notice-bible")
include(":features:signup")
include(":features:image")
include(":features:notice-space")
include(":space-prosessor")
include(":space-annotation")
