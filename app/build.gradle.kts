plugins {

    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    id("com.mikepenz.aboutlibraries.plugin")




}

android {
    namespace = "com.composeit"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.composeit"
        minSdk = 31
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
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "META-INF/LICENSE.md"
            excludes += "META-INF/LICENSE-notice.md"

        }
    }


}

dependencies {
    implementation(project(":libraries:navigation"))
    implementation(project(":libraries:design"))
    implementation(platform(libs.compose.bom))
    implementation(libs.koin.android)
    implementation(libs.compose.windowsizeclass)
    implementation(libs.logcat)
    implementation(libs.compose.toolingpreview)
    implementation(libs.accompanits.navigation)
    implementation(libs.androidx.activity)
    implementation(libs.voyager.navigator)
    implementation(libs.voyager.bottomsheet)

    implementation(project(":data:datastore"))
    implementation(project(":data:repository"))
    implementation(project(":data:local"))
    implementation(project(":data:local"))
    implementation(project(":features:alarm"))
    implementation(project(":features:preference"))
    implementation(project(":features:tracker"))

    implementation(project(":features:glance"))
    implementation(project(":features:search"))
    implementation(project(":features:task"))
    implementation(project(":features:category"))
    implementation(project(":libraries:core"))
    testImplementation(libs.koin.test)
    testImplementation(libs.test.mockk)
    implementation(libs.compose.navigation)
    implementation(libs.androidx.appcompat)
    implementation(libs.compose.activity)
    implementation(libs.bundles.compose)
    implementation(libs.test.mockk)

    implementation(project(":domain"))
    testImplementation("org.testng:testng:6.9.6")
    debugImplementation(libs.ui.tooling)


}