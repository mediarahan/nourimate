package com.telyu.nourimate.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentProfileBinding
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.viewModels
import com.github.dhaval2404.imagepicker.ImagePicker
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
        const val EDIT_PROFILE_PIC_REQUEST = 3
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

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color46))

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
        val options = arrayOf("Camera", "Gallery")
        MaterialAlertDialogBuilder(requireContext())
            .setTitle("Choose Image Source")
            .setItems(options) { dialog, which ->
                when (which) {
                    0 -> launchCamera()
                    1 -> launchGallery()
                }
            }
            .show()
    }

    private fun launchCamera() {
        ImagePicker.with(this)
            .cameraOnly()
            .crop()
            .start(REQUEST_IMAGE_CAPTURE)
    }

    private fun launchGallery() {
        ImagePicker.with(this)
            .galleryOnly()
            .crop()
            .start(REQUEST_PICK_IMAGE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Handling results from camera capture or gallery pick
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_IMAGE_CAPTURE, REQUEST_PICK_IMAGE -> {
                    data?.data?.let { uri ->
                        openEditProfpicActivity(uri)
                    }
                }
                EDIT_PROFILE_PIC_REQUEST -> {
                    // This checks if the data returned from EditProfpicActivity is not null and updates the ImageView.
                    data?.getStringExtra("imageUri")?.let { uriString ->
                        val uri = Uri.parse(uriString)
                        binding.imageViewAvatar.setImageURI(uri)
                    }
                }
            }
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            // Handling error from image picking library
            Toast.makeText(context, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            // Handling cancellation
            if (requestCode == REQUEST_IMAGE_CAPTURE || requestCode == REQUEST_PICK_IMAGE) {
                Toast.makeText(context, "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun openEditProfpicActivity(uri: Uri) {
        val intent = Intent(context, EditProfpicActivity::class.java)
        intent.putExtra("imageUri", uri.toString())
        startActivityForResult(intent, EDIT_PROFILE_PIC_REQUEST)
    }

    private fun refreshProfilePicture() {
        viewModel.profilePicture.observe(viewLifecycleOwner) { uriString ->
            uriString?.let { uriStr ->
                val uri = Uri.parse(uriStr)
                binding.imageViewAvatar.setImageURI(uri)
            }
        }
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

    private fun getBmiBackgroundResource(bmi: Int): Int {
        return when {
            bmi < 18.5 -> R.drawable.rectangle_light_green
            bmi.toDouble() in 18.5..24.9 -> R.drawable.rectangle_green
            bmi.toDouble() in 25.0..29.9 -> R.drawable.rectangle_orange
            else -> R.drawable.rectangle_red
        }
    }

    private fun mapBMIAndName() {
        viewModel.BMI.observe(viewLifecycleOwner) { userBMI ->
            userBMI?.let {
                val formattedBMI = it.toInt()
                binding.bmiResultTextView.text = "Body Mass Index: $formattedBMI"
                val bmiBackground = getBmiBackgroundResource(formattedBMI)
                binding.bmiResultTextView.setBackgroundResource(bmiBackground)
            }
        }
        viewModel.userName.observe(viewLifecycleOwner) { userName ->
            userName?.let {
                val truncatedUserName = truncateUserName(it, wordLimit = 1, maxChars = 8)
                binding.nameTextView.text = truncatedUserName
            }
        }
    }

    private fun truncateUserName(userName: String, wordLimit: Int = 1, maxChars: Int = 8): String {
        val words = userName.split(" ").take(wordLimit).joinToString(" ")
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