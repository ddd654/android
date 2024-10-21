plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt") // 여기를 수정하여 Kotlin DSL 함수 형식으로 변경

}

android {
    namespace = "com.example.roomdatabase"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.roomdatabase"
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
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

    val room_version = "2.6.1"
    implementation("androidx.room:room-runtime:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    val roomVersion = "2.4.2"
    implementation("androidx.room:room-ktx:$roomVersion")


//    def room_version = "2.6.1"
//    implementation "androidx.room:room-runtime:$room_version"
//    annotationProcessor "androidx.room:room-compiler:$room_version"
//    kapt "androidx.room:room-compiler:$room_version"
//    def roomVersion = "2.4.2"
//    implementation "androidx.room:room-ktx:$roomVersion"

}