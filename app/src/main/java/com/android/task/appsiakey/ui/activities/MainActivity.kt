package com.android.task.appsiakey.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.android.task.appsiakey.adapters.PixBayAdapter
import com.android.task.appsiakey.databinding.ActivityMainBinding
import com.android.task.appsiakey.extensions.checkInternet
import com.android.task.appsiakey.extensions.showToastMessage
import com.android.task.appsiakey.model.AppIsKey
import com.android.task.appsiakey.utils.Constants.Companion.key
import com.android.task.appsiakey.utils.NetworkResult
import com.android.task.appsiakey.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val viewModal: MainViewModel by viewModels()
    private var booksAdapter: PixBayAdapter? = null

    private var myItem: AppIsKey? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.apply {
            initializeRV()
            if (checkInternet()) {
                observeData()
            }
            eventListener()
        }
    }

    private fun ActivityMainBinding.eventListener() {
        button.setOnClickListener {
            startActivity(Intent(this@MainActivity, WordCounterActivity::class.java))
        }
    }

    private fun ActivityMainBinding.initializeRV() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        booksAdapter = PixBayAdapter(::nextActivity)
        bookRecyclerView.apply {
            adapter = booksAdapter
        }
    }

    private fun nextActivity(url: String) {
        startActivity(Intent(this@MainActivity, DetailedActivity::class.java).putExtra(key, url))
    }

    private fun observeData() {
        viewModal.getPixBay().observe(this@MainActivity) {
            when (it) {
                is NetworkResult.Success -> {
                    myItem = it.data
                    myItem?.hits?.let { it1 -> booksAdapter?.setData(it1) }
                }
                is NetworkResult.Error -> {
                    it.message?.let { it1 ->
                        showToastMessage(it1)
                    }
                }
                is NetworkResult.Loading -> {

                }
            }

        }
    }
}