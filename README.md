<h1>💾 Room Database + Encrypted Offline Support – Feature Overview</h1>

<p>This feature branch establishes a secure and modular offline persistence layer using <strong>Room Database</strong>, integrated with <strong>Hilt</strong>, and supports <strong>encryption via CryptoHelper</strong> for sensitive fields like usernames.</p>

<hr />

<h2>🎯 Key Objectives</h2>
<ul>
  <li>Enable persistent login caching using Room and DAOs</li>
  <li>Support encryption/decryption of sensitive data (e.g., userName)</li>
  <li>Allow login fallback to offline cache in the absence of internet</li>
  <li>Integrate all offline data flows with existing Worker and ViewModel architecture</li>
</ul>

<hr />

<h2>🧱 Key Components Introduced</h2>

<h3>🛡️ Encryption</h3>
<ul>
  <li><code>CryptoHelper</code>: Centralized utility to encrypt/decrypt using KeyStore</li>
  <li><code>KeyModelRequest/Response</code>: Model for algorithm/blockMode configuration</li>
  <li><code>EncryptionDecryptionManager</code>: Low-level KeyStore encryption API</li>
  <li><code>SecurityModule</code>: Hilt module to provide CryptoHelper</li>
</ul>

<h3>🏠 Room Database Architecture</h3>
<ul>
  <li><code>BaseDao</code> and <code>BaseRoomDatabase</code>: Shared Room architecture</li>
  <li><code>LoginEntity</code>: Stores encrypted userName as String</li>
  <li><code>LoginDao</code>: CRUD operations on cached login</li>
  <li><code>AuthDatabase</code>: RoomDatabase implementation</li>
</ul>

<h3>📡 Repository & Use Cases</h3>
<ul>
  <li><code>AuthRepositoryImpl</code>: Returns login from remote API or Room fallback</li>
  <li><code>CacheLoginOfflineUseCase</code>: Stores login after success</li>
  <li><code>GetLastLoginOfflineUseCase</code>: Reads latest login from DB</li>
  <li><code>GetAllLoginsOfflineUseCase</code>: Used for listing all cached sessions</li>
</ul>

<h3>🧩 ViewModel & UI</h3>
<ul>
  <li><code>RoomDataBaseViewModel</code>: ViewModel for Room-based flows</li>
  <li><code>RoomDataBaseFragment</code>: Displays single login</li>
  <li><code>RoomDBSecondFragment</code>: Lists all logins with adapter</li>
  <li><code>LoginListAdapter</code>: RecyclerView adapter for offline login records</li>
</ul>

<hr />

<h2>🔐 CryptoHelper Usage Example</h2>
<pre><code>
// Encrypt before saving to Room
val encryptedUsername = cryptoHelper.encrypt(username)

// Decrypt after reading from Room
val decryptedUsername = cryptoHelper.decrypt(entity.userName)
</code></pre>

<hr />

<h2>📊 Worker Integration</h2>
<ul>
  <li><code>LoginWorker</code>: Refactored to use CryptoHelper when storing credentials</li>
  <li>Now supports fallback to Room in retry scenarios</li>
</ul>

<hr />

<h2>🧪 Testing Tips</h2>
<ul>
  <li>Run offline and test fallback to Room flow using no-network login</li>
  <li>Use Logcat to inspect encryption success via tagged logs</li>
  <li>Check Room DB contents manually (e.g., via <code>adb shell</code>)</li>
</ul>

<hr />

<h2>📁 File Highlights</h2>
<ul>
  <li><code>BaseRoomDatabase.kt</code>, <code>BaseDao.kt</code>, <code>AuthDatabase.kt</code></li>
  <li><code>CryptoHelper.kt</code>, <code>KeyStoreHelper.kt</code>, <code>EncryptionDecryptionManager.kt</code></li>
  <li><code>RoomDataBaseViewModel.kt</code>, <code>RoomDataBaseFragment.kt</code></li>
  <li><code>LoginEntity.kt</code>, <code>LoginDao.kt</code>, <code>RoomModule.kt</code></li>
  <li><code>item_records.xml</code>, <code>nav_roomdatabase.xml</code></li>
</ul>

<hr />

<h2>✅ Summary</h2>
<p>This branch adds a robust offline persistence layer using Room DB with encryption and Hilt integration. By caching sensitive login data securely, the app can now recover gracefully from no-network scenarios, all while maintaining modular, testable architecture.</p>
