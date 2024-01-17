package com.telyu.nourimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.databinding.FragmentProgramBinding

class ProgramFragment : Fragment() {

    private lateinit var viewModel: ProgramViewModel
    private lateinit var binding: FragmentProgramBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProgramBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProgramViewModel::class.java)

        viewModel.currentWeight.observe(viewLifecycleOwner, Observer {
            binding.weightTextView.text = "Current Weight: $it kg"
        })

        // Implement logic for editing weight (e.g., through EditText)
    }
}
