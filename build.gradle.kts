import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.diffplug.spotless") version "6.11.0"
    id("io.qameta.allure") version "2.11.0"
}

apply(plugin = "com.diffplug.spotless")

val allureVersion = "2.19.0"
val junit5Version = "5.6.2"

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType(Test::class) {
    useJUnitPlatform()

    systemProperty("junit.jupiter.execution.parallel.enabled", "true")
    systemProperty("junit.jupiter.execution.parallel.config.strategy", "dynamic")
    systemProperty("junit.jupiter.extensions.autodetection.enabled", "true")
    systemProperty("chromeoptions.prefs", "credentials_enable_service=false,profile.password_manager_enabled=false")
    systemProperty("selenide.headless", "false")
    systemProperty("selenide.browserSize", "2100x1080")

    testLogging {
        events("passed", "skipped", "failed")
    }
}

configure<io.qameta.allure.gradle.base.AllureExtension> {
    adapter.aspectjWeaver.set(true)
    version.set(allureVersion)
}


repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.codeborne:selenide:6.7.4")
    implementation("io.github.serpro69:kotlin-faker:1.12.0-rc.2")
    implementation("org.assertj:assertj-core:3.23.1")

    implementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.junit.jupiter:junit-jupiter-api:$junit5Version")
    implementation("org.junit.jupiter:junit-jupiter-engine:$junit5Version")
    implementation("org.junit.jupiter:junit-jupiter-params:$junit5Version")
    implementation("io.qameta.allure:allure-java-commons:$allureVersion")
    implementation("io.qameta.allure:allure-selenide:$allureVersion")
}

configure<com.diffplug.gradle.spotless.SpotlessExtension> {
    format("misc") {
        target("*/src/**/*.kt")
        endWithNewline()
        trimTrailingWhitespace()
    }
    kotlin {
        target("*/src/**/*.kt")
        ktlint("0.47.1")
    }
    kotlinGradle {
        target("**/*gradle.kts")
        ktlint("0.47.1")
    }
}
