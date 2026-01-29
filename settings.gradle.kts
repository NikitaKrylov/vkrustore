pluginManagement {
    includeBuild("build-logic")

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

rootProject.name = "VKRuStore"
include(":app")

include(":core:common")
include(":core:db")
include(":core:navigation")
include(":core:network")
include(":core:storage")

include(":feature:appDetail:api")
include(":feature:appDetail:impl")

include(":feature:categories:api")
include(":feature:categories:impl")

include(":feature:onboarding:api")
include(":feature:onboarding:impl")

include(":feature:search:api")
include(":feature:search:impl")

include(":feature:account:api")
include(":feature:account:impl")

include(":feature:showcase:api")
include(":feature:showcase:impl")

include(":screenshotTest")

include(":uikit")
