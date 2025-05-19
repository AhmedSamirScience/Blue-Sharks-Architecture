<h1>⚙️ WorkManager Integration with Retry & Hilt – Feature Branch Overview</h1>

<p>This branch introduces a resilient background job system using Android’s <code>WorkManager</code> API, fully integrated with <strong>Hilt</strong> for dependency injection and enhanced with structured retry handling and logging.</p>

<hr />

<h2>🎯 Purpose</h2>
<ul>
  <li>Ensure reliable background task execution (e.g., login, sync, etc.)</li>
  <li>Integrate with Hilt for clean dependency injection into workers</li>
  <li>Support automatic retries using <code>Result.retry()</code> and backoff policies</li>
</ul>

<hr />

<h2>🧱 Key Components Added</h2>
<ul>
  <li><strong><code>LoginWorker</code></strong>: A custom worker implementing retry logic via <code>runAttemptCount</code></li>
  <li><strong><code>MyApp.kt</code></strong>: Overrides <code>Configuration.Provider</code> to inject <code>HiltWorkerFactory</code></li>
  <li><strong><code>AndroidManifest.xml</code></strong>: Disabled default WorkManager initialization</li>
</ul>

<hr />

<h2>🔁 Retry + Backoff Configuration</h2>
<ul>
  <li>Retry is handled manually using <code>Result.retry()</code></li>
  <li>Retries capped using <code>runAttemptCount</code> to avoid infinite loops</li>
  <li>Configured <strong>exponential backoff</strong> delay of <code>15 seconds</code></li>
  <li>Scheduled next retry with <code>scheduleNext()</code> for long-running workers</li>
</ul>

<pre><code>
// Retry logic inside doWork()
if (runAttemptCount < MAX_RETRIES) {
    return Result.retry()
} else {
    return Result.failure()
}
</code></pre>

<hr />

<h2>🧩 Unique Work Enqueueing</h2>
<ul>
  <li>Jobs are scheduled using <code>enqueueUniqueWork</code> to avoid duplicates</li>
  <li><strong>UNIQUE_WORK_NAME</strong> ensures only one instance of the job is active at any time</li>
</ul>

<pre><code>
WorkManager.getInstance(context).enqueueUniqueWork(
    UNIQUE_WORK_NAME,
    ExistingWorkPolicy.KEEP,
    workRequest
)
</code></pre>

<hr />

<h2>🛠 Integration with Hilt</h2>
<ul>
  <li>Registered <code>HiltWorkerFactory</code> via <code>MyApp</code> using:</li>
</ul>

<pre><code>
@HiltAndroidApp
class MyApp : Application(), Configuration.Provider {
  @Inject lateinit var workerFactory: HiltWorkerFactory

  override fun getWorkManagerConfiguration(): Configuration {
    return Configuration.Builder()
      .setWorkerFactory(workerFactory)
      .build()
  }
}
</code></pre>

<hr />

<h2>📊 Logging & Debugging</h2>
<ul>
  <li>Integrated structured logs via custom <code>Logger</code></li>
  <li>Captured worker lifecycle events (onStart, onRetry, onFailure)</li>
  <li>Useful for debugging backoff timings, worker state, and crash analysis</li>
</ul>

<hr />

<h2>✅ Summary</h2>
<p>
  This branch enhances app resilience by ensuring background operations are handled gracefully with retry logic and proper lifecycle awareness. Leveraging Hilt and structured scheduling allows better testability, observability, and long-term maintainability for worker-based architecture.
</p>
