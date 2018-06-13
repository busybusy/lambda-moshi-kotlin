plugins {
    maven
    kotlin("jvm")
}

dependencies {
    implementation(kotlin("stdlib"))
    compile("com.squareup.moshi", "moshi-kotlin", "1.6.0")
    compile("com.amazonaws", "aws-lambda-java-core", "1.2.0")
}

dependencies {
    val junitVersion = "5.2.0"
    testImplementation(group = "org.junit.jupiter", name = "junit-jupiter-api", version = junitVersion)
    testRuntimeOnly(group = "org.junit.jupiter", name = "junit-jupiter-engine", version = junitVersion)
}

tasks.withType(Test::class.java) {
    useJUnitPlatform()
}