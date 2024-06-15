package com.telyu.nourimate.activities

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.telyu.nourimate.fragments.ProfileFragment
import com.telyu.nourimate.fragments.ProgramFragment
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityNavigationBarBinding
import com.telyu.nourimate.fragments.HistoryFragment
import com.telyu.nourimate.fragments.RecipeFragment
import com.telyu.nourimate.fragments.HomeFragment
import com.telyu.nourimate.fragments.HomeMealHistoryFragment

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

        if (intent.action == "ACTION_SHOW_MEAL_HISTORY") {
            val selectedMeal = intent.getIntExtra("selectedMeal", -1)
            if (selectedMeal != -1) {
                displayMealHistoryFragment(selectedMeal)
            } else {
                setCurrentFragment(HomeFragment())  // Ensure default home fragment if no meal selected
            }
        } else {
            handleDefaultFragmentLoading()
        }

        setupBottomNavigationView()
    }

    private fun handleDefaultFragmentLoading() {
        val loadFragment = intent.getStringExtra("loadFragment")
        when (loadFragment) {
            "HistoryFragment" -> setCurrentFragment(HistoryFragment())
            else -> setCurrentFragment(HomeFragment())
        }
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
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit the app?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.setCancelable(false)
        alertDialogBuilder.show()
    }

}
