plugins {
    id("org.gradle.toolchains.foojay-resolver-convention") version "0.5.0"
}

rootProject.name = "simple-multi-module-app"

include("application")
include("web")
include("domain")