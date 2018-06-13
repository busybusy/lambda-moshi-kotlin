plugins {
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    compile(project(":core"))
}

dependencies {
    val junitVersion = "5.2.0"
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}
