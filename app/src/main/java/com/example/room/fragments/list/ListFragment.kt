package com.example.room.fragments.list

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.room.R
import com.example.room.viewmodel.UserViewModel
import com.example.room.databinding.FragmentListBinding


class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var mUserViewModel: UserViewModel
    private lateinit var binding: FragmentListBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        binding.FloatingActionButton.setOnClickListener {
            findNavController().navigate(R.id.action_listFragment_to_addFragment)
        }
        val adapter = ListAdapter()
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)

        mUserViewModel.readAllData.observe(viewLifecycleOwner, { user->
            adapter.setData(user)
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            val builder = AlertDialog.Builder(requireContext())
                .setTitle("Delete")
                .setMessage("Do you want to delete all users?")
                .setIcon(R.drawable.ic_delete)
                .setPositiveButton("Yes"){ _: DialogInterface, _: Int ->
                    mUserViewModel.deleteAllUser()
                    Toast.makeText(requireContext(), "You removed all users",
                        Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("No"){ _: DialogInterface, _: Int ->

                }
                .create()
            builder.show()
        }
        return super.onOptionsItemSelected(item)
    }
}