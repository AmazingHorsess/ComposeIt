plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.composeit.domain"
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
    packaging {
        resources {
            excludes += "META-INF/LICENSE.md"

        }
    }
}

dependencies {


    implementation(libs.androidx.corektx)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.core)


    testImplementation(libs.test.junit)
    testImplementation(libs.test.junitext)
    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.test.mockk)
    androidTestImplementation("junit:junit:4.12")


}