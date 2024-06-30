package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.activities.LoginActivity
import com.telyu.nourimate.databinding.FragmentLogoutDialogBinding
import com.telyu.nourimate.viewmodels.ProfileViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class LogoutDialogFragment : DialogFragment() {


    private var _binding: FragmentLogoutDialogBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        _binding = FragmentLogoutDialogBinding.inflate(LayoutInflater.from(context))
        val builder = AlertDialog.Builder(requireContext())
        builder.setView(binding.root)

        // Set up the buttons using binding
        binding.buttonLogout.setOnClickListener {
            // Perform logout action, for example navigate to LoginActivity
            // Assuming viewModel is accessible
            viewModel.onSignOutButtonClick()
            viewModel.logout()
            startActivity(Intent(requireContext(), LoginActivity::class.java))
            activity?.finish()  // Finish current activity to prevent user from going back
            dismiss()  // Close the dialog on logout
        }

        binding.buttonCancel.setOnClickListener {
            dismiss()  // Dismiss the dialog when cancel is clicked
        }

        // Create the AlertDialog object and return it
        val dialog = builder.create()
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            // Set the layout parameters of the dialog window
            val params: WindowManager.LayoutParams = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.CENTER
            attributes = params
        }

        // Set dialog width to a percentage of the screen width
        setWidthPercent(85)
    }

    private fun setWidthPercent(percent: Int) {
        val width = (resources.displayMetrics.widthPixels * (percent / 100.0)).toInt()
        dialog?.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}