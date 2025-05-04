package com.samir.bluearchitecture.data.main.source

/**
 * Common constants representing HTTP and app-specific status codes.
 *
 * These are used to map network responses and exceptions to defined error types
 * in the app's data layer (e.g., `NetworkDataSource`).
 *
 * Use these constants when evaluating:
 * - Retrofit responses
 * - API error codes from custom error bodies
 * - Exceptions thrown during networking
 */
interface DataSource {
  companion object {

    // region === ✅ Standard HTTP 2xx (Success) ===

    /** ✅ 200 - OK
     * The request was successful and the server responded with a resource.
     * → Used in GET or POST operations where a valid response body is expected.
     */
    // const val SUCCESS = 200

    /** ✅ 201 - Created
     * The request was successful and a new resource was created.
     * → Common in POST operations like creating users or submitting forms.
     */
    // const val CREATED = 201

    /** ✅ 202 - Accepted
     * The request was accepted for processing, but processing is not complete.
     * → Used in async operations or when responses are delayed.
     */
    // const val ACCEPTED = 202

    /** ✅ 204 - No Content
     * The request succeeded, but there’s no response body to return.
     * → Often returned for DELETE requests or successful PUT with no payload.
     */
    // const val NO_CONTENT = 204
    // endregion

    // region === 🔁 HTTP 3xx (Redirection) ===

    /** 🔁 303 - See Other
     * The client should retrieve the resource from another URI using GET.
     * → Used in some login/redirect-based workflows or legacy APIs.
     */
    const val SEE_OTHERS = 303

    /** 🔁 304 - Not Modified
     * The resource has not changed since the last request.
     * → Used in caching and conditional requests (`If-Modified-Since`, `ETag`).
     */
    // const val NOT_MODIFIED = 304

    // endregion

    // region === ❌ HTTP 4xx (Client Errors) ===

    /** ❌ 400 - Bad Request
     * The request is malformed or missing required parameters.
     * → Often a frontend bug or validation issue.
     * → Show user-friendly validation errors if error body supports it.
     */
    // const val BAD_REQUEST = 400

    /** 🔐 401 - Unauthorized
     * Authentication is missing or invalid.
     * → Typically means the access token is expired or invalid.
     * → You may need to logout the user or refresh the token.
     */
    const val UNAUTHORIZED = 401

    /** ⛔ 403 - Forbidden
     * The user is authenticated but doesn’t have permission for the requested resource.
     * → Common in role-based access control (RBAC).
     * → Show a message like "You don't have access to this feature."
     */
    const val FORBIDDEN = 403

    /** ❓ 404 - Not Found
     * The requested resource doesn't exist on the server.
     * → Handle gracefully — show "not found" or retry options.
     */
    const val NOT_FOUND = 404

    /** ⛔ 405 - Method Not Allowed
     * The HTTP method (GET, POST, etc.) is not supported on the endpoint.
     * → Usually a server-side configuration error.
     */
    // const val METHOD_NOT_ALLOWED = 405

    /** 🔄 409 - Conflict
     * The request conflicts with an existing resource (e.g., duplicate user, overlapping data).
     * → Show contextual errors like "Email already exists" if supported.
     */
    const val CONFLICT = 409

    /** ❗ 422 - Unprocessable Entity
     * The server understood the request but cannot process it (e.g., validation failed).
     * → Common in APIs using JSON-based validation feedback.
     * → Often includes a `message` or `errors` object in the response body.
     */
    const val UNPROCESSABLE_ENTITY = 422

    /** 🚫 429 - Too Many Requests
     * The user has hit a rate limit.
     * → Inform user to wait and retry (e.g., "Please wait a few seconds and try again").
     */
    const val TOO_MANY_REQUESTS = 429

    // endregion

    // region === 🚨 HTTP 5xx (Server Errors) ===

    /** 💥 500 - Internal Server Error
     * A generic error occurred on the server.
     * → Retry logic or fallback messaging is often needed.
     */
    const val INTERNAL_SERVER_ERROR = 500

    /** 🚧 501 - Not Implemented
     * The server doesn't support the requested functionality.
     * → Inform the team — it may indicate an unimplemented backend feature.
     */
    // const val NOT_IMPLEMENTED = 501

    /** 🔌 502 - Bad Gateway
     * The server received an invalid response from an upstream server.
     * → Retry or fallback UI recommended.
     */
    // const val BAD_GATEWAY = 502

    /** 🛑 503 - Service Unavailable
     * The server is overloaded or temporarily down for maintenance.
     * → Retry later; show “Service unavailable” message to the user.
     */
    // const val SERVICE_UNAVAILABLE = 503

    /** ⏱️ 504 - Gateway Timeout
     * The server didn't receive a timely response from an upstream server.
     * → Retry logic or “try again” UI is common here.
     */
    // const val GATEWAY_TIMEOUT = 504

    // endregion

    // region === ⚠️ App-Specific Errors (Non-HTTP) ===

    /** ❓ -1: Unknown
     * An unexpected error occurred (unclassified).
     * → Use as a fallback if no other code matches.
     */
    const val UNKNOWN = -1

    /** 🌐 -2: No Internet
     * Device is not connected to a network.
     * → Often thrown by a `ConnectivityInterceptor` or network check.
     * → Show "No Internet Connection" UI.
     */
    const val NO_INTERNET = -2

    /** ⌛ -3: Timeout
     * The request timed out due to server delay or poor connectivity.
     * → Show retry option or fallback.
     */
    const val TIMEOUT = -3

    /** 🔒 -4: SSL Pinning Failure
     * SSL certificate mismatch — request was blocked for security.
     * → Show security warning or fallback screen.
     */
    // const val SSL_PINNING = -4

    /** ❌ -5: Request Cancelled
     * The request was manually cancelled by the user or system.
     * → Often not shown to users (e.g., when cancelling on navigation).
     */
    // const val CANCELLED = -5

    /** 🧩 -6: Parsing Error
     * The response body was malformed or couldn’t be parsed (e.g., bad JSON).
     * → Show generic error UI; log for backend investigation.
     */
    const val PARSE_ERROR = -6

    // endregion
  }
}
