// Defines the package `signing`, grouping all signing-related configurations.
package sigining

// Imports necessary classes for APK signing configuration in Gradle.
import com.android.build.api.dsl.ApkSigningConfig

// Imports utility function to fetch signing properties from a local file.
import extensions.getLocalProperty

// Imports Gradle API to handle named configurations.
import org.gradle.api.NamedDomainObjectContainer
import org.gradle.api.Project

// Imports Java's File class to read the keystore file path.
import java.io.File

/**
 * `BuildSigning` is a **sealed class** that defines APK signing configurations.
 * - Provides signing setups for `Debug`, `Release`, and `ReleaseExternalQA`.
 * - Ensures that only predefined signing configurations exist.
 *
 * @param name The name of the signing configuration (e.g., `debug`, `release`).
 */
sealed class BuildSigning(val name: String) {

    /**
     * Abstract method to create an APK signing configuration.
     * - Subclasses must override this method to define their signing setup.
     *
     * @param namedDomainObjectContainer The container holding all APK signing configurations.
     */
    abstract fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>)

    // ───────────────────────────────────────────────────────────────────────────────
    // Release Signing Configuration
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Release` signing configuration:
     * - Used for **production releases** on Google Play.
     * - Reads signing credentials from `local.properties` (not stored in source code).
     */
    class Release(private val project: Project) : BuildSigning(SigningTypes.RELEASE) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("release_key.store")) // Path to keystore file.
                storePassword = project.getLocalProperty("release_store.password") // Keystore password.
                keyAlias = project.getLocalProperty("release_key.alias") // Key alias inside keystore.
                keyPassword = project.getLocalProperty("release_key.password") // Key password.

                enableV1Signing = true // Enables legacy (JAR) signing for backward compatibility.
                enableV2Signing = true // Enables modern APK signing (required for Google Play).

            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // QA Release Signing Configuration
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `ReleaseExternalQa` signing configuration:
     * - Used for **QA testing** releases.
     * - Uses a different keystore from the production release.
     */
    class ReleaseExternalQa(private  val project: Project) : BuildSigning(SigningTypes.RELEASE_EXTERNAL_QA) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.create(name) {
                storeFile = File(project.getLocalProperty("qa_key.store")) // Path to QA keystore.
                storePassword = project.getLocalProperty("qa_store.password") // QA keystore password.
                keyAlias = project.getLocalProperty("qa_key.alias") // QA key alias.
                keyPassword = project.getLocalProperty("qa_key.password") // QA key password.

                enableV1Signing = true // Enables legacy (JAR) signing for backward compatibility.
                enableV2Signing = true // Enables modern APK signing (required for Google Play).

            }
        }
    }

    // ───────────────────────────────────────────────────────────────────────────────
    // Debug Signing Configuration
    // ───────────────────────────────────────────────────────────────────────────────

    /**
     * `Debug` signing configuration:
     * - Used for **development and testing**.
     * - Uses the default **Android debug keystore**.
     * - No need to store credentials in `local.properties`.
     */
    class Debug(private val project: Project) : BuildSigning(SigningTypes.DEBUG) {
        override fun create(namedDomainObjectContainer: NamedDomainObjectContainer<out ApkSigningConfig>) {
            namedDomainObjectContainer.getByName(name) {
                storeFile = File(project.rootProject.rootDir, "debug.keystore") // Default debug keystore.
                storePassword = "android" // Default debug keystore password.
                keyAlias = "androiddebugkey" // Default debug key alias.
                keyPassword = "android" // Default debug key password.

                enableV1Signing = true // Enables legacy (JAR) signing.
                enableV2Signing = true // Enables APK signing scheme v2.
            }
        }
    }

}


/**
 * Understanding enableV1Signing and enableV2Signing in Android APK Signing
 * When signing an Android APK, you must digitally sign it to ensure its authenticity and integrity. Android supports different signing schemes, and enableV1Signing and enableV2Signing control which signing method is applied.
 *
 * 🔹 1. enableV1Signing = true (JAR Signing - Legacy)
 * What is V1 Signing?
 *
 * Also known as JAR (Java Archive) Signature Scheme.
 * Used in Android 1.0 to Android 6.0 (API levels 1–23).
 * Signs only the individual files inside the APK.
 * Verification checks each file’s SHA-1 digest in META-INF/ to ensure they haven't changed.
 * Why is V1 Signing Needed?
 * Required for devices running Android 6.0 (Marshmallow) or lower.
 * Backward compatibility: If you disable it, your app won't install on Android 6.0 or older.
 * Weakness: Since it only signs individual files, an attacker can repack the APK by modifying the ZIP structure without changing the individual files.
 * How V1 Signing Works
 * The APK has a META-INF/CERT.SF and CERT.RSA file.
 * These files contain:
 * File digests (hashes) of every file in the APK.
 * A digital signature that validates the digests.
 * When installing the app, Android checks if the hashes match the APK’s contents.
 * Example of V1 Signature Files:
 *
 * sql
 * Copy
 * Edit
 * META-INF/
 *   ├── CERT.RSA (Contains the digital signature)
 *   ├── CERT.SF (Lists all files and their hashes)
 *   ├── MANIFEST.MF (Stores SHA-1 digests of files)
 * Limitations of V1 Signing
 * ❌ Can be tampered with: Attackers can modify the APK ZIP structure without invalidating the signature.
 * ❌ Slow verification: The system checks every single file separately.
 *
 * 🔹 2. enableV2Signing = true (APK Signature Scheme v2)
 * What is V2 Signing?
 *
 * Introduced in Android 7.0 (API level 24 - Nougat).
 * Signs the entire APK file, not just individual files.
 * Uses a stronger cryptographic algorithm (SHA-256 instead of SHA-1).
 * Mandatory for apps published on Google Play.
 * Why is V2 Signing Better?
 * ✅ Protects against tampering: Any modification (even ZIP changes) invalidates the signature.
 * ✅ Faster installation: No need to check every file separately—Android validates the APK as a whole.
 * ✅ More secure: Prevents repacking attacks (modifying APK contents without invalidating the signature).
 *
 * How V2 Signing Works
 * The entire APK is signed as a single entity.
 * The signature is stored in a special block inside the APK (not in META-INF).
 * When installing, Android verifies the entire APK, making it tamper-proof.
 * Limitations of V2 Signing
 * ❌ Not compatible with Android 6.0 and below. (Requires V1 for backward compatibility.)
 * ❌ If the signature is invalid, the APK is completely rejected (which is good for security).
 *
 * 🔹 Why Use Both enableV1Signing and enableV2Signing?
 * Since Android devices run on various OS versions, enabling both ensures maximum compatibility:
 *
 * Signing Version	Android Versions	Security Level	Recommended?
 * V1 Signing (JAR)	Android 1.0 – 6.0 (API 1–23)	Weak (can be tampered with)	✅ Yes (for old devices)
 * V2 Signing (APK Scheme v2)	Android 7.0+ (API 24+)	Strong (protects whole APK)	✅ Yes (for security)
 * By setting:
 *
 * kotlin
 * Copy
 * Edit
 * enableV1Signing = true
 * enableV2Signing = true
 * ✔ Apps will install on Android 6.0 and below (because of V1 Signing).
 * ✔ Apps will have strong security on Android 7.0+ (because of V2 Signing).
 * ✔ Google Play requires V2 Signing, so it's mandatory for publishing.
 *
 * 🔹 What Happens If You Disable One of Them?
 * ❌ If enableV1Signing = false
 * The APK won't install on Android 6.0 or lower.
 * Example: If you try to install the APK on an Android 5.0 device, you’ll get an error like:
 * nginx
 * Copy
 * Edit
 * INSTALL_PARSE_FAILED_NO_CERTIFICATES
 * Solution: Keep enableV1Signing = true for backward compatibility.
 * ❌ If enableV2Signing = false
 * Your app loses the modern APK signing security.
 * Google Play will reject the app because V2 signing is mandatory.
 * Example: If you upload an APK to Google Play, you may get an error:
 * csharp
 * Copy
 * Edit
 * Your APK is not signed with APK Signature Scheme v2.
 * Solution: Keep enableV2Signing = true to comply with Google Play requirements.
 * 🔹 Summary
 * Feature	V1 Signing (JAR Signature Scheme)	V2 Signing (APK Signature Scheme v2)
 * Introduced in	Android 1.0 (API 1)	Android 7.0 (API 24)
 * Required for	Android 6.0 and below	Android 7.0+ and Google Play
 * Signs	Individual files inside APK	The entire APK as a whole
 * Tamper Protection	❌ Weak (APK structure can be modified)	✅ Strong (modifications break the signature)
 * Installation Speed	⏳ Slower (verifies every file separately)	⚡ Fast (verifies the whole APK)
 * Recommended?	✅ Yes (for backward compatibility)	✅ Yes (for security and Google Play compliance)
 */