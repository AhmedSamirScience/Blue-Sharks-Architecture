<hr />

<h2>📦 Feature: ViewModel & Fragment Integration</h2>
<h3>🔁 Branch: <code>1.0-ArchitecturePhase/Feature/4.3-View-Model-Fragment-Integration</code></h3>

<hr />

<h3>✅ Added Flows</h3>

<ul>
  <li>
    <strong>🧩 Basic ViewModel Flow</strong><br />
    <ul>
      <li>Demonstrates standard ViewModel usage inside a fragment.</li>
      <li>Persists ViewModel across configuration changes (e.g., rotation).</li>
      <li><strong>Files:</strong><br />
        <code>BasicVMActivity.kt</code>, <code>BasicVMFragment.kt</code>, <code>BasicVMViewModel.kt</code><br />
        <code>ConfigChangeVMFragment.kt</code>, <code>ConfigChangeVMViewModel.kt</code><br />
        <code>nav_basic_vm.xml</code>
      </li>
    </ul>
  </li>

  <li>
    <strong>🧩 Activity-Scoped Shared ViewModel Flow</strong><br />
    <ul>
      <li>Demonstrates sharing a ViewModel between multiple fragments in the same activity.</li>
      <li>Uses <code>activityViewModels()</code> for shared state and logic.</li>
      <li><strong>Files:</strong><br />
        <code>ActivityVMFirstFragment.kt</code>, <code>ActivityVMSecondFragment.kt</code><br />
        <code>ActivityVMFirstViewModel.kt</code>, <code>ActivityVMSecondViewModel.kt</code><br />
        <code>nav_activity_vm.xml</code>
      </li>
    </ul>
  </li>

  <li>
    <strong>🧩 NavGraph-Scoped Shared ViewModel Flow</strong><br />
    <ul>
      <li>Demonstrates sharing a ViewModel scoped to a <code>navigation graph</code> only.</li>
      <li>Uses <code>navGraphViewModels()</code> with <code>androidx.navigation</code> to scope tightly.</li>
      <li><strong>Files:</strong><br />
        <code>NavGraphVMFirstFragment.kt</code>, <code>NavGraphVMSecondFragment.kt</code><br />
        <code>NavGraphVMFirstViewModel.kt</code>, <code>NavGraphVMSecondViewModel.kt</code><br />
        <code>nav_navgraph_vm.xml</code>
      </li>
    </ul>
  </li>
</ul>

<hr />

<h3>🧠 When &amp; Why to Use Each ViewModel Scope?</h3>
<ul>
  <li><strong>Basic ViewModel:</strong> Used when a single fragment or activity owns the state.</li>
  <li><strong>Activity-scoped ViewModel:</strong> Useful when multiple fragments need shared state or communication.</li>
  <li><strong>NavGraph-scoped ViewModel:</strong> Ideal for encapsulating shared state <i>only</i> during navigation inside a specific navGraph. ViewModel is destroyed once user leaves the graph.</li>
</ul>

<hr />

<h3>🛠️ Updated & Integrated</h3>
<ul>
  <li>🔧 Navigation XMLs (<code>nav_*.xml</code>)</li>
  <li>🧩 Gradle dependencies (ViewModel, Navigation)</li>
  <li>🎨 Manifest entries, app themes, and shared UI layouts</li>
  <li>🔌 Fragment bindings using lifecycle-aware architecture components</li>
</ul>

<hr />

<h3>📚 References</h3>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel" target="_blank">Android ViewModel - Official Docs</a></li>
  <li><a href="https://developer.android.com/jetpack/guide#viewmodel" target="_blank">Jetpack Architecture Guide</a></li>
  <li><a href="https://developer.android.com/jetpack/androidx/releases/lifecycle" target="_blank">Lifecycle + ViewModel Release Notes</a></li>
</ul>
