<h1>📌 Dokka - Kotlin Documentation Generator</h1>

<p><strong>Dokka</strong> is an official documentation generation tool for Kotlin. It converts Kotlin source code and KDocs (Kotlin comments) into structured documentation in various formats.</p>

<h2>🚀 What is Dokka?</h2>

<ul>
    <li>Dokka is a documentation engine for Kotlin that generates API documentation similar to JavaDocs but tailored for Kotlin-specific constructs.</li>
    <li>It supports multiple formats, including HTML, Markdown, and Javadoc.</li>
    <li>Integrates seamlessly with Gradle, making it easy to document Kotlin projects.</li>
</ul>

<h2>🔹 Why Use Dokka?</h2>

<ul>
    <li>📄 <strong>Automatic Documentation Generation</strong> - Extracts documentation from KDoc comments in your Kotlin source code.</li>
    <li>🔄 <strong>Supports Multiple Formats</strong> - HTML, Markdown, Javadoc, GFM (GitHub Flavored Markdown), and more.</li>
    <li>📌 <strong>Java Interoperability</strong> - Handles mixed Java/Kotlin codebases efficiently.</li>
    <li>📦 <strong>Plugin Support</strong> - Extendable through plugins to customize documentation output.</li>
    <li>🛠 <strong>Easy Gradle Integration</strong> - Works with Kotlin Gradle projects with minimal configuration.</li>
</ul>

<h2>🛠️ How to Use Dokka in a Gradle Project?</h2>

<h3>1️⃣ Add Dependencies to <code>build.gradle.kts</code></h3>

<pre><code>
dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
    implementation("org.jetbrains.dokka:kotlin-as-java-plugin:1.9.20")
}
</code></pre>

<h3>2️⃣ Apply the Dokka Plugin</h3>

<pre><code>
plugins {
    id("org.jetbrains.dokka") version "1.9.20"
}
</code></pre>

<h3>3️⃣ Configure Dokka (Optional)</h3>

<pre><code>
dokkaHtml {
    outputDirectory.set(buildDir.resolve("dokka"))
    dokkaSourceSets {
        configureEach {
            includes.from("README.md")
        }
    }
}
</code></pre>

<h2>📊 Available Output Formats</h2>

<ul>
    <li><strong>HTML</strong> - Fully styled API documentation.</li>
    <li><strong>Markdown</strong> - Lightweight format for GitHub and wikis.</li>
    <li><strong>Javadoc</strong> - JavaDoc-style documentation.</li>
    <li><strong>GFM</strong> - GitHub Flavored Markdown.</li>
</ul>

<h2>🔗 Official Documentation</h2>

<p>For more details, visit the official <a href="https://kotlinlang.org/docs/dokka-overview.html" target="_blank">Dokka Documentation</a>.</p>
