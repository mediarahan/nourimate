package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.FragmentProgramBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory


class ProgramFragment : Fragment() {

    private lateinit var binding: FragmentProgramBinding

    private val viewModel by viewModels<ProgramViewModel> {
        ViewModelFactory.getInstance(
            requireContext().applicationContext
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramBinding.inflate(inflater, container, false)
        setCurrentFragment(ProgramEmptyFragment())

        viewModel.userWeightTrack.observe(viewLifecycleOwner) { userProgramDetail ->
            if (userProgramDetail == null)
                setCurrentFragment(ProgramEmptyFragment())
            else {
                if (userProgramDetail.ongoingProgram == 0) {
                    setCurrentFragment(ProgramEmptyFragment())
                } else {
                    setCurrentFragment(ProgramFilledFragment())
                    val ongoingProgram = when (userProgramDetail.ongoingProgram) {
                        1 -> "Maintain Weight"
                        2 -> "Loss Weight"
                        3 -> "Gain Weight"
                        else -> "invalid"
                    }
                    binding.titleTextView.text = ongoingProgram
                    val startDate = Converters().formatDate(userProgramDetail.startDate)
                    val endDate = Converters().formatDate(userProgramDetail.endDate)
                    binding.subtitleTextView.text = "$startDate" + " - " + "$endDate"
                }
            }
        }

        return binding.root
    }

    private fun setupSideButtons() {
        binding.menuIcon.setOnClickListener {
            restoreProgramState()
        }
        binding.notificationIcon.setOnClickListener {
            showNotificationSidebar()
        }
    }


    private fun setCurrentFragment(fragment: Fragment) {
        childFragmentManager.beginTransaction().apply {
            replace(R.id.programContentFrame, fragment)
            commit()
        }
    }

    private fun restoreProgramState() {
        TODO("Not yet implemented")
    }

    private fun showNotificationSidebar() {
        TODO("Not yet implemented")
    }



}

