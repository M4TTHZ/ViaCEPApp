package com.example.viacepapp.Service

import com.example.viacepapp.Model.Endereco
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {

    @GET("{cep}/json/")
    fun buscarEndereco(@Path("cep") cep: String): Call<Endereco>

    // Versão com Coroutines
    @GET("{cep}/json/")
    suspend fun buscarEnderecoCoroutines(@Path("cep") cep: String): Endereco
}