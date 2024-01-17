package com.telyu.nourimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.greetingMessage.observe(viewLifecycleOwner, Observer {
            binding.greetingTextView.text = it
        })

        viewModel.weightMessage.observe(viewLifecycleOwner, Observer {
            binding.weightMessageTextView.text = it
        })

        // Load user profile photo (replace with your implementation)
        // val userProfilePhoto = viewModel.getUserProfilePhoto()
        // binding.profileImageView.setImageBitmap(userProfilePhoto)
    }
}
