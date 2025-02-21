# 🚀 Initial Build Setup & Configuration
**Branch:** `1.0-ArchitecturePhase/Feature/1.2-Signing-And-Flavors-Setup`

---

## 📌 Overview
This branch introduces a **structured and optimized build system** for the Android project. It includes:
- ✅ **Gradle configurations** (build types, signing, flavors)
- ✅ **Dependency management** with centralized `defaultLibraries()`
- ✅ **Java/Kotlin compatibility settings** (JVM 17)
- ✅ **Signing configurations** for different environments (Debug, Release, QA)
- ✅ **Product flavors** for Google, Huawei, Client, and Driver apps
- ✅ **Optimized APK packaging** to reduce build size
- ✅ **Comprehensive documentation** for maintainability  

This setup ensures **scalability, maintainability, and improved build performance**.

---

## 🔹 **Features Added**
### 1️⃣ **Build System Configuration**
- Implemented **build types**: `debug`, `release`, `releaseExternalQA`
- Set up **compile options**:
  - Uses **Java 17** (`sourceCompatibility` & `targetCompatibility`)
  - Uses **Kotlin JVM 17** (`jvmTarget = "17"`)
- Enabled `BuildConfig.java` generation for storing **build metadata**

### 2️⃣ **Dependency Management**
- Moved dependencies to `defaultLibraries()`
- Ensures a **centralized** dependency structure for maintainability
- Reduces **duplicate dependencies** across modules

### 3️⃣ **Signing Configurations**
- Added `SigningTypes`:
  - **Debug signing** (default Android keystore)
  - **Release signing** (used for Play Store)
  - **QA signing** (for internal testing)
- Uses `local.properties` to store signing credentials **securely**

### 4️⃣ **Product Flavors**
- Introduced **flavor dimensions**:
  - `APP` → Groups different app variants (Client, Driver)
  - `STORE` → Groups store variants (Google Play, Huawei AppGallery)
- Defined **flavors** for:
  - **Google** (Play Store)
  - **Huawei** (AppGallery)
  - **Client** (Customer App)
  - **Driver** (Driver App)
- Allows building **multiple versions of the app** from a single codebase.

### 5️⃣ **APK Packaging Optimization**
- Excludes unnecessary `META-INF` license files (`AL2.0`, `LGPL2.1`)
- Reduces **APK size** and improves **build performance**

---

## 🔹 **Concepts Introduced & Explained**
### 🛠️ **Build Types**
Build types define **how the app is built and signed**.
| **Build Type** | **Purpose** | **ProGuard Enabled?** | **Signing Config** |
|--------------|-------------|----------------|--------------|
| `debug` | Development & testing | ❌ No | `debug.keystore` |
| `release` | Play Store production release | ✅ Yes | `release.keystore` |
| `releaseExternalQA` | Internal QA release | ✅ Yes | `qa.keystore` |

### 🛠️ **Product Flavors**
Flavors allow **multiple app variants** from the same codebase.
| **Flavor** | **Store Dimension** | **App Dimension** | **App ID Suffix** |
|-----------|----------------|-------------|--------------|
| `google` | `STORE` | - | `.google` |
| `huawei` | `STORE` | - | `.huawei` |
| `client` | - | `APP` | `.client` |
| `driver` | - | `APP` | `.driver` |

### 🔑 **APK Signing (V1 & V2 Signing)**
| **Signing Scheme** | **Android Versions** | **Security** |
|----------------|----------------|-----------|
| **V1 (JAR Signing)** | Android 1.0 – 6.0 | 🔴 Weak (can be tampered with) |
| **V2 (APK Signing Scheme v2)** | Android 7.0+ | 🟢 Strong (protects entire APK) |
| **V3 & V4 Signing** | Android 9+ | 🟢 Faster signature verification |

> **Why use both V1 & V2?**  
> 🔹 **V1 ensures backward compatibility** (Android 6.0 and below).  
> 🔹 **V2 improves security & faster installation** (Android 7.0+).

---

## 🚀 **How to Use This Setup**
### 📌 **Running the App**
To build and run different variants:
```sh
# Debug build
./gradlew assembleDebug

# Release build (Play Store)
./gradlew assembleRelease

# QA build (for internal testing)
./gradlew assembleReleaseExternalQa

# Build Google Play version
./gradlew assembleGoogleDebug
