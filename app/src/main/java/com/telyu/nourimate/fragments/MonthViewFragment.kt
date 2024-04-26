package com.telyu.nourimate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telyu.nourimate.R
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentMonthViewBinding
import android.widget.ArrayAdapter
import android.widget.GridView
import com.telyu.nourimate.adapter.DateAdapter
import java.util.Calendar

class MonthViewFragment : DialogFragment() {
    private var listener: ((Int) -> Unit)? = null

    private var _binding: FragmentMonthViewBinding? = null
    private val binding get() = _binding!!

    fun setOnMonthSelectedListener(listener: (Int) -> Unit) {
        this.listener = listener
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
