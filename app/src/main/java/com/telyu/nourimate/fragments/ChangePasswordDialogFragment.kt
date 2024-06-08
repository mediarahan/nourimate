package com.telyu.nourimate.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentChangePasswordDialogBinding

class ChangePasswordDialogFragment : DialogFragment() {

    private var _binding: FragmentChangePasswordDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChangePasswordDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return binding.root
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

    companion object {
        fun newInstance(): ChangePasswordDialogFragment {
            return ChangePasswordDialogFragment()
        }
    }

}