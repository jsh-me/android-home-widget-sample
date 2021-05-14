package com.jshme.covidwidget.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import com.jshme.covidwidget.R
import com.jshme.covidwidget.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this

        viewModel.mainViewState.observe(this) { state ->
            when (state) {
                is MainViewState.Loading -> {
                    binding.loadingContainer.visibility = View.VISIBLE
                }

                is MainViewState.Error -> {
                    binding.loadingContainer.visibility = View.GONE
                    binding.textView.text = state.msg
                }

                is MainViewState.Success -> {
                    binding.loadingContainer.visibility = View.GONE
                    binding.textView.text = state.entity.city1n
                }
            }
        }
    }
}
