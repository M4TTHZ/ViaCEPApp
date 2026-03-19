// MainActivity.kt
package com.example.viacepapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.viacepapp.Model.Endereco
import com.example.viacepapp.Service.RetrofitClient
import com.example.viacepapp.view.ConfirmacaoActivity

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

        inicializarViews()
        configurarListeners()
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

    private fun configurarListeners() {
        btnBuscar.setOnClickListener {
            buscarCep()
        }

        btnConfirmar.setOnClickListener {
            confirmarEndereco()

            val endereco = Endereco(
                cep = etCep.text.toString(),
                logradouro = etLogradouro.text.toString(),
                complemento = etComplemento.text.toString(),
                bairro = etBairro.text.toString(),
                localidade = etCidade.text.toString(),
                uf = etUf.text.toString(),
                numero = etNumero.text.toString()
            )

            val intent = Intent(this, ConfirmacaoActivity::class.java)

            intent.putExtra("endereco", endereco)

            startActivity(intent)
        }

        btnHistorico.setOnClickListener {

        }
    }

    private fun buscarCep() {
        val cep = etCep.text.toString().trim()

        // Validação do CEP
        if (cep.isEmpty()) {
            mostrarErro("Por favor, digite um CEP")
            return
        }

        if (cep.length != 8) {
            mostrarErro("CEP deve conter 8 dígitos")
            return
        }

        // Limpar campos anteriores
        limparCamposEndereco()

        // Mostrar ProgressBar
        mostrarCarregamento(true)
        esconderErro()

        buscarCepComCoroutines(cep)
    }


    // Metodo: Usando Coroutines (recomendado)
    private fun buscarCepComCoroutines(cep: String) {
        lifecycleScope.launch {
            try {
                val endereco = withContext(Dispatchers.IO) {
                    RetrofitClient.viaCepService.buscarEnderecoCoroutines(cep)
                }

                mostrarCarregamento(false)

                if (endereco.erro == false) {
                    preencherCampos(endereco)
                } else {
                    mostrarErro("CEP não encontrado")
                }

            } catch (e: Exception) {
                mostrarCarregamento(false)
                mostrarErro("Erro: ${e.message}")
            }
        }
    }

    private fun preencherCampos(endereco: Endereco) {
        etLogradouro.setText(endereco.logradouro)
        etBairro.setText(endereco.bairro)
        etCidade.setText(endereco.localidade)
        etUf.setText(endereco.uf)

        // Se houver complemento na API, preenche
        if (endereco.complemento.isNotBlank()) {
            etComplemento.setText(endereco.complemento)
        }

        // Focar no campo número para preenchimento manual
        etNumero.requestFocus()

        Toast.makeText(this, "Endereço encontrado!", Toast.LENGTH_SHORT).show()
    }

    private fun limparCamposEndereco() {
        etLogradouro.text?.clear()
        etBairro.text?.clear()
        etCidade.text?.clear()
        etUf.text?.clear()
        etComplemento.text?.clear()
        etNumero.text?.clear()
    }

    private fun mostrarCarregamento(mostrar: Boolean) {
        progressBar.visibility = if (mostrar) View.VISIBLE else View.GONE
        btnBuscar.isEnabled = !mostrar
    }

    private fun mostrarErro(mensagem: String) {
        tvErro.text = mensagem
        tvErro.visibility = View.VISIBLE
    }

    private fun esconderErro() {
        tvErro.visibility = View.GONE
    }

    private fun confirmarEndereco() {
        val cep = etCep.text.toString()
        val logradouro = etLogradouro.text.toString()
        val bairro = etBairro.text.toString()
        val cidade = etCidade.text.toString()
        val uf = etUf.text.toString()
        val numero = etNumero.text.toString()
        val complemento = etComplemento.text.toString()

        // Validação básica
        if (logradouro.isEmpty() || bairro.isEmpty() || cidade.isEmpty() ||
            uf.isEmpty() || numero.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos obrigatórios", Toast.LENGTH_SHORT).show()
            return
        }

        // Aqui você pode salvar o endereço no banco de dados ou enviar para outra tela
        val enderecoCompleto = """
            Endereço confirmado:
            CEP: $cep
            Logradouro: $logradouro, $numero
            Complemento: $complemento
            Bairro: $bairro
            Cidade: $cidade - $uf
        """.trimIndent()

        Toast.makeText(this, enderecoCompleto, Toast.LENGTH_LONG).show()
    }
}