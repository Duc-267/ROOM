package com.example.room.fragments.add

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.room.R
import com.example.room.data.User
import com.example.room.data.UserViewModel
import com.example.room.databinding.FragmentAddBinding

class AddFragment : Fragment() {
    private lateinit var binding: FragmentAddBinding
    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        binding.btnAdd.setOnClickListener {
            insertToDataBase()
        }
        mUserViewModel = UserViewModel(requireActivity().application)
        return binding.root
    }

    private fun insertToDataBase() {
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val age = binding.etAge.text
        if(checkInput(firstName , lastName ,age)){
            mUserViewModel.addUser(User(0, firstName, lastName, age.toString().toInt()))
            Toast.makeText(requireContext(), "Added!", Toast.LENGTH_SHORT).show()
            findNavController().navigate(R.id.action_addFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill in!", Toast.LENGTH_SHORT).show()
        }

    }

    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && age.isEmpty())
    }

}