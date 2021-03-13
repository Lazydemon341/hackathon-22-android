plugins {
    id(Plugins.androidApp)
    id(Plugins.kotlinAndroidApp)
    id(Plugins.parcelize)
    id(Plugins.googleServicesPlugin)
    kotlin(Plugins.kapt)
}

kapt {
    correctErrorTypes = true
}

android {
    buildToolsVersion = Config.androidBuildTools
    compileSdkVersion(Config.androidCompileSdk)

    signingConfigs {
        getByName("debug") {
            keyAlias = "KEYSTORE"
            keyPassword = "0123456789"
            storeFile = file("DebugKeyStore.jks")
            storePassword = "0123456789"
        }
    }
    defaultConfig {
        applicationId = Config.applicationId
        minSdkVersion(Config.androidMinSdk)
        targetSdkVersion(Config.androidTargetSdk)
        versionCode = Config.versionCode
        versionName = Config.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs["debug"]
            isDebuggable = true
            buildConfigField(
                "String",
                "BASE_URL",
                """"https://winner-app-2t4oaep36q-ez.a.run.app/""""
            )
        }

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
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
    implementation(Libs.appcompat)
    implementation(Libs.material)
    implementation(Libs.constraintLayout)
    implementation(Libs.coroutines)
    implementation(Libs.lifecycle)
    implementation(Libs.viewModel)
    implementation(Libs.room)
    implementation(Libs.roomKtx)
    implementation(Libs.fragmentKtx)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.0")
    kapt(Libs.roomCompiler)
    platform(Libs.firebase)

    implementation(Libs.adapterDelegates)
    implementation(Libs.cicerone)
    implementation(Libs.glide)
    kapt(Libs.glideCompiler)
    kapt(Libs.moshiCodeGen)
    implementation(Libs.glideOkhttp)
    implementation(Libs.glideRecyclerView)
    implementation(Libs.retrofit)
    implementation(Libs.okhttp)
    implementation(Libs.okhttpLogging)
    implementation(Libs.timber)
    implementation(Libs.workManager)
    implementation(Libs.moshiAdapters)
    implementation(Libs.retrofitConverter)
    implementation(Libs.viewBindingProperty)
    implementation(Libs.dagger)
    kapt(Libs.daggerProcessor)
    implementation(Libs.googleMaps)
}