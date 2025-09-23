package com.example.trujigo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class VerificarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_verificar)

        val etCodigo = findViewById<EditText>(R.id.etCodigo)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)

        val correo = intent.getStringExtra("correo") ?: ""

        btnVerificar.setOnClickListener {
            val codigo = etCodigo.text.toString()
            if (codigo == "123456") { // 🔹 aquí puedes validar con Firebase o tu backend
                Toast.makeText(this, "Correo $correo verificado ✅", Toast.LENGTH_LONG).show()
                // Aquí puedes abrir la pantalla principal de tu app
            } else {
                Toast.makeText(this, "Código incorrecto ❌", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
