package com.example.realtasker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtasker.R
import com.example.realtasker.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.txtViewRegistrarUsuario.setOnClickListener {
            val intentRegistro = Intent(this, RegistrarUsuarioActivity::class.java)
            startActivity(intentRegistro)
        }

        binding.txtViewBackPassword.setOnClickListener {
            val intentRecoveryPassword = Intent(this, RecuperarPasswordActivity::class.java)
            startActivity(intentRecoveryPassword)

        }



        auth = Firebase.auth

        binding.btnLogin.setOnClickListener {
            signIn(
                binding.eTxtCorreoLogin.text.toString(),
                binding.eTxtPasswordLogin.text.toString()
            )
        }



        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val currentUser = auth.currentUser

        checkUserAlreadyLoggedIn(currentUser)

    }

    private fun signIn(email: String, password: String) {
        // [START sign_in_with_email]
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

                    Toast.makeText(this, "Sesion iniciada", Toast.LENGTH_SHORT).show()
                    MainScreen()
                } else {

                    Toast.makeText(
                        baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        // [END sign_in_with_email]
    }

    private fun MainScreen() {
        val infoUserIntent = Intent(this, MainTaskActivity::class.java)
        startActivity(infoUserIntent)
        this.finish()
    }

    private fun checkUserAlreadyLoggedIn(currentUser: FirebaseUser?) {
        if (currentUser != null) {

        } else {

        }
    }

}