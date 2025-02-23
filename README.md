<h1>Spotless & Prettier Integration</h1>

<p>This repository integrates <strong>Spotless</strong> and <strong>Prettier</strong> to ensure consistent code formatting across the project.</p>
<p>These tools help maintain clean, readable, and standardized code automatically.</p>

<hr>

<h2>📌 What is Spotless?</h2>

<p><strong>Spotless</strong> is a powerful Gradle plugin for formatting code automatically.</p>
<p>It helps enforce code style rules for various languages like <strong>Kotlin, Java, XML, Gradle scripts</strong>, and more.</p>

<h3>✅ Features of Spotless:</h3>
<ul>
    <li>Formats Kotlin, Java, XML, and Gradle files.</li>
    <li>Integrates with <strong>Ktlint</strong> for Kotlin formatting.</li>
    <li>Supports Google Java Format, Eclipse Formatter, and more.</li>
    <li>Removes trailing whitespaces and ensures proper indentation.</li>
    <li>Enforces line endings and file-ending newlines.</li>
</ul>

<h3>🛠️ How Spotless Works:</h3>
<p>Spotless applies formatting rules on specific file types and ensures consistency across the codebase.</p>
<p>It uses <code>spotlessCheck</code> to verify formatting and <code>spotlessApply</code> to auto-fix formatting issues.</p>

<hr>

<h2>📌 What is Prettier?</h2>

<p><strong>Prettier</strong> is a widely used opinionated code formatter that ensures consistent style across different file types.</p>
<p>It supports JavaScript, TypeScript, HTML, XML, and more.</p>

<h3>✅ Features of Prettier:</h3>
<ul>
    <li>Automatically formats code for better readability.</li>
    <li>Supports multiple languages including JSON, YAML, and HTML.</li>
    <li>Works with plugins like <code>@prettier/plugin-xml</code> for XML formatting.</li>
    <li>Eliminates unnecessary spaces and enforces proper indentation.</li>
    <li>Prevents formatting debates by following a unified style.</li>
</ul>

<hr>

<h2>🚀 Configuration in Gradle</h2>

<h3>🔧 Applying Spotless Plugin</h3>
<pre><code>
plugins {
    id("com.diffplug.spotless") version "6.22.0"
}
</code></pre>

<h3>🎯 Spotless Configuration</h3>
<pre><code>
spotless {
    format("xml") {
        target("**/*.xml")
        prettier(mapOf("prettier" to "2.7.1", "@prettier/plugin-xml" to "2.2.0"))
            .config(mapOf(
                "parser" to "xml",
                "tabWidth" to 4,
                "printWidth" to 80
            ))
        indentWithSpaces(4)
        trimTrailingWhitespace()
        endWithNewline()
    }

    kotlin {
        target("**/*.kt")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        ktlint("0.49.0")
            .userData(mapOf("android" to "true", "max_line_length" to "120"))
    }

    java {
        target("**/*.java")
        trimTrailingWhitespace()
        indentWithSpaces()
        endWithNewline()
        googleJavaFormat()
    }
}
</code></pre>

<hr>

<h2>⚡ Running Spotless</h2>

<table>
    <thead>
        <tr>
            <th>Command</th>
            <th>Description</th>
        </tr>
    </thead>
    <tbody>
        <tr>
            <td><code>./gradlew spotlessCheck</code></td>
            <td>Checks if code is formatted correctly.</td>
        </tr>
        <tr>
            <td><code>./gradlew spotlessApply</code></td>
            <td>Auto-formats the code.</td>
        </tr>
    </tbody>
</table>

<hr>

<h2>📌 Conclusion</h2>

<p>Integrating <strong>Spotless</strong> and <strong>Prettier</strong> ensures a clean and standardized codebase.</p>
<p>It helps maintain code quality, enforces best practices, and saves time spent on manual formatting.</p>

<h2>🚀 Happy Coding!</h2>
