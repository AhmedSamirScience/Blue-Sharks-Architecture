<h1>🛠️ Ktlint Integration & Code Formatting</h1>

<h2>📌 Branch Overview</h2>
<p>This branch introduces <b>Ktlint</b> to enforce consistent Kotlin code formatting across the project.  
By integrating Ktlint, we ensure that the codebase follows a <b>standardized style</b>, making it more readable and maintainable.</p>

<hr>

<h2>🔄 Changes Introduced in This Branch</h2>

<h3>✅ 1. Added Ktlint Plugin</h3>
<ul>
    <li>Configured <b>Ktlint Gradle Plugin</b> in <code>build.gradle.kts</code>:</li>
</ul>
<pre><code>
plugins {
    id(plugs.BuildPlugins.KTLINT) // Enforce Kotlin code style
}
</code></pre>

<h3>✅ 2. Added Ktlint Configuration in <code>.editorconfig</code></h3>
<ul>
    <li>Ensures that all <code>.kt</code> and <code>.kts</code> files follow the defined formatting rules.</li>
</ul>
<pre><code>
[*.{kt,kts}]
ktlint_function_signature_body_expression_wrapping=always
ktlint_standard=enabled
</code></pre>

<h3>✅ 3. Defined Custom Gradle Tasks</h3>
<ul>
    <li><b><code>ktlintCheck</code></b> → Runs <b>ktlint</b> to check formatting issues.</li>
    <li><b><code>ktlintFormat</code></b> → Fixes auto-correctable formatting issues.</li>
    <li>Added <b><code>ktlintFormat</code></b> to <b><code>preBuild</code></b> to ensure formatting before the build.</li>
</ul>

<h3>✅ 4. Removed Assertion Imports</h3>
<ul>
    <li><b>Updated <code>ExampleInstrumentedTest.kt</code></b> to remove unused <code>assert.*</code> statements.</li>
</ul>

<hr>

<h2>📖 What is Ktlint?</h2>
<p>Ktlint is a <b>static code analysis tool</b> for Kotlin that ensures <b>consistent formatting and style</b>.</p>

<h3>🚀 Why Use Ktlint?</h3>
<ul>
    <li>✔ <b>Automatic Code Formatting</b> – Saves time on manual formatting.</li>
    <li>✔ <b>Enforces Consistency</b> – All developers follow the same coding style.</li>
    <li>✔ <b>CI/CD Integration</b> – Prevents merging of poorly formatted code.</li>
    <li>✔ <b>Lightweight</b> – No runtime impact, only used in development.</li>
</ul>

<hr>

<h2>🛠 How to Use Ktlint in this Project</h2>

<h3>🔍 Check Code Formatting</h3>
<p>To check for formatting issues, run:</p>
<pre><code>
./gradlew ktlintCheck
</code></pre>
<p>or on Windows:</p>
<pre><code>
gradlew.bat ktlintCheck
</code></pre>

<h3>✨ Auto-Fix Formatting Issues</h3>
<p>To automatically format the code, run:</p>
<pre><code>
./gradlew ktlintFormat
</code></pre>
<p>or on Windows:</p>
<pre><code>
gradlew.bat ktlintFormat
</code></pre>

<h3>🏗 Run Before Every Build</h3>
<p>Since <b>ktlintFormat</b> is <b>linked to the <code>preBuild</code> task</b>, it runs automatically before building the project.</p>

<hr>

<h2>🔧 Custom Ktlint Configuration</h2>
<p>Ktlint settings are defined in <code>.editorconfig</code>.</p>

<h3>📄 Example <code>.editorconfig</code></h3>
<pre><code>
[*.{kt,kts}]
ktlint_function_signature_body_expression_wrapping=always
ktlint_standard=enabled
</code></pre>
<ul>
    <li><b><code>ktlint_standard=enabled</code></b> → Enables all standard ktlint rules.</li>
    <li><b><code>ktlint_function_signature_body_expression_wrapping=always</code></b> → Ensures proper function signature formatting.</li>
</ul>

<hr>

<h2>🛠 Gradle Configuration for Ktlint</h2>

<h3>🔌 Adding Ktlint as a Dependency</h3>
<pre><code>
val ktlint: Configuration by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.48.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}
</code></pre>

<h3>🏗 Adding Ktlint Formatting Task</h3>
<pre><code>
tasks.register<JavaExec>("ktlintFormat") {
    group = LifecycleBasePlugin.VERIFICATION_GROUP
    description = "Check Kotlin code style and format"
    classpath = ktlint
    mainClass.set("com.pinterest.ktlint.Main")
    jvmArgs("--add-opens=java.base/java.lang=ALL-UNNAMED")
    args(
        "-F",
        "**/src/**/*.kt",
        "**.kts",
        "!**/build/**",
    )
}
</code></pre>

<p><b>🔹 This ensures that every build runs <code>ktlintFormat</code> before proceeding.</b></p>

<hr>

<h2>🔗 Integrating Ktlint with CI/CD (Optional)</h2>
<p>To prevent unformatted code from being merged into <code>main</code>, add <b>Ktlint checks</b> in GitHub Actions:</p>

<h3>📄 Example <code>.github/workflows/ktlint.yml</code></h3>
<pre><code>
name: Ktlint Check

on:
  pull_request:
    branches:
      - main
      - develop

jobs:
  ktlint:
    runs-on: ubuntu-latest
    steps:
      - name: Checkout Code
        uses: actions/checkout@v3

      - name: Set up JDK 17
        uses: actions/setup-java@v3
        with:
          java-version: '17'
          distribution: 'temurin'

      - name: Run Ktlint Check
        run: ./gradlew ktlintCheck
</code></pre>

<p><b>🔹 This will fail any PR that does not follow the Ktlint rules.</b></p>

<hr>

<h2>🏆 Best Practices for Using Ktlint</h2>
<ul>
    <li>✔ <b>Run <code>ktlintCheck</code> before committing code.</b></li>
    <li>✔ <b>Use <code>ktlintFormat</code> to automatically fix style issues.</b></li>
    <li>✔ <b>Enforce Ktlint checks in CI/CD to maintain code quality.</b></li>
</ul>

<hr>

<h2>🎯 Conclusion</h2>
<p>By integrating <b>Ktlint</b>, we have standardized the <b>Kotlin code style</b>, improving readability and maintainability.  
Developers can <b>automatically format their code</b>, reducing manual effort and ensuring a <b>clean, consistent codebase</b>.</p>


<h2>🛠 Happy Coding with Clean & Formatted Kotlin Code! 🚀</h2>
