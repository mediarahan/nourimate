package com.telyu.nourimate.fragments

import android.app.AlertDialog
import android.content.Intent
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
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.TransitionProgramActivity
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.History
import com.telyu.nourimate.databinding.FragmentProgramBinding
import com.telyu.nourimate.databinding.PopupNotificationProgramkhususBinding
import com.telyu.nourimate.databinding.PopupSettingProgramkhususBinding
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.GeneralUtil
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

        setStatusBarColor(ContextCompat.getColor(requireContext(), R.color.color55))

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        checkIfProgramIsOver()
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

//        binding.notificationIcon.setOnClickListener {
//            showNotificationSidebar()
//        }
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
                binding.titleTextView.text = "Choose Program"
                binding.subtitleTextView.text = "No programs registered"

                finalizeProgramResults()

                val intent = Intent(context, TransitionProgramActivity::class.java)
                startActivity(intent)

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
        viewModel.userWeightTrack.observe(viewLifecycleOwner) { weightTrack ->
            val programName = when (weightTrack.ongoingProgram) {
                1 -> "Maintain Weight"
                2 -> "Loss Weight"
                3 -> "Gain Weight"
                else -> "Invalid Program"
            }

            val startDate = Converters().formatDateToString(weightTrack.startDate)
            // val endDate = Converters().formatDateToString(weightTrack.endDate)
            val todayDate = Converters().dateFromTimestamp(System.currentTimeMillis())
            val todayDateString = Converters().formatDateToString(todayDate)

            viewModel.getNutritionSumsForHistory()
            viewModel.historyNutritionSum.observe(viewLifecycleOwner) { sum ->
                if (sum != null) {
                    val calories = sum.totalCalories.toInt()
                    val fat = sum.totalFat.toInt()
                    val protein = sum.totalProtein.toInt()
                    val carbs = sum.totalCarbs.toInt()

                    //insert history to local
                    viewModel.insertHistory(
                        programName,
                        startDate,
                        todayDateString,
                        calories,
                        fat,
                        protein,
                        carbs,
                        weightTrack.startWeight,
                        weightTrack.endWeight,
                    )

                    //TODO: insert history to backend
                    viewModel.createNewHistory(
                        programName,
                        startDate,
                        todayDateString,
                        calories,
                        fat,
                        protein,
                        carbs,
                        weightTrack.startWeight,
                        weightTrack.endWeight
                    )
                }
            }
            //TODO: insert to detail (update weight)
            viewModel.userDetails.observe(viewLifecycleOwner) { detail ->
                viewModel.insertDetail(
                    detail.dob,
                    detail.height,
                    weightTrack.endWeight, //new endweight
                    detail.waistSize,
                    detail.gender,
                    detail.allergen,
                    detail.disease,
                    detail.bmi,
                )

                //TODO: insert to backend detail
                viewModel.insertDetailBackend(
                    Converters().formatDateToString(detail.dob),
                    detail.height,
                    weightTrack.endWeight,
                    detail.waistSize,
                    detail.gender,
                    detail.allergen,
                    detail.disease,
                )
            }
        }
        viewModel.saveUserProgram(0)
        viewModel.deleteWeightTrackByUserId()
        viewModel.deleteWeightEntriesById()
        viewModel.deleteMealHistoriesById()
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

//        binding.notificationIcon.setOnClickListener {
//            notifPopup.showAtLocation(binding.root, Gravity.END, 0, 0)
//        }
    }

    //=========== Check If Program is Over ==========
    private fun checkIfProgramIsOver() {
        viewModel.userEndDate.observe(viewLifecycleOwner) { date ->
            val today = GeneralUtil.calculateTodayMidnight()
            Log.d("Today", today.toString())
            Log.d("Date", date.toString())
            if (today > date) {
                AlertDialog.Builder(requireContext())
                    .setTitle("Program Over")
                    .setMessage("Your diet program is over. Click OK to check your results.")
                    .setPositiveButton("OK") { _, _ ->
                        finalizeProgramResults()
                        val intent = Intent(context, TransitionProgramActivity::class.java)
                        startActivity(intent)
                    }
                    .show()
            }
        }
    }
}

