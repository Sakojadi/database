package com.example.experiment

import retrofit2.Response
import retrofit2.http.POST
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET

interface ApiService {
    @GET("getClients.php")
    suspend fun getClients(): Response<List<Client>> // Ensure you're using Response<T>

    @FormUrlEncoded
    @POST("addClient.php")
    suspend fun addClient(@Field("name") name: String): Response<Void> // Use Response for POST
}

