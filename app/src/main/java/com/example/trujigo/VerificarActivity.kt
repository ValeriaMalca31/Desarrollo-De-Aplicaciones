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

        // Obtener la referencia de cada uno de los 4 campos de código
        val etCodigo1 = findViewById<EditText>(R.id.etCodigo1)
        val etCodigo2 = findViewById<EditText>(R.id.etCodigo2)
        val etCodigo3 = findViewById<EditText>(R.id.etCodigo3)
        val etCodigo4 = findViewById<EditText>(R.id.etCodigo4)
        val btnVerificar = findViewById<Button>(R.id.btnVerificar)

        val correo = intent.getStringExtra("correo") ?: ""

        btnVerificar.setOnClickListener {
            // Unir el texto de los 4 campos en una sola cadena
            val codigo = etCodigo1.text.toString() +
                    etCodigo2.text.toString() +
                    etCodigo3.text.toString() +
                    etCodigo4.text.toString()

            // Asegúrate de que el código tenga 4 dígitos
            if (codigo.length == 4) {
                if (codigo == "1234") { // 🔹 Cambié la validación a 4 dígitos para que coincida con el UI
                    Toast.makeText(this, "Correo $correo verificado ✅", Toast.LENGTH_LONG).show()
                    // Aquí puedes abrir la pantalla principal de tu app
                } else {
                    Toast.makeText(this, "Código incorrecto ❌", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "El código debe tener 4 dígitos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
