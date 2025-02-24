<h1>Gradle Versions Plugin Integration</h1>

<p>The <strong>Gradle Versions Plugin</strong> is a tool that helps manage and update dependencies in a Gradle-based project. It provides a way to check for outdated dependencies and ensures that your project remains up to date with the latest stable versions.</p>

<h2>📌 Plugin Details</h2>
<ul>
    <li><strong>Library:</strong> <code>com.github.ben-manes:gradle-versions-plugin</code></li>
    <li><strong>Version:</strong> <code>0.51.0</code></li>
    <li><strong>Purpose:</strong> Automatically checks for updates of dependencies in a Gradle project.</li>
    <li><strong>Usage Scope:</strong> Works with Kotlin and Java projects.</li>
</ul>

<h2>🔧 How It Works</h2>
<p>The Gradle Versions Plugin analyzes all declared dependencies and determines if a newer version is available. It categorizes updates into:</p>
<ul>
    <li><strong>Up-to-date</strong> - Dependencies that are on their latest stable version.</li>
    <li><strong>Gradle Updates</strong> - Suggests updates for the Gradle wrapper.</li>
    <li><strong>Exceeding the Latest Stable</strong> - When a dependency is newer than its stable release.</li>
</ul>

<h2>🛠️ Installation</h2>
<p>To enable the Gradle Versions Plugin in your project, add the following configuration:</p>

<pre><code class="language-kotlin">// build.gradle.kts (Root or Module level)
plugins {
    id("com.github.ben-manes.versions") version "0.51.0"
}
</code></pre>

<p>Or, if using a centralized <code>buildSrc</code> approach:</p>
<pre><code class="language-kotlin">id(plugs.BuildPlugins.UPDATE_DEPS_VERSIONS)</code></pre>

<h2>🚀 Running the Dependency Update Check</h2>
<p>Once installed, you can check for outdated dependencies by running:</p>

<pre><code>./gradlew dependencyUpdates</code></pre>

<p>This will generate a report showing available updates for dependencies and Gradle itself.</p>

<h2>📄 Example Output</h2>
<pre><code>Task :dependencyUpdates
The following dependencies have newer versions:
- org.jetbrains.kotlin:kotlin-stdlib [1.8.10 -> 1.9.0]
- androidx.core:core-ktx [1.6.0 -> 1.7.0]
</code></pre>

<h2>🔍 Additional Features</h2>
<ul>
    <li>Filters pre-release versions (<code>--revision=release</code>)</li>
    <li>Outputs report in JSON or XML format for CI/CD integration</li>
    <li>Can ignore specific dependencies from being updated</li>
</ul>

<h2>🎯 Why Use This Plugin?</h2>
<ul>
    <li>Reduces manual effort in tracking updates.</li>
    <li>Ensures that security patches and bug fixes are applied.</li>
    <li>Helps maintain project stability by providing update insights.</li>
</ul>

<h2>📚 Learn More</h2>
<p>For more details, visit the official repository: <a href="https://github.com/ben-manes/gradle-versions-plugin">Gradle Versions Plugin</a>.</p>
