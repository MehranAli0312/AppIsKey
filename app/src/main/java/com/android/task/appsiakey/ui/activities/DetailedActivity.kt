package com.android.task.appsiakey.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.task.appsiakey.databinding.ActivityDetailedBinding
import com.android.task.appsiakey.extensions.load
import com.android.task.appsiakey.utils.Constants.Companion.key

class DetailedActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityDetailedBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initializeRV()
    }

    private fun initializeRV() {
        val url = intent.getStringExtra(key)
        binding.imageView.load(url)
    }
}