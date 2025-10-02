package com.example.trujigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class VerificarActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificar)

        auth = Firebase.auth

        val tvCorreoUsuario = findViewById<TextView>(R.id.tvCorreoUsuario)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)
        val tvReenviar = findViewById<TextView>(R.id.tvReenviar)

        //  Correo recibido desde RegisterActivity
        val correo = intent.getStringExtra("correo") ?: "Usuario Desconocido"
        tvCorreoUsuario.text = correo

        // Botón "Ya verifiqué, continuar"
        btnVerificar.setOnClickListener {
            val user = auth.currentUser

            if (user == null) {
                Toast.makeText(this, "No hay usuario activo. Inicie sesión nuevamente.", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            //  Recargar datos desde Firebase (muy importante)
            user.reload().addOnCompleteListener { reloadTask ->
                if (reloadTask.isSuccessful) {
                    if (user.isEmailVerified) {
                        Toast.makeText(this, "¡Correo verificado! Acceso concedido.", Toast.LENGTH_LONG).show()

                        // Ir a MainActivity
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this, "Aún no has verificado tu correo. Revisa tu email.", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this, "Error al comprobar verificación. Intenta de nuevo.", Toast.LENGTH_SHORT).show()
                }
            }
        }

        //  Reenviar correo de verificación
        tvReenviar.setOnClickListener {
            val user = auth.currentUser
            user?.sendEmailVerification()?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    Toast.makeText(this, "Se ha reenviado el correo de verificación a $correo.", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error al reenviar correo. Intenta más tarde.", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
