package com.telyu.nourimate.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telyu.nourimate.data.repository.NourimateRepository
import com.telyu.nourimate.utils.Injection

class ViewModelFactory private constructor(
    private val repository: NourimateRepository,
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(SignUpViewModel::class.java) -> {
                SignUpViewModel(repository) as T
            }

            modelClass.isAssignableFrom(LoginViewModel::class.java) -> {
                LoginViewModel(repository) as T
            }

            modelClass.isAssignableFrom(ProfileViewModel::class.java) -> {
                ProfileViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> {
                RecipeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(EditProfileViewModel::class.java) -> {
                EditProfileViewModel(repository) as T
            }

            modelClass.isAssignableFrom(UserDetailViewModel::class.java) -> {
                UserDetailViewModel(repository) as T
            }

            modelClass.isAssignableFrom(EditProfpicViewModel::class.java) -> {
                EditProfpicViewModel(repository) as T
            }

            modelClass.isAssignableFrom(MachineLearningViewModel::class.java) -> {
                MachineLearningViewModel(repository) as T
            }

            modelClass.isAssignableFrom(HomeViewModel::class.java) -> {
                HomeViewModel(repository) as T
            }

            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(
                        Injection.provideRepository(context),
                    )
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

}