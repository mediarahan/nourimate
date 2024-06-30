package com.telyu.nourimate.activities

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.telyu.nourimate.fragments.ProfileFragment
import com.telyu.nourimate.fragments.ProgramFragment
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityNavigationBarBinding
import com.telyu.nourimate.databinding.CustomLogoutDialogBinding
import com.telyu.nourimate.fragments.HistoryFragment
import com.telyu.nourimate.fragments.RecipeFragment
import com.telyu.nourimate.fragments.HomeFragment
import com.telyu.nourimate.fragments.HomeMealHistoryFragment
import com.telyu.nourimate.fragments.LogoutDialogFragment

class NavigationBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Using View Binding to inflate layout
        binding = ActivityNavigationBarBinding.inflate(layoutInflater)
        window.statusBarColor = ContextCompat.getColor(this, R.color.color16)
        setContentView(binding.root)

        // Check the action of the intent
        Log.d("intent", intent.action.toString())

        when (intent.action) {
            "ACTION_SHOW_MEAL_HISTORY" -> {
                val selectedMeal = intent.getIntExtra("selectedMeal", -1)
                if (selectedMeal != -1) {
//                   displayMealHistoryFragment(selectedMeal)
                    // pindah ke home
                    loadHomeMealFragment(selectedMeal)
                    // homeFragment.simulateButtonClickAnimation(SelectedMeal)
                } else {
                    setCurrentFragment(HomeFragment())  // Ensure default home fragment if no meal selected
                }
            }
            "ACTION_SHOW_HISTORY_TUTORIAL" -> {
                loadProfileFragment()
            }
            else -> {
                loadHomeFragment()
            }
        }

        setupBottomNavigationView()
    }

    private fun loadProfileFragment() {
        val profileFragment = ProfileFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
        transaction.replace(R.id.fragmentContainer, profileFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        // Set the selected item in the navigation bar
        binding.bottomNavigationView.selectedItemId = R.id.nav_profile

        // Delay execution to simulate button click after transition
        Handler(Looper.getMainLooper()).postDelayed({
            // Simulate button click to HistoryFragment with animation
            //loadHistoryFr
            profileFragment.simulateButtonClickAnimation()
        }, 300)  // Adjust time based on your animation duration
    }

    private fun loadHomeMealFragment(selectedMeal: Int) {
        val homeFragment = HomeFragment().apply {
            arguments = Bundle().apply {
                putInt("selectedMeal", selectedMeal)
            }
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.fade_in)
        transaction.replace(R.id.fragmentContainer, homeFragment)
        transaction.addToBackStack(null)
        transaction.commit()

        // Set the selected item in the navigation bar
        binding.bottomNavigationView.selectedItemId = R.id.nav_home

        // Delay execution to simulate button click after transition
        Handler(Looper.getMainLooper()).postDelayed({
            // Simulate button click to HomeMealFragment with animation
            homeFragment.simulateButtonClickAnimation(selectedMeal)
        }, 300)  // Adjust time based on your animation duration
    }



    private fun loadHomeFragment() {
        val homeFragment = HomeFragment()
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, homeFragment)
        transaction.commit()
    }



    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener { menuItem ->
            val fragment = when (menuItem.itemId) {
                R.id.nav_home -> HomeFragment()
                R.id.nav_recipe -> RecipeFragment()
                R.id.nav_program -> ProgramFragment()
                R.id.nav_profile -> ProfileFragment()
                else -> null
            }
            fragment?.let { setCurrentFragment(it) }
            true  // Always return true to denote the item selection was successful
        }
    }

    private fun displayMealHistoryFragment(mealTime: Int) {
        val fragment = HomeMealHistoryFragment().apply {
            arguments = Bundle().apply {
                putInt("mealTime", mealTime)
            }
        }
        setCurrentFragment(fragment)
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            showLogoutConfirmationDialog()
        }
    }

    private fun showLogoutConfirmationDialog() {
        val dialogFragment = LogoutDialogFragment()
        dialogFragment.show(supportFragmentManager, "logoutDialog")
    }
}