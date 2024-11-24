package com.example.experiment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class MainActivity : AppCompatActivity() {

    private lateinit var clientViewModel: ClientViewModel
    private lateinit var editTextClientName: EditText
    private lateinit var buttonAddClient: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var clientAdapter: ClientAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clientViewModel = ViewModelProvider(this).get(ClientViewModel::class.java)

        editTextClientName = findViewById(R.id.editTextClientName)
        buttonAddClient = findViewById(R.id.buttonAddClient)
        recyclerView = findViewById(R.id.recyclerView)

        // Set up RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        clientAdapter = ClientAdapter()
        recyclerView.adapter = clientAdapter

        // Observe LiveData from ViewModel
        clientViewModel.clients.observe(this, Observer { clients ->
            clients?.let {
                clientAdapter.setClients(it) // Update RecyclerView when client list changes
            }
        })

        // Button click listener to add client
        buttonAddClient.setOnClickListener {
            val name = editTextClientName.text.toString()
            if (name.isNotEmpty()) {
                clientViewModel.addClient(name) // Add client and refresh the list
                editTextClientName.text.clear() // Clear the input field after adding
            } else {
                Toast.makeText(this, "Please enter a client name", Toast.LENGTH_SHORT).show()
            }
        }

        // Fetch clients when the activity is created
        clientViewModel.fetchClients()
    }
}
