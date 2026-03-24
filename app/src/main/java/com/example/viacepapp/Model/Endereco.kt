package com.example.viacepapp.Model

import java.io.Serializable

data class Endereco(
    val cep: String,
    val logradouro: String,
    val complemento: String,
    val bairro: String,
    val localidade: String,
    val uf: String,
    val erro: Boolean = false,
    val numero: String
) : Serializable