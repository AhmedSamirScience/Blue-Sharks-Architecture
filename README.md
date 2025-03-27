<hr />

<h2>🎯 Feature: Base Classes Integration in Presentation Layer</h2>
<h3>📌 Branch: <code>1.0-ArchitecturePhase/Feature/2.2-Base-Classes-Integration</code></h3>

<hr />

<h3>✅ Overview</h3>
<p>This update introduces <strong>core foundational components</strong> for the <code>presentation layer</code> in a scalable, modular Android architecture.</p>

<ul>
  <li>📦 Establishes reusable <strong>base classes</strong> for Fragments, Activities, and ViewModels</li>
  <li>🧠 Adds ViewModel state management and lifecycle helpers</li>
  <li>🎛 Implements utility classes for back press, click handling, and argument passing</li>
  <li>🧪 Sets up initial test scaffolding for presentation components</li>
</ul>

<hr />

<h3>🆕 Added Files</h3>

<ul>
  <li><strong>Base Classes</strong>
    <ul>
      <li><code>BaseFragment.kt</code></li>
      <li><code>BaseActivity.kt</code></li>
      <li><code>BaseViewModel.kt</code></li>
    </ul>
  </li>

  <li><strong>Back Press & Lifecycle Managers</strong>
    <ul>
      <li><code>BackPressedHandlerActivity.kt</code></li>
      <li><code>BackPressedStateManager.kt</code></li>
      <li><code>LifecycleStateManager.kt</code></li>
    </ul>
  </li>

  <li><strong>ViewModel State Management</strong>
    <ul>
      <li><code>ViewModelStateHandler.kt</code></li>
      <li><code>ViewStateManager.kt</code></li>
    </ul>
  </li>

  <li><strong>Argument & Setup Helpers</strong>
    <ul>
      <li><code>SafeArgsFragmentManager.kt</code> – Simplifies working with Jetpack Safe Args</li>
      <li><code>FragmentSetupContract.kt</code> – Enforces consistent fragment initialization</li>
    </ul>
  </li>

  <li><strong>Click Handling</strong>
    <ul>
      <li><code>ClickListenerManager.kt</code> – Prevents double taps and centralizes click logic</li>
    </ul>
  </li>

  <li><strong>Logging</strong>
    <ul>
      <li><code>Logger.kt</code> – Standard logging utility for debugging and crash tracing</li>
    </ul>
  </li>

  <li><strong>Tests</strong>
    <ul>
      <li><code>ExampleUnitTest.kt</code></li>
      <li><code>ExampleInstrumentedTest.kt</code></li>
    </ul>
  </li>
</ul>

<hr />

<h3>🧠 When & Why to Use These Base Classes?</h3>

<ul>
  <li>⏱ Reduce boilerplate code across modules and features</li>
  <li>🔁 Promote reusability and consistency in UI setup and ViewModel interactions</li>
  <li>🧩 Easily plug lifecycle-aware utilities (e.g. <code>repeatOnLifecycle</code>) into shared classes</li>
  <li>🔐 Improve argument safety with Safe Args and type-safe navigation</li>
</ul>

<hr />

<h3>📚 References</h3>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank">ViewModel Architecture - Android Docs</a></li>
  <li><a href="https://developer.android.com/topic/libraries/architecture/lifecycle" target="_blank">Lifecycle Management</a></li>
  <li><a href="https://developer.android.com/jetpack" target="_blank">Jetpack Architecture Components</a></li>
</ul>
