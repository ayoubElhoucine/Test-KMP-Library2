import org.jetbrains.kotlin.gradle.dsl.JvmTarget

import org.jetbrains.kotlin.gradle.plugin.mpp.apple.XCFramework

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        compilations.all {
            compileTaskProvider.configure {
                compilerOptions {
                    jvmTarget.set(JvmTarget.JVM_1_8)
                }
            }
        }
    }

    val xcf = XCFramework()
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            xcf.add(this)
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            //put your multiplatform dependencies here
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.ayoub.submodule"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

tasks.register("assembleMainXCFramework") {
    dependsOn("linkReleaseFrameworkIosArm64", "linkReleaseFrameworkIosX64", "linkReleaseFrameworkIosSimulatorArm64")

    doLast {
        val outputDir = file("${buildDir}/XCFrameworks/release")
        outputDir.mkdirs()

        val mainFramework = kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("iosArm64")
            .binaries.getFramework("Release").outputDirectory.absolutePath

        val submoduleFramework = kotlin.targets.getByName<org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget>("iosArm64")
            .binaries.getFramework("Release").outputDirectory.absolutePath

        exec {
            commandLine(
                "xcodebuild",
                "-create-xcframework",
                "-framework", mainFramework,
                "-framework", submoduleFramework,
                "-output", "${outputDir.absolutePath}/Submodule.xcframework"
            )
        }
    }
}