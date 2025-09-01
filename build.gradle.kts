plugins {
    id("java")
    id("io.freefair.lombok")
    id("io.papermc.paperweight.userdev")
    id("com.gradleup.shadow")
    id("de.eldoria.plugin-yml.bukkit")
}

group = providers.gradleProperty("pluginGroup").get()
version = providers.gradleProperty("pluginVersion").get()

repositories {
    maven { url = uri("https://repo.papermc.io/repository/maven-public/") }
}

dependencies {
    compileOnly("io.papermc.paper:paper-api:${providers.gradleProperty("minecraftVersion").get()}-R0.1-SNAPSHOT")
    paperweight.paperDevBundle("${providers.gradleProperty("minecraftVersion").get()}-R0.1-SNAPSHOT")

    if (providers.gradleProperty("useLocalLib").map {
        it.toBoolean()
    }.get()) {
        val localLibDir: String by project
        val localLibName: String by project
        implementation(files("$localLibDir/$localLibName"))
    }
}

tasks.jar {
    enabled = false
}

tasks.shadowJar {
    archiveClassifier.set("")

    val outputJarDir = providers.gradleProperty("outputJarDir").get()
    destinationDirectory.set(file(outputJarDir))
}

tasks.assemble {
    dependsOn(tasks.shadowJar)
}

bukkit {
    name = providers.gradleProperty("pluginName").get()
    main = providers.gradleProperty("pluginMain").get()
    apiVersion = providers.gradleProperty("paperApiVersion").get()
    version = providers.gradleProperty("pluginVersion").get()
    author = providers.gradleProperty("pluginAuthor").get()
    description = providers.gradleProperty("pluginDescription").get()
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(providers.gradleProperty("javaVersion").get().toInt()))
}
