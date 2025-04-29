# ─────────────────────────────────────────────────────────────────────────────
# ProGuard Rule: Keep all classes and members inside the logging package
# ─────────────────────────────────────────────────────────────────────────────

# This rule keeps all classes, fields, and methods inside the
# `com.samir.bluearchitecture.ui.utils.logging` package and its subpackages.
#
# - `-keep class ... { *; }` ensures:
#     • Classes are preserved (not removed or obfuscated).
#     • All methods and fields inside these classes are kept as-is.
#     • Useful for logging utilities that may use reflection or need readable names at runtime.
-keep class com.samir.bluearchitecture.ui.utils.logging.** {
    *;
}