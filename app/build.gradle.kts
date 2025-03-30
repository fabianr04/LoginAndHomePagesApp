plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.dagger.hilt.android")
    id("com.google.devtools.ksp")
    alias(libs.plugins.jetbrains.kotlin.serialization)
    id("com.google.gms.google-services")
}

android {
    namespace = "com.cursojetpack.footballplayers"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.cursojetpack.footballplayers"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.androidx.hilt.navigation.compose)
    implementation(libs.androidx.navigation.compose.v289)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)

    //implementation(dagger hilt)
    implementation(libs.hilt.android)
    implementation(libs.androidx.runtime.livedata)
    implementation(libs.firebase.firestore.ktx)
    implementation(libs.googleid)
    implementation(libs.firebase.firestore)
    implementation(libs.firebase.auth)

    ksp(libs.hilt.compiler)

    implementation(platform(libs.firebase.bom))
    implementation(libs.firebase.auth.ktx)
    implementation(libs.play.services.auth)

    //implementation supabase
    implementation("io.ktor:ktor-client-core:3.1.1")
    implementation("io.github.jan-tennert.supabase:storage-kt:3.1.3") // Para Supabase Storage
    implementation("io.ktor:ktor-client-android:3.1.1")
    implementation("io.ktor:ktor-client-okhttp:2.3.4") // Motor específico para Android
    implementation("io.ktor:ktor-client-plugins:2.3.5") // Plugins esenciales

    implementation(libs.coil.compose)
    implementation(libs.androidx.credentials.core)
    implementation(libs.androidx.credentials.compat)
    implementation(libs.kotlinx.serialization.json)

    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}