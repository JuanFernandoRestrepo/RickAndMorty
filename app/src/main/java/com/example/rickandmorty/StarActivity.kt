package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.AppTheme)
        Thread.sleep(3000)
        super.onCreate(savedInstanceState)
        FirebaseAuth.getInstance().signOut()
        startActivity(Intent(this, RegistroActivity::class.java))
        finish()
    }
}
