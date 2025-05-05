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
#    void onCreate(...);
#    void onCreateView(...);
#    void onViewCreated(...);
#    void onStart(...);
#    void onResume(...);
#    void onPause(...);
#    void onStop(...);
#    void onDestroyView(...);
#    void onDestroy(...);
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
-keep class **.*ViewModel {
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
-keep class **.databinding.** { *; }
################################################################################################

################################################################################################
# 👁️‍🗨️ RULE: Preserve Class Names in `viewmodelcases` Package
################################################################################################

# 📌 Why:
# - Classes inside this package (e.g., ViewModels, Fragments) may be instantiated via reflection,
#   XML (e.g., FragmentContainerView), or Hilt ViewModel factory.
# - These features require the class name to remain intact, but they do not care about field/method names.

# ✅ What This Does:
# - Keeps the full class name (e.g., com.samir.bluearchitecture.viewmodelcases.SomeViewModel)
# - Allows shrinking: unused classes are still removed
# - Allows member obfuscation: method/field names can still be shortened for optimization
# - Lightweight: does NOT prevent shrinking or R8 optimization

# ❌ Does NOT:
# - Prevent internal members (fields/methods) from being obfuscated or stripped if unused
# - Prevent removed classes if they're not reachable in the app

# 💡 Use this when:
# - You want to preserve class names for reflection or diagnostics
# - But still want full R8 optimizations inside those classes

# 📦 Target Scope:
# - All classes under `com.samir.bluearchitecture.viewmodelcases` and its subpackages

-keepnames class com.samir.bluearchitecture.viewmodelcases.**
################################################################################################
