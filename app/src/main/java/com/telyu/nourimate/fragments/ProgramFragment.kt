package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.core.content.ContextCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.FakeFoodData
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.databinding.FragmentProgramBinding
import com.telyu.nourimate.databinding.PopupNotificationProgramkhususBinding
import com.telyu.nourimate.databinding.PopupSettingProgramkhususBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.viewmodels.ProgramViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import kotlinx.coroutines.launch


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

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color16))

        //buat dummy
        //fillDatabaseWithFakeData()

        setupSideButtons()

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

    private fun setStatusBarColor(color: Int) {
        val window = requireActivity().window
        val insetsController = WindowInsetsControllerCompat(window, window.decorView)

        insetsController.isAppearanceLightStatusBars =
            true // Set true or false depending on the status bar icons' color
        insetsController.isAppearanceLightNavigationBars =
            true // Set true or false depending on the navigation bar icons' color

        window.statusBarColor = color
    }

    private fun setupSideButtons() {
        setupSettingPopup()

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

    private fun restoreProgramState(settingPopup: PopupWindow) {
        Log.d("Click", "Click detected")
        val builder = AlertDialog.Builder(requireContext())
            .setTitle("Confirm Restore")
            .setMessage("End the current program and save your results?")
            .setPositiveButton("Yes") { _, _ ->
                setCurrentFragment(ProgramEmptyFragment())
                viewModel.deleteWeightTrackByUserId()
                binding.titleTextView.text = "Choose Program"
                binding.subtitleTextView.text = "No programs registered"

                val currentFragment = childFragmentManager.findFragmentById(R.id.programContentFrame)
                if (currentFragment is ProgramEmptyFragment) {
                    finalizeProgramResults()
                }

                settingPopup.dismiss()
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
        builder.show()
    }

    private fun showNotificationSidebar() {
        setupNotificationPopup()
    }

    //For inputting final results of the program, and deleting relevant weight entries and track
    private fun finalizeProgramResults() {
        Log.d("ProgramFragment", "Finalizing program results")
        viewModel.userWeightTrack.observe(viewLifecycleOwner) { weightTrack ->
            if (weightTrack != null) {
                val programName = when (weightTrack.ongoingProgram) {
                    1 -> "Maintain Weight"
                    2 -> "Loss Weight"
                    3 -> "Gain Weight"
                    else -> "Invalid Program"
                }

                val startDate = Converters().formatDateToString(weightTrack.startDate)
                val endDate = Converters().formatDateToString(weightTrack.endDate)

                viewModel.getNutritionSumsForHistory()
                viewModel.historyNutritionSum.observe(viewLifecycleOwner) { sum ->
                    if (sum != null) {
                        val calories = sum.totalCalories.toInt()
                        val fat = sum.totalFat.toInt()
                        val protein = sum.totalProtein.toInt()
                        val carbs = sum.totalCarbs.toInt()

                        viewModel.userId.observe(viewLifecycleOwner) { id ->
                            if (id != null) {
                                val history = History(
                                    id = 0,
                                    programName = programName,
                                    startDate = startDate,
                                    endDate = endDate,
                                    calories = calories,
                                    protein = protein,
                                    fat = fat,
                                    carbs = carbs,
                                    startWeight = weightTrack.startWeight,
                                    endWeight = weightTrack.endWeight,
                                    userId = id
                                )
                                viewModel.insertHistory(history)
                            } else {
                                Log.e("ProgramFragment", "User ID is null, cannot insert history")
                            }
                        }
                    } else {
                        Log.e("ProgramFragment", "Nutrition sum is null, cannot proceed with history calculation")
                    }
                }
            } else {
                Log.e("ProgramFragment", "Weight track data is null, cannot finalize program results")
            }
        }
    }


    //===== Setting UI =====
    private fun setupSettingPopup() {
        val displayMetrics = Resources.getSystem().displayMetrics
        val width = (displayMetrics.widthPixels * 0.75).toInt()  // 75% of the screen width
        val settingPopupBinding = PopupSettingProgramkhususBinding.inflate(layoutInflater)
        val settingPopup = PopupWindow(
            settingPopupBinding.root,
            width,
            ViewGroup.LayoutParams.MATCH_PARENT,
            true
        ).apply {
            isOutsideTouchable = true
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))  // Transparent unused area
        }

        settingPopupBinding.btnRestoreProgram.setOnClickListener {
            restoreProgramState(settingPopup)
        }

        binding.menuIcon.setOnClickListener {
            settingPopup.showAtLocation(binding.root, Gravity.START, 0, 0)
        }
    }

    private fun setupNotificationPopup() {
        val displayMetrics = Resources.getSystem().displayMetrics
        val width = (displayMetrics.widthPixels * 0.75).toInt()
        val notifPopupBinding = PopupNotificationProgramkhususBinding.inflate(layoutInflater)
        val notifPopup = PopupWindow(
            notifPopupBinding.root,
            width,
            ViewGroup.LayoutParams.MATCH_PARENT,
            true
        ).apply {
            isOutsideTouchable = true
            setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

        binding.notificationIcon.setOnClickListener {
            notifPopup.showAtLocation(binding.root, Gravity.END, 0, 0)
        }
    }

    //===== Buat masukin dummy data =====
    private fun fillDatabaseWithFakeData() {

        // Untuk isi database dengan fake data
        val dao = UserDatabase.getInstance(requireContext()).userDao()
        val fakeFoodData = FakeFoodData()

        val mappedWeightEntries = fakeFoodData.weightEntries.map { weightEntry ->
            WeightEntry(
                id = weightEntry.id,
                weight = weightEntry.weight,
                date = weightEntry.date,
                userId = weightEntry.userId,
            )
        }

        lifecycleScope.launch {
            mappedWeightEntries.forEach { weightEntry ->
                dao.insertWeightEntry(weightEntry)
            }
        }

    }
}

