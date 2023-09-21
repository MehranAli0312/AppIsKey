package com.android.task.appsiakey.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.task.appsiakey.databinding.ActivityWordCounterBinding
import com.android.task.appsiakey.extensions.wordCounts

class WordCounterActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityWordCounterBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initialize()
    }

    private fun initialize() {
        binding.run {
            counterBtn.setOnClickListener {
                val inputText = editText.text.toString()
                val result = inputText.wordCounts()
                resultTextview.text = result.toString()
            }
        }
    }
}