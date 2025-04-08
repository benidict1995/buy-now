package com.benidict.feature_signup.ui.details

import androidx.lifecycle.ViewModel
import com.benidict.common_utils.validation.isEmailValid
import com.benidict.common_utils.validation.isFieldsEmpty
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class UserDetailsFormViewModel @Inject constructor(): ViewModel() {

    fun isFormFieldsValid(email: String, firstName: String, lastName: String): Boolean {
        return isEmailValid(email) && isFieldsEmpty(listOf(firstName, lastName, email)).not()
    }
}