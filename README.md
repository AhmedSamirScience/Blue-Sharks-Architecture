<h1>🔍 Detekt - Static Code Analysis for Kotlin</h1>

<p>
    <strong>Detekt</strong> is a powerful **static code analysis tool** designed for Kotlin projects. It helps enforce best coding practices by identifying **code smells, complexity issues, performance bottlenecks, and potential security risks**.
</p>

<h2>📌 Why Use Detekt?</h2>
<ul>
    <li><strong>Improves Code Quality:</strong> Detects common issues like long functions, nested complexity, and unused code.</li>
    <li><strong>Prevents Code Smells:</strong> Highlights bad coding practices and potential refactoring areas.</li>
    <li><strong>CI/CD Integration:</strong> Ensures code consistency in automated pipelines.</li>
    <li><strong>Custom Rule Support:</strong> Allows the creation of project-specific static analysis rules.</li>
    <li><strong>Multiple Report Formats:</strong> Generates reports in **HTML, XML, JSON, and SARIF**.</li>
    <li><strong>Configurable:</strong> Can be fully customized with a `detekt.yml` configuration file.</li>
</ul>

<h2>🚀 Installation & Setup</h2>

<h3>1️⃣ Apply Detekt Plugin in <code>build.gradle.kts</code></h3>
<pre><code>
plugins {
    id("io.gitlab.arturbosch.detekt") version "1.23.3"
}
</code></pre>

<h3>2️⃣ Using <code>buildSrc</code></h3>
<pre><code>
id(plugs.BuildPlugins.DETEKT)
</code></pre>

<h3>3️⃣ Add Dependency</h3>
<pre><code>
dependencies {
    implementation("io.gitlab.arturbosch.detekt:detekt-gradle-plugin:1.23.3")
}
</code></pre>

<h2>⚙️ Configuration</h2>
<p>
    Detekt provides a **default configuration**, but you can customize it by generating a <code>detekt.yml</code> file.
</p>

<h3>Generate Default Configuration</h3>
<pre><code>
./gradlew detektGenerateConfig
</code></pre>

<h3>Modify <code>detekt.yml</code></h3>
<p>Enable or disable specific rules in your project:</p>

<pre><code>
complexity:
  TooManyFunctions:
    active: true
    threshold: 10  # Maximum number of functions allowed in a class
</code></pre>

<h2>📊 Running Detekt</h2>

<h3>1️⃣ Run Detekt as a Gradle Task</h3>
<pre><code>
./gradlew detekt
</code></pre>

<h3>2️⃣ Run via CLI</h3>
<pre><code>
detekt --input src --config detekt.yml
</code></pre>

<h2>📜 Report Generation</h2>
<p>
    Detekt can generate reports in multiple formats to visualize detected issues.
</p>

<h3>Enable Report Generation in <code>build.gradle.kts</code></h3>
<pre><code>
detekt {
    reports {
        xml.required.set(true)   // XML format (used in CI/CD)
        html.required.set(true)  // User-friendly HTML format
        sarif.required.set(true) // SARIF format (used by GitHub)
    }
}
</code></pre>

<h3>Example HTML Report</h3>
<p>
    After running Detekt, you can find an **HTML report** inside the <code>build/reports/detekt</code> folder.
</p>

<h2>🔄 Auto-correcting Issues</h2>
<p>Some minor issues can be automatically fixed using the <code>--auto-correct</code> flag:</p>
<pre><code>
./gradlew detekt --auto-correct
</code></pre>

<h2>✅ CI/CD Integration</h2>
<p>Detekt can be integrated into **GitHub Actions, GitLab CI, Jenkins, and other CI/CD pipelines**.</p>

<h3>GitHub Actions Example</h3>
<pre><code>
name: Detekt Analysis
on: [push, pull_request]
jobs:
  detekt:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK
        uses: actions/setup-java@v3
        with:
          distribution: 'temurin'
          java-version: '17'
      - name: Run Detekt
        run: ./gradlew detekt
</code></pre>

<h2>🔎 Writing Custom Detekt Rules</h2>
<p>
    If the default rules are not enough, you can create custom **Detekt rules** to match your project’s requirements.
</p>

<h3>Example: Preventing <code>println</code> Statements</h3>
<pre><code>
class NoPrintStatement(config: Config) : Rule(config) {
    override val issue = Issue(
        id = "NoPrintStatement",
        severity = Severity.Warning,
        description = "Avoid using System.out.println",
        debt = Debt.FIVE_MINS
    )

    override fun visitCallExpression(expression: KtCallExpression) {
        if (expression.text.contains("println")) {
            report(CodeSmell(issue, Entity.from(expression), "Use a logger instead of println"))
        }
    }
}
</code></pre>

<h2>📌 Best Practices</h2>
<ul>
    <li>Run Detekt as part of your **pre-commit hook** to enforce code quality.</li>
    <li>Regularly update **detekt.yml** to refine rules as your project evolves.</li>
    <li>Combine **Detekt with Spotless** for automatic formatting.</li>
    <li>Use **Detekt + CI/CD** to maintain a clean and efficient codebase.</li>
</ul>

<h2>📌 Useful Resources</h2>
<ul>
    <li><a href="https://detekt.dev/">Official Detekt Documentation</a></li>
    <li><a href="https://github.com/detekt/detekt">Detekt GitHub Repository</a></li>
</ul>

<h2>📜 License</h2>
<p>Detekt is licensed under the Apache 2.0 License.</p>
