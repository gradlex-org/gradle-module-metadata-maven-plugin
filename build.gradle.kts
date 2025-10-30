plugins { id("org.gradlex.maven-plugin-development") version "1.0.3" }

version = "1.2"

val mvnVersion = "3.9.11"

dependencies {
    implementation("com.google.code.gson:gson:2.13.2")

    compileOnly("org.apache.maven:maven-core:$mvnVersion")
    compileOnly("org.apache.maven:maven-plugin-api:$mvnVersion")
    compileOnly("org.apache.maven.plugin-tools:maven-plugin-annotations:3.15.2")
}

configurations.api {
    withDependencies { clear() } // remove gradleApi() dependency
}

tasks.javadoc {
    options {
        this as StandardJavadocDocletOptions
        tags("goal:a:Goal:", "requiresProject:a:Requires Project:", "threadSafe:a:Thread Safe:")
    }
}

mavenPlugin {
    name = "Gradle Module Metadata Maven Plugin"
    description = "A Maven plugin to publish Gradle Module Metadata"
    helpMojoPackage = "org.gradlex.maven.gmm"
}

publishingConventions {
    mavenCentral {
        displayName = mavenPlugin.name
        description = mavenPlugin.description
    }
}
