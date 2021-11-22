import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.springframework.boot") version "2.5.6"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.5.31"
    kotlin("plugin.spring") version "1.5.31"
}

group = "com.kevvlvl.kotlinexamples"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {

    val springBootVersion = "2.5.6"
    val testcontainersVersion = "1.16.2"
    val assertjVersion = "3.21.0"

    implementation("org.springframework.boot:spring-boot-starter-webflux:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa:$springBootVersion")
    implementation("org.springframework.boot:spring-boot-starter-validation:$springBootVersion")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.13.0")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions:1.1.5")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.5.2-native-mt")
    implementation("org.hibernate:hibernate-core:5.6.1.Final")
    implementation("org.postgresql:postgresql:42.3.1")
    developmentOnly("org.springframework.boot:spring-boot-devtools:$springBootVersion")
    testImplementation("org.springframework.boot:spring-boot-starter-test:$springBootVersion")
    testImplementation("io.projectreactor:reactor-test:3.4.12")
    testImplementation("org.mockito:mockito-core:4.0.0")
    testImplementation("org.testcontainers:testcontainers:$testcontainersVersion")
    testImplementation("org.testcontainers:junit-jupiter:$testcontainersVersion")
    testImplementation("org.testcontainers:postgresql:$testcontainersVersion")
    testImplementation("org.assertj:assertj-core:$assertjVersion")

}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "15"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
