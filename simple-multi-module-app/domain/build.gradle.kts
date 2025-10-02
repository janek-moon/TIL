plugins {
    kotlin("plugin.spring")
    kotlin("plugin.jpa")
    kotlin("kapt")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.plugin.spring")
    }
}

