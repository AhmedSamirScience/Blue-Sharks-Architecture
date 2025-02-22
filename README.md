# 🚀 Feature: Login Module

**Branch:** `1.0-ArchitecturePhase/Feature/1.3-Feature-Login-Modulee`

---

## 📌 Overview
This branch introduces the **Login Module** as part of the project's modularization effort. The goal is to separate authentication-related logic into an independent module, improving **scalability, maintainability, and reusability**.

### 🔹 **Key Features of This Branch**
- ✅ **Created a standalone `feature:login` module**.
- ✅ **Integrated it into the Gradle build system** (`settings.gradle.kts`).
- ✅ **Ensured modular dependency management** via `DependenciesProvider.kt`.
- ✅ **Set up Java 17/Kotlin JVM 17 compatibility**.
- ✅ **Added ProGuard rules for the module**.
- ✅ **Ensured testability with instrumentation support (`AndroidJUnitRunner`)**.

---

## 🔹 **How to Use This Module**
### **1️⃣ Running the Login Module**
```sh
./gradlew :feature:login:assembleDebug
