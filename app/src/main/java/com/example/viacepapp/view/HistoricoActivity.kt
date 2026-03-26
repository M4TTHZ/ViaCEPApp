package com.example.viacepapp.view

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viacepapp.R

class HistoricoActivity : AppCompatActivity() {
    private lateinit var tvCep: TextView
    private lateinit var tvLogradouro: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvCidadeUf: TextView
    private lateinit var tvNumero: TextView
    private lateinit var tvComplemento: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_historico)

        inicializarViews()
        exibirEndereco()

    }
    private fun inicializarViews() {
        tvCep = findViewById(R.id.tvCep)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvBairro = findViewById(R.id.tvBairro)
        tvCidadeUf = findViewById(R.id.tvCidadeUf)
        tvNumero = findViewById(R.id.tvNumero)
        tvComplemento = findViewById(R.id.tvComplemento)
    }
    private fun exibirEndereco() {
        val sharedPreferences = getSharedPreferences("EnderecoPrefs", MODE_PRIVATE)
        val cep = sharedPreferences.getString("cep", "")
        val logradouro = sharedPreferences.getString("logradouro", "")
        val complemento = sharedPreferences.getString("complemento", "")
        val bairro = sharedPreferences.getString("bairro", "")
        val localidade = sharedPreferences.getString("localidade", "")
        val uf = sharedPreferences.getString("uf", "")
        val numero = sharedPreferences.getString("numero", "")

        tvCep.text = cep
        tvLogradouro.text = logradouro
        tvBairro.text = bairro
        tvCidadeUf.text = "$localidade - $uf"
        tvNumero.text = numero
        tvComplemento.text = complemento
    }


}