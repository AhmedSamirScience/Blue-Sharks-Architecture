<h2>🚀 Feature: ViewModel + Activity Integration</h2>
<h3><code>Branch: 1.0-ArchitecturePhase/Feature/4.2-View-Model-Activity-Integration</code></h3>

<p>
This feature integrates the <strong>MainActivity</strong> and <strong>HomeActivity</strong> using a <strong>shared ViewModel architecture</strong> and updated layout resources, enabling smooth modular navigation and state handling across activities.
</p>

<hr />

<h3>📌 What’s Included?</h3>
<ul>
  <li>🧩 <strong>ViewModel Integration:</strong> Shared ViewModel setup between Main and Home flows for cleaner state management.</li>
  <li>🧱 <strong>Updated Layouts:</strong> New and updated XML layout files for the UI:
    <ul>
      <li><code>activity_home.xml</code></li>
      <li><code>activity_main.xml</code></li>
      <li><code>activity_main_view_model.xml</code></li>
    </ul>
  </li>
  <li>🏗️ <strong>Activity Enhancements:</strong> Refactored and integrated activities:
    <ul>
      <li><code>HomeActivity.kt</code></li>
      <li><code>MainActivity.kt</code></li>
      <li><code>MainViewModelActivity.kt</code></li>
    </ul>
  </li>
  <li>📛 <strong>AndroidManifest.xml:</strong> Registered new activity entries and updated intent filters if required.</li>
  <li>⚙️ <strong>Gradle Updates:</strong> `build.gradle.kts` updated to reflect changes in dependencies, view binding, or architecture setup.</li>
  <li>📦 <strong>Constants:</strong> Used across modules for reusability:
    <ul>
      <li><code>Constants.kt</code> → Holds global constants.</li>
      <li><code>LocalKeys.kt</code> → Holds shared preference keys or local tags.</li>
    </ul>
  </li>
  <li>🧪 <strong>Testing:</strong> Added/updated unit or scaffold tests in <code>Test.kt</code>.</li>
</ul>

<hr />

<h3>🎯 Why This Is Important</h3>
<ul>
  <li>✅ Enables modular navigation and separation of concerns between Home and Main UI flows.</li>
  <li>✅ Promotes reuse of logic using shared ViewModels (especially for shared UI state or event bus).</li>
  <li>✅ Reduces boilerplate by leveraging clean activity-ViewModel binding patterns.</li>
  <li>✅ Prepares the foundation for deeper navigation flows and dynamic screen rendering.</li>
</ul>

<hr />

<h3>📁 Directory Overview</h3>
<pre>
feature/
└── main/
    ├── ui/
    │   ├── activities/
    │   │   ├── HomeActivity.kt
    │   │   ├── MainActivity.kt
    │   │   └── MainViewModelActivity.kt
    │   └── layouts/
    │       ├── activity_home.xml
    │       ├── activity_main.xml
    │       └── activity_main_view_model.xml
    ├── viewmodel/
    │   └── SharedViewModel.kt
    ├── utils/
    │   ├── Constants.kt
    │   └── LocalKeys.kt
</pre>

<hr />

<h3>🔌 Manifest Snippet</h3>
<pre><code>&lt;activity android:name=".ui.activities.MainActivity" /&gt;
&lt;activity android:name=".ui.activities.HomeActivity"&gt;
    &lt;intent-filter&gt;
        &lt;action android:name="android.intent.action.MAIN" /&gt;
        &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
    &lt;/intent-filter&gt;
&lt;/activity&gt;
</code></pre>

<hr />

<h3>🧠 When &amp; Why to Use Shared ViewModels Between Activities?</h3>
<ul>
  <li>When both activities share logic or state (e.g., login status, user session).</li>
  <li>When you want to persist logic across screens without creating separate ViewModels.</li>
  <li>When using <code>Activity-scoped ViewModel</code> via <code>ViewModelProvider(this)</code>.</li>
</ul>

<hr />

<h3>🔗 References</h3>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank">Android ViewModel - Official Docs</a></li>
  <li><a href="https://developer.android.com/jetpack/docs/guide" target="_blank">Jetpack Guide to Modern Architecture</a></li>
</ul>

 
