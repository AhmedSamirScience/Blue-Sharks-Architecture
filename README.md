<h2>🧠 Presentation Core Layer — Development Branch (02-Presentation-Core)</h2>

<p>
  This branch lays the foundational building blocks for the presentation layer, defining common base classes, navigation helpers, and lifecycle/state management utilities.
  It promotes <strong>consistency, reusability, and testability</strong> across all feature modules that rely on UI, ViewModels, and Fragment/Activity coordination.
</p>

<hr/>

<h3>📌 Purpose</h3>

<p>
  To centralize and abstract common functionality in Activities, Fragments, and ViewModels. This improves modularization and avoids redundant code across the app’s presentation layer.
</p>

<ul>
  <li>📦 Reusable <strong>base classes</strong> for Fragments, Activities, ViewModels</li>
  <li>🔙 Back press and state control managers</li>
  <li>📡 ViewModel state handling & UI state flow</li>
  <li>📎 Safe navigation using <strong>Safe Args</strong> abstraction</li>
  <li>🧪 Scaffold for presentation layer testing</li>
</ul>

<hr/>

<h3>✅ Key Changes</h3>

<h4>🧱 Base Class Setup</h4>
<ul>
  <li>➕ <code>BaseActivity.kt</code>, <code>BaseFragment.kt</code> — lifecycle-safe binding and injection logic</li>
  <li>➕ <code>BaseViewModel.kt</code> — sealed state flow, error handling, and coroutineScope extensions</li>
  <li>📦 <code>FragmentSetupContract.kt</code> — defines structure for binding views and ViewModel setup</li>
</ul>

<h4>🔄 Navigation & Lifecycle</h4>
<ul>
  <li>➕ <code>BackPressedHandlerActivity.kt</code> — centralized back press delegation</li>
  <li>➕ <code>BackPressedStateManager.kt</code> — observable back press event handling</li>
  <li>➕ <code>SafeArgsFragmentManager.kt</code> — simplifies type-safe navigation</li>
  <li>🧭 Enables compatibility with <strong>Jetpack Navigation Component</strong> and safe navigation</li>
</ul>

<h4>🧠 ViewModel & State Flow</h4>
<ul>
  <li>➕ <code>ViewModelStateHandler.kt</code> — reacts to ViewModel states using sealed classes (e.g. Loading, Success, Error)</li>
  <li>➕ <code>ViewStateManager.kt</code> — separates business logic and UI rendering</li>
  <li>✅ Provides reactive integration with <code>repeatOnLifecycle</code></li>
</ul>

<h4>🧪 Testing Utilities</h4>
<ul>
  <li>📁 <code>ExampleInstrumentedTest.kt</code> — basic Android instrumentation test</li>
  <li>📁 <code>ExampleUnitTest.kt</code> — placeholder for future unit test integration</li>
</ul>

<hr/>

<h3>🚀 Why This Matters</h3>

<ul>
  <li>✅ <strong>DRY Principle</strong> → Avoid duplicating Fragment/Activity boilerplate</li>
  <li>🔗 <strong>Safe Navigation</strong> → Type-safe transitions via <code>SafeArgsFragmentManager</code></li>
  <li>🔍 <strong>Better Debugging</strong> → Logging through centralized <code>Logger.kt</code></li>
  <li>🧪 <strong>Testing First</strong> → Clean testable ViewModel states and separation of UI logic</li>
  <li>♻️ <strong>Scalability</strong> → A plug-and-play setup for future feature modules</li>
</ul>

<hr/>

<h3>🗂️ Folder & Class Overview</h3>

<table>
  <thead>
    <tr>
      <th>File/Class</th>
      <th>Purpose</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><code>BaseActivity.kt</code></td>
      <td>Abstract Activity with lifecycle state support</td>
    </tr>
    <tr>
      <td><code>BaseFragment.kt</code></td>
      <td>Fragment with automatic binding and ViewModel hookup</td>
    </tr>
    <tr>
      <td><code>BaseViewModel.kt</code></td>
      <td>Base ViewModel class using StateFlow and coroutines</td>
    </tr>
    <tr>
      <td><code>ViewModelStateHandler.kt</code></td>
      <td>Handles UI state transitions from ViewModel to View</td>
    </tr>
    <tr>
      <td><code>SafeArgsFragmentManager.kt</code></td>
      <td>Type-safe wrapper for Jetpack Navigation</td>
    </tr>
    <tr>
      <td><code>BackPressedHandlerActivity.kt</code></td>
      <td>Parent activity back press listener delegation</td>
    </tr>
    <tr>
      <td><code>FragmentSetupContract.kt</code></td>
      <td>Interface for organizing view setup logic</td>
    </tr>
  </tbody>
</table>

<hr/>

<h3>📅 Commits Summary</h3>

<ul>
  <li><strong>Mar 4, 2025</strong>: Added core base classes and architecture for presentation layer</li>
  <li><strong>Mar 5, 2025</strong>: Completed Safe Args support for fragment navigation</li>
</ul>

<hr/>

<h3>🚀 When to Use This Branch</h3>

<ul>
  <li>📦 You are building new feature modules and need a stable base for Fragments/ViewModels</li>
  <li>🔍 You want to implement state-based UI updates using Flow/StateFlow</li>
  <li>🔗 You plan to use Safe Args and Navigation Components across your modules</li>
</ul>

<hr/>

<h3>🔗 Related Branches</h3>

<ul>
  <li><strong>Feature/2.2-Base-Classes-Integration</strong> — All base class integrations and SafeArgs setup</li>
  <li><strong>Feature/2.1-Add-Core-Modules</strong> — Sets up data/domain/presentation module structure</li>
</ul>
