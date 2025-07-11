plugins {
    id("com.gradle.develocity") version "4.0.2"
}

rootProject.name = "gradle-module-metadata-maven-plugin"

@Suppress("UnstableApiUsage")
dependencyResolutionManagement {
    repositories.mavenCentral()
}

develocity {
    buildScan {
        val isCi = providers.environmentVariable("CI").getOrElse("false").toBoolean()
        if (isCi) {
            termsOfUseUrl = "https://gradle.com/help/legal-terms-of-use"
            termsOfUseAgree = "yes"
        } else {
            publishing.onlyIf { false }
        }
    }
}
