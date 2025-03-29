<h2>🤝 Understanding the Shared ViewModel Concept in Android</h2>
<p>
  A <strong>Shared ViewModel</strong> is a design pattern that allows multiple UI components (usually <code>Fragments</code>) to
  access and interact with the <strong>same instance of a ViewModel</strong>. This enables seamless communication and state sharing
  across the UI without relying on callbacks, interfaces, or tightly coupled logic.
</p>

<hr />

<h3>🧠 What Is a Shared ViewModel?</h3>
<ul>
  <li>A <code>ViewModel</code> scoped to an <strong>Activity</strong> and shared across all Fragments hosted within it.</li>
  <li>Managed by Android’s lifecycle-aware <code>ViewModelProvider</code>.</li>
  <li>Enables shared business logic, UI state, and reactive data (e.g., using <code>LiveData</code> or <code>StateFlow</code>).</li>
</ul>

<pre><code>
// In Fragment A and Fragment B (inside the same Activity)
val viewModel: SharedViewModel by activityViewModels()
</code></pre>

<hr />

<h3>🎯 Why Use a Shared ViewModel?</h3>
<ul>
  <li>✅ Share state across multiple fragments (e.g., a form wizard, tab flow, multi-step UI).</li>
  <li>✅ Avoid tightly coupled interfaces or callback-based communication.</li>
  <li>✅ Retain data through configuration changes (rotation, dark/light mode).</li>
  <li>✅ Centralize business logic and avoid duplication.</li>
  <li>✅ Simplify navigation and coordination between fragments.</li>
</ul>

<hr />

<h3>📦 When to Use a Shared ViewModel?</h3>
<ul>
  <li>➡️ When multiple fragments in the same activity need to:
    <ul>
      <li>Read or write to the same piece of data.</li>
      <li>Trigger events (e.g., navigation, actions) on each other.</li>
      <li>Observe shared <code>StateFlow</code> / <code>LiveData</code> streams.</li>
    </ul>
  </li>
  <li>➡️ When building wizard-like UIs (e.g., onboarding, checkout flows).</li>
  <li>➡️ When using <code>ViewPager2</code> or bottom navigation with fragments.</li>
  <li>➡️ When sharing session or authentication state across views.</li>
</ul>

<hr />

<h3>⚙️ How It Works</h3>
<p>
  The shared ViewModel is tied to the lifecycle of the Activity using:
</p>
<pre><code>
val viewModel: MySharedViewModel by activityViewModels()
</code></pre>
<ul>
  <li>✅ <strong>activityViewModels()</strong>: A Kotlin property delegate that binds the ViewModel to the Activity scope.</li>
  <li>✅ Fragments get the exact same ViewModel instance.</li>
  <li>✅ All data changes reflect immediately in all observers.</li>
</ul>

<hr />

<h3>🔄 Shared ViewModel + StateFlow Pattern</h3>
<pre><code class="kotlin">
// SharedViewModel.kt
class SharedViewModel : ViewModel() {
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    fun increment() {
        _counter.value++
    }
}
</code></pre>

<pre><code class="kotlin">
// In multiple fragments
lifecycleScope.launch {
    viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
        viewModel.counter.collect {
            textView.text = "Count: $it"
        }
    }
}
</code></pre>

<hr />

<h3>💡 Benefits of Using Shared ViewModels</h3>
<ul>
  <li>🧼 Cleaner architecture and separation of concerns</li>
  <li>♻️ Reactive and lifecycle-safe state management</li>
  <li>🔗 Decoupled communication between UI components</li>
  <li>🔐 Centralized logic — fewer bugs and race conditions</li>
  <li>📱 Survives configuration changes out of the box</li>
</ul>

<hr />

<h3>❗ Important Considerations</h3>
<ul>
  <li>⛔ Only fragments within the same <strong>Activity</strong> can share a ViewModel via <code>activityViewModels()</code>.</li>
  <li>✅ For fragments in different navigation graphs, consider using <code>navGraphViewModels()</code>.</li>
  <li>✅ For app-wide shared logic (e.g., auth), consider using <code>Application-scoped ViewModel</code> via DI frameworks like Hilt.</li>
</ul>

<hr />

<h3>📚 References</h3>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/viewmodel">Android ViewModel - Official Documentation</a></li>
  <li><a href="https://developer.android.com/topic/libraries/architecture/lifecycle">Lifecycle-aware components</a></li>
  <li><a href="https://developer.android.com/jetpack/guide">Guide to Modern App Architecture</a></li>
</ul>

<hr />

<h3>✅ Conclusion</h3>
<p>
  Shared ViewModels are a best practice in modern Android development when multiple fragments require access to the same data or business logic.
  By scoping your ViewModel to the Activity, you ensure state consistency, reduce boilerplate code, and improve maintainability.
</p>
