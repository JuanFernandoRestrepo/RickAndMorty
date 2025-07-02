package com.example.rickandmorty

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.rickandmorty.databinding.ActivityRegistroBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException

class RegistroActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityRegistroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRegistroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerButton.setOnClickListener {
            val email = binding.emailEditText.text.toString().trim()
            val password = binding.passwordEditText.text.toString().trim()

            if (email.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length >= 6) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { signInTask ->
                        if (signInTask.isSuccessful) {
                            startActivity(Intent(this, MainActivity::class.java))
                            finish()
                        } else {
                            val exception = signInTask.exception
                            if (exception is FirebaseAuthInvalidUserException) {
                                auth.createUserWithEmailAndPassword(email, password)
                                    .addOnCompleteListener { createTask ->
                                        if (createTask.isSuccessful) {
                                            Toast.makeText(this, "Usuario creado correctamente", Toast.LENGTH_SHORT).show()
                                            startActivity(Intent(this, MainActivity::class.java))
                                            finish()
                                        } else {
                                            Toast.makeText(this, "Error al crear usuario: ${createTask.exception?.message}", Toast.LENGTH_LONG).show()
                                            Log.e("Registro", "Error al crear usuario", createTask.exception)
                                        }
                                    }
                            } else if (exception is FirebaseAuthInvalidCredentialsException) {
                                Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
                            } else {
                                Toast.makeText(this, "Error al iniciar sesión: ${exception?.message}", Toast.LENGTH_LONG).show()
                                Log.e("Registro", "Error al iniciar sesión", exception)
                            }
                        }
                    }
            } else {
                Toast.makeText(this, "Completa todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
