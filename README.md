<h1>📦 NavGraph-Scoped ViewModel – Feature Branch Overview</h1>

<p>
  This branch demonstrates how to use <strong>NavGraph-scoped ViewModels</strong> in Android using Jetpack Navigation Component,
  <code>StateFlow</code>, and <code>repeatOnLifecycle</code>. This approach enables multiple fragments within the same
  navigation graph to share the same ViewModel, providing a clean, lifecycle-aware state-sharing mechanism.
</p>

<hr />

<h2>🎯 Goals of This Feature</h2>
<ul>
  <li>Implement <strong>graph-scoped</strong> ViewModel sharing.</li>
  <li>Demonstrate stable ViewModel initialization using <code>getBackStackEntry()</code>.</li>
  <li>Persist ViewModel state across fragment navigation (within the same graph).</li>
  <li>Support multiple isolated navigation graphs for modular architecture.</li>
</ul>

<hr />

<h2>🧠 What is a NavGraph-Scoped ViewModel?</h2>
<p>
  A NavGraph-scoped ViewModel is a ViewModel that is tied to a specific navigation graph rather than an activity or a fragment.
  This allows all fragments inside that navigation graph to share the same instance of a ViewModel.
</p>

<div style="background:#f0f9ff;border-left:4px solid #3498db;padding:10px;margin:1rem 0;">
  <strong>Why it matters:</strong> Enables isolated, reusable ViewModel scopes — ideal for flows like onboarding, wizards, and feature modules.
</div>

<hr />

<h2>✅ Benefits</h2>
<ul>
  <li>🔁 Shared state across multiple fragments in a flow.</li>
  <li>🎯 Cleaner logic separation – no more bundle passing.</li>
  <li>🚀 Ideal for modularization (onboarding, authentication, etc).</li>
  <li>♻️ Better ViewModel lifecycle management compared to activity-scoped alternatives.</li>
</ul>

<hr />

<h2>📁 Project Structure</h2>
<ul>
  <li><code>NavGraphVMFirstFragment.kt</code> → Displays and updates shared counter.</li>
  <li><code>NavGraphVMSecondFragment.kt</code> → Uses the same ViewModel, updates and reflects changes.</li>
  <li><code>NavGraphVMThirdFragment.kt</code> → Optional isolated flow in a separate nav graph.</li>
  <li><code>NavGraphVMFirstViewModel.kt</code> → Holds shared state using <code>StateFlow</code>.</li>
  <li><code>nav_navgraph_vm.xml</code>, <code>nav_navgraph_second_vm.xml</code> → Navigation graph definitions.</li>
</ul>

<hr />

<h2>🔄 ViewModel Setup</h2>
<p>
  <code>navGraphViewModels()</code> often causes timing issues if used too early (e.g., before graph is created).
  Instead, use this pattern:
</p>

<pre><code class="kotlin">
val backStackEntry = findNavController().getBackStackEntry(R.id.nav_navgraph_vm)
val viewModel: NavGraphVMViewModel by viewModels({ backStackEntry })
baseViewModel = viewModel
</code></pre>

<p>This guarantees proper lifecycle and safe initialization.</p>

<hr />

<h2>📊 ViewModel State Management</h2>
<p>Each fragment observes <code>StateFlow</code> using <code>repeatOnLifecycle</code> to collect updates only when the view is visible:</p>

<pre><code class="kotlin">
lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        baseViewModel.counter.collect { count ->
            baseViewBinding.myItem = count
        }
    }
}
</code></pre>

<hr />

<h2>📄 Related Files</h2>
<ul>
  <li><code>NavGraphVMFirstFragment.kt</code></li>
  <li><code>NavGraphVMSecondFragment.kt</code></li>
  <li><code>NavGraphVMThirdFragment.kt</code></li>
  <li><code>NavGraphVMFirstViewModel.kt</code></li>
  <li><code>CounterClass.kt</code></li>
  <li><code>nav_navgraph_vm.xml</code>, <code>nav_navgraph_second_vm.xml</code></li>
  <li>Corresponding <code>layout/</code> XML files</li>
</ul>

<hr />

<h2>🧪 Testing Tips</h2>
<ul>
  <li>✅ Log ViewModel instance hash codes to verify reuse across fragments.</li>
  <li>✅ Test navigation between fragments to ensure state retention.</li>
  <li>✅ Rotate screen – observe ViewModel survival.</li>
  <li>✅ Navigate between different nav graphs – verify ViewModel scoping isolation.</li>
</ul>

<hr />

<h2>🚀 Conclusion</h2>
<p>
  The <strong>NavGraph-scoped ViewModel</strong> pattern offers a scalable way to manage shared UI state in modular flows.
  This feature branch showcases how to set it up correctly and use it effectively with <code>StateFlow</code>,
  <code>repeatOnLifecycle</code>, and Navigation Component best practices.
</p>

<p><strong>💡 Tip:</strong> Combine this with clean architecture and feature modules for a robust, scalable Android codebase.</p>
