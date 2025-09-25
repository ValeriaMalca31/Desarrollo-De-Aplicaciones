package com.example.trujigo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class PlanificarFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Infla el layout del fragmento
        return inflater.inflate(R.layout.fragment_planificar, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnCompartir = view.findViewById<Button>(R.id.btnCompartir)
        val overlay = view.findViewById<View>(R.id.overlay)
        val qrModal = view.findViewById<CardView>(R.id.qrModal)
        val closeButton = view.findViewById<ImageButton>(R.id.closeButton)

        //presionar compartir
        btnCompartir.setOnClickListener {
            overlay.visibility = View.VISIBLE
            qrModal.visibility = View.VISIBLE
        }

        //presionar X
        closeButton.setOnClickListener {
            overlay.visibility = View.GONE
            qrModal.visibility = View.GONE
        }

        //cerrar al tocar fuera del modal
        overlay.setOnClickListener {
            overlay.visibility = View.GONE
            qrModal.visibility = View.GONE
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PlanificarFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}