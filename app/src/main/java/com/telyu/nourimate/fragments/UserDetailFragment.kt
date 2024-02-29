package com.telyu.nourimate.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.activities.EditProfileActivity
import com.telyu.nourimate.databinding.FragmentUserDetailBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.UserDetailViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class UserDetailFragment : Fragment() {
    private lateinit var binding: FragmentUserDetailBinding
    private val viewModel by viewModels<UserDetailViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentUserDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.backButton.setOnClickListener {
            // Navigasi kembali ke ProfileFragment
            requireActivity().supportFragmentManager.popBackStack()
        }

        getAllData()
        mapAllDataToView()

    }

    private fun getAllData() {
        //dapatkan value email dari user preference
        //gunakan email untuk mendapatkan id pengguna di database
        //gunakan email untuk mendapatkan nama pengguna di database
        //, dipisah karena entity nya pun terpisan
        viewModel.userEmail.observe(viewLifecycleOwner) { userEmail ->
            userEmail?.let {
                viewModel.getUserIdByEmail(it)
                viewModel.getUserNameByEmail(it)
            }
        }

        //gunakan id pengguna untuk mendapatkan detail pengguna dari database
        viewModel.userId.observe(viewLifecycleOwner) {userId ->
            if (userId != null)
                viewModel.getUserDetailsById(userId)
        }
    }

    private fun mapAllDataToView() {
        //mapping setiap atribut detail ke edittext
        viewModel.userDetails.observe(viewLifecycleOwner) {userDetails ->
            userDetails?.let {detail ->
                val formattedDate = Converters().formatDateToString(detail.dob)
                binding.textViewBirth.text = formattedDate
                binding.textViewHeight.text = "${detail.height?.toString() ?: ""} cm"
                binding.textViewWeight.text = "${detail.weight?.toString() ?: ""} kg"
                binding.textViewWaist.text = "${detail.waistSize?.toString() ?: ""} cm"
                binding.textViewGender.text = detail.gender
                binding.textViewAllergy.text = detail.allergen
                binding.textViewDisease.text = detail.disease
            }
        }

        //mapping nama dari entity user, terpisah
        viewModel.userName.observe(viewLifecycleOwner) {userName ->
            binding.textViewName.text = userName
        }
    }

}
