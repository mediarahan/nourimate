package com.telyu.nourimate.activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.UserDatabase
import com.telyu.nourimate.data.local.models.WeightEntry
import com.telyu.nourimate.data.local.models.WeightTrack
import com.telyu.nourimate.utils.Converters
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        val bmw = findViewById<View>(R.id.bmw)
        val blw = findViewById<View>(R.id.blw)
        val bgw = findViewById<View>(R.id.bgw)

        val db = UserDatabase.getInstance(this).userDao()
        val pref = UserPreference.getInstance(dataStore)

//        val weightEntries = listOf(
//            WeightEntry(weight = 70, date = Converters().fromStringToDate("2024-06-11")!!, userId)
//        )


//        bmw.setOnClickListener {
//            CoroutineScope(Dispatchers.Main).launch {
//                db.insertWeightEntry()
//            }
//        }

        blw.setOnClickListener {

        }

        bgw.setOnClickListener {

        }
    }

    init {
        CoroutineScope(Dispatchers.Main).launch {
           // val userId = pref.getUserId().first()
        }
    }

}