package com.telyu.nourimate.activities

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
import androidx.lifecycle.ViewModelProvider
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

        viewModel = obtainViewModel(this@EditProfpicActivity)


        val imageUri = Uri.parse(intent.getStringExtra("imageUri"))
        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver, imageUri)

        binding.imageToCrop.setImageBitmap(bitmap)
        binding.imageToCrop.setOnTouchListener { view, event ->
            // Contoh sederhana, sebenarnya Anda perlu membuat logika untuk memilih area crop
            true
        }

        binding.buttonSaveCrop.setOnClickListener {
            saveCroppedImage()
        }
    }

    private fun saveCroppedImage() {
        // Mendapatkan Bitmap dari ImageView
        binding.imageToCrop.buildDrawingCache()
        val croppedBitmap = Bitmap.createBitmap(binding.imageToCrop.drawingCache)

        try {
            // Simpan ke storage
            val file = File(getExternalFilesDir(null), "cropped_image.jpg")
            val fOut = FileOutputStream(file)
            croppedBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut)
            fOut.flush()
            fOut.close()

            Toast.makeText(this, "Image Saved Successfully", Toast.LENGTH_SHORT).show()
        } catch (e: Exception) {
            e.printStackTrace()
            Toast.makeText(this, "Error Saving Image", Toast.LENGTH_SHORT).show()
        }
    }

    private fun startGallery() {
        launcherGallery.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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
