package com.telyu.nourimate.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telyu.nourimate.R
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.databinding.FragmentDecadeViewBinding
import android.widget.ArrayAdapter
import android.widget.GridView
import java.util.Calendar

class DecadeViewFragment : DialogFragment() {
    private var listener: ((Int) -> Unit)? = null

    private var _binding: FragmentDecadeViewBinding? = null
    private val binding get() = _binding!!

    fun setOnYearSelectedListener(listener: (Int) -> Unit) {
        this.listener = listener
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val metrics = resources.displayMetrics
            val width = metrics.widthPixels * 0.85
            val height = metrics.heightPixels * 0.5
            setLayout(width.toInt(), height.toInt())
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            setGravity(Gravity.CENTER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDecadeViewBinding.inflate(inflater, container, false)
        val view = binding.root

        val gridView: GridView = binding.gridViewDecade

        // Create a list of years from 1950 to 2100
        val years = (1980..2030).toList()

        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, years)
        gridView.adapter = adapter
        gridView.setOnItemClickListener { _, _, position, _ ->
            listener?.invoke(years[position])
            dismiss()
        }

        return view
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}