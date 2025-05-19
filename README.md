<h1>🔐 Parallel Login Use Case Flow – Feature Branch Overview</h1>

<p>This branch introduces the implementation of <strong>parallel login use case execution</strong> across the <code>BasicRD</code> remote data feature, enhancing authentication responsiveness and modularity.</p>

<hr />

<h2>🎯 Goal</h2>
<ul>
  <li>Support multiple login strategies using parallel coroutine execution</li>
  <li>Improve user experience by handling multi-endpoint authentication simultaneously</li>
  <li>Maintain clean architecture by extending <code>AsyncUseCase</code> usage and ViewModel result collection</li>
</ul>

<hr />

<h2>📦 Key Features</h2>
<ul>
  <li><code>LoginVersionTwoUseCase</code>: A second use case executed in parallel with <code>LoginUseCase</code></li>
  <li>Fragments and ViewModels updated to launch both use cases using <code>viewModelScope.launch { ... }</code> and <code>async</code></li>
  <li>Unified result handling with <code>OutCome</code> and <code>LiveDataResource</code> for predictable state transitions</li>
</ul>

<hr />

<h2>🧱 Updated Components</h2>
<ul>
  <li><code>BasicRDFirstFragment</code> and <code>BasicRDFirstViewModel</code>: now trigger parallel login flows</li>
  <li><code>secondScreen</code> ViewModel: mirrors parallel flow with result mapping</li>
  <li>Updated layouts: new UI strings and progress states</li>
  <li>Navigation graph enhancements for improved screen transition and backstack handling</li>
</ul>

<hr />

<h2>🧪 Testing Scenarios</h2>
<ul>
  <li>Test login with API A and API B firing concurrently</li>
  <li>Validate OutCome state resolution for both success and failure cases</li>
  <li>Use slow network to observe parallel coroutine independence</li>
  <li>Check if the final state correctly reflects the latest successful result or fallback error</li>
</ul>

<hr />

<h2>📄 Recommended Pattern</h2>
<p>Each login use case encapsulates a separate logic unit, allowing independent request dispatch. ViewModel collects both results using coroutine <code>async { }</code> and processes their responses within a unified result mapper.</p>

<pre><code>
viewModelScope.launch {
  val first = async { loginUseCase(...) }
  val second = async { loginVersionTwoUseCase(...) }

  val result1 = first.await()
  val result2 = second.await()
  handleCombinedResult(result1, result2)
}
</code></pre>

<hr />

<h2>✅ Summary</h2>
<p>
  This upgrade empowers the app to handle more robust authentication scenarios, such as social login + standard login fallback, or identity verification + OTP. It also serves as a practical case study in applying structured concurrency with Kotlin coroutines inside MVVM architecture.
</p>
