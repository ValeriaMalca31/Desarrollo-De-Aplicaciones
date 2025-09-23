package com.example.trujigo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        val etCorreoRecuperacion = findViewById<EditText>(R.id.etCorreoRecuperacion)
        val btnEnviarCorreo = findViewById<Button>(R.id.btnEnviarCorreo)

        btnEnviarCorreo.setOnClickListener {
            val correo = etCorreoRecuperacion.text.toString()
            if (correo.isNotEmpty()) {
                // 🔹 Aquí iría la lógica real con Firebase Auth o backend
                Toast.makeText(
                    this,
                    "Se ha enviado un enlace a $correo 📩",
                    Toast.LENGTH_LONG
                ).show()
                finish() // volver al login
            } else {
                Toast.makeText(this, "Ingrese un correo válido ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
