import org.jetbrains.kotlin.gradle.targets.js.dsl.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")
//    id("org.jetbrains.kotlin.multiplatform")
}

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

// https://github.com/google/ksp/issues/1288#issuecomment-1587376988
//kotlin {
//    jvmToolchain(21)
//
//    @OptIn(ExperimentalWasmDsl::class)
//    wasmJs {
//        moduleName = "composeApp"
//        browser {
//            val projectDirPath = project.projectDir.path
//            commonWebpackConfig {
//                outputFileName = "composeApp.js"
//                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
//                    static = (static ?: mutableListOf()).apply {
//                        // Serve sources to debug inside browser
//                        add(projectDirPath)
//                    }
//                }
//            }
//        }
//        binaries.executable()
//    }
//
////    androidTarget {
////        @OptIn(ExperimentalKotlinGradlePluginApi::class)
////        compilerOptions {
////            jvmTarget.set(JvmTarget.JVM_11)
////        }
////    }
//
//    jvm("desktop")
//
//    listOf(
//        iosX64(),
//        iosArm64(),
//        iosSimulatorArm64()
//    ).forEach { iosTarget ->
//        iosTarget.binaries.framework {
//            baseName = "ComposeApp"
//            isStatic = true
//        }
//    }
//
//    sourceSets {
//        val desktopMain by getting
//
////        androidMain.dependencies {
////            implementation(libs.androidx.activity.compose)
////        }
//        commonMain.dependencies {
//            implementation(libs.androidx.lifecycle.viewmodel)
//            implementation(libs.androidx.lifecycle.runtime.compose)
//            implementation(projects.shared)
//
//            implementation(libs.kotlinx.coroutines.core)
//            implementation(project.dependencies.platform(libs.koin.bom))
//            implementation(libs.koin.core)
//        }
//        desktopMain.dependencies {
//            implementation(libs.kotlinx.coroutines.swing)
//        }
//    }
//}


java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

// https://github.com/google/ksp/issues/1288#issuecomment-1587376988
kotlin {
    jvmToolchain(21)
}


dependencies {
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockito.kotlin)
    testImplementation(libs.mockito.inline)
    testImplementation(libs.kotlinx.coroutines.test)

}
