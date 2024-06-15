package com.telyu.nourimate.fragments

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.DialogFragment
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.FragmentInfoNutritionDialogBinding

class InfoNutritionDialogFragment : DialogFragment() {

    private var _binding: FragmentInfoNutritionDialogBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInfoNutritionDialogBinding.inflate(inflater, container, false)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        val nutrition = arguments?.getString("nutrientType")
        val statusString = arguments?.getString("status")
        val status = HomeFragment.UserNutritionStatus.valueOf(statusString ?: "NORMAL")
        Log.d("InfoNutritionDialog", "Nutrient: $nutrition, Status: $status")

        displayNutrientMessages(nutrition, status)

        binding.buttonSave.setOnClickListener {
            dismiss()
        }

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.apply {
            val params: WindowManager.LayoutParams = attributes
            params.width = ViewGroup.LayoutParams.MATCH_PARENT
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            params.gravity = Gravity.CENTER
            attributes = params
        }

        setWidthPercent(85)
    }

    private fun displayNutrientMessages(nutrientType: String?, status: HomeFragment.UserNutritionStatus?) {
        val message = when (nutrientType) {
            "Calories" -> {
                when (status) {
                    HomeFragment.UserNutritionStatus.EXCESS -> getString(R.string.excess_calories)
                    HomeFragment.UserNutritionStatus.DEFICIT -> getString(R.string.calories_deficiency)
                    else -> "Caloric intake is within normal limits."
                }
            }
            "Protein" -> {
                when (status) {
                    HomeFragment.UserNutritionStatus.EXCESS -> getString(R.string.excess_protein)
                    HomeFragment.UserNutritionStatus.DEFICIT -> getString(R.string.protein_deficiency)
                    else -> "Protein intake is within normal limits."
                }
            }
            "Fat" -> {
                when (status) {
                    HomeFragment.UserNutritionStatus.EXCESS -> getString(R.string.excess_fat)
                    HomeFragment.UserNutritionStatus.DEFICIT -> getString(R.string.fat_deficiency)
                    else -> "Fat intake is within normal limits."
                }
            }
            "Carbs" -> {
                when (status) {
                    HomeFragment.UserNutritionStatus.EXCESS -> getString(R.string.excess_carbs)
                    HomeFragment.UserNutritionStatus.DEFICIT -> getString(R.string.carbs_deficiency)
                    else -> "Carbohydrate intake is within normal limits."
                }
            }
            "Water" -> {
                when (status) {
                    HomeFragment.UserNutritionStatus.EXCESS -> "You have consumed almost enough water for today"
                    HomeFragment.UserNutritionStatus.DEFICIT -> "Don't forget to drink your water!"
                    else -> "Water intake is within normal limits."
                }
            }
            else -> "Nutrient information is unavailable."

        }
        binding.textinfo.text = message
    }


    private fun setWidthPercent(percent: Int) {
        val width = (resources.displayMetrics.widthPixels * (percent / 100.0)).toInt()
        dialog?.window?.setLayout(width, WindowManager.LayoutParams.WRAP_CONTENT)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        fun newInstance(nutrientType: String, status: HomeFragment.UserNutritionStatus): InfoNutritionDialogFragment {
            val fragment = InfoNutritionDialogFragment()
            val args = Bundle()
            args.putString("nutrientType", nutrientType)
            args.putString("status", status.name)
            fragment.arguments = args
            return fragment
        }
    }

}