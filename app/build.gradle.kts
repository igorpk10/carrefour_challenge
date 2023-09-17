plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
    id("androidx.navigation.safeargs.kotlin")
}

val compileVersion = 34

android {
    namespace = "com.igor.carrefourchallenge"
    compileSdk = compileVersion

    defaultConfig {
        applicationId = "com.igor.carrefourchallenge"
        minSdk = 22
        targetSdk = compileVersion
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

    viewBinding {
        this.enable = true
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    //Core
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.recyclerview:recyclerview:1.3.1")

    //Hilt
    var hilt = "2.44"
    implementation("com.google.dagger:hilt-android:${hilt}")
    kapt("com.google.dagger:hilt-android-compiler:${hilt}")

    //Retrofit
    var retrofit = "2.9.0"
    implementation("com.squareup.retrofit2:retrofit:${retrofit}")
    implementation("com.squareup.retrofit2:converter-gson:${retrofit}")

    //Gson
    val gson = "2.10.1"
    implementation("com.google.code.gson:gson:${gson}")

    //OkHttp
    val okhttp = "4.9.0"
    implementation(platform("com.squareup.okhttp3:okhttp-bom:${okhttp}"))
    implementation("com.squareup.okhttp3:okhttp")
    implementation("com.squareup.okhttp3:logging-interceptor")

    //Navigation
    val nav_version = "2.7.2"
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

    //Lifecycle
    val lifecycle_version = "2.6.2"
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:${lifecycle_version}")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${lifecycle_version}")

    // Glide
    val glide = "4.13.2"
    implementation("com.github.bumptech.glide:glide:${glide}")

    // Lottie
    val lottie = "5.2.0"
    implementation("com.airbnb.android:lottie:${lottie}")

    //Shimmer
    val shimmer = "0.5.0"
    implementation("com.facebook.shimmer:shimmer:${shimmer}")

    // Test Libs
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt {
    correctErrorTypes = true
}