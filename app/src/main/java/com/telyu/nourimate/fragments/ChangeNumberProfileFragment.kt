package com.telyu.nourimate.fragments

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.activities.EditProfileActivity
import com.telyu.nourimate.data.remote.Result
import com.telyu.nourimate.databinding.FragmentChangeNumberProfileBinding
import com.telyu.nourimate.viewmodels.DetailChangeViewModel
import com.telyu.nourimate.viewmodels.RecipeViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class ChangeNumberProfileFragment : DialogFragment() {

    private var _binding: FragmentChangeNumberProfileBinding? = null
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
        _binding = FragmentChangeNumberProfileBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        binding.buttonSave.setOnClickListener {
            changePhoneNumber()
        }

        return binding.root
    }

    private fun changePhoneNumber() {
        val phoneNumber = binding.editTextCurrentNumber.text.toString()
        val confirmPhoneNumber = binding.editTextConfirmNewNumber.text.toString()
        viewModel.changePhoneNumber(phoneNumber, confirmPhoneNumber).observe(viewLifecycleOwner) { result ->
            when (result) {
                is Result.Loading -> showLoading(true)
                is Result.Success -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Change Phone Number Success", Toast.LENGTH_SHORT).show()
                    dismiss()
                }
                is Result.Error -> {
                    showLoading(false)
                    Toast.makeText(requireContext(), "Change Phone Number Failed", Toast.LENGTH_SHORT).show()
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
        fun newInstance():ChangeNumberProfileFragment {
            return ChangeNumberProfileFragment()
        }
    }

}