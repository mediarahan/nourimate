package com.telyu.nourimate.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.databinding.ActivityEditProfpicBinding
import com.telyu.nourimate.fragments.ProfileFragment
import com.telyu.nourimate.viewmodels.EditProfpicViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory

class EditProfpicActivity : AppCompatActivity() {

    private var currentImageUri: Uri? = null
    private lateinit var binding: ActivityEditProfpicBinding
    private lateinit var viewModel: EditProfpicViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfpicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = obtainViewModel(this@EditProfpicActivity)

        binding.galleryButton.setOnClickListener {
            startGallery()
        }

        binding.uploadButton.setOnClickListener {
            insertProfpic()
            val intent = Intent(this, NavigationBarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
    }

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        if (uri != null) {
            currentImageUri = uri
            showImage()
        } else {
            Log.d("Photo Picker", "No media selected")
        }
    }

    private fun showImage() {
        currentImageUri?.let {
            binding.previewImageView.setImageURI(it)
        }
    }

    private fun insertProfpic() {
        viewModel.userEmail.observe(this) {userEmail ->
            viewModel.getUserIdByEmail(userEmail)
        }

        viewModel.userId.observe(this) {userId ->
            val profpic = Profpic(userId,currentImageUri.toString())
            viewModel.insertProfpic(profpic)
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): EditProfpicViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[EditProfpicViewModel::class.java]
    }

}