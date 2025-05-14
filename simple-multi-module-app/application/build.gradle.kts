import org.springframework.boot.gradle.tasks.bundling.BootJar
import org.springframework.boot.gradle.tasks.run.BootRun

apply {
    plugin("org.jetbrains.kotlin.plugin.spring")
}

dependencies {
    implementation(project(":web"))
    implementation(project(":domain"))

    implementation("org.springframework.boot:spring-boot-starter")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-webflux")
    testImplementation("org.springframework.graphql:spring-graphql-test")
}

tasks.withType<BootJar> {
    enabled = true
    val jarName = project.properties["jarName"]

    archiveFileName = jarName?.let {
        "$it.jar"
    } ?: "${project.parent?.name ?: project.name}-${project.version}.jar"
}

tasks.withType<BootRun> {
    enabled = true
}

