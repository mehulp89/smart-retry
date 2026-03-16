# SmartRetry

🚀 **SmartRetry** is a lightweight Kotlin retry library for Android and JVM that simplifies retrying failed operations using configurable retry strategies.

It helps developers easily retry network requests, API calls, or any operation that may fail temporarily.

---

## ✨ Features

* Simple and lightweight
* Kotlin-first API
* Coroutine support
* Configurable retry attempts
* Exponential backoff support
* Works with Android and JVM

---

## 📦 Installation

### Gradle

```gradle
implementation("com.android:smart-retry:1.0.0")
```

> Maven Central publishing coming soon.

---

## 🚀 Basic Usage

```kotlin
val retry = SmartRetry(maxAttempts = 3)

val result = retry.execute {
    api.getUser()
}
```

---

## 📱 Android Example

Below is a simple example from the sample app demonstrating how to retry a failing API call.

```kotlin
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.retryButton)
        val text = findViewById<TextView>(R.id.resultText)

        button.setOnClickListener {

            lifecycleScope.launch {

                val retry = SmartRetry(maxAttempts = 5)

                try {
                    val result = retry.execute {
                        fakeApiCall()
                    }

                    text.text = result

                } catch (e: Exception) {
                    text.text = "Failed after retries"
                }
            }
        }
    }

    private fun fakeApiCall(): String {
        if (Random.nextBoolean()) {
            throw RuntimeException("API failed")
        }
        return "Success!"
    }
}
```

---

## ⚙️ Configuration

SmartRetry supports configurable retry policies.

```kotlin
val retry = SmartRetry(
    maxAttempts = 5,
    initialDelay = 1000,
    maxDelay = 10000,
    factor = 2.0
)
```

| Parameter    | Description                    |
| ------------ | ------------------------------ |
| maxAttempts  | Maximum retry attempts         |
| initialDelay | Delay before first retry       |
| maxDelay     | Maximum delay between retries  |
| factor       | Exponential backoff multiplier |

---

## 📂 Project Structure

```
smart-retry
 ├ smart-retry      # Core retry library
 ├ sample           # Example Android app
 ├ README.md
 ├ LICENSE
```

---

## 📈 Use Cases

SmartRetry is useful for:

* Retrying network requests
* Handling intermittent API failures
* Database retry operations
* File system operations
* Any unreliable external service

---

## 🛠 Roadmap

Planned improvements:

* Retry policies (Linear / Exponential / Jitter)
* Retrofit retry interceptor
* OkHttp integration
* Circuit breaker support
* Metrics and logging

---

## 🤝 Contributing

Contributions are welcome!

If you have suggestions, improvements, or bug fixes, feel free to open a Pull Request.

---

## 📄 License

MIT License.

---

## 👨‍💻 Author

**Mehul Patel**

Mobile Architect / Engineering Manager
Passionate about building scalable mobile platforms and developer tools.
