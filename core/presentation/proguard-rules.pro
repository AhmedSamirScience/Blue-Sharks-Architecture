# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

####################################################################################################
# 📌 WHY THIS IS NEEDED:
# - DataBindingComponent is a central part of the Android Data Binding framework.
# - When using R8 in multiple modules (with `minifyEnabled true`), each module may
#   independently obfuscate this interface to the same short name (e.g., `a.a`).
# - This leads to "Type a.a is defined multiple times" during the final app merge step.
#
# 🚫 Without this rule, R8 renames the class (e.g., to `a.a`) in both modules.
# ✅ With this rule, it retains the original name, avoiding duplication across modules.
#
# 🧩 Applies to:
# - Any module using Android DataBinding (core, feature, presentation, etc.)
# - Especially important in multi-module projects with release R8 enabled per module.
#
# 💡 Best Practice:
# - Add this rule to each module using DataBinding and minification.
# - Or disable `minifyEnabled` in libraries and obfuscate only in the app module.
#
# 🔐 Final note:
# This does not stop shrinking or removing unused classes — it only preserves the name.
# ----------------------------------------------------------------------------------
#-keep class androidx.databinding.DataBindingComponent { *; }
####################################################################################################


####################################################################################################
# 🧩 BaseActivity – Retain for Navigation and Lifecycle Safety
####################################################################################################
# ✅ This rule tells R8 to:
#   - Preserve the `BaseActivity` class
#   - Keep all its members (methods and fields)
#
# 📌 WHY IS THIS NEEDED?
# - `BaseActivity` is likely subclassed across multiple modules.
# - If R8 does not see direct usage (e.g., via reflection or only subclass usage in another module),
#   it may strip it completely, causing runtime crashes.
#
# 🚫 Without this rule, you may get:
#   - java.lang.ClassNotFoundException or NoClassDefFoundError
#   - Errors when using Activity-level reflection
#
# ✅ With this rule:
#   - The class name and all its members (like lifecycle methods, view binding logic, etc.) are preserved
#
# 💡 TIP: If you want to keep only constructors and lifecycle methods (for more optimization), use a narrower rule.
-keep public class com.samir.bluearchitecture.presentation.activity.BaseActivity { *; }
####################################################################################################

####################################################################################################
# 🧩 BaseFragment – Preserve for Navigation XML and Reflection Use
####################################################################################################
# ✅ This rule ensures:
#   - The `BaseFragment` class is not stripped or renamed
#   - All methods inside `BaseFragment` are retained
#
# 📌 WHY IS THIS NEEDED?
# - Android Navigation Component and `<fragment>` tags refer to fragment classes **by name (string)**.
# - BaseFragment methods may be called via reflection (e.g., lifecycle, state restore, etc.)
#
# 🚫 Without this rule:
#   - R8 might remove BaseFragment, causing crashes during layout inflation or FragmentContainerView loading.
#
# ✅ With this rule:
#   - The class and methods like `onCreateView`, `onViewCreated`, etc., remain intact for runtime use
#
# 💡 TIP: For a more optimized version, consider keeping only lifecycle methods instead of `{ *; }`.
-keep class com.samir.bluearchitecture.presentation.fragment.BaseFragment { *; }
####################################################################################################

####################################################################################################
# 🧩 BaseViewModel – Needed When Using Reflection or Custom DI
####################################################################################################
# ✅ This rule preserves:
#   - The `BaseViewModel` class
#   - All its members
#
# 📌 WHY IS THIS NEEDED?
# - ViewModels may be instantiated via `ViewModelProvider` or injected via Hilt, both of which use reflection.
# - If `BaseViewModel` contains abstract lifecycle methods like `start()` or `stop()`, they must be retained.
#
# 🚫 Without this rule:
#   - You may see: `ViewModelProvider.Factory` unable to create your subclass due to missing supertype
#   - Lifecycle-aware ViewModel methods might silently break
#
# ✅ With this rule:
#   - R8 keeps the class and all method implementations (e.g., initialization, cleanup)
#
# 💡 TIP: If you prefer to reduce the keep surface, target only lifecycle methods or constructors.
-keep class com.samir.bluearchitecture.presentation.viewModel.BaseViewModel { *; }
####################################################################################################