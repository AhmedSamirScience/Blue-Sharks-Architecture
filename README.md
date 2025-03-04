# 📌 Core Modules - Clean Architecture Setup

## 🌱 Branch Name
`1.0-ArchitecturePhase/Feature/2.1-Add-Core-Modules`

## 🚀 Overview
This branch introduces the foundational **core modules** for implementing **Clean Architecture**. The project is now divided into three distinct layers:

1️⃣ **Data Layer** (`data` module) - Handles data sources, API calls, and local storage.
2️⃣ **Domain Layer** (`domain` module) - Contains business logic and use cases.
3️⃣ **Presentation Layer** (`presentation` module) - Manages UI logic and user interactions.

This modularization ensures **scalability, maintainability, and separation of concerns**. ✅

---

## 📂 Project Structure

### **📁 Data Layer (`data` Module)**
Responsible for handling all **data sources**, including:
- API interactions (Retrofit, OkHttp, etc.)
- Local databases (Room, SharedPreferences, etc.)
- Repositories to provide data to the domain layer

📌 **Files & Configurations**
```
├── data/
│   ├── build.gradle.kts  // Module-specific dependencies
│   ├── AndroidManifest.xml  // Necessary for defining the module
│   ├── ExampleUnitTest.kt  // Placeholder test file
│   ├── ExampleInstrumentedTest.kt  // Placeholder instrumented test
│   ├── consumer-rules.pro  // ProGuard consumer rules
│   ├── proguard-rules.pro  // ProGuard configurations
```

---

### **📁 Domain Layer (`domain` Module)**
This layer contains **business logic** and acts as an intermediary between **data** and **presentation** layers.

📌 **Key Responsibilities:**
- Defines **Use Cases** (Application-specific business rules)
- Provides **abstractions** for data sources
- Operates independently of external frameworks

📌 **Files & Configurations**
```
├── domain/
│   ├── build.gradle.kts
│   ├── AndroidManifest.xml
│   ├── ExampleUnitTest.kt
│   ├── ExampleInstrumentedTest.kt
│   ├── consumer-rules.pro
│   ├── proguard-rules.pro
```

---

### **📁 Presentation Layer (`presentation` Module)**
Handles **UI logic** and connects with the domain layer via **ViewModels**.

📌 **Key Responsibilities:**
- Uses **ViewModel, LiveData, and StateFlow** to manage UI state
- Contains **UI-related business logic**
- Completely independent from the data layer

📌 **Files & Configurations**
```
├── presentation/
│   ├── build.gradle.kts
│   ├── AndroidManifest.xml
│   ├── ExampleUnitTest.kt
│   ├── ExampleInstrumentedTest.kt
│   ├── consumer-rules.pro
│   ├── proguard-rules.pro
```

---

## 🏗 Root Project Configurations

📁 **Other Files Added**
```
├── settings.gradle.kts  // Includes the new modules
├── .gitignore  // Ensures proper version control
```

📌 **settings.gradle.kts** Example:
```kotlin
rootProject.name = "MyApplication"
include(":data")
include(":domain")
include(":presentation")
```

---

## 🛠 Clean Architecture Implementation
This structure follows **Uncle Bob’s Clean Architecture Principles**:
✅ Separation of concerns  
✅ Modularization  
✅ Scalable and maintainable architecture  

---

## 📖 Reference
📖 [Clean Architecture by Uncle Bob](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)

---

