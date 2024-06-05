plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("kotlin-parcelize")
}

android {
    namespace = "com.composeit.category"
    compileSdk = 34

    defaultConfig {
        minSdk = 31

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"

        }
    }
}

dependencies {

    implementation(project(":libraries:core"))
    implementation(project(":features:category-api"))
    implementation(project(":domain"))
    implementation(project(":libraries:design"))
    implementation(libs.koin.android)
    implementation(libs.koin.compose)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.toolingpreview)
    debugImplementation(libs.ui.tooling)

    implementation(libs.compose.activity)
    implementation(libs.bundles.compose)
    implementation(platform(libs.compose.bom))
    implementation(libs.androidx.corektx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.material)
    testImplementation(libs.test.junit)
    androidTestImplementation(libs.test.junitext)
    androidTestImplementation(libs.espresso.core)
}