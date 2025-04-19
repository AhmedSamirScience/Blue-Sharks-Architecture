<!DOCTYPE html>
<html lang="en">
 
<body>

<h1>🧠 Android ViewModel Feature Collection – Development Branch</h1>
<p>This document aggregates all ViewModel-related features implemented in the <strong>1.0-ArchitecturePhase</strong> development branch. It explores best practices for reactive UI with <code>ViewModel</code>, <code>StateFlow</code>, and scoped lifecycle management across Fragments, Activities, and Navigation Graphs in a modular Android architecture.</p>

<hr />

<h2>📋 Feature Branch Overview</h2>
<table>
  <thead>
    <tr>
      <th>Branch</th>
      <th>Description</th>
    </tr>
  </thead>
  <tbody>
    <tr><td><code>4.1-Creating-Feature-Module</code></td><td>Introduces clean feature modularization</td></tr>
    <tr><td><code>4.2-View-Model-Activity-Integration</code></td><td>Shared ViewModel between Main and Home activities</td></tr>
    <tr><td><code>4.3-View-Model-Fragment-Integration</code></td><td>Fragment, Activity, and NavGraph scoped ViewModels</td></tr>
    <tr><td><code>4.4-View-Model-Basic-Integration</code></td><td>Basic ViewModel with StateFlow and config handling</td></tr>
    <tr><td><code>4.5-Shared-ViewModel-Between-Fragments</code></td><td>Shared ViewModel using <code>activityViewModels()</code></td></tr>
    <tr><td><code>4.6-NavGraph-Scoped-ViewModel</code></td><td>Scoped ViewModel to Navigation Graph using <code>getBackStackEntry()</code></td></tr>
  </tbody>
</table>

<hr />

<h2>📦 Feature 4.1 – Creating Feature Module</h2>
<ul>
  <li>✅ Created a feature module under <code>feature/</code></li>
  <li>🔧 Gradle setup and manifest defined</li>
  <li>🧪 Test and ProGuard placeholders included</li>
  <li>🎯 Supports modularity, scalability, and focused testing</li>
</ul>

<hr />

<h2>🚀 Feature 4.2 – ViewModel + Activity Integration</h2>
<ul>
  <li>📍 Shared ViewModel used across <code>MainActivity</code> and <code>HomeActivity</code></li>
  <li>📁 Layouts: <code>activity_main.xml</code>, <code>activity_home.xml</code></li>
  <li>🔗 Manifest entries and Gradle integration</li>
  <li>📦 Includes shared constants and ViewModel logic</li>
</ul>

<hr />

<h2>🧩 Feature 4.3 – ViewModel & Fragment Integration</h2>
<ul>
  <li>✅ <strong>Fragment ViewModel:</strong> <code>viewModels()</code></li>
  <li>✅ <strong>Activity-scoped ViewModel:</strong> <code>activityViewModels()</code></li>
  <li>✅ <strong>NavGraph-scoped ViewModel:</strong> <code>navGraphViewModels()</code> or <code>getBackStackEntry()</code></li>
  <li>📁 Files: <code>ActivityVMFirstFragment.kt</code>, <code>NavGraphVMFirstFragment.kt</code>, etc.</li>
</ul>

<hr />

<h2>🧠 Feature 4.4 – ViewModel Basic Integration</h2>
<ul>
  <li>🧪 ViewModel with <code>StateFlow</code> for local and retained state</li>
  <li>🔄 Configuration change handling</li>
  <li>🎯 Comparison between retained and non-retained UI state</li>
</ul>
<pre>
├── BasicVMFragment.kt
├── ConfigChangeVMFragment.kt
├── BasicVMViewModel.kt
├── ConfigChangeVMViewModel.kt
</pre>

<hr />

<h2>🤝 Feature 4.5 – Shared ViewModel Between Fragments</h2>
<p><strong>Concept:</strong> Shared ViewModel scoped to an <code>Activity</code> using <code>activityViewModels()</code>.</p>
<ul>
  <li>✅ Enables state sharing between sibling fragments</li>
  <li>✅ Ideal for wizard flows or coordinated UIs</li>
  <li>✅ Reduces boilerplate using lifecycle-aware communication</li>
</ul>
<pre><code>
// Fragment A and B
val sharedViewModel: SharedViewModel by activityViewModels()
</code></pre>
<p>Implements a counter with:</p>
<pre><code class="kotlin">
class SharedViewModel : ViewModel() {
  private val _counter = MutableStateFlow(0)
  val counter: StateFlow<Int> = _counter

  fun increment() {
    _counter.value++
  }
}
</code></pre>

<hr />

<h2>📦 Feature 4.6 – NavGraph-Scoped ViewModel</h2>
<p><strong>Concept:</strong> Share ViewModel only between fragments inside a specific <code>NavGraph</code>.</p>
<ul>
  <li>✅ Scope isolation for clean navigation flow</li>
  <li>✅ ViewModel persists during in-graph navigation</li>
  <li>⚠️ Uses <code>getBackStackEntry()</code> for safe initialization</li>
</ul>

<pre><code class="kotlin">
val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
val viewModel: NavGraphVMViewModel by viewModels({ backStackEntry })
</code></pre>

<h4>💡 Ideal Use Cases:</h4>
<ul>
  <li>Onboarding flows</li>
  <li>Authentication modules</li>
  <li>Nested feature graphs</li>
</ul>

<hr />

<h2>📚 References</h2>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">Android ViewModel</a></li>
  <li><a href="https://developer.android.com/kotlin/flow/stateflow-and-sharedflow">StateFlow & SharedFlow</a></li>
  <li><a href="https://developer.android.com/jetpack/guide">Jetpack Architecture Guide</a></li>
</ul>

</body>
</html>
