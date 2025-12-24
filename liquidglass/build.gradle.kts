plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    `maven-publish`
}

android {
    namespace = "com.yourpackage.liquidglass"
    compileSdk {
        version = release(36)
    }

    defaultConfig {
        minSdk = 24
    }

    buildTypes {
        release {
            isMinifyEnabled = false
        }
    }
    
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    
    kotlinOptions {
        jvmTarget = "11"
    }
    
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.haze)
}

// Version from gradle.properties or default
val libraryVersion = project.findProperty("LIQUIDGLASS_VERSION") as String? ?: "1.0.0"

// Publishing configuration
afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "com.github.dilarakiraz"
                artifactId = "liquid-glass-tabbar"
                version = libraryVersion

                from(components["release"])

                pom {
                    name.set("Liquid Glass Tab Bar")
                    description.set("A beautiful iOS-style liquid glass tab bar for Android Jetpack Compose with blur effects and gradient borders")
                    url.set("https://github.com/dilarakiraz/liquid-glass-tabbar")
                    
                    licenses {
                        license {
                            name.set("MIT License")
                            url.set("https://opensource.org/licenses/MIT")
                        }
                    }
                    
                    developers {
                        developer {
                            id.set("dilarakiraz")
                            name.set("Dilara Kiraz")
                        }
                    }
                    
                    scm {
                        connection.set("scm:git:git://github.com/dilarakiraz/liquid-glass-tabbar.git")
                        developerConnection.set("scm:git:ssh://github.com:dilarakiraz/liquid-glass-tabbar.git")
                        url.set("https://github.com/dilarakiraz/liquid-glass-tabbar")
                    }
                }
            }
        }
    }
}

