package com.android.example_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import com.android.smartretry.SmartRetry
import kotlinx.coroutines.launch
import kotlin.random.Random

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