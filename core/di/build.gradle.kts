plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.serialization.json)
}

android {
    namespace = "com.thezayin.di"
    compileSdk = 35

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
    implementation(project(":core:drawable"))
    implementation(project(":core:entities"))
    implementation(project(":core:framework"))
    implementation(project(":analytics"))
    implementation(project(":databases"))
    implementation(project(":ads"))

    implementation(project(":splash"))
    implementation(project(":setting"))

    implementation(project(":previews:data"))
    implementation(project(":previews:domain"))
    implementation(project(":previews:presentation"))

    implementation(project(":home:data"))
    implementation(project(":home:domain"))
    implementation(project(":home:presentation"))

    implementation(project(":favourite:data"))
    implementation(project(":favourite:domain"))
    implementation(project(":favourite:presentation"))

    implementation(project(":categories:data"))
    implementation(project(":categories:domain"))
    implementation(project(":categories:presentation"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.play.services.measurement.api)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //json
    implementation(libs.kotlinx.serialization.json)
    
    //koin dependency injection
    implementation(libs.koin.core)
    implementation(libs.koin.compose)
    implementation(libs.koin.android)
    testImplementation(libs.koin.test.junit4)
    implementation(libs.koin.androidx.navigation)
}