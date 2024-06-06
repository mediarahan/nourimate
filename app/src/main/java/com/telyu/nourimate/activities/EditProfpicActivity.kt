package com.telyu.nourimate.activities

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.R
import com.telyu.nourimate.data.local.models.Profpic
import com.telyu.nourimate.databinding.ActivityEditProfpicBinding
import com.telyu.nourimate.viewmodels.EditProfpicViewModel
import com.telyu.nourimate.viewmodels.ViewModelFactory
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream

class EditProfpicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfpicBinding
    private lateinit var viewModel: EditProfpicViewModel
    private var currentImageUri: Uri? = null

    private val launcherGallery = registerForActivityResult(
        ActivityResultContracts.PickVisualMedia()
    ) { uri: Uri? ->
        uri?.let {
            val internalUri = copyImageToInternalStorage(it)
            currentImageUri = internalUri
        } ?: Log.d("Photo Picker", "No media selected")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfpicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setStatusBarColor(resources.getColor(R.color.color47, theme))

        viewModel = obtainViewModel(this@EditProfpicActivity)


        val imageUriString = intent.getStringExtra("imageUri")
        imageUriString?.let {
            val imageUri = Uri.parse(it)
            binding.imageToCrop.setImageURI(imageUri)
        }


        binding.buttonSaveCrop.setOnClickListener {
            saveCroppedImage()
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

    private fun saveCroppedImage() {
        binding.imageToCrop.buildDrawingCache()
        val croppedBitmap = Bitmap.createBitmap(binding.imageToCrop.drawingCache)

        try {
            // Save to storage
            val file = File(getExternalFilesDir(null), "cropped_image.jpg")
            val fOut = FileOutputStream(file)
            croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut)
            fOut.flush()
            fOut.close()

            // Update the URI to new saved image
            currentImageUri = Uri.fromFile(file)

            // Insert or update the profile picture in the database
            insertProfpic()

            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show()

            // Navigate back to ProfileFragment
            navigateBackWithResult()

        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error Saving Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun navigateBackWithResult() {
        val intent = Intent()
        intent.putExtra("imageUri", currentImageUri.toString())
        setResult(Activity.RESULT_OK, intent)
        finish()
    }




    private fun copyImageToInternalStorage(uri: Uri): Uri {
        val inputStream = contentResolver.openInputStream(uri) ?: return Uri.EMPTY
        val file = createCustomTempFile()
        val outputStream = FileOutputStream(file)
        inputStream.copyTo(outputStream)
        inputStream.close()
        outputStream.close()
        return Uri.fromFile(file)
    }

    private fun createCustomTempFile(): File {
        val storageDir = getExternalFilesDir(null)
        return File.createTempFile("profpic_", ".jpg", storageDir)
    }


    private fun insertProfpic() {
        currentImageUri?.let { uri ->
            viewModel.userEmail.observe(this) { userEmail ->
                viewModel.getUserIdByEmail(userEmail)
            }
            viewModel.userId.observe(this) { userId ->
                val profpic = Profpic(userId, uri.toString())
                viewModel.insertProfpic(profpic)
            }
        } ?: Log.e("EditProfpicActivity", "No image selected")
    }

    private fun obtainViewModel(activity: AppCompatActivity): EditProfpicViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[EditProfpicViewModel::class.java]
    }
}
