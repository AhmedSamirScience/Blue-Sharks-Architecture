<h1>🗂️ Encrypted Preference DataStore Integration – Feature Overview</h1>

<p>This feature branch introduces a modern, secure, and modular way to persist small amounts of key-value data using <strong>Android DataStore</strong>, enhanced with encryption. It replaces SharedPreferences with a safer and lifecycle-aware alternative.</p>

<hr />


<h2>🔐 Key Features</h2>
<ul>
  <li><strong>Encrypted DataStore:</strong> Ensures secure key-value storage using encryption wrappers around DataStore.</li>
  <li><strong>Hilt-based Injection:</strong> Provides singleton access to the DataStore via <code>DataStoreModule</code>.</li>
  <li><strong>Domain-Level UseCases:</strong> Abstracts read/write logic into reusable business logic classes.</li>
  <li><strong>MVVM Integration:</strong> ViewModels interact with the domain layer to read/write preferences reactively.</li>
</ul>

<hr />

<h2>📁 Structure Overview</h2>
<ul>
  <li><code>EncryptedPreferencesDataStoreManager.kt</code> – Manages secure DataStore operations</li>
  <li><code>DataStoreModule.kt</code> – Hilt module to inject the DataStore manager</li>
  <li><strong>Use Cases:</strong>
    <ul>
      <li><code>SaveUserIdUseCase</code>, <code>GetUserIdUseCase</code></li>
      <li><code>SaveServerTimeUseCase</code>, <code>GetServerTimeUseCase</code></li>
    </ul>
  </li>
  <li><code>AuthRepository.kt</code> – Interface extended to include preference methods</li>
  <li><code>AuthRepositoryImpl.kt</code> – Implements the logic using DataStore manager</li>
  <li><code>PrefDataStoreFirstViewModel.kt</code>, <code>PrefDataStoreSecViewModel.kt</code> – ViewModels handling DataStore logic</li>
</ul>

<hr />

<h2>💡 Use Case Example</h2>
<pre><code>// Saving userId securely
saveUserIdUseCase("12345")

// Getting userId reactively
val userId = getUserIdUseCase().first()
</code></pre>

<hr />

<h2>📲 UI & Navigation</h2>
<ul>
  <li>Two screens were created to demonstrate DataStore usage across ViewModels</li>
  <li>ViewModel observes the stored state using Flow and binds it to UI</li>
  <li>Navigation graph updated to support <code>prefDataStore</code> flow</li>
</ul>

<hr />

<h2>📦 Updated/Added Files</h2>
<ul>
  <li><code>EncryptedPreferencesDataStoreManager.kt</code></li>
  <li><code>DataStoreModule.kt</code></li>
  <li><code>AuthRepository.kt</code>, <code>AuthRepositoryImpl.kt</code></li>
  <li><code>PrefDataStoreFirstViewModel.kt</code>, <code>PrefDataStoreSecViewModel.kt</code></li>
  <li><code>SaveUserIdUseCase</code>, <code>GetUserIdUseCase</code></li>
  <li><code>SaveServerTimeUseCase</code>, <code>GetServerTimeUseCase</code></li>
  <li>All related layout files and <code>nav_pref_data_store.xml</code></li>
</ul>

<hr />

<h2>✅ Benefits</h2>
<ul>
  <li>More secure and maintainable than <code>SharedPreferences</code></li>
  <li>Fully supports coroutine and reactive programming with <code>Flow</code></li>
  <li>Encourages separation of concerns with proper layering: <code>UI → ViewModel → UseCase → Repository</code></li>
  <li>Modular and testable due to clear abstraction boundaries</li>
</ul>

<hr />

<h2>📚 Learning Resources</h2>
<ul>
  <li><a href="https://developer.android.com/topic/libraries/architecture/datastore">Android DataStore Official Docs</a></li>
  <li><a href="https://developer.android.com/topic/libraries/architecture">Guide to Android App Architecture</a></li>
</ul>

