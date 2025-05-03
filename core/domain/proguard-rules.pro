################################################################################################
# ✅ Keep OutCome sealed class and subclasses (Success, Error, Empty)
################################################################################################

# Why?
# - `OutCome` is a sealed class with generic subtypes used across modules (e.g., remotedata, viewmodelcases).
# - It's often returned from `UseCase` classes and passed to UI.
# - R8 might strip or rename them if used through generics or reflection.

# What this rule does:
# - Preserves the class and all members (subclasses like OutCome$Success, etc.)
-keep class com.samir.bluearchitecture.domain.main.result.** { *; }



################################################################################################
# ✅ Keep all UseCase and AsyncUseCase classes
################################################################################################

# Why?
# - `AsyncUseCase` is a generic abstract base class used for structured coroutine-based use cases.
# - Subclasses (like LoginUseCase) rely on reflection or coroutine dispatching and may be stripped.

# What this rule does:
# - Keeps both `AsyncUseCase` and any subclasses or interfaces in the `usecase` package.


# You can use this rule to keep all classes in the usecase package
#-keep class com.samir.bluearchitecture.domain.main.usecase.** { *; }

# Or you can use these lines
-keep class com.samir.bluearchitecture.domain.main.usecase.UseCase { *; }
-keep class com.samir.bluearchitecture.domain.main.usecase.AsyncUseCase { *; }
-keepnames class com.samir.bluearchitecture.domain.main.usecase.**



################################################################################################
# ✅ Preserve ErrorMessageMapper used inside OutCome.Error
################################################################################################

# Why?
# - `ErrorMessageMapper` may be serialized/deserialized (e.g., from API).
# - Also used internally to show mapped error messages in the app.

# What this rule does:
# - Prevents removal or renaming of this class to maintain runtime integrity.
-keep class com.samir.bluearchitecture.domain.main.model.ErrorMessageMapper { *; }