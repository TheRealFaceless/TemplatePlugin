rootProject.name = providers.gradleProperty("pluginName").get()

pluginManagement {
    plugins {
        id("io.freefair.lombok") version "8.11"
        id("io.papermc.paperweight.userdev") version "2.0.0-beta.18"
        id("com.gradleup.shadow") version "9.0.0-rc3"
        id("de.eldoria.plugin-yml.bukkit") version "0.7.1"
    }
    repositories {
        gradlePluginPortal()
        maven("https://repo.papermc.io/repository/maven-public/")
    }
}
