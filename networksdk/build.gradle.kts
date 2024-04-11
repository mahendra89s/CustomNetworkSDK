import java.io.FileInputStream
import java.util.*

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
    kotlin("kapt")
    id("com.google.dagger.hilt.android")
    id("maven-publish")
}

android {
    namespace = "com.example.networksdk"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.gson)
    implementation(libs.okhttp)
    implementation(libs.logging)

    implementation (libs.hilt)
    kapt(libs.hiltCompiler)
}

/*val githubProperties = Properties()
githubProperties.load(FileInputStream(rootProject.file("github.properties")))

fun getVersionName(): String {
    return "2.0.1"
}

fun getArtificatId(): String {
    return "networksdk"
}*/

publishing{
    publications{
        register<MavenPublication>("release"){
            afterEvaluate{
                from(components["release"])
            }
        }
    }
}
/*
publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.network.sdk"
                artifactId = getArtificatId()
                version = getVersionName()
                artifact("$buildDir/outputs/aar/${getArtificatId()}-release.aar")
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"

            url = uri("https://maven.pkg.github.com/mahendra89s/CustomNetworkSDK")
            credentials {
                username = githubProperties["gpr.usr"] as String? ?: System.getenv("GPR_USER")
                password =
                    githubProperties["gpr.key"] as String? ?: System.getenv("GPR_API_KEY")

            }
        }
    }
}*/
