<hr />

<h2>🧠 Feature 4.4 – ViewModel Basic Integration</h2>
<h3>🔀 Branch: <code>1.0-ArchitecturePhase/Feature/4.4-View-Model-Basic-Integration</code></h3>

<hr />
<h3>📌 Summary</h3>
<p>This feature demonstrates how to integrate the Android <code>ViewModel</code> component with Kotlin <code>StateFlow</code> to handle UI state reactively and persistently across configuration changes. It includes both:</p>
<ul>
  <li>✅ A basic ViewModel-bound fragment with counter logic</li>
  <li>🔁 A configuration change scenario showing retained vs. non-retained values</li>
</ul>

<hr />
<h3>📂 Files & Structure</h3>

<pre><code>
📁 viewmodelcases/flow1Basic/
├── activity/
│   └── BasicVMActivity.kt
├── fragment/f1BasicVM/
│   ├── BasicVMFragment.kt
│   ├── BasicVMViewModel.kt
│   └── DisplayClass.kt
├── fragment/f2ConfiqChangeVM/
│   ├── ConfigChangeVMFragment.kt
│   └── ConfigChangeVMViewModel.kt
📁 res/layout/
├── fragment_basic_v_m.xml
└── fragment_config_change_v_m.xml
</code></pre>

<hr />
<h3>🧩 Feature Breakdown</h3>

<h4>1️⃣ <code>BasicVMFragment.kt</code></h4>
<ul>
  <li>Uses <code>viewModels()</code> to inject <code>BasicVMViewModel</code></li>
  <li>Collects data from <code>counter: StateFlow&lt;Int&gt;</code></li>
  <li>Lifecycle-safe using <code>repeatOnLifecycle</code></li>
  <li>Updates UI with <code>dataBinding</code> + custom <code>DisplayClass</code></li>
</ul>

<h4>2️⃣ <code>ConfigChangeVMFragment.kt</code></h4>
<ul>
  <li>Demonstrates <strong>retained</strong> vs. <strong>non-retained</strong> values:</li>
  <li><strong>Retained:</strong> via <code>ViewModel + StateFlow</code></li>
  <li><strong>Non-retained:</strong> normal variable inside Fragment</li>
  <li>Includes real-time collection of <code>BasicVMViewModel</code>’s counter too</li>
</ul>

<h4>3️⃣ <code>BasicVMActivity.kt</code></h4>
<ul>
  <li>Extends base activity structure</li>
  <li>Defines fragment container & transition animations</li>
</ul>

<h4>4️⃣ ViewModels</h4>
<ul>
  <li>📍 <code>BasicVMViewModel</code>: basic <code>_counter</code> flow</li>
  <li>📍 <code>ConfigChangeVMViewModel</code>: starts at 100, increments by 10</li>
  <li>🚀 Both extend <code>BaseViewModel</code> for future scalability</li>
</ul>

<hr />
<h3>🛠️ ViewModel + StateFlow – Key Concepts</h3>

<h4>What’s the Benefit?</h4>
<ul>
  <li>✅ Survives config changes (rotation, background/foreground, etc.)</li>
  <li>✅ Emits UI state reactively with <code>StateFlow</code></li>
  <li>✅ Prevents memory leaks using lifecycle-aware collection</li>
</ul>

<h4>Best Practice Pattern</h4>
<pre><code>lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.counter.collect { value ->
            // update UI safely
        }
    }
}</code></pre>

<h4>Why not LiveData?</h4>
<ul>
  <li>⚠️ LiveData is lifecycle-aware but less powerful</li>
  <li>💡 StateFlow is more Kotlin idiomatic and coroutine-compatible</li>
  <li>💪 Better suited for complex UDF (unidirectional data flow)</li>
</ul>

<hr />
<h3>📱 UI Output Examples</h3>

<ul>
  <li><strong>Initial UI:</strong> Counter = 0</li>
  <li><strong>Click Button:</strong> Counter increments → UI updated instantly</li>
  <li><strong>Rotate Screen:</strong> Counter persists using ViewModel</li>
  <li><strong>Non-retained value:</strong> Resets on screen rotation</li>
</ul>

<hr />
<h3>🚀 Learnings</h3>
<ul>
  <li>🧠 How to build and scope ViewModels properly</li>
  <li>🔁 Difference between local and retained state</li>
  <li>🔄 Lifecycle-safe coroutine collection using <code>repeatOnLifecycle</code></li>
  <li>📚 Structuring code in feature modules (flow1Basic)</li>
</ul>

<hr />
<h3>🔌 Manifest Snippet</h3>
<pre><code>&lt;activity android:name=".viewmodelcases.flow1Basic.activity.BasicVMActivity"&gt;
    &lt;intent-filter&gt;
        &lt;action android:name="android.intent.action.MAIN" /&gt;
        &lt;category android:name="android.intent.category.LAUNCHER" /&gt;
    &lt;/intent-filter&gt;
&lt;/activity&gt;
</code></pre>

<hr />
<h3>📚 References</h3>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">ViewModel - Android Official Docs</a></li>
  <li><a href="https://developer.android.com/kotlin/flow/stateflow-and-sharedflow">StateFlow & SharedFlow Guide</a></li>
  <li><a href="https://developer.android.com/topic/libraries/architecture/lifecycle">Lifecycle-aware programming</a></li>
</ul>
