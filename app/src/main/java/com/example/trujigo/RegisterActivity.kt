package com.example.trujigo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth
        val db = Firebase.firestore

        // Referencias a los elementos
        val etDni = findViewById<EditText>(R.id.etDni)
        val etUsuario = findViewById<EditText>(R.id.etUsuario)
        val etPassword = findViewById<EditText>(R.id.etPassword)
        val etCelular = findViewById<EditText>(R.id.etCelular)
        val etCorreo = findViewById<EditText>(R.id.etCorreo)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrar)
        val tvIrLogin = findViewById<TextView>(R.id.tvIrLogin)
        val btnAtras = findViewById<ImageButton>(R.id.btnAtras)

        // Acción de la flecha atrás → volver a Login
        btnAtras.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnRegistrar.setOnClickListener {
            val dni = etDni.text.toString().trim()
            val usuario = etUsuario.text.toString().trim()
            val password = etPassword.text.toString().trim()
            val celular = etCelular.text.toString().trim()
            val correo = etCorreo.text.toString().trim()

            // Validación
            if (dni.isEmpty() || usuario.isEmpty() || password.isEmpty() || celular.isEmpty() || correo.isEmpty()) {
                Toast.makeText(this, "Por favor complete todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(this, "La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(correo).matches()) {
                Toast.makeText(this, "Ingrese un correo válido", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Registro en Firebase Authentication
            auth.createUserWithEmailAndPassword(correo, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        user?.sendEmailVerification()

                        // Guardar datos adicionales en Firestore
                        val uid = user?.uid
                        val userMap = hashMapOf(
                            "dni" to dni,
                            "usuario" to usuario,
                            "celular" to celular,
                            "correo" to correo
                        )

                        if (uid != null) {
                            db.collection("usuarios").document(uid).set(userMap)
                                .addOnSuccessListener {
                                    Toast.makeText(this, "Registro exitoso. Verifique su correo.", Toast.LENGTH_LONG).show()

                                    // Ir a VerificarActivity
                                    val intent = Intent(this, VerificarActivity::class.java)
                                    intent.putExtra("correo", correo)
                                    startActivity(intent)
                                    finish()
                                }
                                .addOnFailureListener { e ->
                                    Toast.makeText(this, "Error al guardar datos: ${e.message}", Toast.LENGTH_SHORT).show()
                                }
                        }

                    } else {
                        val errorMessage = when (task.exception?.message) {
                            "The email address is already in use by another account." -> "El correo ya está registrado."
                            "The email address is badly formatted." -> "El formato del correo no es válido."
                            else -> task.exception?.message ?: "Error desconocido."
                        }
                        Toast.makeText(this, "Fallo en el registro: $errorMessage", Toast.LENGTH_LONG).show()
                    }
                }
        }

        // Link de "¿Ya tiene cuenta? Inicie Sesión"
        tvIrLogin.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
