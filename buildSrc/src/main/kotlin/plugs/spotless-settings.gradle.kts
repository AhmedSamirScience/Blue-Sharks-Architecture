import com.diffplug.gradle.spotless.SpotlessPlugin

apply<SpotlessPlugin>()

@Suppress("INACCESSIBLE_TYPE")
configure<com.diffplug.gradle.spotless.SpotlessExtension> {

    format("xml") {
        target("**/*.xml")
        prettier(mapOf("prettier" to "2.7.1", "@prettier/plugin-xml" to "2.2.0"))
            .config(
                mapOf(
                    "parser" to "xml",
                    "tabWidth" to 4,
                    "printWidth" to 80,
                    "useTabs" to false,
                    "semi" to true,
                    "singleQuote" to false,
                    "attributeSortOrder" to arrayOf("name", "id", "type"),
                    "selfClosingTags" to arrayOf("br", "img")
                )
            )
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.kt"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.49.0")
            .userData(mapOf("android" to "true", "max_line_length" to "120"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }

    java {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.java"),
                    "exclude" to listOf("**/build/**", "**/buildSrc/**", "**/.*")
                )
            )
        )

        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        // eclipse()
        // The eclipse() method in the spotless plugin is used to apply the Eclipse Code Formatter to Java files.
        // While it's originally associated with Eclipse IDE, it doesn't mean that it only works with Eclipse projects.
        // The Eclipse formatter configuration can be used by other IDEs, including Android Studio.
        googleJavaFormat()
        //method is used as the formatter for Java files.
        // googleJavaFormat is a formatter that follows the Google Java Style Guide.
    }

    kotlinGradle {
        target(
            fileTree(
                mapOf(
                    "dir" to ".",
                    "include" to listOf("**/*.gradle.kts", "*.gradle.kts"),
                    "exclude" to listOf("**/build/**")
                )
            )
        )
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.49.0")
            .userData(mapOf("android" to "true"))
            .editorConfigOverride(mapOf("indent_size" to 2))
    }
}

tasks.named("preBuild") {
    dependsOn("spotlessCheck")
}
tasks.named("preBuild") {
    dependsOn("spotlessApply")
}

/**
 * /*
 *  * Spotless Formatting Violations Documentation
 *  *
 *  * This document provides examples of formatting violations based on the Spotless rules defined in your Gradle configuration.
 *  */
 *
 * // 1. XML Formatting Violations
 *
 * // A. Wrong Attribute Order
 * // ❌ Incorrect (Violates Attribute Order)
 * // <Button id="btn1" name="Submit" type="primary"/>
 * // ✅ Correct (Attributes in Order: name → id → type)
 * // <Button name="Submit" id="btn1" type="primary"/>
 *
 * // B. Missing Newline at EOF
 * // ❌ Incorrect
 * // <LinearLayout>
 * //     <TextView text="Hello"/>
 * // </LinearLayout>⏎(No newline here)
 * // ✅ Correct
 * // <LinearLayout>
 * //     <TextView text="Hello"/>
 * // </LinearLayout>
 * // ⏎(Has a newline at the end)
 *
 * // C. Self-Closing Tags Not Used
 * // ❌ Incorrect: <img></img>
 * // ✅ Correct: <img/>
 *
 * // 2. Kotlin Formatting Violations
 *
 * // A. Wrong Indentation (Tabs Instead of Spaces)
 * // ❌ Incorrect
 * fun main() {
 * 	println("Hello")  // Uses a tab instead of 2 spaces
 * }
 * // ✅ Correct
 * fun main() {
 *   println("Hello")  // Uses 2 spaces
 * }
 *
 * // B. Exceeding Line Length (120 Characters)
 * // ❌ Incorrect
 * fun veryLongFunction() { println("This is a very long line that exceeds the 120-character limit which is not allowed by ktlint") }
 * // ✅ Correct
 * fun veryLongFunction() {
 *   println("This is a very long line that exceeds the 120-character limit " +
 *           "which is not allowed by ktlint")
 * }
 *
 * // C. Trailing Whitespace
 * // ❌ Incorrect
 * fun main() {
 *   println("Hello")  // Extra spaces here ␣␣␣␣
 * }
 * // ✅ Correct
 * fun main() {
 *   println("Hello")
 * }
 *
 * // 3. Java Formatting Violations
 *
 * // A. Wrong Indentation (Tabs Instead of Spaces)
 * // ❌ Incorrect
 * public class Main {
 * 	public static void main(String[] args) {
 * 		System.out.println("Hello");
 * 	}
 * }
 * // ✅ Correct
 * public class Main {
 *   public static void main(String[] args) {
 *     System.out.println("Hello");
 *   }
 * }
 *
 * // B. Trailing Whitespace
 * // ❌ Incorrect
 * public class Main {
 *   public static void main(String[] args) {
 *     System.out.println("Hello");    // Extra spaces ␣␣␣␣
 *   }
 * }
 * // ✅ Correct
 * public class Main {
 *   public static void main(String[] args) {
 *     System.out.println("Hello");
 *   }
 * }
 *
 * // 4. Kotlin Gradle (KTS) Formatting Violations
 *
 * // A. Wrong Indentation (4 Spaces Instead of 2)
 * // ❌ Incorrect
 * plugins {
 *     id("com.android.application")
 * }
 * // ✅ Correct
 * plugins {
 *   id("com.android.application")
 * }
 *
 * // Summary:
 * // - XML: Attribute order, missing newline, self-closing tags
 * // - Kotlin: Indentation, max line length, trailing whitespace, missing EOF newline
 * // - Java: Indentation, trailing whitespace, missing EOF newline
 * // - Gradle (KTS): Indentation
 *
 */