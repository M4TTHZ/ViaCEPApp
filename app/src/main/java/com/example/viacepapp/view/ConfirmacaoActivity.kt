package com.example.viacepapp.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.viacepapp.R
import android.widget.Button
import android.widget.TextView
import com.example.viacepapp.Model.Endereco

class ConfirmacaoActivity : AppCompatActivity() {

    // Referências para os elementos da tela
    private lateinit var tvResumoEndereco: TextView
    private lateinit var tvCep: TextView
    private lateinit var tvLogradouro: TextView
    private lateinit var tvNumero: TextView
    private lateinit var tvComplemento: TextView
    private lateinit var tvBairro: TextView
    private lateinit var tvCidadeUf: TextView
    private lateinit var btnEditar: Button
    private lateinit var btnConfirmar: Button
    private lateinit var btnCancelar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmacao)


        inicializarViews()
        getEndereco()
        configurarListeners()

    }
    private fun inicializarViews() {
        tvResumoEndereco = findViewById(R.id.tvResumoEndereco)
        tvCep = findViewById(R.id.tvCep)
        tvLogradouro = findViewById(R.id.tvLogradouro)
        tvNumero = findViewById(R.id.tvNumero)
        tvComplemento = findViewById(R.id.tvComplemento)
        tvBairro = findViewById(R.id.tvBairro)
        tvCidadeUf = findViewById(R.id.tvCidadeUf)
        btnEditar = findViewById(R.id.btnEditar)
        btnConfirmar = findViewById(R.id.btnConfirmar)
        btnCancelar = findViewById(R.id.btnCancelar)
    }

    private fun getEndereco(){
        val endereco = intent.getSerializableExtra("endereco") as? Endereco

        if (endereco != null) {
            tvResumoEndereco.text = "${endereco.logradouro}, ${endereco.numero} - ${endereco.bairro}, ${endereco.localidade} - ${endereco.uf}"
            tvCep.text = "${endereco.cep}"
            tvLogradouro.text = "${endereco.logradouro}"
            tvNumero.text = "${endereco.numero}"
            tvComplemento.text = "${endereco.complemento}"
            tvBairro.text = "${endereco.bairro}"
            tvCidadeUf.text = "${endereco.localidade} - ${endereco.uf}"
        }
    }


    private fun configurarListeners() {
        btnEditar.setOnClickListener {
            finish() // Volta para a MainActivity para editar o endereço
        }

        btnConfirmar.setOnClickListener {
            // Aqui você pode implementar a lógica para salvar o endereço confirmado
            finish() // Fecha a ConfirmacaoActivity após confirmar
        }

        btnCancelar.setOnClickListener {
            finish()
        }
    }
}