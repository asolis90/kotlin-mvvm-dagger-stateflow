package com.asolis.kotlinmvvmdagger.ui.main

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.asolis.kotlinmvvmdagger.KotlinMVVMDaggerApplication
import com.asolis.kotlinmvvmdagger.data.models.Article
import com.asolis.kotlinmvvmdagger.databinding.MainActivityBinding
import com.asolis.kotlinmvvmdagger.ui.main.adapter.MainActivityAdapter
import com.asolis.kotlinmvvmdagger.ui.state.UIState
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainActivityViewModelFactory: MainActivityViewModelFactory

    private lateinit var adapter: MainActivityAdapter
    private lateinit var binding: MainActivityBinding
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependencies()
        binding = MainActivityBinding.inflate(layoutInflater)
        adapter = MainActivityAdapter(arrayListOf())
        setContentView(binding.root)
        setupUI()
        setupObserver()
    }

    private fun setupUI() {
        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObserver() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect {
                    when (it) {
                        is UIState.Success -> {
                            binding.progressBar.visibility = View.GONE
                            renderList(it.data)
                            binding.recyclerView.visibility = View.VISIBLE
                        }

                        is UIState.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                            binding.recyclerView.visibility = View.GONE
                        }

                        is UIState.Error -> {
                            //Handle Error
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(this@MainActivity, it.message, Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
        }
    }

    private fun renderList(articleList: List<Article>) {
        adapter.addData(articleList)
        adapter.notifyDataSetChanged()
    }

    private fun injectDependencies() {
        (application as? KotlinMVVMDaggerApplication)?.applicationComponent?.inject(this)
        getViewModel()
    }

    private fun getViewModel(): MainActivityViewModel {
        viewModel = ViewModelProviders.of(
            this,
            mainActivityViewModelFactory
        )[MainActivityViewModel::class.java]
        return viewModel
    }
}