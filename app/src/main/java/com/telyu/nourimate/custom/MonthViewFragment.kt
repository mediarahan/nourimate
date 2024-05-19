package com.telyu.nourimate.custom

import android.os.Bundle
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentMonthViewBinding
import android.widget.ArrayAdapter

class MonthViewFragment : DialogFragment() {
    private var listener: ((Int) -> Unit)? = null

    private var _binding: FragmentMonthViewBinding? = null
    private val binding get() = _binding!!

    fun setOnMonthSelectedListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val width = context.resources.displayMetrics.widthPixels * 0.85 // Menggunakan 85% dari lebar layar
            setLayout(width.toInt(), ViewGroup.LayoutParams.WRAP_CONTENT)
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMonthViewBinding.inflate(inflater, container, false)
        val view = binding.root

        val months = arrayOf("Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec")
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, months)
        binding.gridViewMonth.adapter = adapter // Use the correct binding instance here

        binding.gridViewMonth.setOnItemClickListener { _, _, position, _ ->
            listener?.invoke(position)
            dismiss()
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Here's where you set the item click listener
        binding.gridViewMonth.setOnItemClickListener { _, _, position, _ ->
            listener?.invoke(position) // The 'listener' will pass the position back to the activity/fragment that implemented it.
            dismiss() // Dismiss the dialog fragment once a month is selected
        }

        // Set up the adapter for the GridView if not already done in onCreateView
    }



    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
