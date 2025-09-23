package com.example.trujigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Referencias a los elementos
        val etDni = findViewById<EditText>(R.id.etDni)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val tvIrLogin = findViewById<TextView>(R.id.tvIrLogin)

        // Botón de registrarse
        btnRegistrar.setOnClickListener {
            val dni = etDni.text.toString()
            val usuario = etUsuario.text.toString()
            val password = etPassword.text.toString()
            val celular = etCelular.text.toString()
            val correo = etCorreo.text.toString()

            if (dni.isEmpty() || usuario.isEmpty() || password.isEmpty() || celular.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                // Ir a verificación después de registrar
                val intent = Intent(this, VerificarActivity::class.java)
                intent.putExtra("correo", correo)
                startActivity(intent)
            }
        }

        // Volver al login
        tvIrLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}

