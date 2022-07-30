import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import org.jetbrains.kotlin.gradle.plugin.KotlinSourceSet


plugins {
	val kotlinVersion = "1.6.21"

	id("org.springframework.boot") version "2.7.1"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.ewerk.gradle.plugins.querydsl") version "1.0.10"
	kotlin("jvm") version kotlinVersion
	kotlin("kapt") version kotlinVersion
	kotlin("plugin.spring") version kotlinVersion
	kotlin("plugin.jpa") version kotlinVersion
}


	group = "kr.co.jh"
version = "0.0.1-SNAPSHOT"
	java.sourceCompatibility = JavaVersion.VERSION_11

	configurations {
		compileOnly {
			extendsFrom(configurations.annotationProcessor.get())
		}
	}

	val querydslVersion = "5.0.0" //querydsl

	repositories {
		mavenCentral()
	}

	dependencies {
		implementation("org.springframework.boot:spring-boot-starter-data-jdbc")
		//jpa
		implementation("org.springframework.boot:spring-boot-starter-data-jpa")
//	FIXME: 시큐리티 그래큐엘 적용후 진행
//	implementation("org.springframework.boot:spring-boot-starter-security")
		implementation("org.springframework.boot:spring-boot-starter-web")
		developmentOnly("org.springframework.boot:spring-boot-devtools")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")

		//querydsl
		implementation("com.querydsl:querydsl-jpa:$querydslVersion")
		kapt("org.springframework.boot:spring-boot-configuration-processor")
		kapt("com.querydsl:querydsl-apt:$querydslVersion:jpa")

		//lombok
		compileOnly("org.projectlombok:lombok")
		annotationProcessor("org.projectlombok:lombok")
		testCompileOnly("org.projectlombok:lombok")
		testAnnotationProcessor("org.projectlombok:lombok")


		//코틀린
		implementation("org.jetbrains.kotlin:kotlin-reflect")
		implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")

		//DB
		runtimeOnly("mysql:mysql-connector-java")
		runtimeOnly("com.h2database:h2:1.4.200")
		implementation("com.github.gavlyukovskiy:p6spy-spring-boot-starter:1.8.0")

		//GraphQL
		implementation("org.springframework.boot:spring-boot-starter-graphql")
		implementation("com.fasterxml.jackson.module:jackson-module-kotlin")


		//Test
		testImplementation("org.springframework.boot:spring-boot-starter-test")
		testImplementation("org.springframework.security:spring-security-test")
		testImplementation("org.springframework.graphql:spring-graphql-test")
		testImplementation("org.springframework:spring-webflux")



	}

	tasks.withType<KotlinCompile> {
		kotlinOptions {
			freeCompilerArgs = listOf("-Xjsr305=strict")
			jvmTarget = "11"
		}
	}

	tasks.withType<Test> {
		useJUnitPlatform()
	}

allOpen {
	annotation("javax.persistence.Entity")
	annotation("javax.persistence.MappedSuperclass")
	annotation("javax.persistence.Embeddable")
}



	sourceSets["main"].withConvention(KotlinSourceSet::class) {
		kotlin.srcDir("$buildDir/generated/source/kapt/main")
	}

