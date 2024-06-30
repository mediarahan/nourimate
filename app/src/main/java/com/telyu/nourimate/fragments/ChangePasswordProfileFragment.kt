package com.telyu.nourimate.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.databinding.FragmentChangePasswordProfileBinding
import com.telyu.nourimate.viewmodels.DetailChangeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class ChangePasswordProfileFragment : DialogFragment() {

    private var _binding: FragmentChangePasswordProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailChangeViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordProfileBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        binding.buttonSave.setOnClickListener {
            changePhoneNumber()
        }

        return binding.root
    }

    private fun changePhoneNumber() {
        val oldPassword = binding.editTextCurrentPassword.text.toString()
        val password = binding.editTextNewPassword.text.toString()
        val confirmPassword = binding.editTextNewPassword.text.toString()

        viewModel.changePassword(oldPassword,password, confirmPassword).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Login Success", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Login Failed", Toast.LENGTH_SHORT).show()
                }
            }
        }
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

    private fun showLoading(isLoading: Boolean) {
        binding.progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }

    companion object {
        fun newInstance():ChangePasswordProfileFragment {
            return ChangePasswordProfileFragment()
        }
    }

}