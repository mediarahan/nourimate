package com.telyu.nourimate.fragments

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.PopupWindow
import androidx.appcompat.app.AlertDialog
import androidx.core.app.NotificationCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Detail
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.databinding.FragmentProgramBinding
import com.telyu.nourimate.databinding.PopupNotificationProgramkhususBinding
import com.telyu.nourimate.databinding.PopupSettingProgramkhususBinding
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

    private var isStartWeightSet = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProgramBinding.inflate(inflater, container, false)
        setupSettingPopup()
        setupNotificationPopup()

        // Restore the saved program state from SharedPreferences
        val sharedPref = activity?.getSharedPreferences("ProgramPrefs", Context.MODE_PRIVATE)
        val savedProgram = sharedPref?.getString("SelectedProgram", "") ?: ""
        if (savedProgram.isNotEmpty()) {
            viewModel.selectedProgram.value = savedProgram
        }

        restoreProgramState()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupSpinner()

        viewModel.selectedProgram.observe(viewLifecycleOwner) { program ->
            if (program != null) changeFragmentView(program)
        }

    }

    //yu ai pokonya mah

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
            showConfirmRestoreDialog(settingPopup)
        }

        binding.menuIcon.setOnClickListener {
            settingPopup.showAtLocation(binding.root, Gravity.START, 0, 0)
        }
    }

    //INI FUNGSI YANG NGATUR SI RESTORE PROGRAM ITU NGAPAIN
    private fun showConfirmRestoreDialog(settingPopup: PopupWindow) {
        AlertDialog.Builder(requireContext())
            .setTitle("Confirm Restore")
            .setMessage("Are you sure you want to restore and change the program?")
            .setPositiveButton("Yes") { dialog, id ->
                restoreProgramState()
                settingPopup.dismiss()
                binding.programSpinner.visibility = View.VISIBLE
                binding.rectangleprogramImageView.visibility = View.VISIBLE
                binding.titleTextView.visibility = View.VISIBLE
                binding.subtitleTextView.visibility = View.VISIBLE
                binding.programSpinner.setSelection(0)

                //ilangin UI utamanya
                binding.groupStartingAndCurrentWeight.visibility = View.GONE
                binding.groupGraph.visibility = View.GONE
                binding.groupBurger.visibility = View.GONE

                //Ngebuat boolean nya false lagi, supaya bisa di enter pas masuk program lain
                isStartWeightSet = false

                //TODO: Masukin si weight nya ke database
                val endWeight = binding.textViewCurrentWeight.text.toString().toInt()
                viewModel.userDetails.observe(viewLifecycleOwner) {detail ->
                    detail.let {
                        val details = Detail(it.detailId, it.dob, it.height, endWeight.toFloat(), it.waistSize, it.gender, it.allergen, it.disease, it.bmi)
                        viewModel.insertDetail(details)
                    }
                }

            }
            .setNegativeButton("No") { dialog, id ->
                settingPopup.dismiss()
            }
            .show()
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

    private fun setupSpinner() {
        val adapter = ArrayAdapter(
            requireContext(),
            R.layout.custom_spinner_item,
            resources.getStringArray(R.array.program_choices)
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.programSpinner.adapter = adapter

        binding.programSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    if (position > 0) {  // Skip if the 'Select Program' hint is selected
                        val choice = parent.getItemAtPosition(position).toString()

                        // Prepare the confirmation dialog
                        val dialogBuilder = AlertDialog.Builder(requireContext())
                        dialogBuilder.setTitle("Confirm Program Selection")
                            .setMessage("Are you sure you want to choose this program?")
                            .setPositiveButton("Yes") { _, _ ->
                                handleProgramSelection(choice, position)
                            }
                            .setNegativeButton("No") { _, _ ->
                                binding.programSpinner.setSelection(0)
                            }

                        when (position) {
                            1 -> dialogBuilder.show()
                            2 -> dialogBuilder.show()
                            3 -> dialogBuilder.show()
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>) {}
            }
    }

    private fun handleProgramSelection(programName: String, position: Int) {
        viewModel.selectedProgram.value = programName
        changeFragmentView(programName)

        // Hide spinner and other irrelevant UI elements
        binding.programSpinner.visibility = View.GONE
        binding.rectangleprogramImageView.visibility = View.GONE
        binding.groupStartingAndCurrentWeight.visibility = View.VISIBLE
        binding.groupGraph.visibility = View.VISIBLE
        binding.groupBurger.visibility = View.VISIBLE

        when (position) {
            1 -> setupProgram("Maintain Weight") {}

            2 -> setupProgram("Loss Weight") {}

            3 -> setupProgram("Gain Weight") {}
        }
    }

    private fun setupProgram(title: String, additionalSetup: () -> Unit) {
        binding.titleTextView.text = title

        // Set up common observations
        //setupWeightTrackInsertion(title)
        setupWeightEntryObservation()
        setupUserDetailsObservation()

        // Additional setup specific to each program
        additionalSetup()
    }

    private fun setupWeightTrackInsertion(title: String) {
        var programId = 0

        when (title) {
            "Maintain Weight" -> programId = 1
            "Loss Weight" -> programId = 2
            "Gain Weight" -> programId = 3
        }

        val currenttimemillis = System.currentTimeMillis()
        val todayInTimestampPlus10 = currenttimemillis + (5 * 1000)
        val today = Converters().dateFromTimestamp(currenttimemillis)
        val todayPlus10 = Converters().dateFromTimestamp(todayInTimestampPlus10)
//        val nextWeekInTimestamp = GeneralUtil.getDateNextWeek()
//        val nextWeek = Converters().fromTimestamp(nextWeekInTimestamp)

        viewModel.userId.observe(viewLifecycleOwner) { id ->
            val userWeightTrack =
                WeightTrack(0, programId, today, todayPlus10, 0, 0, today, id)
            viewModel.insertWeightTrack(userWeightTrack)
        }
    }

    private fun setupWeightEntryObservation() {
        val weightEntryDateList = mutableListOf<Long>()
        val weightEntryWeightList = mutableListOf<Int>()

        viewModel.weightEntryDate.observe(viewLifecycleOwner) { longDates ->
            weightEntryDateList.clear()
            weightEntryDateList.addAll(longDates)
        }

        viewModel.weightEntryWeight.observe(viewLifecycleOwner) { weights ->
            weightEntryWeightList.clear()
            weightEntryWeightList.addAll(weights)
        }

        binding.apply {
            if (weightEntryDateList.isEmpty()) {
                val currentDate = Converters().formatDate(Converters().fromTimestamp(System.currentTimeMillis()))
                subtitleTextView.text = currentDate + " - " + currentDate
            } else {
                val earliestDate = Converters().formatDate(Converters().fromTimestamp(weightEntryDateList.minOrNull()))
                val latestDate = Converters().formatDate(Converters().fromTimestamp(weightEntryDateList.maxOrNull()))

                subtitleTextView.text = earliestDate + " - " + latestDate
            }

            if (weightEntryWeightList.isEmpty()) {
                textViewStartingWeight.text = "0"
                textViewCurrentWeight.text = "0"
            } else {
                val startingWeight = weightEntryWeightList.minOrNull() ?: 0
                val currentWeight = weightEntryWeightList.maxOrNull() ?: 0

                binding.textViewStartingWeight.text = startingWeight.toString()
                binding.textViewCurrentWeight.text = currentWeight.toString()
            }
        }

        viewModel.weightEntries.observe(viewLifecycleOwner) { weightEntries ->
            val weightList = mutableListOf<Int>()
            val dateList = mutableListOf<String>()

            weightEntries.forEach { entry ->
                weightList.add(entry.weight)
                dateList.add(Converters().formatDateToString(entry.date))
            }
            binding.weightChart.setWeights(weightList)
            binding.weightChart.setDates(dateList)
        }

//        viewModel.weightTrack.observe(viewLifecycleOwner) { weightTrack ->
//            val startDate = Converters().formatDateToString(weightTrack.startDate)
//            val endDate = Converters().formatDateToString(weightTrack.endDate)
//            binding.subtitleTextView.text = startDate
//            binding.subtitleTextView2.text = endDate
//            binding.textViewCurrentWeight.text = weightTrack.startWeight.toString()
//            binding.textViewStartingWeight.text = weightTrack.endWeight.toString()
//        }
    }


    private fun setupUserDetailsObservation() {
        viewModel.userDetails.observe(viewLifecycleOwner) { details ->
            binding.WeightBurgerProgramKhususTextView.text = "Weight : " + details.weight?.toInt().toString()
            binding.heightBurgerProgramKhususTextView.text = "Height : " + details.height?.toInt().toString()
        }
        viewModel.userName.observe(viewLifecycleOwner) { user ->
            binding.LabelBurgerProgramKhususTextView.text = "Keep up the progress, " + user
        }
    }

//    <item>Maintain Weight</item>
//    <item>Loss Weight</item>
//    <item>Gain Weight</item>

    private fun restoreProgramState() {
        viewModel.selectedProgram.value?.let { program ->
            val position = resources.getStringArray(R.array.program_choices).indexOf(program)
            binding.programSpinner.setSelection(if (position != -1) position else 0)
            changeFragmentView(program)  // Ensure UI is updated according to the restored program
        }
    }


    private fun changeFragmentView(program: String) {
        binding.programSpinner.visibility = View.VISIBLE

        // Setup the common edit weight functionality
        setupWeightEdit()

        // Additional settings based on the program
        when (program) {
            "Maintain Weight" -> setupMaintainWeightView()
            "Loss Weight" -> setupLossWeightView()
            "Gain Weight" -> setupGainWeightView()
        }
    }

    //huhah
    private fun setupWeightEdit() {
        val weightEntryDateList = mutableListOf<Long>()

        //setting supaya cuman bisa input 1 minggu
        viewModel.weightEntryDate.observe(viewLifecycleOwner) { longDates ->
            weightEntryDateList.clear()
            weightEntryDateList.addAll(longDates)
        }
        val latestDate = weightEntryDateList.maxOrNull()
        val today = System.currentTimeMillis()
        val timeBetweenWeightInputs = latestDate?.minus(today)
        Log.d("timeBetweenWeightInputs", timeBetweenWeightInputs.toString())

        if (timeBetweenWeightInputs != null) {
            if (timeBetweenWeightInputs >= 604800000) {
                binding.iconeditweightImageView.isEnabled = true
                binding.iconeditweightImageView.visibility = View.VISIBLE
            } else {
                binding.iconeditweightImageView.isEnabled = false
                binding.iconeditweightImageView.visibility = View.GONE
            }
        }

        binding.iconeditweightImageView.setOnClickListener {

            val editText = EditText(context).apply {
                inputType = InputType.TYPE_CLASS_NUMBER
                setText(binding.textViewCurrentWeight.text.toString())
            }

            AlertDialog.Builder(requireContext()).apply {
                setTitle("Update Current Weight")
                setMessage("Enter the new weight:")
                setView(editText)
                setPositiveButton("OK") { dialog, _ ->
                    val newWeight = editText.text.toString()
                    if (newWeight.isNotEmpty()) {
                        binding.textViewCurrentWeight.text = newWeight
                    }

                    val today = Converters().dateFromTimestamp(System.currentTimeMillis())
                    val weightEntry = viewModel.userId.value?.let { id ->
                        WeightEntry(0, newWeight.toInt(), today, id)
                    }

                    if (weightEntry != null) {
                        viewModel.insertWeightEntry(weightEntry)
                        it.isEnabled = false
                        it.visibility = View.GONE
                    }

                    dialog.dismiss()
                }
                setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
                show()
            }
        }
    }

    // Define separate methods for each program logic if needed
    //semua ini buat yang atur2 kalori
    private fun setupMaintainWeightView() {
        // Specific logic for Maintain Weight
    }

    private fun setupLossWeightView() {
        // Specific logic for Loss Weight
    }

    private fun setupGainWeightView() {
        // Specific logic for Gain Weight
    }

    private fun sendNotification(title: String, message: String) {
        val notificationManager =
            requireContext().getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        val notificationChannelId = "button_enable_channel"

        // Check for Android Oreo and newer versions to create a notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                notificationChannelId,
                "Button Enable",
                NotificationManager.IMPORTANCE_DEFAULT
            )
            notificationManager.createNotificationChannel(channel)
        }

        val notificationBuilder = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationCompat.Builder(requireContext(), notificationChannelId)
        } else {
            NotificationCompat.Builder(requireContext())
        }

        notificationBuilder.setSmallIcon(R.drawable.iconcd)
            .setContentTitle(title)
            .setContentText(message)
            .setAutoCancel(true)

        notificationManager.notify(1, notificationBuilder.build())
    }


}

