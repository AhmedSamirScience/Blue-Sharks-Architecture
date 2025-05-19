<body>

  <h1>📦 Clean Modular Android Architecture</h1>
  <p>
    This repository follows a fully modularized architecture with clean layering, reactive ViewModel patterns, remote and local data management,
    and scalable UI conventions. The project was developed incrementally through development branches focused on distinct responsibilities.
  </p>

  <hr />

  <h2>🔧 01 - Build-Src Module</h2>
  <ul>
    <li>Centralized Gradle plugin setup for <strong>Dokka</strong>, <strong>Ktlint</strong>, <strong>Detekt</strong>, <strong>Spotless</strong>, <strong>Prettier</strong>, and <strong>Gradle Versions Plugin</strong></li>
    <li>Ensures consistent formatting, linting, documentation, and dependency version tracking</li>
    <li>Supports scalable Gradle configuration across all modules</li>
  </ul>

  <h2>🎨 02 - UI Core Layer</h2>
  <ul>
    <li>Defines shared animations, styles, colors, and layout helpers</li>
    <li>Introduced naming conventions and folder structure standards</li>
    <li>Includes utilities for form input, date/time pickers, and layout contracts</li>
  </ul>

  <h2>🧱 03 - Presentation Core</h2>
  <ul>
    <li>Includes base classes: <code>BaseFragment</code>, <code>BaseActivity</code>, <code>BaseViewModel</code></li>
    <li>ViewModel state management with <code>ViewModelStateHandler</code> and <code>ViewStateManager</code></li>
    <li>Navigation support via Safe Args, Fragment setup contract, and lifecycle-safe logging</li>
  </ul>

  <h2>📦 04 - ViewModel Use Cases</h2>
  <ul>
    <li>Demonstrates various ViewModel scopes:
      <ul>
        <li>Activity-scoped ViewModel (<code>activityViewModels()</code>)</li>
        <li>NavGraph-scoped ViewModel (<code>getBackStackEntry()</code>)</li>
        <li>Basic ViewModel lifecycle with StateFlow</li>
      </ul>
    </li>
    <li>Fragments share and observe ViewModels safely across multiple scopes</li>
    <li>Supports onboarding, wizard, and multi-screen coordination patterns</li>
  </ul>

  <h2>🔌 05 - Remote Data & Storage</h2>
  <ul>
    <li><strong>RemoteDataModule</strong>: Retrofit, OkHttp, DI with Hilt, custom interceptors</li>
    <li><strong>Parallel Login UseCases</strong>: Async operations with outcome mapping</li>
    <li><strong>WorkManager Setup</strong>: Retry scheduling and Hilt integration for background jobs</li>
    <li><strong>Room Database</strong>: Offline caching with encryption and secure DAO access</li>
    <li><strong>Preference DataStore</strong>: Key-value persistence via EncryptedPreferencesDataStoreManager</li>
  </ul>

  <hr />

  <h2>🧠 Architecture Highlights</h2>
  <ul>
    <li>✅ Clean separation of Data, Domain, and Presentation layers</li>
    <li>📦 Modular Gradle project with shared dependencies and ProGuard optimization</li>
    <li>🔁 Lifecycle-aware flows using <code>StateFlow</code>, <code>repeatOnLifecycle</code>, and coroutine scopes</li>
    <li>🔐 Secure data handling using Android KeyStore and DataStore encryption</li>
    <li>📊 Scalable feature modules with testable ViewModel + UI patterns</li>
  </ul>

  <h2>📁 Folder Overview</h2>
  <ul>
    <li><code>buildSrc/</code> – Central plugin management and Gradle config</li>
    <li><code>core/ui/</code> – Shared UI definitions and helpers</li>
    <li><code>core/data/</code> – Networking, interceptors, encryption, Room</li>
    <li><code>core/domain/</code> – Business logic, use cases, outcomes</li>
    <li><code>core/presentation/</code> – Base classes and lifecycle utils</li>
    <li><code>feature/viewmodelcases/</code> – ViewModel integration examples</li>
    <li><code>feature/remotedata/</code> – Login API & use case execution</li>
    <li><code>feature/offlinedata/</code> – Room DB + DataStore integration</li>
  </ul>

  <hr />

  <h2>✅ Final Notes</h2>
  <p>This master branch integrates all development efforts across architecture, UI consistency, data handling, background jobs, and reactive ViewModel design. Each module is independently testable, well-documented, and structured for scale.</p>

  <p><strong>Branch:</strong> <code>main</code> or <code>master</code></p>
</body>
</html>
