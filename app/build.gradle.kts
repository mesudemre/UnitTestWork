import java.util.*

plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.parcelize)
    alias(libs.plugins.kapt)
    alias(libs.plugins.hilt)
}

android {
    compileSdk = libs.versions.compileSdk.get().toInt()
    namespace = "com.mesutemre.unittestwork"

    defaultConfig {
        applicationId = "com.mesutemre.unittestwork"
        minSdk = libs.versions.minSdk.get().toInt()
        targetSdk = libs.versions.targetSdk.get().toInt()
        versionCode = libs.versions.appVersion.code.get().toInt()
        versionName = libs.versions.appVersion.name.get()

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }

        Properties().apply {
            load(project.rootProject.file("local.properties").inputStream())
            manifestPlaceholders["googleMapsApiKey"] = getProperty("MAPS_API_KEY", "")
            manifestPlaceholders["googleAdsKey"] = getProperty("ADMOB_API_KEY", "")
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
        isCoreLibraryDesugaringEnabled = true
    }

    buildFeatures {
        compose = true
        buildConfig = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {

    val composeBom = platform(libs.androidx.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)

    implementation(libs.kotlin.stdlib)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlin.reflection)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.lifecycle.viewModelCompose)
    implementation(libs.androidx.lifecycle.runtime.compose)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.core.splashscreen)

    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.foundation)
    implementation(libs.androidx.compose.foundation.layout)
    implementation(libs.androidx.compose.ui.util)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.animation)
    implementation(libs.androidx.compose.material.iconsExtended)
    implementation(libs.androidx.compose.ui.tooling.preview)
    //implementation(libs.androidx.compose.runtime.livedata)
    implementation(libs.accompanist.systemuicontroller)
    implementation(libs.intuit)
    implementation(libs.datastore)
    implementation(libs.hilt.android)
    implementation(libs.hilt.common)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.lottie)
    implementation(libs.okhttp3)
    implementation(libs.okhttp.logging)
    implementation(libs.retrofit.base)
    implementation(libs.retrofit.rxjava)
    implementation(libs.retrofit.converter.scalars)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.gson)
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)
    kapt(libs.androidx.hilt.compiler)
    kapt(libs.hilt.common)
    kapt(libs.hilt.ext.compiler)
    implementation(libs.androidx.work.runtime.ktx)
    implementation(libs.androidx.work.hilt)
    implementation(libs.androidx.constraintlayout.legacy)
    implementation(libs.coil)
    implementation(libs.picasso)
    //implementation(libs.androidx.glance.appwidget)
    implementation(libs.google.play.services.location)
    implementation(libs.android.maps.compose)
    implementation(libs.android.maps.compose.utils)
    implementation(libs.glide)

    debugImplementation(libs.androidx.compose.ui.tooling)
    coreLibraryDesugaring(libs.android.tools.desugarJdk)

    testImplementation(libs.junit)
    testImplementation(libs.mock)
    testImplementation(libs.androidx.test.ext.truth)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.turbine)
    testImplementation(libs.retrofit.test)
    androidTestImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.core)
    androidTestImplementation(libs.androidx.test.espresso.core)
    androidTestImplementation(libs.androidx.test.ext.junit)
}