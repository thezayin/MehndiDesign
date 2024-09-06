pluginManagement {
    repositories {
        google {
            content {
                includeGroupByRegex("com\\.android.*")
                includeGroupByRegex("com\\.google.*")
                includeGroupByRegex("androidx.*")
            }
        }
        mavenCentral()
        gradlePluginPortal()
        maven { url = uri("https://jitpack.io") }
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "Mehndi Design"
include(":app")
include(":ads")
include(":analytics")
include(":databases")
include(":previews:presentation")
include(":previews:domain")
include(":previews:data")
include(":favourite:domain")
include(":favourite:data")
include(":favourite:presentation")
include(":home:domain")
include(":home:data")
include(":home:presentation")
include(":core:drawable")
include(":core:entities")
include(":core:framework")
include(":core:di")
include(":core:dialogs")
include(":categories:domain")
include(":categories:data")
include(":categories:presentation")
include(":setting")
include(":splash")
