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

    buildTypes {
        release {
            // Signing is handled by:
            // 1. build.yml: uses default debug key for CI artifacts
            // 2. build_and_sign.yml: uses release key via r0adkll/sign-android-release action
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
