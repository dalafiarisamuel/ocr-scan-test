plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.android)
    kotlin(Plugins.kapt)
    id("name.remal.check-dependency-updates") version "1.2.2"
    id(Plugins.daggerHilt)
    id(Plugins.androidxNavigationsafeArgsKotlin)
    id(Plugins.kotlinParcelize)
}

android {
    compileSdkVersion(Versions.compilesdk)

    defaultConfig {
        applicationId = Application.id
        minSdkVersion(Versions.minsdk)
        targetSdkVersion(Versions.targetsdk)
        versionCode = Application.versionCode
        versionName = Application.versionName
        testInstrumentationRunner = "ng.mint.ocrscanner.HiltTestRunner"
    }

    buildFeatures {
        dataBinding = true
    }

    buildTypes {

        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        targetCompatibility = Java.javaVersion
        sourceCompatibility = Java.javaVersion
    }

    kotlinOptions {
        jvmTarget = Java.javaVersion.toString()
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementAll(Dependencies.implementations)
    implementAll(SupportDependencies.supportImplementation)
    testImplementAll(TestDependencies.testImplementation)
    testAndroidImplementAll(AndroidTestDependencies.androidTestImplementation)
    kaptImplementAll(AnnotationProcessors.AnnotationProcessorsImplementation)
    kaptAndroidTestImplementAll(AnnotationProcessors.AnnotationProcessorsImplementation)
    debugImplementationAll(DebugDependencies.debugImplementation)
}