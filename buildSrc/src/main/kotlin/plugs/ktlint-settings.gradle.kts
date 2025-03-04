import com.android.build.gradle.internal.tasks.factory.dependsOn

val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.48.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

// ktlintFormat checks and formats Kotlin code style, attempting to automatically fix some violations.

tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    // see https://pinterest.github.io/ktlint/install/cli/#command-line-usage for more information
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}

tasks{
    named("preBuild").dependsOn("ktlintFormat")
}

/**
 * /**
 *  * Ktlint Formatting Guide
 *  * This file provides examples of common formatting violations and how to correct them
 *  * based on Ktlint rules.
 *  */
 *
 * package com.example.ktlintguide
 *
 * // Incorrect Indentation
 * fun incorrectIndentation() {
 * println("Hello, World!")
 * }
 *
 * // Correct Indentation
 * fun correctIndentation() {
 *     println("Hello, World!")
 * }
 *
 * // Trailing Spaces - Incorrect
 * val nameWithSpace = "Ahmed"     // Extra spaces at the end (violates ktlint)
 *
 * // Correct
 * val nameWithoutSpace = "Ahmed"
 *
 * // Unnecessary Semicolon - Incorrect
 * val ageWithSemicolon = 30;
 *
 * // Correct
 * val ageWithoutSemicolon = 30
 *
 * // Braces Placement - Incorrect
 * fun incorrectBraces() { if (true) println("Inline statement is not recommended") }
 *
 * // Correct
 * fun correctBraces() {
 *     if (true) {
 *         println("Proper block formatting")
 *     }
 * }
 *
 * // Import Order - Incorrect
 * import kotlin.io.println
 * import java.util.*
 * import kotlin.collections.List
 *
 * // Correct (Alphabetical Order, No Unused Imports)
 * import java.util.*
 * import kotlin.collections.List
 *
 * // Line Length - Incorrect (Exceeds 120 characters)
 * val longString = "This is a very long line that exceeds the maximum recommended length of 120 characters in Ktlint."
 *
 * // Correct (Proper Line Break)
 * val longStringFormatted =
 *     "This is a very long line that exceeds the maximum recommended length of 120 characters in Ktlint."
 *
 * // Spacing Around Operators - Incorrect
 * val incorrectSpacing = 10+ 20
 *
 * // Correct
 * val correctSpacing = 10 + 20
 *
 * // Blank Line Before Closing Brace - Incorrect
 * fun incorrectBlankLine() {
 *     println("Hello")
 *
 * }
 *
 * // Correct
 * fun correctBlankLine() {
 *     println("Hello")
 * }
 *
 * // Function Parameter Formatting - Incorrect
 * fun incorrectFunctionFormat(name: String, age: Int,
 * city: String) { println("Hello $name") }
 *
 * // Correct
 * fun correctFunctionFormat(
 *     name: String,
 *     age: Int,
 *     city: String
 * ) {
 *     println("Hello $name")
 * }
 *
 */