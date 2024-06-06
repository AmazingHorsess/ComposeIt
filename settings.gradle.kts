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
    }
}

rootProject.name = "Compose It"
include(":app")
include(":libraries:navigation")
include(":libraries:di")
include(":data:datastore")
include(":domain")
include(":data:repository")
include(":libraries:design")
include(":data:local")
include(":libraries:core")
include(":features:task")
include(":features:category-api")
include(":features:alarm-api")
include(":features:alarm")
include(":features:category")
include(":features:preference")
include(":features:glance")
include(":features:search")
include(":features:tracker")
