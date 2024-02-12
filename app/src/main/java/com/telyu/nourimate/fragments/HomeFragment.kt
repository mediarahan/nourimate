package com.telyu.nourimate.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.databinding.FragmentHomeBinding
import com.telyu.nourimate.viewmodels.HomeViewModel

class HomeFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        viewModel.greetingMessage.observe(viewLifecycleOwner, Observer {
            binding.greetingTextView.text = it
        })

        viewModel.weightMessage.observe(viewLifecycleOwner, Observer {
            binding.weightMessageTextView.text = it
        })

        viewModel.userProfilePhoto.observe(viewLifecycleOwner, Observer { userProfilePhoto ->
            binding.profileImageView.setImageBitmap(userProfilePhoto)
        })

        // Mengamati perubahan pada waktu tidur
        viewModel.sleepTime.observe(viewLifecycleOwner, Observer { sleepTime ->
            binding?.timeInSleep?.text = getString(R.string.sleep_time_format, sleepTime)
        })

        // Mengamati perubahan pada waktu bangun
        viewModel.wakeUpTime.observe(viewLifecycleOwner, Observer { wakeUpTime ->
            binding?.wakeUpTime?.text = getString(R.string.wake_up_time_format, wakeUpTime)
        })

        viewModel.runningSpeed.observe(viewLifecycleOwner, Observer { speed ->
            binding?.runningSpeedTextView?.text = speed
        })

        // Amati data grafik lari dan perbarui CustomRunningGraphView
        viewModel.runningGraphData.observe(viewLifecycleOwner, Observer { graphData ->
            val runningGraphView = binding?.runningGraphView as? CustomRunningGraphView
            runningGraphView?.setData(graphData)
        })

        // Observe the macronutrient data and update the UI
        viewModel.totalCalories.observe(viewLifecycleOwner, Observer { calories ->
            binding?.textViewTotalCalories?.text = getString(R.string.total_calories, calories)
            // Assuming you have a ProgressBar for calories
            binding?.progressBarTotalCalories?.progress = viewModel.caloriesProgress.value ?: 0
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }
}
