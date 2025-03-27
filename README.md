<h2>📦 Feature Module Setup — <code>Feature/4.1-Creating-Feature-Module</code></h2>

<p>This branch introduces the creation of a new modular feature as part of the clean and scalable Android architecture. The goal is to isolate features into their own modules for better maintainability, scalability, and team collaboration.</p>

<hr />

<h3>📁 What Was Added?</h3>
<ul>
  <li>✅ <strong>New Feature Module:</strong> Created under the standard Gradle setup in <code>feature/</code> directory.</li>
  <li>✅ <code><strong>build.gradle.kts</strong></code> — Configured with required plugins (e.g., <code>com.android.library</code> or <code>com.android.application</code>).</li>
  <li>✅ <code><strong>AndroidManifest.xml</strong></code> — Base manifest to declare the module's components if needed.</li>
  <li>✅ <code><strong>proguard-rules.pro</strong></code> — Prepared for future optimization/minification rules.</li>
  <li>✅ <code><strong>.gitignore</strong></code> — Ensures IDE and build files are not committed accidentally.</li>
  <li>✅ <strong>Test Files:</strong>
    <ul>
      <li><code>ExampleUnitTest.kt</code></li>
      <li><code>ExampleInstrumentedTest.kt</code></li>
    </ul>
  </li>
</ul>

<hr />

<h3>🧠 Why Modularization?</h3>
<p>Using feature-based modules enables a clean and maintainable codebase, especially in large-scale enterprise apps.</p>
<ul>
  <li>🔄 <strong>Separation of Concerns:</strong> Each feature works independently — makes it easier to read and maintain.</li>
  <li>📦 <strong>Reusability:</strong> Modules can be reused or even shipped independently if needed.</li>
  <li>🧪 <strong>Focused Testing:</strong> Run tests on one feature without building the entire app.</li>
  <li>👥 <strong>Parallel Development:</strong> Enables multiple team members to work on different modules simultaneously.</li>
  <li>📈 <strong>Faster Build Times:</strong> Only the modified modules are built, improving developer productivity.</li>
</ul>

<hr />

<h3>⚙️ How to Include This Module</h3>

<ol>
  <li>
    <strong>Include the module in <code>settings.gradle.kts</code>:</strong>
    <pre><code>include(":feature:your_feature_name")</code></pre>
  </li>
  <li>
    <strong>Configure Gradle Plugin:</strong>
    <pre><code>
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}
    </code></pre>
  </li>
  <li>
    <strong>Define Android namespace in <code>build.gradle.kts</code>:</strong>
    <pre><code>namespace = "com.yourpackage.feature.your_feature_name"</code></pre>
  </li>
  <li>
    <strong>Connect dependencies using <code>implementation(project(...))</code>:</strong>
    <pre><code>implementation(project(":core:designsystem"))</code></pre>
  </li>
</ol>

<hr />

<h3>📁 Folder Structure Example</h3>

<pre>
feature/
└── your_feature_name/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   ├── res/
    │   │   └── AndroidManifest.xml
    │   ├── test/
    │   │   └── ExampleUnitTest.kt
    │   └── androidTest/
    │       └── ExampleInstrumentedTest.kt
    ├── build.gradle.kts
    ├── proguard-rules.pro
    └── .gitignore
</pre>

<hr />

<h3>📝 Best Practices</h3>
<ul>
  <li>Use <strong>feature-based naming</strong> (e.g., <code>feature:login</code>, <code>feature:profile</code>) for clarity.</li>
  <li>Use <strong>dependency inversion</strong> to communicate between modules via interfaces or shared contracts.</li>
  <li>Minimize tight coupling by using <code>:core</code> modules for shared code.</li>
  <li>Add unit and UI tests to maintain robustness within each module.</li>
</ul>

<hr />

<h3>🔗 Future Improvements</h3>
<ul>
  <li>🔒 Add Hilt or Koin for DI in this module.</li>
  <li>🎨 Integrate shared theming via <code>:core:ui</code>.</li>
  <li>🧪 Add ViewModel and Repository with proper architecture layers.</li>
</ul>
