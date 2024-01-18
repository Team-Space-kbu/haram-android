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
include(":repository")
include(":features:home")
include(":features:core-ui")
include(":features:book")
include(":features:navigate")
include(":features:mileage")
include(":features:chapel")
include(":features:other")
include(":features:board")
include(":features:time_table")
include(":features:main")
include(":features:partners")
include(":features:auth")
include(":features:bible")
