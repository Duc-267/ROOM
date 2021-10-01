package com.example.room.fragments.update

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.room.R
import com.example.room.databinding.FragmentUpdateBinding
import com.example.room.model.User
import com.example.room.viewmodel.UserViewModel

class UpdateFragment : Fragment() {
    private lateinit var binding:FragmentUpdateBinding
    private lateinit var mUserViewModel: UserViewModel
    private val args by navArgs<UpdateFragmentArgs>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        binding.etUpdateAge.setText( args.currentUser.age.toString())
        binding.etUpdateFirstName.setText( args.currentUser.firstName)
        binding.etUpdateLastName.setText( args.currentUser.lastName)
        binding.btnUpdate.setOnClickListener {
            updateInfo()
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    private fun updateInfo() {
        val firstName = binding.etUpdateFirstName.text.toString()
        val lastName= binding.etUpdateLastName.text.toString()
        val age = binding.etUpdateAge.text
        if(checkInput(firstName, lastName, age)){
            val updateUser = User(args.currentUser.id,firstName, lastName, age.toString().toInt())
            mUserViewModel.updateUser(updateUser)
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
            Toast.makeText(requireContext(), "Added Successfully!!", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(requireContext(), "Please fill in!", Toast.LENGTH_LONG).show()
        }
    }
    private fun checkInput(firstName: String, lastName: String, age: Editable): Boolean {
        return !(TextUtils.isEmpty(firstName) || TextUtils.isEmpty(lastName) || age.isEmpty())
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.menu_delete){
            
        }
        return super.onOptionsItemSelected(item)
    }
}