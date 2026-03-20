package com.example.viacepapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.viacepapp.R
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
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

    private lateinit var endereco: Endereco

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_confirmacao)

        inicializarViews()
        getEndereco()
        configurarListeners()

        // Habilitar o botão de voltar na ActionBar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Confirmar Endereço"
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


    private fun getEndereco() {
        endereco = intent.getSerializableExtra("endereco") as? Endereco
            ?: run {
                Toast.makeText(this, "Erro: endereço não encontrado", Toast.LENGTH_SHORT).show()
                finish()
                return
            }

        tvResumoEndereco.text = "${endereco.logradouro}, ${endereco.numero} - ${endereco.bairro}, ${endereco.localidade} - ${endereco.uf}"
        tvCep.text = endereco.cep
        tvLogradouro.text = endereco.logradouro
        tvNumero.text = endereco.numero
        tvComplemento.text = if (endereco.complemento.isBlank()) "Não informado" else endereco.complemento
        tvBairro.text = endereco.bairro
        tvCidadeUf.text = "${endereco.localidade} - ${endereco.uf}"
    }

    private fun configurarListeners() {
        // BOTÃO EDITAR - Volta normalmente (NÃO limpa campos)
        btnEditar.setOnClickListener {
            setResult(RESULT_OK)  // RESULT_OK para NÃO limpar
            finish()
        }

        btnConfirmar.setOnClickListener {
            setResult(RESULT_CANCELED)  // RESULT_OK para NÃO limpar
            finish()
        }

        btnCancelar.setOnClickListener {
            // RESULT_CANCELED faz a MainActivity limpar os campos
            setResult(RESULT_CANCELED)
            finish()
        }
    }

    // Botão voltar da ActionBar
    override fun onSupportNavigateUp(): Boolean {
        // Voltar NÃO limpa campos
        setResult(RESULT_OK)
        finish()
        return true
    }

    // Botão voltar físico
    @SuppressLint("GestureBackNavigation")
    override fun onBackPressed() {
        // Voltar NÃO limpa campos
        setResult(RESULT_OK)
        super.onBackPressed()
    }
}