<h2>📂 Module File Breakdown</h2>

<p>This section explains the role of each file and how it contributes to the Android module’s functionality, styling, or structure.</p>

<hr>

<h3>📄 XML Drawables</h3>
<ul>
  <li><code>all_rect_crn1_strblacksldandgray.xml</code> — Custom shape with black stroke and gray fill (1dp corner radius).</li>
  <li><code>all_rect_crn1_strblacksldmainclr.xml</code> — Similar to above but uses the app’s main color as the fill.</li>
  <li><code>spinner_selector_btn_maincolorgaryblackanddark.xml</code> — State selector for a spinner button using grayscale and dark modes.</li>
</ul>

<hr>

<h3>🎨 Resource Files</h3>
<ul>
  <li><code>colors.xml</code> — Centralized color definitions for light/dark themes or branding.</li>
  <li><code>strings.xml</code> — String resources to support localization and cleaner code structure.</li>
</ul>

<hr>

<h3>🧩 Fonts</h3>
<p><strong>Roboto Font Family</strong> — Used for custom typography across the app:</p>
<ul>
  <li><code>roboto_black.ttf</code>, <code>roboto_black_italic.ttf</code></li>
  <li><code>roboto_bold.ttf</code>, <code>roboto_bold_italic.ttf</code></li>
  <li><code>roboto_medium.ttf</code>, <code>roboto_medium_italic.ttf</code></li>
  <li><code>roboto_regular.ttf</code>, <code>roboto_light.ttf</code>, <code>roboto_thin.ttf</code></li>
  <li><code>roboto_italic.ttf</code>, <code>roboto_light_italic.ttf</code>, <code>roboto_thin_italic.ttf</code></li>
</ul>

<hr>

<h3>🧠 Naming Convention Documents</h3>
<p>These XMLs are used as internal references or documentation:</p>
<ul>
  <li><code>naming_convenstion.xml</code> — Describes general naming guidelines.</li>
  <li><code>naming_convention_for_short_name_of_android_widgets.xml</code> — Covers short name rules (e.g., <code>btnLogin</code>).</li>
  <li><code>naming_convention_principle.xml</code> <em>(appears twice)</em> — May need cleanup if duplicated by mistake.</li>
</ul>

<hr>

<h3>📜 Manifest & Gradle</h3>
<ul>
  <li><code>AndroidManifest.xml</code> — Declares components like activities, services, and permissions for this module.</li>
  <li><code>build.gradle.kts</code> — Kotlin DSL build configuration for this module.</li>
</ul>

<hr>

<h3>📦 Kotlin Classes</h3>
<ul>
  <li><code>DependenciesProvider.kt</code> — Likely manages dependency injection or provides centralized access to dependencies.</li>
</ul>

<hr>

<h3>🧪 Test Files</h3>
<ul>
  <li><code>ExampleInstrumentedTest.kt</code> — Android-specific tests (run on device/emulator).</li>
  <li><code>ExampleUnitTest.kt</code> — JVM unit tests that run on the local machine.</li>
</ul>

<hr>

