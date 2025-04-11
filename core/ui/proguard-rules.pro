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
# 🔒 Logger ProGuard Rules – Protecting the Logging Utility from R8 Removal & Obfuscation
#
# 📌 Why these rules are needed:
# The `Logger` class is a Kotlin `object` (i.e., a singleton) located in the `core.ui` module.
# It is used across multiple modules (such as presentation and domain) to log debug, info,
# warning, error, and verbose messages, often with reflection-based references to Activities,
# Fragments, ViewModels, or UseCases.
#
# When R8 runs in release builds, it performs aggressive tree-shaking and obfuscation:
#   - It may **remove** classes/methods it assumes are unused (even if used reflectively).
#   - It may **rename** classes (e.g., `Logger` -> `a.a`) which breaks debugging or logging tags.
#
# 🚫 Without these rules, you'll encounter runtime crashes like:
#     "Missing class com.samir.bluearchitecture.ui.utils.logging.Logger"
#     especially when used in `BackPressedHandlerActivity` or anywhere via reflection.
#
# ✅ Solution:
# These rules guarantee that the `Logger` object and its public methods (like `d`, `e`, `i`, `w`, etc.)
# remain available and readable even in release builds.
#
# 🔍 Rule Breakdown:
# 1️⃣ `-keep` ensures the class and its public members aren't removed or renamed.
# 2️⃣ `-keepnames` ensures the class name remains readable (`Logger`, not `a.a`).
####################################################################################################
# ✅ Prevent Logger class from being removed or renamed
-keep class com.samir.bluearchitecture.ui.utils.logging.Logger {
    public *;
}

# ✅ Prevent Logger name from being obfuscated (e.g., Logger -> a.a)
-keepnames class com.samir.bluearchitecture.ui.utils.logging.Logger
####################################################################################################



