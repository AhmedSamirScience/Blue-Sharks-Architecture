<h1>🚀 Build-Src Module - Gradle Plugin Integrations</h1>

<p>This branch (<strong>1.0-ArchitecturePhase/Development/01-Build-Src-Module</strong>) introduces structured Gradle configuration for <strong>plugin management</strong>, <strong>code quality enforcement</strong>, and <strong>dependency management</strong>. Below is a breakdown of all the plugins integrated into this branch.</p>

<hr>

<h2>📌 Table of Contents</h2>
<ul>
    <li><a href="#dokka">📖 Dokka - Kotlin Documentation Generator</a></li>
    <li><a href="#gradle-versions">🔄 Gradle Versions Plugin - Dependency Updates</a></li>
    <li><a href="#ktlint">🛠 Ktlint - Kotlin Code Formatter</a></li>
    <li><a href="#spotless">🎯 Spotless - Code Formatting</a></li>
    <li><a href="#prettier">🎨 Prettier - JavaScript/HTML/CSS Formatter</a></li>
    <li><a href="#detekt">⚠️ Detekt - Kotlin Static Analysis</a></li>
</ul>

<hr>

<h2 id="dokka">📖 1. Dokka - Kotlin Documentation Generator</h2>

<p><strong>Dokka</strong> is used to generate structured documentation from Kotlin code comments.</p>

<h3>🚀 Why Use Dokka?</h3>
<ul>
    <li>📄 Converts KDoc comments into HTML, Markdown, and Javadoc.</li>
    <li>🔄 Works with mixed Java-Kotlin projects.</li>
    <li>📦 Supports custom output formats and plugins.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>🛠️ When maintaining a <strong>large project</strong> with complex APIs.</li>
    <li>📚 When generating <strong>open-source documentation</strong>.</li>
</ul>

<h3>🛠️ How to Use?</h3>
<pre><code>
dependencies {
    implementation("org.jetbrains.dokka:dokka-gradle-plugin:1.9.20")
    implementation("org.jetbrains.dokka:kotlin-as-java-plugin:1.9.20")
}
</code></pre>

<h3>🔗 Official Docs:</h3>
<p><a href="https://kotlinlang.org/docs/dokka-overview.html" target="_blank">Dokka Documentation</a></p>

<hr>

<h2 id="gradle-versions">🔄 2. Gradle Versions Plugin - Dependency Updates</h2>

<p>The <strong>Gradle Versions Plugin</strong> keeps track of outdated dependencies and suggests updates.</p>

<h3>🚀 Why Use It?</h3>
<ul>
    <li>🔍 Automates dependency update checks.</li>
    <li>📊 Reduces security risks from outdated libraries.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>📆 When working on a <strong>long-term project</strong>.</li>
    <li>🚀 When preparing for a <strong>major version upgrade</strong>.</li>
</ul>

<h3>🛠️ How to Use?</h3>
<pre><code>
dependencies {
    implementation("com.github.ben-manes:gradle-versions-plugin:0.51.0")
}
</code></pre>

<h3>🔗 Official Docs:</h3>
<p><a href="https://github.com/ben-manes/gradle-versions-plugin" target="_blank">Gradle Versions Plugin</a></p>

<hr>

<h2 id="ktlint">🛠 3. Ktlint - Kotlin Code Formatter</h2>

<p><strong>Ktlint</strong> is a Kotlin linter and formatter that enforces a consistent coding style.</p>

<h3>🚀 Why Use Ktlint?</h3>
<ul>
    <li>📌 Auto-formats Kotlin code.</li>
    <li>📊 Helps maintain coding consistency.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>⚡ When multiple developers are contributing.</li>
    <li>💡 When following a strict <strong>Kotlin coding style guide</strong>.</li>
</ul>

<h3>🛠️ How to Use?</h3>
<pre><code>
id("org.jlleitschuh.gradle.ktlint") version "11.3.2"
</code></pre>

<h3>🔗 Official Docs:</h3>
<p><a href="https://github.com/pinterest/ktlint" target="_blank">Ktlint Documentation</a></p>

<hr>

<h2 id="spotless">🎯 4. Spotless - Code Formatting</h2>

<p><strong>Spotless</strong> is a Gradle plugin for formatting Java, Kotlin, and XML files.</p>

<h3>🚀 Why Use Spotless?</h3>
<ul>
    <li>📄 Supports multiple languages.</li>
    <li>🔧 Enforces consistent code formatting.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>🛠️ When a project contains **both Java and Kotlin code**.</li>
</ul>

<h3>🛠️ How to Use?</h3>
<pre><code>
dependencies {
    implementation("com.diffplug.spotless:spotless-plugin-gradle:6.22.0")
}
</code></pre>

<h3>🔗 Official Docs:</h3>
<p><a href="https://github.com/diffplug/spotless" target="_blank">Spotless Documentation</a></p>

<hr>

<h2 id="prettier">🎨 5. Prettier - JavaScript/HTML/CSS Formatter</h2>

<p><strong>Prettier</strong> enforces a consistent style for JavaScript, TypeScript, and CSS.</p>

<h3>🚀 Why Use Prettier?</h3>
<ul>
    <li>📝 Supports JS, TypeScript, HTML, and CSS.</li>
    <li>✅ Works with Spotless for full project consistency.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>📌 When working on frontend-heavy applications.</li>
</ul>

<h3>🔗 Official Docs:</h3>
<p><a href="https://prettier.io/" target="_blank">Prettier Documentation</a></p>

<hr>

<h2 id="detekt">⚠️ 6. Detekt - Kotlin Static Code Analysis</h2>

<p><strong>Detekt</strong> helps identify code smells and potential issues in Kotlin.</p>

<h3>🚀 Why Use Detekt?</h3>
<ul>
    <li>📊 Identifies security vulnerabilities.</li>
    <li>🔄 Enforces clean code principles.</li>
</ul>

<h3>📌 When to Use?</h3>
<ul>
    <li>📊 When improving **code maintainability**.</li>
</ul>

<h3>🛠️ How to Use?</h3>
<pre><code>
dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")
}
</code></pre>

<h3>🔗 Official Docs:</h3>
<p><a href="https://detekt.dev/" target="_blank">Detekt Documentation</a></p>

<hr>

<h2>🎯 Summary</h2>

<p>This branch introduces several Gradle plugins to improve:</p>
<ul>
    <li>📄 Automated documentation with <strong>Dokka</strong>.</li>
    <li>🔄 Dependency tracking using <strong>Gradle Versions Plugin</strong>.</li>
    <li>🛠 Code formatting via <strong>Ktlint</strong> and <strong>Spotless</strong>.</li>
    <li>🎨 JavaScript formatting via <strong>Prettier</strong>.</li>
    <li>⚠️ Static code analysis with <strong>Detekt</strong>.</li>
</ul>

<p>🚀 <strong>Ensuring better code quality, maintainability, and collaboration.</strong></p>
