package com.example.experiment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.experiment.databinding.ItemClientBinding

class ClientAdapter : RecyclerView.Adapter<ClientAdapter.ClientViewHolder>() {
    private var clientList: List<Client> = listOf()

    // Method to update the client list and refresh the RecyclerView
    fun setClients(clients: List<Client>) {
        this.clientList = clients
        notifyDataSetChanged() // Notify the adapter that the data has changed
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ClientViewHolder {
        // Inflate the layout using ViewBinding (ItemClientBinding)
        val binding = ItemClientBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ClientViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ClientViewHolder, position: Int) {
        val client = clientList[position]
        holder.binding.client = client  // Bind the client object to the binding
    }

    override fun getItemCount(): Int {
        return clientList.size
    }

    class ClientViewHolder(val binding: ItemClientBinding) : RecyclerView.ViewHolder(binding.root)
}
