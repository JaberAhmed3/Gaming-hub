plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.flutter.flutter-gradle-plugin")
}

import java.util.Properties
import org.gradle.api.GradleException

val flutterVersionCode = project.findProperty("flutter.versionCode") as String? ?: "1"
val flutterVersionName = project.findProperty("flutter.versionName") as String? ?: "1.0.0"
val keystoreProperties = Properties()
val keystorePropertiesFile = rootProject.file("key.properties")
val hasReleaseKeystore = keystorePropertiesFile.exists()

if (hasReleaseKeystore) {
    keystorePropertiesFile.inputStream().use { keystoreProperties.load(it) }
}

android {
    namespace = "com.mdzaberahmed.ffboostpanel"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.mdzaberahmed.ffboostpanel"
        minSdk = 21
        targetSdk = 35
        versionCode = flutterVersionCode.toInt()
        versionName = flutterVersionName
    }

    signingConfigs {
        if (hasReleaseKeystore) {
            create("release") {
                val storeFilePath = keystoreProperties["storeFile"] as String
                storeFile = rootProject.file(storeFilePath)
                storePassword = keystoreProperties["storePassword"] as String
                keyAlias = keystoreProperties["keyAlias"] as String
                keyPassword = keystoreProperties["keyPassword"] as String
            }
        }
    }

    buildTypes {
        release {
            if (!hasReleaseKeystore) {
                throw GradleException("Missing android/key.properties for release signing.")
            }
            signingConfig = signingConfigs.getByName("release")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    kotlinOptions {
        jvmTarget = "17"
    }
}

flutter {
    source = "../.."
}
