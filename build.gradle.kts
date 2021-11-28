import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.6.0"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.6.0"
    kotlin("plugin.spring") version "1.6.0"
}

group = "com.kevvlvl.simpleapi"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

project.ext {
    set("springBootVersion", "2.6.0")
    set("testcontainersVersion", "1.16.2")
    set("assertjVersion", "3.21.0")
    set("cucumberVersion", "7.0.0")
    set("junit5Version", "5.8.1")
    set("junitSuiteVersion", "1.8.1")
}

dependencies {

    implementation("org.springframework.boot:spring-boot-starter-webflux:" + project.extra["springBootVersion"])
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:" + project.extra["springBootVersion"])
    implementation("org.springframework.boot:spring-boot-starter-validation:" + project.extra["springBootVersion"])
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")
    implementation("org.hibernate:hibernate-core:5.6.1.Final")
    implementation("org.postgresql:postgresql:42.3.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools:" + project.extra["springBootVersion"])


    testImplementation("org.springframework.boot:spring-boot-starter-test:" + project.extra["springBootVersion"])
    testImplementation("io.projectreactor:reactor-test:3.4.12")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.testcontainers:testcontainers:" + project.extra["testcontainersVersion"])
    testImplementation("org.testcontainers:junit-jupiter:" + project.extra["testcontainersVersion"])
    testImplementation("org.testcontainers:postgresql:" + project.extra["testcontainersVersion"])
    testImplementation("org.assertj:assertj-core:" + project.extra["assertjVersion"])
    testImplementation("io.cucumber:cucumber-spring:" + project.extra["cucumberVersion"])

    testImplementation(platform("org.junit:junit-bom:" + project.extra["junit5Version"]))
    testImplementation(platform("io.cucumber:cucumber-bom:" + project.extra["cucumberVersion"]))

    testImplementation("io.cucumber:cucumber-java:" + project.extra["cucumberVersion"])
    testImplementation("io.cucumber:cucumber-junit-platform-engine:" + project.extra["cucumberVersion"])
    testImplementation("org.junit.platform:junit-platform-suite:" + project.extra["junitSuiteVersion"])
    testImplementation("org.junit.jupiter:junit-jupiter:" + project.extra["junit5Version"])
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
    systemProperty("cucumber.junit-platform.naming-strategy", "long")
}
