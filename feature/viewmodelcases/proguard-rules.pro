# ----------------------------------------------------------------------------------
# ✅ KEEP RULE: Prevent obfuscation of the DataBindingComponent interface
# ----------------------------------------------------------------------------------
# This rule tells R8 / ProGuard to keep the `androidx.databinding.DataBindingComponent`
# class and all its members (methods, fields) without renaming or removing them.
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
-keep class androidx.databinding.DataBindingComponent { *; }
####################################################################################################



################################################################################################
# 👇 Fragment Rule: Keep Fragment subclasses used by Navigation XML or FragmentContainerView
################################################################################################

# Why?
# R8 removes unused classes to shrink your APK, and this includes Fragments that are never
# directly instantiated in code but are instead referenced via:
#   - XML navigation graphs
#   - FragmentContainerView / <fragment> tags in XML
#   - FragmentManager.instantiate() via class name (reflection)
#
# What this rule does:
# - Keeps all classes under "com.samir.bluearchitecture.viewmodelcases" that have names
#   ending with "Fragment" and their full structure.
# - Retains their public constructors and lifecycle methods that are typically invoked
#   via the Fragment lifecycle (onCreate, onCreateView, onViewCreated).
#
# When is it needed?
# ✅ Required in a multi-module app if the fragment is only used in XML or through reflection.
# ❌ Not usually required if fragments are instantiated directly in Kotlin code and not renamed.
#
# This rule helps avoid:
# - java.lang.ClassNotFoundException for fragment class
# - android.view.InflateException during layout inflation
#
# Safe & minimal usage — keeps essential behavior without over-exposing internal logic.
-keep class com.samir.bluearchitecture.viewmodelcases.**.*Fragment {
    public <init>();
#    void onAttach(...);
    void onCreate(...);
    void onCreateView(...);
    void onViewCreated(...);
    void onStart(...);
    void onResume(...);
    void onPause(...);
    void onStop(...);
    void onDestroyView(...);
    void onDestroy(...);
#    void onDetach(...);
#    void onSaveInstanceState(...);
#    void onViewStateRestored(...);
}
####################################################################################################



################################################################################################
# 👇 ViewModel Rule: Keep ViewModel subclasses used with reflection, Hilt, or SavedStateHandle
################################################################################################

# Why?
# ViewModel classes are sometimes:
#   - Created using reflection by AndroidX ViewModelProvider
#   - Instantiated with SavedStateHandle (which uses reflection to inject it)
#   - Used by Dagger Hilt for dependency injection (@HiltViewModel)
#
# What this rule does:
# - Keeps ViewModel subclasses within "com.samir.bluearchitecture.viewmodelcases"
# - Preserves their no-argument public constructor (or Hilt-assisted constructor)
#
# When is it needed?
# ✅ Required when:
#   - ViewModel is used via reflection (SavedStateViewModelFactory, Hilt, etc.)
#   - The ViewModel is in a dynamic-feature or feature module
# ❌ Not needed if R8 can trace the constructor directly and class isn’t renamed
#
# Helps prevent:
# - java.lang.InstantiationException
# - java.lang.NoSuchMethodException
# - Hilt runtime errors during ViewModel injection
#
# Light and safe — avoids keeping all ViewModel code, just essentials.
-keep class com.samir.bluearchitecture.viewmodelcases.**.*ViewModel {
    public <init>();
}
####################################################################################################



################################################################################################
# 👇 DataBinding Rule: Keep DataBinding-generated classes for the viewmodelcases feature module
################################################################################################

# Why?
# - The Data Binding compiler generates binding classes like ActivityBasicRdactivityBinding
#   inside the `databinding` package.
# - When R8 obfuscation is enabled, these classes may be renamed to short names like `a.a`.
# - If multiple modules contain DataBinding classes and are obfuscated separately, this causes:
#     ❌ `Type a.a is defined multiple times` error at merge/dex time.

# What this rule does:
# - Keeps all classes under `com.samir.bluearchitecture.viewmodelcases.databinding`
# - Prevents renaming, shrinking, and removal of their fields and methods
# - Ensures these classes remain accessible and uniquely named across modules

# When is it needed?
# ✅ Required in multi-module projects when:
#   - DataBinding is enabled in multiple feature modules
#   - R8/ProGuard is enabled for each module
# ❌ Not needed if you disable minification in all but the final app module

# Helps prevent:
# - Duplicate class errors during build
# - ClassNotFoundException or NoSuchMethodException at runtime
# - Debugging nightmares due to stripped/mangled binding classes

# Safe and minimal — scoped to DataBinding only for viewmodelcases module.
-keep class com.samir.bluearchitecture.viewmodelcases.databinding.** { *; }
################################################################################################