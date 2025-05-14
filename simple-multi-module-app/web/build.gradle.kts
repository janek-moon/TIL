dependencies {
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-websocket")
    implementation("org.springframework.boot:spring-boot-starter-graphql")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    testImplementation("org.springframework.graphql:spring-graphql-test")
    implementation(project(":domain"))
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.plugin.spring")
    }

    dependencies {
        implementation(project(":domain"))
    }
}
