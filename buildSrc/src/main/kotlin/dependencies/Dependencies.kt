package dependencies

object Dependencies {

    //region Default Libraries
    //region Android Core + UI Framework
    /**
     * Android KTX: Kotlin Extensions for Android Framework
     *
     * Dependency:
     *     implementation("androidx.core:core-ktx:${DependenciesVersions.ANDROID_CORE}")
     *
     * Provides Kotlin-friendly extension functions to simplify Android development.
     *
     * ✅ Top Kotlin extensions (grouped by type):
     *
     * 🔹 SharedPreferences
     * sharedPreferences.edit {
     *     putString("key", "value")
     *     remove("key")
     *     clear()
     * }
     *
     * 🔹 Bundle creation
     * val bundle = bundleOf("key" to "value", "id" to 123)
     *
     * 🔹 Context extensions
     * val clipboard = context.getSystemService<ClipboardManager>()
     * val isNight = context.isNightModeActive
     * val isConnected = context.isConnected
     *
     * 🔹 View extensions
     * view.isVisible = true
     * view.isGone = false
     * view.isInvisible = true
     * view.doOnPreDraw { ... }
     * view.postDelayed({ ... }, 1000)
     *
     * 🔹 TextView and EditText
     * textView.text = "Hello"
     * editText.doAfterTextChanged { text -> ... }
     * editText.doOnTextChanged { text, start, before, count -> ... }
     *
     * 🔹 LiveData
     * liveData.observe(owner) { value -> ... }
     *
     * 🔹 Parcelables
     * @Parcelize
     * data class User(val id: Int, val name: String) : Parcelable
     *
     * 🔹 Handler and Looper
     * Handler(Looper.getMainLooper()).postDelayed({ ... }, 500)
     *
     * 🔹 File and Path
     * val file = File(context.filesDir, "myfile.txt")
     * file.readText()
     * file.writeText("Hello World")
     *
     * 🔹 ContentValues
     * val values = contentValuesOf(
     *     "name" to "Ahmed",
     *     "age" to 30
     * )
     *
     * 🔹 Color and Resources (may require your own extensions)
     * val color = context.getColorCompat(R.color.my_color)
     * val drawable = context.getDrawableCompat(R.drawable.ic_icon)
     */
    const val ANDROID_CORE = "androidx.core:core-ktx:${DependenciesVersions.ANDROID_CORE}"

    /**
     * AppCompat: Backward Compatibility Support Library
     *
     * Dependency:
     *     implementation("androidx.appcompat:appcompat:${DependenciesVersions.APP_COMPAT}")
     *
     * ✅ Purpose:
     * Provides support for newer Android features on older Android versions.
     * Essential for consistent UI behavior, theming, and component access across API levels.
     *
     * ✅ Common Usage:
     *
     * 🔹 Base Classes
     * - AppCompatActivity           // Instead of Activity
     * - AppCompatDialog             // Instead of Dialog
     * - AppCompatDelegate           // For day/night mode handling
     *
     * 🔹 Theming & Styling
     * - Theme.AppCompat.Light.NoActionBar
     * - Enables vector drawables on older Android versions.
     *
     * 🔹 Support Widgets
     * - AppCompatButton
     * - AppCompatImageView
     * - AppCompatEditText
     * - AppCompatCheckBox
     * (used internally by `MaterialComponents` and most XML widgets)
     *
     * 🔹 Dark Mode Support (Pre-Android 10)
     * AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
     *
     * 🔹 Action Bar / Toolbar
     * - setSupportActionBar(toolbar)
     * - supportActionBar?.setDisplayHomeAsUpEnabled(true)
     *
     * 🔹 Menu Support
     * - onCreateOptionsMenu(menu: Menu)
     * - onOptionsItemSelected(item: MenuItem)
     *
     * ⚠️ Must be used when targeting API levels below 29 to maintain visual consistency.
     */
    const val APP_COMPAT = "androidx.appcompat:appcompat:${DependenciesVersions.APP_COMPAT}"

    /**
     * Material Components for Android (MDC)
     *
     * Dependency:
     *     implementation("com.google.android.material:material:${DependenciesVersions.ANDROID_MATERIAL}")
     *
     * ✅ Purpose:
     * Official Google library for implementing **Material Design UI components** and theming.
     * Provides a modern look, accessibility, animations, and responsive design patterns.
     *
     * ✅ Common Components:
     *
     * 🔹 Buttons
     * - MaterialButton
     * - MaterialButtonToggleGroup
     *
     * 🔹 Text Fields
     * - TextInputLayout
     * - TextInputEditText
     *   → Features: hint animation, error messages, password toggle, counter, helper text
     *
     * 🔹 Navigation
     * - BottomNavigationView
     * - NavigationView (for side drawer)
     * - TabLayout
     *
     * 🔹 Dialogs & Bottom Sheets
     * - MaterialAlertDialogBuilder
     * - BottomSheetDialog
     * - BottomSheetDialogFragment
     *
     * 🔹 Snackbars & Toasts
     * - Snackbar.make(...).setAction("Undo") { ... }.show()
     *
     * 🔹 App Bar & Scrolling
     * - AppBarLayout
     * - CollapsingToolbarLayout
     * - CoordinatorLayout (supports scroll behavior)
     *
     * 🔹 Chips & Selection Controls
     * - Chip, ChipGroup
     * - RadioButton, SwitchMaterial, CheckBoxMaterial
     *
     * 🔹 Others
     * - MaterialCardView
     * - FloatingActionButton
     * - Slider
     * - ProgressIndicator (LinearProgressIndicator, CircularProgressIndicator)
     *
     * ✅ Theming Features:
     * - Material Theming (custom typography, shapes, colors)
     * - `Theme.Material3.*` and `Theme.MaterialComponents.*`
     * - Supports both **Material 2** and **Material 3 (M3)** design systems
     *
     * ⚠️ Requires using a compatible theme:
     * → Theme.MaterialComponents.DayNight.NoActionBar (for M2)
     * → Theme.Material3.DayNight (for M3)
     */
    const val ANDROID_MATERIAL = "com.google.android.material:material:${DependenciesVersions.ANDROID_MATERIAL}"

    /**
     * ConstraintLayout: Advanced Layout Manager for Android
     *
     * Dependency:
     *     implementation("androidx.constraintlayout:constraintlayout:${DependenciesVersions.CONSTRAINT_LAYOUT}")
     *
     * ✅ Purpose:
     * Enables building complex and responsive UI layouts with a flat view hierarchy.
     * Offers performance and flexibility superior to nested LinearLayouts or RelativeLayouts.
     *
     * ✅ Key Features:
     *
     * 🔹 Basic Constraints
     * - layout_constraintTop_toTopOf, layout_constraintBottom_toBottomOf
     * - layout_constraintStart_toStartOf, layout_constraintEnd_toEndOf
     * - Allows views to align to parent or other sibling views
     *
     * 🔹 Chains
     * - Horizontal or vertical chains: packed, spread, spread_inside
     * - Use `layout_constraintHorizontal_chainStyle` and `layout_constraintVertical_chainStyle`
     *
     * 🔹 Guidelines
     * - Vertical or horizontal invisible lines to align content
     * - Fixed position or percentage-based
     *
     * 🔹 Barriers
     * - Dynamic positioning based on the size of multiple widgets
     * - Useful for adapting to varying text or content lengths
     *
     * 🔹 Groups
     * - Group multiple views to show/hide them together
     *
     * 🔹 Placeholders
     * - Temporarily swap views dynamically at runtime
     *
     * 🔹 Flow (from ConstraintLayout 2.0+)
     * - Handles dynamic wrapping, alignment, and spacing of items (similar to FlexboxLayout)
     *
     * 🔹 Layer (from ConstraintLayout 2.1+)
     * - Apply common transformations (scale, rotate, alpha) to multiple views together
     *
     * 🔹 Dimension Constraints
     * - Match constraints: `0dp` with `app:layout_constraintWidth_default="spread"`
     * - Ratio support: `layout_constraintDimensionRatio="16:9"`
     *
     * ✅ Tools Support:
     * - Fully supported in Android Studio Layout Editor with visual drag-and-drop
     *
     * ✅ Best Practices:
     * - Prefer ConstraintLayout for performance in complex UIs
     * - Avoid deeply nested layouts (e.g., nested LinearLayouts) when possible
     */
    const val CONSTRAIN_LAYOUT = "androidx.constraintlayout:constraintlayout:${DependenciesVersions.CONSTRAINT_LAYOUT}"

    /**
     * AndroidX Legacy Support (v4)
     *
     * Dependency:
     *     implementation("androidx.legacy:legacy-support-v4:${DependenciesVersions.LEGACY_SUPPORT}")
     *
     * ✅ Purpose:
     * Brings forward many classes and components from the old `android.support.v4` package into AndroidX.
     * Provides backward compatibility for features not yet fully migrated or needed for legacy app modules.
     *
     * ✅ Key Components Included:
     *
     * 🔹 Loaders
     * - LoaderManager, CursorLoader, AsyncTaskLoader (for backward-compatible background loading)
     *
     * 🔹 Broadcast Helpers
     * - LocalBroadcastManager → Local-only broadcasts within the app process
     *
     * 🔹 Media
     * - MediaBrowserCompat
     * - MediaSessionCompat (used in legacy media playback)
     *
     * 🔹 App Widgets
     * - AppWidgetHost, AppWidgetProviderInfo
     *
     * 🔹 Accessibility
     * - AccessibilityManagerCompat
     *
     * 🔹 Fragment Compatibility (before `androidx.fragment`)
     * - For supporting older Fragment APIs (used prior to full migration to `androidx.fragment`)
     *
     * ✅ When to Use:
     * - Only if you maintain legacy code or use libraries that still depend on older components.
     * - Safe to remove in fully modernized apps that no longer rely on support-v4 components.
     *
     * ⚠️ Note:
     * - This is a transitional library — not needed for new Jetpack-based apps using modern APIs.
     * - Prefer replacing with individual modern components (e.g., `androidx.fragment`, `androidx.media`, etc.).
     */
    const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:${DependenciesVersions.LEGACY_SUPPORT}"
    //endregion

    //region Testing (Unit + Instrumentation)
    /**
     * JUnit: Java Unit Testing Framework (v4)
     *
     * Dependency:
     *     testImplementation("junit:junit:${DependenciesVersions.JUNIT}")
     *
     * ✅ Purpose:
     * Core framework for writing and running **unit tests** in JVM (non-Android-dependent).
     * Used in the `test/` source set to test pure Kotlin or Java code.
     *
     * ✅ Common Annotations:
     *
     * 🔹 @Test
     * Marks a method as a test case.
     *
     * 🔹 @Before
     * Runs before each test method. Useful for setting up test environments.
     *
     * 🔹 @After
     * Runs after each test method. Used for cleanup.
     *
     * 🔹 @BeforeClass
     * Runs once before any test in the class (must be static).
     *
     * 🔹 @AfterClass
     * Runs once after all tests in the class (must be static).
     *
     * 🔹 @Ignore
     * Skips a specific test method.
     *
     * ✅ Common Assertions:
     *
     * - assertEquals(expected, actual)
     * - assertNotEquals(unexpected, actual)
     * - assertTrue(condition)
     * - assertFalse(condition)
     * - assertNull(value)
     * - assertNotNull(value)
     * - assertSame(expected, actual)
     * - assertNotSame(unexpected, actual)
     * - fail("This should never happen")
     *
     * ✅ Example:
     * ```kotlin
     * class MathUtilsTest {
     *     @Test
     *     fun addition_isCorrect() {
     *         assertEquals(4, 2 + 2)
     *     }
     * }
     * ```
     *
     * ✅ Notes:
     * - Used primarily for **pure business logic tests**
     * - Does not require Android runtime or emulator/device
     * - Fast to run and CI-friendly
     */
    const val JUNIT = "junit:junit:${DependenciesVersions.JUNIT}"

    /**
     * AndroidX Test: JUnit Extensions (androidx.test.ext:junit)
     *
     * Dependency:
     *     androidTestImplementation("androidx.test.ext:junit:${DependenciesVersions.TEST_EXT}")
     *
     * ✅ Purpose:
     * Provides Android-specific JUnit 4 integration to run **instrumented tests** on real devices or emulators.
     * Used in the `androidTest/` source set for testing Android components (Activities, Fragments, etc.).
     *
     * ✅ Key Features:
     *
     * 🔹 AndroidJUnit4 Runner
     * - Enables using `@RunWith(AndroidJUnit4::class)` for test classes.
     * - Required to run Android-specific code inside instrumented tests.
     *
     * 🔹 ActivityScenarioRule (via androidx.test.core)
     * - Automatically launches an `Activity` for testing and closes it after.
     * ```kotlin
     * @get:Rule
     * val activityRule = ActivityScenarioRule(MainActivity::class.java)
     * ```
     *
     * ✅ Common Usage:
     *
     * ```kotlin
     * @RunWith(AndroidJUnit4::class)
     * class LoginScreenTest {
     *
     *     @Before
     *     fun setup() {
     *         // setup before each test
     *     }
     *
     *     @Test
     *     fun loginButton_isDisplayed() {
     *         onView(withId(R.id.loginButton)).check(matches(isDisplayed()))
     *     }
     * }
     * ```
     *
     * ✅ Notes:
     * - Works together with `espresso-core` for UI interaction and assertions.
     * - Automatically connects to `AndroidJUnitRunner` defined in `build.gradle`.
     * - Requires a device or emulator to run (unlike pure `junit:junit`).
     */
    const val TEST_EXT = "androidx.test.ext:junit:${DependenciesVersions.TEST_EXT}"

    /**
     * Espresso Core: Android UI Testing Framework
     *
     * Dependency:
     *     androidTestImplementation("androidx.test.espresso:espresso-core:${DependenciesVersions.TEST_ESPRESSO}")
     *
     * ✅ Purpose:
     * Provides APIs to write reliable **UI tests** for Android apps.
     * Simulates user interactions and verifies UI behavior in real-time on devices/emulators.
     *
     * ✅ Key APIs:
     *
     * 🔹 onView(ViewMatcher)
     * Targets a specific view for interaction or assertion.
     * - onView(withId(R.id.my_button))
     * - onView(withText("Submit"))
     * - onView(withContentDescription("Settings"))
     * - onView(withHint("Email"))
     * - onView(isDisplayed())
     *
     * 🔹 perform(ViewAction)
     * Executes user actions.
     * - click()
     * - typeText("Hello")
     * - replaceText("Updated")
     * - clearText()
     * - pressBack()
     * - scrollTo()
     * - closeSoftKeyboard()
     * - longClick()
     * - doubleClick()
     * - swipeLeft(), swipeRight(), swipeUp(), swipeDown()
     *
     * 🔹 check(ViewAssertion)
     * Verifies UI conditions.
     * - matches(isDisplayed())
     * - matches(withText("Welcome"))
     * - matches(isChecked())
     * - matches(isEnabled())
     * - matches(withHint("Enter your name"))
     * - matches(hasErrorText("Field required"))
     * - matches(withEffectiveVisibility(Visibility.GONE))
     *
     * 🔹 onData()
     * Interacts with AdapterViews (e.g., ListView, Spinner).
     * - onData(hasToString(startsWith("Item"))).perform(click())
     *
     * 🔹 IdlingResource
     * Handles synchronization with background tasks to prevent flaky tests.
     *
     * ✅ Example:
     * ```kotlin
     * @Test
     * fun submitButton_click_navigatesToHome() {
     *     onView(withId(R.id.submitButton)).perform(click())
     *     onView(withId(R.id.homeScreen)).check(matches(isDisplayed()))
     * }
     * ```
     *
     * ✅ Notes:
     * - Works with `ActivityScenarioRule` and `AndroidJUnit4`.
     * - Must run on an emulator or device (`androidTest/`).
     * - Often used with `androidx.test.ext:junit` for full instrumentation support.
     */
    const val TEST_ESPRESSO = "androidx.test.espresso:espresso-core:${DependenciesVersions.TEST_ESPRESSO}"
    //endregion
    //endregion

    //region Jetpack ViewModel & Lifecycle ---(viewModel dependencies)
    /**
     * AndroidX Lifecycle LiveData KTX
     *
     * Dependency:
     *     implementation("androidx.lifecycle:lifecycle-livedata-ktx:${DependenciesVersions.LIFECYCLE_LIVE_DATA}")
     *
     * ✅ Purpose:
     * Provides Kotlin extensions for using **LiveData**, a lifecycle-aware observable data holder class.
     * Helps build reactive UIs that automatically respond to data changes while respecting lifecycle states.
     *
     * ✅ Key Features:
     *
     * 🔹 LiveData Basics
     * - `LiveData<T>` → Observable data holder (read-only)
     * - `MutableLiveData<T>` → Mutable version for internal use
     * ```kotlin
     * val liveData = MutableLiveData<String>()
     * liveData.value = "Hello"
     * liveData.observe(viewLifecycleOwner) { value -> ... }
     * ```
     *
     * 🔹 Lifecycle Awareness
     * - Automatically stops observing when lifecycle owner is `DESTROYED`
     * - Helps prevent memory leaks and crashes from invalid observers
     *
     * 🔹 Kotlin Extensions (from `-ktx`)
     * - `observe` with lambda:
     * ```kotlin
     * myLiveData.observe(viewLifecycleOwner) { data ->
     *     // handle data
     * }
     * ```
     * - `LiveDataScope` for building `liveData {}` blocks (coroutine-style)
     * ```kotlin
     * val liveData = liveData {
     *     val result = repository.loadFromNetwork()
     *     emit(result)
     * }
     * ```
     *
     * 🔹 Transformations
     * - `Transformations.map()`, `Transformations.switchMap()` for data conversion
     *
     * ✅ Usage in ViewModel:
     * ```kotlin
     * class MyViewModel : ViewModel() {
     *     private val _userName = MutableLiveData<String>()
     *     val userName: LiveData<String> = _userName
     *
     *     fun updateName(newName: String) {
     *         _userName.value = newName
     *     }
     * }
     * ```
     *
     * ✅ Best Practices:
     * - Expose only `LiveData<T>` to UI (not `MutableLiveData`)
     * - Use with `viewLifecycleOwner` in fragments
     * - Combine with `LiveDataScope` or `CoroutineScope` for async emissions
     *
     * ✅ Notes:
     * - Works great with `ViewModel`, `DataBinding`, and `Navigation` components.
     * - Alternative to `StateFlow` when full lifecycle integration is preferred.
     */
    const val LIFECYCLE_LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:${DependenciesVersions.LIFECYCLE_LIVE_DATA}"

    /**
     * AndroidX Lifecycle ViewModel KTX
     *
     * Dependency:
     *     implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.LIFECYCLE_VIEWMODEL}")
     *
     * ✅ Purpose:
     * Provides Kotlin extensions and coroutine support for **ViewModel**, a lifecycle-aware component
     * designed to store and manage UI-related data in a lifecycle-conscious way.
     *
     * ✅ Key Features:
     *
     * 🔹 ViewModel Basics
     * - Designed to survive configuration changes (e.g., screen rotation)
     * - Used for storing and managing UI state/data
     * ```kotlin
     * class MyViewModel : ViewModel() {
     *     val userName = MutableLiveData<String>()
     * }
     * ```
     *
     * 🔹 Kotlin Extensions (from `-ktx`)
     * - ViewModel scopes for launching coroutines:
     * ```kotlin
     * viewModelScope.launch {
     *     val result = repository.fetchData()
     *     _uiState.value = result
     * }
     * ```
     * - Automatically canceled when ViewModel is cleared (prevents leaks)
     *
     * 🔹 ViewModel Instantiation
     * - With `ViewModelProvider(...)`
     * - With Hilt or Koin for DI (e.g., `@HiltViewModel`)
     * ```kotlin
     * val viewModel: MyViewModel by viewModels()
     * ```
     *
     * 🔹 Shared ViewModel
     * - Use `activityViewModels()` to share data between fragments
     * ```kotlin
     * val sharedViewModel: MyViewModel by activityViewModels()
     * ```
     *
     * ✅ Integration:
     * - Works seamlessly with `LiveData`, `StateFlow`, `Room`, `Navigation`, and `DataBinding`
     *
     * ✅ Best Practices:
     * - Keep ViewModel clean: no context or view references
     * - Use `SavedStateHandle` for state restoration
     * - Separate UI logic from business logic with `Repository` layer
     *
     * ✅ Notes:
     * - Enables coroutine support via `viewModelScope` (no need for manual `Job`/`Dispatcher` setup)
     * - Often used alongside `lifecycle-livedata-ktx` and `lifecycle-runtime-ktx`
     */
    const val LIFECYCLE_VIEWMODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:${DependenciesVersions.LIFECYCLE_VIEWMODEL}"

    /**
     * AndroidX Lifecycle Runtime KTX
     *
     * Dependency:
     *     implementation("androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFECYCLE_RUNTIME_KTX}")
     *
     * ✅ Purpose:
     * Provides lifecycle-aware components and **Kotlin coroutine support** for observing and reacting to
     * lifecycle events in a clean, idiomatic way.
     *
     * ✅ Key Features:
     *
     * 🔹 Lifecycle-Aware Coroutine Scopes
     * - Built-in `lifecycleScope` and `repeatOnLifecycle()` extensions
     * ```kotlin
     * lifecycleScope.launch {
     *     // Runs when lifecycle is at least STARTED
     * }
     * ```
     * ```kotlin
     * viewLifecycleOwner.lifecycleScope.launch {
     *     repeatOnLifecycle(Lifecycle.State.STARTED) {
     *         myFlow.collect { value -> ... }
     *     }
     * }
     * ```
     *
     * 🔹 Lifecycle-Aware Dispatching
     * - Automatically cancels coroutines when the lifecycle is destroyed
     * - Prevents memory leaks and unexpected crashes from lingering jobs
     *
     * 🔹 Extensions for LifecycleOwner
     * - `lifecycle.currentState`
     * - `lifecycle.addObserver(...)`
     * - `LifecycleObserver`, `DefaultLifecycleObserver`
     *
     * 🔹 ViewModel Lifecycle Integration
     * - Enables `viewModelScope` (provided by `lifecycle-viewmodel-ktx`)
     *
     * ✅ Common Use Cases:
     * - Collecting `Flow` or `StateFlow` in Fragments/Activities safely
     * - Launching background tasks tied to lifecycle (e.g., API calls, animations)
     * - Observing lifecycle without boilerplate
     *
     * ✅ Best Practices:
     * - Use `repeatOnLifecycle` to avoid unnecessary recomputation or collection
     * - Avoid using global scopes inside lifecycle-aware components
     * - Prefer `viewLifecycleOwner.lifecycleScope` inside fragments
     *
     * ✅ Example:
     * ```kotlin
     * viewLifecycleOwner.lifecycleScope.launch {
     *     repeatOnLifecycle(Lifecycle.State.STARTED) {
     *         viewModel.state.collect { state -> render(state) }
     *     }
     * }
     * ```
     */
    const val LIFECYCLE_RUNTIME_KTX = "androidx.lifecycle:lifecycle-runtime-ktx:${DependenciesVersions.LIFECYCLE_RUNTIME_KTX}"
    //endregion

    //region Navigation Component ----(Navigation Graph for Kotlin language implementation)
    /**
     * Android Jetpack Navigation: Fragment KTX
     *
     * Dependency:
     *     implementation("androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.NAV_VERSION}")
     *
     * ✅ Purpose:
     * Provides Kotlin extensions and Jetpack Navigation support for Fragment-based apps.
     * Enables type-safe and lifecycle-aware **navigation between fragments** using Navigation Components.
     *
     * ✅ Key Features:
     *
     * 🔹 Navigation with NavController
     * - findNavController() extension on Fragment/View
     * ```kotlin
     * findNavController().navigate(R.id.action_home_to_details)
     * findNavController().popBackStack()
     * ```
     *
     * 🔹 Safe Args Support (with Gradle plugin)
     * - Pass arguments safely using generated directions
     * ```kotlin
     * val action = HomeFragmentDirections.actionHomeToDetails(userId = 42)
     * findNavController().navigate(action)
     * ```
     *
     * 🔹 Lifecycle Awareness
     * - Navigation operations respect fragment lifecycle (no more "FragmentManager already saved state" crashes)
     *
     * 🔹 ViewModel Sharing
     * - `navGraphViewModels()` and `activityViewModels()` for shared ViewModels across destinations
     *
     * 🔹 onBackPressed Handling
     * - Integrated with Navigation UI to handle back stack cleanly and correctly
     *
     * ✅ Common Extensions:
     * - Fragment.findNavController()
     * - View.findNavController()
     * - requireActivity().onBackPressedDispatcher.addCallback { ... }
     *
     * ✅ Best Practices:
     * - Use with `navigation-ui-ktx` to integrate bottom nav or toolbar actions
     * - Pair with Safe Args for type-safe argument passing
     * - Define navigation graphs in `res/navigation/(*).xml`
     *
     * ✅ Example:
     * ```kotlin
     * // In Fragment
     * val navController = findNavController()
     * navController.navigate(R.id.action_home_to_profile)
     * ```
    */
    const val NAVIGATION_FRAGMENT_KTX = "androidx.navigation:navigation-fragment-ktx:${DependenciesVersions.NAV_VERSION}"

    /**
     * Android Jetpack Navigation: UI KTX
     *
     * Dependency:
     *     implementation("androidx.navigation:navigation-ui-ktx:${DependenciesVersions.NAV_VERSION}")
     *
     * ✅ Purpose:
     * Provides Kotlin extensions and UI binding support for Jetpack Navigation.
     * Integrates `NavController` with common UI components like **Toolbar**, **BottomNavigationView**, and **DrawerLayout**.
     *
     * ✅ Key Features:
     *
     * 🔹 Toolbar Integration
     * - Automatically handles title updates and up/back actions.
     * ```kotlin
     * val navController = findNavController()
     * NavigationUI.setupActionBarWithNavController(activity, navController)
     * NavigationUI.setupWithNavController(toolbar, navController)
     * ```
     *
     * 🔹 BottomNavigationView Integration
     * - Handles navigation via bottom nav items and manages fragment back stack.
     * ```kotlin
     * bottomNav.setupWithNavController(navController)
     * ```
     *
     * 🔹 Navigation Drawer Integration
     * - Binds DrawerMenu selections to navigation destinations.
     * ```kotlin
     * navView.setupWithNavController(navController)
     * ```
     *
     * 🔹 AppBarConfiguration
     * - Allows specifying top-level destinations (where the "up" arrow is replaced with a hamburger).
     * ```kotlin
     * val appBarConfig = AppBarConfiguration(setOf(R.id.homeFragment, R.id.profileFragment), drawerLayout)
     * setupActionBarWithNavController(navController, appBarConfig)
     * ```
     *
     * ✅ Common Classes & Functions:
     * - `NavigationUI.setupWithNavController(...)`
     * - `AppBarConfiguration(...)`
     * - `NavController.navigateUp()`
     *
     * ✅ Notes:
     * - Works in combination with `navigation-fragment-ktx`
     * - Keeps UI state and back stack behavior consistent with platform expectations
     * - Reduces manual wiring of UI components to navigation logic
     */
    const val NAVIGATION_KTX = "androidx.navigation:navigation-ui-ktx:${DependenciesVersions.NAV_VERSION}"
    //endregion

    //region Dependency Injection (Hilt) -------(dagger Hilt)
    /**
     * Dagger Hilt for Android (Core Library)
     *
     * Dependency:
     *     implementation("com.google.dagger:hilt-android:${DependenciesVersions.DAGGER_HILT_ANDROID}")
     *
     * ✅ Purpose:
     * Provides the main runtime library for **Hilt**, a dependency injection (DI) framework built on top of Dagger.
     * Simplifies DI in Android apps by integrating with app components like Activities, Fragments, ViewModels, Services, etc.
     *
     * ✅ Key Features:
     *
     * 🔹 Component-Scoped Dependency Injection
     * - ApplicationComponent (@Singleton)
     * - ActivityComponent (@ActivityScoped)
     * - FragmentComponent (@FragmentScoped)
     * - ViewModelComponent (@ViewModelScoped)
     *
     * 🔹 Standard Annotations
     * - `@HiltAndroidApp` → Application class entry point
     * - `@AndroidEntryPoint` → Used on Activities, Fragments, Services, ViewModels
     * - `@Inject` → Constructor or field injection
     * - `@Module` and `@InstallIn(...)` → Define how to provide dependencies
     *
     * 🔹 ViewModel Injection (requires `hilt-lifecycle-viewmodel`)
     * ```kotlin
     * @HiltViewModel
     * class MyViewModel @Inject constructor(
     *     private val repository: MyRepository
     * ) : ViewModel()
     * ```
     *
     * 🔹 Scope Management
     * - Automatically manages the lifecycle and scope of injected dependencies
     * - Follows Android lifecycle patterns to avoid memory leaks
     *
     * 🔹 Integration with Other Jetpack Libraries
     * - Works seamlessly with `Navigation`, `WorkManager`, `Compose`, and more
     *
     * ✅ Example Usage:
     * ```kotlin
     * @AndroidEntryPoint
     * class MyFragment : Fragment() {
     *     @Inject lateinit var analytics: AnalyticsHelper
     * }
     *
     * @Module
     * @InstallIn(SingletonComponent::class)
     * object AppModule {
     *     @Provides
     *     fun provideApiService(): ApiService = ...
     * }
     * ```
     *
     * ✅ Notes:
     * - Requires `kapt "com.google.dagger:hilt-compiler"` in your project
     * - Reduces boilerplate and setup compared to manual Dagger
     * - Lifecycle-aware and fully integrated into Android's architecture
     */
    const val DAGGER_HILT_ANDROID = "com.google.dagger:hilt-android:${DependenciesVersions.DAGGER_HILT_ANDROID}"
    //const val hiltLifeCycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${dependencies.DependenciesVersions.HILT_LIFECYCLE_VIEWMODEL}"

    /**
     * Dagger Hilt Compiler
     *
     * Dependency:
     *     kapt("com.google.dagger:hilt-compiler:${DependenciesVersions.DAGGER_HILT_COMPILER}")
     *
     * ✅ Purpose:
     * Generates all necessary Dagger/Hilt boilerplate at compile-time.
     * Required for Hilt annotations like `@HiltAndroidApp`, `@AndroidEntryPoint`, `@Inject`, `@HiltViewModel`, etc.
     *
     * ✅ Key Responsibilities:
     *
     * 🔹 Code Generation
     * - Generates Dagger components and subcomponents
     * - Handles dependency graphs for all Hilt entry points
     * - Links modules, scopes, and lifecycle components together
     *
     * 🔹 Required With:
     * - `hilt-android` (runtime)
     * - `@HiltAndroidApp` in Application class
     * - `@AndroidEntryPoint` on Activities, Fragments, Services, BroadcastReceivers
     * - `@HiltViewModel` for ViewModel injection (also requires `hilt-lifecycle-viewmodel` if using older versions)
     *
     * ✅ Setup in Gradle:
     * ```kotlin
     * plugins {
     *     id("kotlin-kapt")
     * }
     *
     * dependencies {
     *     implementation("com.google.dagger:hilt-android:...")
     *     kapt("com.google.dagger:hilt-compiler:...")
     * }
     * ```
     *
     * ✅ Notes:
     * - **Must** be used with the Kotlin `kapt` plugin for annotation processing.
     * - Without this, Hilt injection will fail at compile time with missing generated classes.
     * - If using Hilt with Java only, use `annotationProcessor` instead of `kapt`.
     *
     * ⚠️ Best Practices:
     * - Always match the compiler version with `hilt-android`
     * - Add `kapt correctErrorTypes = true` in `kapt` block if using with other processors (e.g., Room)
     */
    const val DAGGER_HILT_COMPILER = "com.google.dagger:hilt-compiler:${DependenciesVersions.DAGGER_HILT_COMPILER}"

    /**
     * AndroidX Hilt Compiler (for Jetpack integrations)
     *
     * Dependency:
     *     kapt("androidx.hilt:hilt-compiler:${DependenciesVersions.ANDROID_HILT_COMPILER}")
     *
     * ✅ Purpose:
     * Generates code required for Hilt to work with **Jetpack libraries** such as:
     * - `ViewModel`
     * - `Navigation`
     * - `WorkManager`
     * - `SavedStateHandle`
     *
     * ✅ Required For:
     *
     * 🔹 `@HiltViewModel`
     * - Enables constructor injection into `ViewModel` classes
     * - Works with `viewModel()` / `hiltViewModel()` in Fragments or Compose
     *
     * 🔹 `@HiltWorker`
     * - Enables injection into `ListenableWorker` classes
     *
     * 🔹 `@AssistedInject`
     * - For assisted injection (e.g., when injecting runtime parameters)
     *
     * ✅ Example – ViewModel:
     * ```kotlin
     * @HiltViewModel
     * class LoginViewModel @Inject constructor(
     *     private val repository: AuthRepository,
     *     savedStateHandle: SavedStateHandle
     * ) : ViewModel()
     * ```
     *
     * ✅ Example – Worker:
     * ```kotlin
     * @HiltWorker
     * class SyncWorker @AssistedInject constructor(
     *     @Assisted appContext: Context,
     *     @Assisted workerParams: WorkerParameters,
     *     private val apiService: ApiService
     * ) : CoroutineWorker(appContext, workerParams) { ... }
     * ```
     *
     * ✅ Notes:
     * - Works alongside `com.google.dagger:hilt-compiler`
     * - Part of AndroidX and tailored for Jetpack architecture components
     * - Use `kapt` (not `annotationProcessor`) in Kotlin projects
     *
     * ✅ Best Practices:
     * - Always include this when using Hilt with ViewModels or WorkManager
     * - Keep versions of all Hilt-related libraries in sync to avoid runtime errors
     */
    const val ANDROID_HILT_COMPILER = "androidx.hilt:hilt-compiler:${DependenciesVersions.ANDROID_HILT_COMPILER}"
    //endregion

    //region Coroutines
    /**
     * Kotlinx Coroutines Core
     *
     * Dependency:
     *     implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.KOTLINX_COROUTINE_CORE}")
     *
     * ✅ Purpose:
     * Provides the core library for **Kotlin Coroutines**, enabling structured concurrency and
     * asynchronous programming with suspend functions, coroutine builders, scopes, and context switching.
     *
     * ✅ Key Features:
     *
     * 🔹 Coroutine Builders
     * - `launch { ... }` → Fire-and-forget
     * - `async { ... }` → Deferred result (use `.await()`)
     * - `runBlocking { ... }` → Blocks current thread (used for testing or bridging non-coroutines)
     * - `withContext(...) { ... }` → Switch coroutine context (e.g., to IO or Main)
     *
     * 🔹 Suspend Functions
     * - Marked with `suspend` keyword
     * - Can call other suspend functions or coroutine APIs
     * - Pauses execution without blocking threads
     *
     * 🔹 CoroutineScope & Job Management
     * - `CoroutineScope` to launch coroutines tied to a lifecycle
     * - `SupervisorJob()` for independent error handling among child coroutines
     *
     * 🔹 Dispatchers
     * - `Dispatchers.Default` → CPU-bound work
     * - `Dispatchers.IO` → Disk/network IO
     * - `Dispatchers.Main` → Main thread (Android-specific, provided by `coroutines-android`)
     * - `Dispatchers.Unconfined` → Advanced/non-restricted coroutine dispatcher
     *
     * 🔹 Cancellation Support
     * - Coroutines are cancellable via Job
     * ```kotlin
     * val job = scope.launch {
     *     delay(3000)
     *     println("Done")
     * }
     * job.cancel()
     * ```
     *
     * 🔹 Flows (Reactive Stream API)
     * - `flow { emit(...) }`
     * - `collect { value -> ... }`
     * - `stateIn()`, `shareIn()` for state sharing in reactive UIs (usually used with StateFlow/SharedFlow)
     *
     * ✅ Best Practices:
     * - Use structured concurrency via `CoroutineScope`
     * - Avoid `GlobalScope` in production code
     * - Use appropriate dispatcher for the task type (CPU vs IO)
     *
     * ✅ Example:
     * ```kotlin
     * viewModelScope.launch(Dispatchers.IO) {
     *     val result = repository.fetchData()
     *     withContext(Dispatchers.Main) {
     *         _uiState.value = result
     *     }
     * }
     * ```
     *
     * ✅ Notes:
     * - For Android Main dispatcher, include `kotlinx-coroutines-android`
     * - Also used in non-Android Kotlin (JVM, Native, JS, Multiplatform)
     */
    const val KOTLINX_COROUTINES_CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${DependenciesVersions.KOTLINX_COROUTINE_CORE}"

    /**
     * Kotlinx Coroutines Android
     *
     * Dependency:
     *     implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersions.KOTLINX_COROUTINES_ANDROID}")
     *
     * ✅ Purpose:
     * Provides Android-specific support for Kotlin Coroutines, including a **Main thread dispatcher**
     * (`Dispatchers.Main`) for launching coroutines safely on the UI thread.
     *
     * ✅ Key Features:
     *
     * 🔹 Dispatchers.Main
     * - Enables launching coroutines on the main (UI) thread
     * ```kotlin
     * CoroutineScope(Dispatchers.Main).launch {
     *     // safe to update UI here
     * }
     * ```
     *
     * 🔹 Lifecycle-Aware Coroutine Usage
     * - Frequently used with `viewModelScope`, `lifecycleScope`, and `repeatOnLifecycle`
     * - Automatically cancels coroutines on lifecycle destruction
     *
     * 🔹 Seamless Integration with Jetpack
     * - Works with LiveData, ViewModel, Navigation, Room, WorkManager, etc.
     * - Commonly used for UI-bound tasks like showing data or handling events
     *
     * ✅ Example Usage:
     * ```kotlin
     * viewModelScope.launch(Dispatchers.Main) {
     *     val result = repository.loadUserData()
     *     _userData.value = result
     * }
     * ```
     *
     * ✅ Notes:
     * - Must be added for `Dispatchers.Main` to function on Android.
     * - Internally uses `Handler` and `Looper.getMainLooper()` to post work to the UI thread.
     * - Should always be used in combination with `kotlinx-coroutines-core`.
     *
     * ✅ Best Practices:
     * - Always switch to `Dispatchers.IO` for heavy tasks like disk or network access, and back to `Dispatchers.Main` for UI updates.
     * - Use `Dispatchers.Main.immediate` when launching coroutines from the main thread and you want it to run immediately without delay.
     */
    const val KOTLINX_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${DependenciesVersions.KOTLINX_COROUTINES_ANDROID}"
    //endregion

    //region Networking (Retrofit + OkHttp) ---------(retrofit)
    /**
     * Retrofit (Square) – Core Networking Library
     *
     * Dependency:
     *     implementation("com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT_SQUARE_UP}")
     *
     * ✅ Purpose:
     * Retrofit is a **type-safe HTTP client** for Android and Java. It simplifies the process of
     * making API calls, parsing JSON/XML, and mapping server responses to Kotlin/Java data classes.
     *
     * ✅ Key Features:
     *
     * 🔹 Declarative API Interface
     * - Define endpoints using annotations on an interface:
     * ```kotlin
     * interface ApiService {
     *     @GET("users/{id}")
     *     suspend fun getUser(@Path("id") userId: Int): Response<User>
     *
     *     @POST("login")
     *     suspend fun login(@Body credentials: LoginRequest): Response<LoginResponse>
     * }
     * ```
     *
     * 🔹 Dynamic URL Handling
     * - `@Path`, `@Query`, `@Body`, `@Header`, `@Field`, `@Part`, `@Url`, etc.
     *
     * 🔹 Built-in Converters (via adapter/converter libs)
     * - JSON/XML/Proto parsing handled using:
     *   - Gson: `converter-gson`
     *   - Moshi: `converter-moshi`
     *   - Kotlin Serialization: `converter-kotlinx-serialization`
     *   - Scalars (for plain strings, ints, etc.): `converter-scalars`
     *
     * 🔹 Coroutine & Call Adapter Support
     * - Synchronous (`Call<T>`) or asynchronous (`suspend fun`)
     * - For RxJava support: use `adapter-rxjava2` or `adapter-rxjava3`
     *
     * 🔹 Interoperability with OkHttp
     * - Built on top of OkHttp; supports interceptors, logging, caching, timeouts, etc.
     *
     * ✅ Typical Retrofit Setup:
     * ```kotlin
     * val retrofit = Retrofit.Builder()
     *     .baseUrl("https://api.example.com/")
     *     .addConverterFactory(GsonConverterFactory.create())
     *     .build()
     *
     * val api = retrofit.create(ApiService::class.java)
     * ```
     *
     * ✅ Best Practices:
     * - Use `suspend` functions for modern coroutine-based networking
     * - Combine with `OkHttpClient` for authentication, logging, or request manipulation
     * - Always wrap responses in `Result`, `sealed class`, or handle `Response<T>` manually for error handling
     *
     * ✅ Notes:
     * - Retrofit by itself doesn't do JSON parsing — requires a converter (e.g. Gson, Moshi)
     * - Works great with `ViewModel`, `LiveData`, `StateFlow`, or plain coroutines
     */
    const val RETROFIT_SQUARE_UP = "com.squareup.retrofit2:retrofit:${DependenciesVersions.RETROFIT_SQUARE_UP}"

    /**
     * Retrofit Gson Converter – JSON Serialization/Deserialization
     *
     * Dependency:
     *     implementation("com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT_SQUARE_UP_CONVERTER_GSON}")
     *
     * ✅ Purpose:
     * Integrates **Gson** with Retrofit to automatically convert between JSON and Kotlin/Java objects.
     * Used as a `ConverterFactory` in Retrofit to handle request/response body parsing.
     *
     * ✅ Key Features:
     *
     * 🔹 Automatic JSON Mapping
     * - Converts JSON response into data classes using Gson under the hood
     * - Converts request bodies (e.g., `@Body`) into JSON automatically
     * - Eliminates manual JSON parsing
     *
     * 🔹 Simple Retrofit Integration
     * ```kotlin
     * val retrofit = Retrofit.Builder()
     *     .baseUrl("https://api.example.com/")
     *     .addConverterFactory(GsonConverterFactory.create())
     *     .build()
     * ```
     *
     * 🔹 Custom Gson Configuration Support
     * ```kotlin
     * val gson = GsonBuilder()
     *     .setLenient()
     *     .create()
     *
     * val retrofit = Retrofit.Builder()
     *     .addConverterFactory(GsonConverterFactory.create(gson))
     *     .build()
     * ```
     *
     * ✅ Example API Call:
     * ```kotlin
     * @POST("login")
     * suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
     * ```
     * Retrofit + converter-gson will:
     * - Serialize `LoginRequest` → JSON
     * - Deserialize JSON response → `LoginResponse`
     *
     * ✅ Notes:
     * - Requires `implementation("com.google.code.gson:gson")` (automatically included via this converter)
     * - One of the most popular and mature converters for Retrofit
     * - Alternative: Moshi (more modern and Kotlin-native)
     *
     * ✅ Best Practices:
     * - Mark data classes with default values to avoid crash on missing fields
     * - Use `@SerializedName("json_key")` when JSON keys don't match property names
     * - For advanced cases, consider writing a custom `TypeAdapter`
     */
    const val RETROFIT_SQUARE_UP_CONVERTER_GSON = "com.squareup.retrofit2:converter-gson:${DependenciesVersions.RETROFIT_SQUARE_UP_CONVERTER_GSON}"

    /**
     * OkHttp (Square) – HTTP & Networking Client
     *
     * Dependency:
     *     implementation("com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}")
     *
     * ✅ Purpose:
     * A powerful, efficient, and low-level HTTP client for Android and Java.
     * Retrofit uses OkHttp internally as its networking layer.
     * Can be used directly or extended via interceptors, custom configurations, and WebSockets.
     *
     * ✅ Key Features:
     *
     * 🔹 HTTP Client Basics
     * ```kotlin
     * val client = OkHttpClient()
     * val request = Request.Builder()
     *     .url("https://api.example.com")
     *     .build()
     * val response = client.newCall(request).execute()
     * ```
     *
     * 🔹 Asynchronous & Synchronous Requests
     * - `execute()` → blocking
     * - `enqueue(callback)` → non-blocking
     *
     * 🔹 Interceptors
     * - Modify or inspect requests and responses (useful for logging, headers, auth)
     * ```kotlin
     * val logging = HttpLoggingInterceptor().apply {
     *     level = HttpLoggingInterceptor.Level.BODY
     * }
     * val client = OkHttpClient.Builder()
     *     .addInterceptor(logging)
     *     .addInterceptor { chain ->
     *         val newRequest = chain.request().newBuilder()
     *             .addHeader("Authorization", "Bearer token")
     *             .build()
     *         chain.proceed(newRequest)
     *     }
     *     .build()
     * ```
     *
     * 🔹 Connection Management
     * - Connection pooling
     * - Automatic GZIP compression
     * - Transparent HTTP/2 support
     *
     * 🔹 Timeouts & Caching
     * ```kotlin
     * val client = OkHttpClient.Builder()
     *     .connectTimeout(15, TimeUnit.SECONDS)
     *     .readTimeout(30, TimeUnit.SECONDS)
     *     .cache(Cache(File(cacheDir, "http_cache"), 10L * 1024 * 1024)) // 10MB
     *     .build()
     * ```
     *
     * 🔹 WebSocket Support
     * - Built-in support for full-duplex WebSocket connections
     *
     * ✅ Integration with Retrofit:
     * - Retrofit uses OkHttp as its default `Call.Factory`
     * - You can provide a custom `OkHttpClient` to enhance logging, caching, or token handling
     *
     * ✅ Best Practices:
     * - Use interceptors for auth tokens, error handling, and logging
     * - Reuse a single `OkHttpClient` instance (it's expensive to create)
     * - Set appropriate timeout values for production apps
     *
     * ✅ Notes:
     * - Core foundation for most modern Android networking stacks
     * - Lightweight, powerful, and battle-tested by companies like Google, Square, etc.
     */
    const val OKHTTP_SQUARE_UP = "com.squareup.okhttp3:okhttp:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}"

    /**
     * OkHttp Logging Interceptor (Square)
     *
     * Dependency:
     *     implementation("com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}")
     *
     * ✅ Purpose:
     * Provides a powerful **logging mechanism** for OkHttp network calls.
     * Intercepts and logs HTTP request/response data for debugging purposes.
     *
     * ✅ Key Features:
     *
     * 🔹 Logs Network Traffic
     * - Logs request URL, headers, body, response code, response body, and time taken
     * - Helps debug REST APIs and inspect HTTP payloads
     *
     * 🔹 Configurable Logging Levels
     * ```kotlin
     * val logging = HttpLoggingInterceptor().apply {
     *     level = HttpLoggingInterceptor.Level.BODY
     * }
     * ```
     * Levels include:
     * - `NONE`: No logs
     * - `BASIC`: Logs request/response line
     * - `HEADERS`: Logs headers only
     * - `BODY`: Logs everything (headers + body)
     *
     * 🔹 Usage with OkHttpClient
     * ```kotlin
     * val client = OkHttpClient.Builder()
     *     .addInterceptor(HttpLoggingInterceptor().apply {
     *         level = HttpLoggingInterceptor.Level.BODY
     *     })
     *     .build()
     * ```
     *
     * 🔹 Retrofit Integration
     * - Logging is visible for all Retrofit calls that use the above OkHttpClient
     *
     * ✅ Notes:
     * - Should be **disabled or set to NONE** in production to avoid leaking sensitive data
     * - Can slow down requests when using `Level.BODY` due to full body parsing
     * - Helps identify serialization issues, incorrect URLs, or HTTP errors during development
     *
     * ✅ Best Practices:
     * - Use `Level.BODY` during development and QA
     * - Switch to `Level.NONE` or remove the interceptor in release builds
     * - Wrap in debug-check if using `BuildConfig.DEBUG`:
     * ```kotlin
     * if (BuildConfig.DEBUG) {
     *     builder.addInterceptor(loggingInterceptor)
     * }
     * ```
     */
    const val INTERCEPTOR_SQUARE_UP = "com.squareup.okhttp3:logging-interceptor:${DependenciesVersions.OKHTTP_SQUARE_SQUARE_UP}"
    //endregion

    //region Design & Dimensions (Responsive UI) -----(design margins)
    /**
     * Intuit SDP (Scalable Size Unit) for Android
     *
     * Dependency:
     *     implementation("com.intuit.sdp:sdp-android:${DependenciesVersions.DESIGN_MARGINS}")
     *
     * ✅ Purpose:
     * Provides a scalable `sdp` (scale-independent pixel) dimension unit to create responsive and consistent UI sizes
     * across various screen sizes and densities. Helps achieve better **UI scaling** without relying solely on `dp`.
     *
     * ✅ Key Features:
     *
     * 🔹 Scalable Dimensions via XML
     * - Use `@dimen/_sdp_value` in layout XML
     * - Example:
     * ```xml
     * android:layout_margin="@dimen/_16sdp"
     * android:padding="@dimen/_8sdp"
     * android:layout_width="@dimen/_120sdp"
     * ```
     *
     * 🔹 Predefined Sizes
     * - Includes values from `_1sdp` to `_600sdp` by default
     * - Automatically scales based on screen size and density
     *
     * 🔹 Consistency Across Devices
     * - Ensures padding, margins, text sizes, and view dimensions look proportionally the same
     *   on small, normal, large, and x-large screens
     *
     * ✅ Notes:
     * - No Java/Kotlin code required — works entirely through XML.
     * - Especially useful in designs requiring pixel-perfect spacing across a wide device range.
     * - Often paired with `ssp-android` for text sizes.
     *
     * ✅ Best Practices:
     * - Use for **layout dimensions** (widths, heights, margins, paddings)
     * - Avoid for **text sizes** — use `ssp-android` instead (scale-independent sp)
     */
    const val INTUIT_SDB = "com.intuit.sdp:sdp-android:${DependenciesVersions.DESIGN_MARGINS}"

    /**
     * Intuit SSP (Scalable SP Unit) for Android
     *
     * Dependency:
     *     implementation("com.intuit.ssp:ssp-android:${DependenciesVersions.DESIGN_MARGINS}")
     *
     * ✅ Purpose:
     * Provides a scalable `ssp` (scale-independent sp) unit for defining **text sizes** that scale proportionally
     * across different screen sizes and densities, improving readability and consistency in UI design.
     *
     * ✅ Key Features:
     *
     * 🔹 Scalable Text Sizes via XML
     * - Use `@dimen/_ssp_value` in layout XML
     * - Example:
     * ```xml
     * android:textSize="@dimen/_14ssp"
     * android:textSize="@dimen/_22ssp"
     * ```
     *
     * 🔹 Predefined Sizes
     * - Includes values from `_1ssp` to `_600ssp` by default
     * - Automatically adjusts for both screen density and size
     *
     * 🔹 Consistent Typography
     * - Makes text readable on both small and large screens without manual tuning
     * - Aligns with the visual scale provided by `sdp-android`
     *
     * ✅ Notes:
     * - Works entirely through XML — no need to use programmatic scaling.
     * - Ideal for apps with rich text UI across phones and tablets.
     * - Complements `sdp-android` (used for margins, paddings, widths, heights).
     *
     * ✅ Best Practices:
     * - Use for **textSize** only — use `sdp-android` for all other layout dimensions
     * - Avoid mixing hardcoded `sp` with `ssp` in the same layout
     */
    const val INTUIT_SSP = "com.intuit.ssp:ssp-android:${DependenciesVersions.DESIGN_MARGINS}"
    //endregion

    // region Media & Animations -----(gif drawable)
    /**
     * Android GIF Drawable (by droidsonroids)
     *
     * Dependency:
     *     implementation("pl.droidsonroids.gif:android-gif-drawable:${DependenciesVersions.GIF_DRAWABLE}")
     *
     * ✅ Purpose:
     * Enables native **GIF support** for Android apps with smooth playback, performance optimization,
     * and full control over animated GIFs.
     *
     * ✅ Key Features:
     *
     * 🔹 Native GIF Rendering
     * - High-performance decoding and rendering of animated `.gif` files
     * - Based on C++ native code for efficient resource usage
     *
     * 🔹 GIF Support in ImageViews
     * - Use `pl.droidsonroids.gif.GifImageView` as a drop-in replacement for `ImageView`
     * ```xml
     * <pl.droidsonroids.gif.GifImageView
     *     android:layout_width="wrap_content"
     *     android:layout_height="wrap_content"
     *     android:src="@drawable/animated_gif" />
     * ```
     *
     * 🔹 Programmatic Control
     * - Load from assets, file, input stream, byte array, or URL
     * - Pause/resume animation, seek to frame, set loop count, reset, etc.
     * ```kotlin
     * val gifDrawable = GifDrawable(context.assets, "loading.gif")
     * gifDrawable.stop()
     * gifDrawable.start()
     * gifDrawable.loopCount = 3
     * ```
     *
     * 🔹 Custom Controls
     * - `GifDrawable` gives full access to frame count, duration, state, and playback
     * - Can be used for progress indicators, loaders, animated stickers, etc.
     *
     * ✅ Notes:
     * - Ideal when you need precise control or performance beyond what Glide/Coil offers for GIFs
     * - Works well with image views in both Java and Kotlin projects
     *
     * ✅ Best Practices:
     * - Recycle `GifDrawable` in `onViewDetachedFromWindow()` or `onDestroyView()` to avoid leaks
     * - Prefer async loading for large GIF files
     */
    const val GIF_DRAWABLE = "pl.droidsonroids.gif:android-gif-drawable:${DependenciesVersions.GIF_DRAWABLE}"
    //endregion

    //region Worker Manager
    const val WORK_RUNTIME = "androidx.work:work-runtime-ktx:${DependenciesVersions.WORK_RUNTIME}"
    const val HILT_WORK = "androidx.hilt:hilt-work:${DependenciesVersions.ANDROIDX_HILT}"
    //endregion


    //region Room Data Base
    // Room
    const val ROOM_RUNTIME = "androidx.room:room-runtime:${DependenciesVersions.ROOM_RUNTIME}"
    const val ROOM_COMPILER = "androidx.room:room-compiler:${DependenciesVersions.ROOM_COMPILER}"

    // Room with Kotlin Coroutines (suspend & Flow support)
    const val ROOM_KTX = "androidx.room:room-ktx:${DependenciesVersions.ROOM_KTX}"
    //endregion
}