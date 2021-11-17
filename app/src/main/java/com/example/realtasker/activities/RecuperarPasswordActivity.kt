package com.example.realtasker.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.realtasker.databinding.ActivityRecuperarPasswordBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RecuperarPasswordActivity : AppCompatActivity() {

    lateinit var binding: ActivityRecuperarPasswordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRecuperarPasswordBinding.inflate(layoutInflater)


        binding.btnEnviar.setOnClickListener {
            val emailAddress = binding.xTxtRecoveryEmail.text.toString()

            Firebase.auth.sendPasswordResetEmail(emailAddress)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "Correo enviado", Toast.LENGTH_SHORT).show()
                        backHome()
                    }
                }
        }



        setContentView(binding.root)
    }

    private fun backHome() {
        val intent = Intent(this, MainActivity::class.java)

        startActivity(intent)
    }
}