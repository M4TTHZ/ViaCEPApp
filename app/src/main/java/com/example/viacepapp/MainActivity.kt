package com.example.viacepapp

import android.os.Bundle
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {

    // Referências para os elementos da tela
    private lateinit var etCep: TextInputEditText
    private lateinit var etLogradouro: TextInputEditText
    private lateinit var etBairro: TextInputEditText
    private lateinit var etCidade: TextInputEditText
    private lateinit var etUf: TextInputEditText
    private lateinit var etNumero: TextInputEditText
    private lateinit var etComplemento: TextInputEditText
    private lateinit var btnBuscar: Button
    private lateinit var btnConfirmar: Button
    private lateinit var btnHistorico: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var tvErro: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        inicializarViews();

    }

    private fun inicializarViews() {
        etCep = findViewById(R.id.etCep)
        etLogradouro = findViewById(R.id.etLogradouro)
        etBairro = findViewById(R.id.etBairro)
        etCidade = findViewById(R.id.etCidade)
        etUf = findViewById(R.id.etUf)
        etNumero = findViewById(R.id.etNumero)
        etComplemento = findViewById(R.id.etComplemento)
        btnBuscar = findViewById(R.id.btnBuscarCep)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnHistorico = findViewById(R.id.btnHistorico)
        progressBar = findViewById(R.id.progressBar)
        tvErro = findViewById(R.id.tvErro)
    }
}