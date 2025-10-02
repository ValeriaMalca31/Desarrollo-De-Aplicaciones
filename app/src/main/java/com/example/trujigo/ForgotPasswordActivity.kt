package com.example.trujigo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth // Importar FirebaseAuth
import com.google.firebase.auth.ktx.auth // Importar extensión KTX
import com.google.firebase.ktx.Firebase // Importar Firebase KTX

class ForgotPasswordActivity : AppCompatActivity() {

    // Declaramos la instancia de Firebase Auth
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        // Inicializamos Firebase Auth
        auth = Firebase.auth

        // Referencias a las vistas
        // NOTA: Asegúrate de que las IDs coincidan con tu XML (etCorreoRecuperacion, btnEnviarCorreo)
        val etCorreoRecuperacion = findViewById<EditText>(R.id.etCorreoRecuperacion)
        val btnEnviarCorreo = findViewById<Button>(R.id.btnEnviarCorreo)

        btnEnviarCorreo.setOnClickListener {
            val correo = etCorreoRecuperacion.text.toString().trim() // Usamos trim() para eliminar espacios

            if (correo.isEmpty()) {
                Toast.makeText(this, "Por favor, ingrese su correo electrónico ❌", Toast.LENGTH_SHORT).show()
            } else {
                // Llamamos a la función de recuperación de contraseña de Firebase
                enviarCorreoDeRecuperacion(correo)
            }
        }
    }

    /**
     * Llama a la función de Firebase para enviar el correo de restablecimiento de contraseña.
     */
    private fun enviarCorreoDeRecuperacion(email: String) {
        // Deshabilitamos el botón para prevenir múltiples peticiones
        val btnEnviarCorreo = findViewById<Button>(R.id.btnEnviarCorreo)
        btnEnviarCorreo.isEnabled = false

        // La función clave de Firebase para enviar el enlace de recuperación
        auth.sendPasswordResetEmail(email)
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // ÉXITO: Firebase informa que el enlace se envió.
                    Toast.makeText(
                        this,
                        "Se ha enviado un enlace de recuperación a $email. ¡Revisa tu bandeja (y Spam)! ✅",
                        Toast.LENGTH_LONG
                    ).show()
                    finish() // Cerramos esta Activity y regresamos al Login
                } else {
                    // FALLO: Puede ser que el correo no esté registrado o haya un error de red.
                    val errorMessage = task.exception?.message ?: "Error desconocido al enviar el enlace."
                    Toast.makeText(
                        this,
                        "Fallo: $errorMessage",
                        Toast.LENGTH_LONG
                    ).show()
                }
                // Volvemos a habilitar el botón
                btnEnviarCorreo.isEnabled = true
            }
    }
}