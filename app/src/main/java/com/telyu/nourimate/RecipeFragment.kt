// RecipeFragment.kt
package com.telyu.nourimate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.databinding.FragmentRecipeBinding

class RecipeFragment : Fragment() {

    private lateinit var viewModel: RecipeViewModel
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!

    private var dailyOrWeeklyClicked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.btnDaily.setOnClickListener {
            dailyOrWeeklyClicked = false
            // Handle Daily button click
        }

        binding.btnWeekly.setOnClickListener {
            dailyOrWeeklyClicked = true
            // Handle Weekly button click
        }

        binding.btnBreakfast.setOnClickListener {
            if (dailyOrWeeklyClicked) {
            } else {
                // Show a message or take appropriate action indicating that Daily or Weekly should be clicked first
            }
        }

        binding.btnLunch.setOnClickListener {
            if (dailyOrWeeklyClicked) {
            } else {
                // Show a message or take appropriate action indicating that Daily or Weekly should be clicked first
            }
        }

        binding.btnDinner.setOnClickListener {
            if (dailyOrWeeklyClicked) {
            } else {
                // Show a message or take appropriate action indicating that Daily or Weekly should be clicked first
            }
        }

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        viewModel.greetingMessage.observe(viewLifecycleOwner, Observer {
            binding.recipeGreetingTextView.text = "Hello, $it"
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
