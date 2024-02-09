package com.telyu.nourimate

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.telyu.nourimate.databinding.ActivityNavigationBarBinding

class NavigationBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Menggunakan View Binding untuk menginflate layout
        binding = ActivityNavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val homeFragment = HomeFragment()
        setCurrentFragment(homeFragment)

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

    private fun setCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentContainer, fragment)
            commit()
        }
    }
}
