package com.telyu.nourimate.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.dao.UserDao
import com.telyu.nourimate.data.local.db.UserDatabase
import kotlinx.coroutines.launch

class DebugActivity : AppCompatActivity() {

    private lateinit var dao: UserDao
    private lateinit var deleteAllButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_debug)

        dao = UserDatabase.getInstance(this).userDao()

        deleteAllButton = findViewById(R.id.ButtonDeleteAllUserDb)

        deleteAllButton.setOnClickListener {
            deleteAllRecords()
        }
    }

    private fun deleteAllRecords() {
        lifecycleScope.launch {
            dao.deleteAllRecords()
        }
    }
}