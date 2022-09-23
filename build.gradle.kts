import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.gradle.api.tasks.testing.logging.TestExceptionFormat.FULL

plugins {
    kotlin("plugin.jpa") version "1.6.21"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
    id("org.springframework.boot") version "2.7.1"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    id("com.diffplug.spotless") version "6.11.0"
}

apply(plugin = "com.diffplug.spotless")

repositories {
    mavenCentral()
}

dependencies {
//    implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("com.codeborne:selenide:6.7.4")
    implementation("org.springframework.boot:spring-boot-starter-web")
//    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
//    implementation("org.jetbrains.kotlin:kotlin-reflect")
//    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("io.github.serpro69:kotlin-faker:1.12.0-rc.2")
    implementation("io.github.microutils:kotlin-logging-jvm:3.0.0")

    testImplementation("org.assertj:assertj-core:3.23.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("com.diffplug.spotless:spotless-plugin-gradle:6.11.0")
    }
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

tasks {
    test {
        useJUnitPlatform()
        testLogging {
            showExceptions = true
            exceptionFormat = FULL
            showStackTraces = true
        }

        if (project.hasProperty("browser")) {
            systemProperty("browser", project.property("browser") ?: "chrome")
        }
        systemProperty("chromeoptions.prefs", "credentials_enable_service=false,profile.password_manager_enabled=false")
//        systemProperty("wdm.chromeDriverVersion", "80")
        systemProperty("selenide.headless", "false")
        systemProperty("selenide.browserSize", "2100x1080")
        if (project.hasProperty("grid")) {
            systemProperty("browser.remote", "true")
            systemProperty("selenide.remote", "http://${project.property("grid")}:4444/wd/hub")
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}