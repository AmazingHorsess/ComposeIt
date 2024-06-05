plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.ksp)
}

android {
    namespace = "com.composeit.local"
    compileSdk = 34

    defaultConfig {
        minSdk = 31

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }
    ksp{
        arg("room.schemaLocation", "$projectDir/schemas")
        arg("room.incremental", "true")
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

    implementation(project(":data:repository"))

    implementation(libs.koin.core)

    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.androidx.room.ktx)

    api(libs.androidx.room.runtime)
    implementation(project(":libraries:core"))
    ksp(libs.androidx.room.compiler)


    androidTestImplementation(libs.test.junitext)
    androidTestImplementation(libs.test.runner)
    androidTestImplementation(libs.androidx.room.test)

    testImplementation(libs.test.junit)
    testImplementation(libs.test.mockk)
    testImplementation(libs.kotlinx.coroutines.test)
}