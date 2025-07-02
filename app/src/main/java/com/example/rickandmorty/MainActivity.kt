package com.example.rickandmorty

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.servicies.ApiInterface
import com.example.rickandmorty.servicies.ApiServices
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.rickandmorty.adapter.RMCharacterPagingAdapter
import com.example.rickandmorty.viewmodel.CharactersViewModel
import com.example.rickandmorty.viewmodel.CharactersViewModelFactory
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private val viewModel: CharactersViewModel by viewModels {
        CharactersViewModelFactory(ApiServices.getIntance().create(ApiInterface::class.java))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = RMCharacterPagingAdapter()
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.pagingDataFlow.collectLatest { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        FirebaseAuth.getInstance().signOut()
    }

}
