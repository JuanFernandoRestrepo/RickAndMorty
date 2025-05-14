package com.example.rickandmorty

import Characters
import RmCharacter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.rickandmorty.adapter.CharacterAdapter
import com.example.rickandmorty.servicies.ApiInterface
import com.example.rickandmorty.servicies.ApiServices
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        Thread.sleep(3000)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        getCharacterData {
            recyclerView.adapter = CharacterAdapter(it)
        }
    }

    private fun getCharacterData(callback: (List<RmCharacter>) -> Unit) {
        val apiService = ApiServices.getIntance().create(ApiInterface::class.java)
        apiService.getCharacters().enqueue(object : Callback<Characters> {
            override fun onResponse(call: Call<Characters>, response: Response<Characters>) {
                response.body()?.results?.let {
                    callback(it)
                }
            }



            override fun onFailure(call: Call<Characters>, t: Throwable) {

            }
        })
    }
}
