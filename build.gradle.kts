import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "3.0.5"
	id("io.spring.dependency-management") version "1.1.0"
	kotlin("jvm") version "1.7.22"
	kotlin("plugin.spring") version "1.7.22"
	kotlin("plugin.jpa") version "1.7.22"
}

group = "ind.ni3mumbaikar.microservices"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-data-jpa")
	implementation("org.springframework.boot:spring-boot-starter-security")
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
//	implementation("com.oracle.database.jdbc:ojdbc10:19.18.0.0")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
	testImplementation("org.springframework.security:spring-security-test")
	runtimeOnly("com.oracle.database.jdbc:ojdbc8:21.1.0.0") {
		exclude( group= "com.oracle.database.ha", module="simplefan")
		exclude( group= "com.oracle.database.ha", module= "ons")
	}
	runtimeOnly ("com.oracle.database.jdbc:ucp:21.1.0.0")
	runtimeOnly ("com.oracle.database.security:oraclepki:21.1.0.0")
	runtimeOnly ("com.oracle.database.security:osdt_cert:21.1.0.0")
	runtimeOnly ("com.oracle.database.security:osdt_core:21.1.0.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "17"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
