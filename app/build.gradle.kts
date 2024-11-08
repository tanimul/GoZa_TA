plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)

    alias(libs.plugins.google.dagger.hilt.android)
    alias(libs.plugins.kotlin.kapt)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.navSafeArgs)
    id("kotlin-parcelize")
}

android {
    namespace = "com.tanimul.goza_ta"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tanimul.goza_ta"
        minSdk = 24
        targetSdk = 34
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
        dataBinding = true
        viewBinding = true
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    // Logging with Timber
    implementation(libs.timber)

    // Dependency Injection with Hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.android.compiler)
    implementation(libs.hilt.navigation.fragment)

    implementation(libs.androidx.fragment.ktx)

    // Lifecycle components
    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.lifecycle.common.java8)

    // Navigation Graph
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)

    // Coroutines
    api(libs.coroutines.core)
    api(libs.coroutines.android)
    implementation(libs.coroutines.core)

    // Gson
    implementation(libs.gson)

    // Circular Imageview
    implementation(libs.circleimageview)

    // Glide
    implementation(libs.glide.library)
    annotationProcessor(libs.glide.compiler)

    // Retrofit
    implementation(libs.retrofit.core)
    implementation(libs.retrofit.converter.gson)
    implementation(libs.okhttp.logging.interceptor)

    // Paging-3
    implementation(libs.paging.runtime.ktx)

    //Room for local data storage
    implementation(libs.androidx.room.ktx)
    ksp(libs.androidx.room.compiler)
    androidTestImplementation(libs.androidx.room.testing)

    // Data storage and preferences
    implementation(libs.androidx.datastore.preferences)

    // RxJava3 Support
    implementation (libs.androidx.paging.rxjava3)
    implementation (libs.rxandroid)
    implementation (libs.rxjava3.retrofit.adapter)

    implementation (libs.banner)
}