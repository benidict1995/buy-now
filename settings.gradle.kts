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

rootProject.name = "buy-now"
include(":app")
include(":model")
include(":network")
include(":persistence")
include(":feature-login")
include(":feature-signup")
include(":common-ui")
include(":common-utils")
include(":home")
include(":feature-home")
include(":data")
include(":feature-category")
include(":feature-product")
include(":feature-notification")
include(":feature-main")
include(":feature-cart")
include(":feature-favorite")
