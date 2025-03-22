<h2>📁 UI & Architecture Utilities</h2>
<p>This module provides essential base classes and utilities to streamline Android development, including base UI components, lifecycle helpers, keyboard handling, delayed execution, and more.</p>

<hr />

<h3>📦 Base Classes</h3>
<ul>
  <li><strong><code>BaseActivity.kt</code></strong> — Abstract base class for activities providing common setup like lifecycle observers, logging, or event handling.</li>
  <li><strong><code>BaseFragment.kt</code></strong> — Reusable base class for fragments with view binding, back press handling, and lifecycle-aware logic.</li>
</ul>

<hr />

<h3>🔄 Lifecycle & Back Press Management</h3>
<ul>
  <li><strong><code>BackPressedHandlerActivity.kt</code></strong> — Handles custom back press behavior in activities (e.g., double press to exit, confirmation dialogs).</li>
  <li><strong><code>BackPressedStateManager.kt</code></strong> — Centralized manager for tracking and delegating back press events.</li>
  <li><strong><code>LifecycleStateManager.kt</code></strong> — Utility for tracking lifecycle states and triggering appropriate actions on lifecycle transitions.</li>
</ul>

<hr />

<h3>⏱️ Delayed Actions</h3>
<ul>
  <li><strong><code>DelayedActionHandler.kt</code></strong> — Executes delayed actions with cancellation support, commonly used for throttling button clicks or search input.</li>
</ul>

<hr />

<h3>🎥 Animation</h3>
<ul>
  <li><strong><code>AnimationHelper.kt</code></strong> — Reusable animation utilities for smooth transitions, fade-ins, scaling, or view manipulation.</li>
</ul>

<hr />

<h3>🎹 Keyboard Management</h3>
<ul>
  <li><strong><code>SoftKeyBoardUtils.kt</code></strong> — Hides or shows the soft keyboard programmatically, improves UX for input forms.</li>
</ul>

<hr />

<h3>🧾 Logging</h3>
<ul>
  <li><strong><code>Logger.kt</code></strong> — Custom logging wrapper that enables structured logs (e.g., based on build types or tags).</li>
</ul>

<hr />

<h3>🔑 Constants & Keys</h3>
<ul>
  <li><strong><code>Constants.kt</code></strong> — Stores global constants like delay durations, log tags, shared keys, etc. (Appears twice — consider deduplication).</li>
  <li><strong><code>LocalKeys.kt</code></strong> — Keys used for local storage or inter-component communication (e.g., SharedPreferences, Bundle).</li>
</ul>

<hr />

<h3>🔄 View State Handling</h3>
<ul>
  <li><strong><code>ViewModelStateHandler.kt</code></strong> — Manages UI states like loading, success, error, and empty for screens based on ViewModel states.</li>
</ul>

<hr />

<h3>🎛️ Spinner UI</h3>
<ul>
  <li><strong><code>SpinnerAdapter.kt</code></strong> — Custom adapter to render dropdown items with dynamic styling and behavior.</li>
  <li><strong><code>layout_spinner_item.xml</code></strong> — XML layout used to display individual items in a spinner (drop-down list).</li>
</ul>

<hr />

<h3>🔧 Gradle</h3>
<ul>
  <li><strong><code>build.gradle.kts</code></strong> — Kotlin-based Gradle configuration for this module (dependencies, plugins, settings).</li>
</ul>

<hr />
