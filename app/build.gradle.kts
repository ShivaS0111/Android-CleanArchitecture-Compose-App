plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.movies"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.movies"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        //testInstrumentationRunner = "com.example.test_movie_app.HiltTestRunner"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"


        vectorDrawables {
            useSupportLibrary = true
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments ["room.schemaLocation"] = "$projectDir/schemas"
            }
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
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    testOptions {
        unitTests {
            //includeAndroidResources = true
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-compose:1.9.0")
    implementation(platform("androidx.compose:compose-bom:2024.06.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.paging:paging-common-ktx:3.3.0")
    implementation ("androidx.compose.material:material:1.6.8")


    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")
   // implementation("com.squareup.okhttp3:okhttp:4.9.0")
    implementation ("com.squareup.okhttp3:okhttp:4.12.0")
    implementation ("com.squareup.okhttp3:logging-interceptor:4.10.0")

    // Coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.9.0-RC")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.0")

    // Coroutine Lifecycle Scopes
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.3")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.3")
    implementation("androidx.activity:activity-ktx:1.9.0")


    //dagger-hilt

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.51.1")
    implementation("androidx.test.ext:junit-ktx:1.2.1")
    implementation("androidx.lifecycle:lifecycle-runtime-compose-android:2.8.4")

    implementation("androidx.hilt:hilt-navigation-compose:1.2.0")
    implementation("androidx.compose.ui:ui-test-junit4-android:1.7.5")
    kapt ("com.google.dagger:hilt-compiler:2.51.1")
    //coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    //nav graph
    val nav_version = "2.7.7"
    api("androidx.navigation:navigation-fragment-ktx:$nav_version")

    val colorPicker = "0.7.0"
    implementation ("com.godaddy.android.colorpicker:compose-color-picker:$colorPicker")

// with Android ColorInt extensions
    implementation ("com.godaddy.android.colorpicker:compose-color-picker-android:$colorPicker")
// desktop jvm version
    //implementation ("com.godaddy.android.colorpicker:compose-color-picker-jvm:$colorPicker")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.2.1")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.6.1")
    androidTestImplementation( "com.google.dagger:hilt-android-testing:2.51.1")

    androidTestImplementation(platform("androidx.compose:compose-bom:2024.06.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling:1.6.8")
    debugImplementation("androidx.compose.ui:ui-test-manifest:1.6.8")

    testImplementation( "org.mockito:mockito-core:5.8.0")

    implementation ("com.google.dagger:hilt-android:2.51.1")
    kaptAndroidTest ("com.google.dagger:hilt-android-compiler:2.51.1")
    androidTestImplementation ("androidx.arch.core:core-testing:2.2.0")

    testImplementation( "androidx.arch.core:core-testing:2.2.0")
    testImplementation( "io.mockk:mockk:1.13.2")
    testImplementation ("app.cash.turbine:turbine:1.0.0")
    testImplementation ("androidx.test:runner:1.6.1")

    // Other testing dependencies
    testImplementation ("junit:junit:4.13.2")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test-jvm:1.8.0")
    testImplementation ("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.9.0-RC")

    testImplementation ("org.mockito:mockito-core:5.8.0")

    // For Robolectric tests.
    //testImplementation ("org.robolectric:robolectric:4.6.1") // Required if you need Robolectric for resource tests

    implementation("org.bouncycastle:bcprov-jdk15on:1.70")
    implementation("org.bouncycastle:bcpkix-jdk15on:1.70")

    testImplementation("com.google.dagger:hilt-android-testing:2.51.1")
    kaptAndroidTest("com.google.dagger:hilt-android-compiler:2.51.1")
    // ...with Java.
    //androidTestAnnotationProcessor("com.google.dagger:hilt-android-compiler:2.51.1")

    androidTestImplementation("androidx.test.uiautomator:uiautomator:2.3.0")

    val room_version = "2.6.1"

    api("androidx.room:room-runtime:$room_version")
    api( "androidx.room:room-ktx:2.6.1")

    annotationProcessor("androidx.room:room-compiler:$room_version")

    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
    //room testing
    androidTestImplementation ("androidx.room:room-testing:$room_version")

}