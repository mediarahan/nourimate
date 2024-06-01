package com.telyu.nourimate.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentProfileBinding
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.EditProfpicActivity
import com.telyu.nourimate.activities.LoginActivity
import com.telyu.nourimate.databinding.CustomLogoutDialogBinding
import com.telyu.nourimate.viewmodels.ProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color20))

        displayImage()
        setupButtons()
        getBMIAndName()
        mapBMIAndName()

        binding.cardViewAddAvatar.setOnClickListener {
            val intent = Intent(requireContext(), EditProfpicActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars = true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }

    private fun showLogoutConfirmationDialog() {
        // Inflate the custom layout using ViewBinding
        val binding = CustomLogoutDialogBinding.inflate(layoutInflater)

        // Create the AlertDialog using the builder
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        // Set the dialog message
        binding.textViewMessage.text = "Are you sure you want to logout?"

        // Configure the dialog buttons
        builder.setPositiveButton("Logout") { _, _ ->
            viewModel.onSignOutButtonClick()
            viewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            requireActivity().finish()
        }

        builder.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        // Create and show the dialog
        builder.create().show()
    }


    private fun getBMIAndName() {
        viewModel.userEmail.observe(viewLifecycleOwner) {userEmail ->
            userEmail?.let {
                viewModel.getUserIdByEmail(it)
                viewModel.getUserNameByEmail(it)
            }
        }

        viewModel.userId.observe(viewLifecycleOwner) {userId ->
            viewModel.getBMIById(userId)
        }
    }

    private fun mapBMIAndName() {
        viewModel.BMI.observe(viewLifecycleOwner) {userBMI ->
            binding.bmiResultTextView.text = "Body Mass Index: ${ userBMI.toString() }"
        }

        viewModel.userName.observe(viewLifecycleOwner) { userName ->
            userName?.let {
                val truncatedUserName = truncateUserName(it, wordLimit = 1, maxChars = 8)
                binding.nameTextView.text = truncatedUserName
            }
        }
    }

    private fun truncateUserName(userName: String, wordLimit: Int = 1, maxChars: Int = 8): String {
        // Memisahkan string menjadi kata-kata dan mengambil beberapa kata pertama berdasarkan wordLimit
        val words = userName.split(" ").take(wordLimit).joinToString(" ")
        // Memotong string jika panjangnya melebihi maxChars
        return if (words.length > maxChars) words.substring(0, maxChars) else words
    }

    private fun displayImage() {
        viewModel.userId.observe(viewLifecycleOwner) {userId ->
            if (userId != null) {
                viewModel.getProfpicById(userId)
            }
        }

        viewModel.profilePicture.observe(viewLifecycleOwner) { uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.imageViewAvatar.setImageURI(uri)
            }
        }
    }

    private fun setupButtons() {
        // Handle click on settings icon
        binding.settingsIcon.setOnClickListener {
            val settingFragment = SettingFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, settingFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Handle click on logout icon
        binding.SignOutButton.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        // Implementasi event click untuk tombol Profile
        binding.profileIcon.setOnClickListener {
            // Kode untuk menuju ke EditProfileFragment
            val editProfileFragment = UserDetailFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, editProfileFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol Account
        binding.accountIcon.setOnClickListener {
            // Kode untuk menuju ke AccountFragment
            val accountFragment = AccountFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, accountFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol History
        binding.historyIcon.setOnClickListener {
            // Kode untuk menuju ke AccountFragment
            val historyFragment = HistoryFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, historyFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol Community
        binding.communityIcon.setOnClickListener {
            // Kode untuk menuju ke CommunityFragment
            val communityFragment = CommunityFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, communityFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol FAQ
        binding.faqIcon.setOnClickListener {
            // Kode untuk menuju ke FaqFragment
            val faqFragment = FaqFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, faqFragment)
                addToBackStack(null)
                commit()
            }
        }

    }
}