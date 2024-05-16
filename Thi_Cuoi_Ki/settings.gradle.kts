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
        jcenter()
        mavenCentral()
        maven {
            url = uri("https://www.jitpack.io" )
        }
    }
}

rootProject.name = "Thi_Cuoi_Ki"
include(":app")
