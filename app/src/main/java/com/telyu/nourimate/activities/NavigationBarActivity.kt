package com.telyu.nourimate.activities

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import com.telyu.nourimate.fragments.ProfileFragment
import com.telyu.nourimate.fragments.ProgramFragment
import com.telyu.nourimate.R
import com.telyu.nourimate.databinding.ActivityNavigationBarBinding
import com.telyu.nourimate.fragments.RecipeFragment
import com.telyu.nourimate.fragments.HomeFragment
import com.telyu.nourimate.fragments.HomeMealHistoryFragment
import com.telyu.nourimate.fragments.ProgramEmptyFragment

class NavigationBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBarBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan View Binding untuk menginflate layout
        binding = ActivityNavigationBarBinding.inflate(layoutInflater)
        setStatusBarColor(resources.getColor(R.color.color16, theme))
        setContentView(binding.root)

        if (intent.hasExtra("openFragment")) {
            when (intent.getStringExtra("openFragment")) {
                "HomeMealHistoryFragment" -> setCurrentFragment(HomeMealHistoryFragment())
                "ProgramEmptyFragment" -> setCurrentFragment(ProgramFragment())
            }
        } else {
            // Fragment default ketika aplikasi dibuka
            setCurrentFragment(HomeFragment())
        }

        setupBottomNavigationView()

    }

    private fun setupBottomNavigationView() {
        binding.bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_home -> setCurrentFragment(HomeFragment())
                R.id.nav_recipe -> setCurrentFragment(RecipeFragment())
                R.id.nav_program -> setCurrentFragment(ProgramFragment())
                R.id.nav_profile -> setCurrentFragment(ProfileFragment())
            }
            true
        }
    }


    private fun setStatusBarColor(color: Int) {
        val window = window
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)

        // Set to 'true' to ensure status bar icons are dark, useful for light status bar backgrounds
        insetsController.isAppearanceLightStatusBars = true
        // Set to 'true' to ensure navigation bar icons are dark, useful for light navigation bar backgrounds
        insetsController.isAppearanceLightNavigationBars = true

        // Set the status bar color
        window.statusBarColor = color
    }

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            addToBackStack(null)  // Menambahkan transaksi ini ke back stack
            commit()
        }
    }



    override fun onBackPressed() {
        super.onBackPressed()
        val alertDialogBuilder = AlertDialog.Builder(this)
        alertDialogBuilder.setTitle("Exit App")
        alertDialogBuilder.setMessage("Are you sure you want to exit the app?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            finishAffinity()
        }
        alertDialogBuilder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }
        alertDialogBuilder.show()
    }

}