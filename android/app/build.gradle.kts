plugins {
    id("com.android.application")
    id("kotlin-android")
    id("dev.flutter.flutter-gradle-plugin")
}

val flutterVersionCode = project.findProperty("flutter.versionCode") as String? ?: "1"
val flutterVersionName = project.findProperty("flutter.versionName") as String? ?: "1.0.0"

android {
    namespace = "com.mdzaberahmed.ffboostpanel"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.mdzaberahmed.ffboostpanel"
        minSdk = flutter.minSdkVersion
        targetSdk = 34
        versionCode = flutterVersionCode.toInt()
        versionName = flutterVersionName
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("android/debug.keystore")
        }
        create("release") {
            storeFile = System.getenv("KEYSTORE_FILE")?.let { file(it) }
                ?: file("upload-keystore.jks")
            storePassword = System.getenv("KEYSTORE_PASSWORD") ?: ""
            keyAlias = System.getenv("KEY_ALIAS") ?: ""
            keyPassword = System.getenv("KEY_PASSWORD") ?: ""
        }
    }

    buildTypes {
        release {
            signingConfig = if (System.getenv("KEYSTORE_PASSWORD") != null) {
                signingConfigs.getByName("release")
            } else {
                signingConfigs.getByName("debug")
            }
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
