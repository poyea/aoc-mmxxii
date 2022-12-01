plugins {
    kotlin("jvm") version "1.7.22"

    application
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")

    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
    testImplementation("org.junit.jupiter:junit-jupiter:5.7.1")
}

application {
    mainClass.set("poyea.aoc.mmxxii.day01.Day01Kt")
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}

tasks.named<Test>("test") {
    useJUnitPlatform() 
}
