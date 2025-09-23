package com.example.trujigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val btnLogin = findViewById<Button>(R.id.btnLogin)
        val tvCrearCuenta = findViewById<TextView>(R.id.tvCrearCuenta)
        val tvOlvido = findViewById<TextView>(R.id.tvOlvido) // 👈 agregamos esta línea

        // Botón de iniciar sesión
        btnLogin.setOnClickListener {
            val usuario = etUsuario.text.toString()
            val pass = etPassword.text.toString()

            if (usuario.isNotEmpty() && pass.isNotEmpty()) {
                //  Aquí podrías validar usuario/contraseña con BD o Firebase
                Toast.makeText(this, "Inicio de sesión exitoso ✅", Toast.LENGTH_SHORT).show()

                // Ir a pantalla principal directamente
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish() // para que no vuelva atrás al login
            } else {
                Toast.makeText(this, "Complete todos los campos ❌", Toast.LENGTH_SHORT).show()
            }
        }

        // Link a registro
        tvCrearCuenta.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        // Link a recuperación de contraseña 👇
        tvOlvido.setOnClickListener {
            val intent = Intent(this, ForgotPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}

