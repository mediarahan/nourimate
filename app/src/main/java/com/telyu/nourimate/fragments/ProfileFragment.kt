package com.telyu.nourimate

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.FragmentProfileBinding
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.telyu.nourimate.fragments.AccountFragment
import com.telyu.nourimate.fragments.CommunityFragment
import com.telyu.nourimate.fragments.EditProfileFragment
import com.telyu.nourimate.fragments.FaqFragment
import com.telyu.nourimate.fragments.HistoryFragment
import com.telyu.nourimate.fragments.SettingFragment


class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var bmiResultTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bmiResultTextView = binding.bmiResultTextView

        sharedPreferences = requireContext().getSharedPreferences("ProfileData", Context.MODE_PRIVATE)

        // Tampilkan hasil BMI
        showBMIResult()

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
        binding.logoutIcon.setOnClickListener {
            showLogoutConfirmationDialog()
        }

        // Implementasi event click untuk tombol Profile
        binding.profileButton.setOnClickListener {
            // Kode untuk menuju ke EditProfileFragment
            val editProfileFragment = EditProfileFragment()
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
            // Kode untuk menuju ke HistoryFragment
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

    private fun showLogoutConfirmationDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setTitle("Logout")
            .setMessage("Are you sure you want to logout?")
            .setPositiveButton("Logout") { _, _ ->
                // Perform logout action, for example navigate to LoginActivity
                startActivity(Intent(requireContext(), LoginActivity::class.java))
                requireActivity().finish() // Finish current activity to prevent user from going back
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showBMIResult() {
        // Ambil nilai tinggi dan berat dari SharedPreferences
        val height = sharedPreferences.getFloat("height", 0f)
        val weight = sharedPreferences.getFloat("weight", 0f)

        // Hitung BMI
        val bmi = calculateBMI(height, weight)

        // Tampilkan hasil BMI
        bmiResultTextView.text = "Body Mass Index: %.2f".format(bmi)
    }

    private fun calculateBMI(height: Float, weight: Float): Float {
        // Hitung BMI menggunakan rumus
        return weight / (height * height)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
