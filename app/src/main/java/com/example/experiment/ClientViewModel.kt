package com.example.experiment
import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import kotlinx.coroutines.launch
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope

class ClientViewModel(application: Application) : AndroidViewModel(application) {

    private val _clients = MutableLiveData<List<Client>>()
    val clients: LiveData<List<Client>> = _clients

    private val apiService = RetrofitInstance.api

    // Fetch clients from the API
    fun fetchClients() {
        viewModelScope.launch {
            try {
                val response = apiService.getClients()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _clients.postValue(it) // Update LiveData with the list of clients
                    }
                } else {
                    Log.e("ClientViewModel", "Failed to fetch clients, code: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("ClientViewModel", "Error fetching clients: ${e.message}")
            }
        }
    }

    // Add a new client to the API
    fun addClient(name: String) {
        viewModelScope.launch {
            try {
                val response = apiService.addClient(name)
                if (response.isSuccessful) {
                    fetchClients() // Refresh clients after adding
                } else {
                    Log.e("ClientViewModel", "Failed to add client")
                }
            } catch (e: Exception) {
                Log.e("ClientViewModel", "Error adding client: ${e.message}")
            }
        }
    }
}

