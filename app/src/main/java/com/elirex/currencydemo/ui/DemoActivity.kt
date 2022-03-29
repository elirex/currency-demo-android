package com.elirex.currencydemo.ui

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.elirex.currencydemo.databinding.ActivityDemoBinding
import com.elirex.currencydemo.ui.extension.setOnDebounceClickListener
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class DemoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDemoBinding

    private val viewModel: DemoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDemoBinding.inflate(layoutInflater)
            .apply {
                lifecycleOwner = this@DemoActivity
                viewModel = this@DemoActivity.viewModel
            }.also {
                setContentView(it.root)
                it.loadButton.setOnDebounceClickListener {
                    viewModel.loadCurrencyInfoList()
                }
                it.sortButton.setOnDebounceClickListener {
                    viewModel.sortCurrencyInfoList()
                }
            }
        lifecycleScope.launchWhenStarted {
            viewModel.showSnackbar.collect {
                if (it != Resources.ID_NULL) {
                    showSnackbar(getString(it))
                }
            }
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_SHORT).show()
    }
}