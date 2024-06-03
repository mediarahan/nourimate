package com.telyu.nourimate.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentProfileBinding
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.EditProfpicActivity
import com.telyu.nourimate.activities.LoginActivity
import com.telyu.nourimate.viewmodels.ProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    companion object {
        private const val REQUEST_IMAGE_CAPTURE = 1
        private const val REQUEST_PICK_IMAGE = 2
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
            showImageSourceDialog()
        }
    }

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars = true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars = true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }

    private fun showImageSourceDialog() {
        val options = arrayOf("Camera", "Galeri")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose Image Source")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> dispatchTakePictureIntent()
                    1 -> dispatchPickPictureIntent()
                }
            }
            .show()
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(requireContext().packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    private fun dispatchPickPictureIntent() {
        val pickIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(pickIntent, REQUEST_PICK_IMAGE)
    }


    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                // Perform logout action, for example navigate to LoginActivity
                viewModel.onSignOutButtonClick()
                viewModel.logout()
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish() // Finish current activity to prevent user from going back
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
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

        viewModel.userName.observe(viewLifecycleOwner) {userName ->
            binding.nameTextView.text = userName
        }
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
        binding.profileButton.setOnClickListener {
            // Kode untuk menuju ke EditProfileFragment
            val editProfileFragment = UserDetailFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, editProfileFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol Account
        binding.accountButton.setOnClickListener {
            // Kode untuk menuju ke AccountFragment
            val accountFragment = AccountFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, accountFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol History
        binding.historyButton.setOnClickListener {
            // Kode untuk menuju ke AccountFragment
            val historyFragment = HistoryFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, historyFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol Community
        binding.communityButton.setOnClickListener {
            // Kode untuk menuju ke CommunityFragment
            val communityFragment = CommunityFragment()
            requireActivity().supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, communityFragment)
                addToBackStack(null)
                commit()
            }
        }

        // Implementasi event click untuk tombol FAQ
        binding.faqButton.setOnClickListener {
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