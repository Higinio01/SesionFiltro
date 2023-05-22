plugins {
    id("java");
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.google.guava:guava:31.1-jre")
    implementation("org.slf4j:slf4j-simple:2.0.7")
    implementation ("io.javalin:javalin:5.5.0")
    implementation ("io.javalin:javalin:4.0.0")
}

tasks.test {
    useJUnitPlatform()
}

