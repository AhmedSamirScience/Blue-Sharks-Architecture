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
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()

        mavenLocal()
        maven {
            url = uri("file://${rootDir}/build/local-maven")
        }
    }
}

rootProject.name = "bluearchitecture"
include(":app")
include(":feature:login")
include(":core:presentation")
include(":core:data")
include(":core:domain")
include(":core:ui")
include(":feature:viewmodelcases")
include(":feature:remotedata")
include(":feature:offlinedata")
include(":core:utils")
