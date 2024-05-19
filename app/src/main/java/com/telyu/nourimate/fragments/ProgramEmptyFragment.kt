package com.telyu.nourimate.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.ChooseProgramActivity
import com.telyu.nourimate.databinding.FragmentProgramEmptyBinding

class ProgramEmptyFragment : Fragment() {

    private lateinit var binding: FragmentProgramEmptyBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramEmptyBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonChooseProgram.setOnClickListener {
            startActivity(Intent(requireContext(), ChooseProgramActivity::class.java))
        }
    }
}