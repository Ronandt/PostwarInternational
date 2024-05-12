plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-kapt")
    id("io.realm.kotlin")
}

android {
    namespace = "com.example.worldskillschinaprac"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.worldskillschinaprac"
        minSdk = 30
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.0") //
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")//

    implementation("androidx.activity:activity-compose:1.9.0")//
    implementation(platform("androidx.compose:compose-bom:2024.04.01"))
    implementation("androidx.compose.ui:ui:1.6.6") //
    implementation("androidx.compose.ui:ui-graphics:1.6.6")//
    implementation("androidx.compose.ui:ui-tooling-preview:1.6.6") //
    implementation("androidx.compose.material3:material3:1.2.1") //
    implementation("androidx.compose.material:material:1.6.6") //
    implementation("androidx.compose.animation:animation:1.6.6") //
    implementation("androidx.compose.foundation:foundation:1.6.6") //
    implementation("androidx.compose.compiler:compiler:1.5.12")
    implementation("androidx.compose.runtime:runtime:1.6.6")
    implementation("io.coil-kt:coil-compose:2.5.0") //
    implementation("com.squareup.moshi:moshi:1.15.0") //Add
    implementation("androidx.media3:media3-exoplayer:1.3.1") //Add
    implementation("androidx.media3:media3-exoplayer-dash:1.3.1") //Add
    implementation("androidx.media3:media3-ui:1.3.1") //Add
    implementation("androidx.navigation:navigation-compose:2.7.7") //
    implementation("androidx.room:room-ktx:2.6.1") //
    implementation("androidx.exifinterface:exifinterface:1.3.7")
    implementation("androidx.media3:media3-session:1.3.1") //
    var camerax_version = "1.3.3"
    // The following line is optional, as the core library is included indirectly by camera-camera2
    implementation("androidx.camera:camera-core:${camerax_version}") //Add
    implementation("androidx.camera:camera-camera2:${camerax_version}")
    implementation("androidx.camera:camera-view:${camerax_version}")
    kapt("com.squareup.moshi:moshi-kotlin-codegen:1.14.0")
    implementation("androidx.biometric:biometric:1.1.0") //Add
    //   implementation("androidx.frag")
    // For AppWidgets support
    implementation("androidx.glance:glance-appwidget:1.0.0") //Add

    // For interop APIs with Material 2
    implementation("androidx.glance:glance-material:1.0.0") //Add

    // For interop APIs with Material 3
    implementation("androidx.glance:glance-material3:1.0.0") //Add
    val room_version = "2.6.1"
    implementation("io.realm.kotlin:library-base:1.11.0") //Add
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    testImplementation("junit:junit:4.13.2") //
    androidTestImplementation("androidx.test.ext:junit:1.5.0")//
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.0") //
    androidTestImplementation("androidx.compose.ui:ui-test-junit4") //
    debugImplementation("androidx.compose.ui:ui-tooling") //
    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0") //
    debugImplementation("androidx.compose.ui:ui-test-manifest") //
}