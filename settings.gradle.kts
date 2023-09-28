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
    }
}
rootProject.name = "biblemon"
include (":app")
include (":domain")
include (":data")
include (":shared")

include(":feature:book")
include(":feature:home")
include(":feature:ui-core")
