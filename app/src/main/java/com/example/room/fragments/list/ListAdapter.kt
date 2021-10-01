package com.example.room.fragments.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.room.model.User
import com.example.room.databinding.ItemHolderBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.ListHolder>() {
    private var listUser = emptyList<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        val binding = ItemHolderBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListHolder(binding)
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = listUser[position]
        holder.age.text = currentItem.age.toString()
        holder.firstName.text = currentItem.firstName
        holder.lastName.text = currentItem.lastName
        holder.id.text = currentItem.id.toString()
        holder.tempBinding.rowLayout.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
            holder.tempBinding.rowLayout.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int = listUser.size

    inner class ListHolder(binding:ItemHolderBinding):RecyclerView.ViewHolder(binding.root) {
        val tempBinding = binding
        val id = binding.tvId
        val firstName = binding.tvFirstName
        val lastName = binding.tvLastName
        var age = binding.tvAge
    }
    fun setData(user: List<User>){
        this.listUser = user
        notifyDataSetChanged()
    }
}