package com.telyu.nourimate.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Recommendation
import com.telyu.nourimate.databinding.ActivityMachineLearningBinding
import com.telyu.nourimate.viewmodels.MachineLearningViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.util.Date

class MachineLearningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMachineLearningBinding
    private lateinit var viewModel: MachineLearningViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMachineLearningBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@MachineLearningActivity)

//        viewModel.allRecipeNames.observe(this) { recipeNames ->
//            val spinnerAdapter = ArrayAdapter(this, R.layout.spinner_nourimate, recipeNames)
//            spinnerAdapter.setDropDownViewResource(R.layout.spinner_nourimate)
//            binding.spinnerRecipe.adapter = spinnerAdapter
//        }
//
//        binding.submitButton.setOnClickListener {
//            insertRecommendationAndCrossRef()
//            val intent = Intent(this, NavigationBarActivity::class.java)
//            startActivity(intent)
//        }

    }

//    private fun insertRecommendationAndCrossRef() {
//        val recommendation = Recommendation(0, Date(), false)
//        //DAPETIN VALUE ID UNTUK MEAL
//        val mealType =
//            binding.radiomealtime.findViewById<RadioButton>(binding.radiomealtime.checkedRadioButtonId)?.text?.toString()
//
//        if (mealType != null) {
//            viewModel.getMealIdByName(mealType)
//        }
//
//        //DAPETIN VALUE ID UNTUK RECIPE
//        binding.spinnerRecipe.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(
//                parent: AdapterView<*>?,
//                view: View?,
//                position: Int,
//                id: Long
//            ) {
//                val selectedRecipeName = parent?.getItemAtPosition(position).toString()
//                viewModel.getRecipeIdByName(selectedRecipeName)
//            }
//
//            override fun onNothingSelected(parent: AdapterView<*>?) {
//                // Do nothing or handle the case when nothing is selected
//            }
//        }
//
//        viewModel.insertRecommendation(recommendation)
//    }

    private fun obtainViewModel(activity: AppCompatActivity): MachineLearningViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MachineLearningViewModel::class.java]
    }

}