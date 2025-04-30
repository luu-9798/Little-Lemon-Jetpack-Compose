plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
}

android {
    namespace = "com.luu9798.little_lemon_jetpack_compose"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.luu9798.little_lemon_jetpack_compose"
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
    implementation(libs.androidx.ui.graphics)

    //Compose BOM(Bill of Material): Sync versions of Compose libraries
    implementation(platform(libs.androidx.compose.bom))

    //Core Compose UI: basic UI elements like text, column, row, etc.
    implementation(libs.androidx.ui)

    //Material 3 components: Modern material UI design
    implementation(libs.androidx.material3)

    //Tooling support: @Preview for visualization
    implementation(libs.androidx.ui.tooling.preview)

    //Navigation for Compose: declarative navigations (NavHost, NavController)
    implementation(libs.androidx.navigation.compose)

    //Window size class
    implementation(libs.androidx.material3.window.size.class1)

    //Test implementation
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)

    //Debug implementation
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}