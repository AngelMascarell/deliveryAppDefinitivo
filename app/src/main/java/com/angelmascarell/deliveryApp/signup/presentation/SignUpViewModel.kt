import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.angelmascarell.deliveryApp.core.routes.Routes
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException
import java.lang.Exception
import java.util.regex.Pattern

class SignUpViewModel : ViewModel() {

    private val _emailOrPhoneState = MutableLiveData("")
    val emailOrPhoneState: LiveData<String> get() = _emailOrPhoneState

    private val _usernameState = MutableLiveData("")
    val usernameState: LiveData<String> get() = _usernameState

    private val _fullNameState = MutableLiveData("")
    val fullNameState: LiveData<String> get() = _fullNameState

    private val _passwordState = MutableLiveData("")
    val passwordState: LiveData<String> get() = _passwordState

    private val _isSignUpButtonEnabled = MutableLiveData(false)
    val isSignUpButtonEnabled: LiveData<Boolean> get() = _isSignUpButtonEnabled

    private val _isLoading = MutableLiveData(false)
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _idState = MutableLiveData(0)
    val idState: LiveData<Int> get() = _idState


    fun onEmailOrPhoneChanged(emailOrPhone: String) {
        _emailOrPhoneState.value = emailOrPhone
        updateSignUpButtonState()
    }

    fun onUsernameChanged(username: String) {
        _usernameState.value = username
        updateSignUpButtonState()
    }

    fun onFullNameChanged(fullName: String) {
        _fullNameState.value = fullName
        updateSignUpButtonState()
    }

    fun onPasswordChanged(password: String) {
        _passwordState.value = password
        updateSignUpButtonState()
    }

    private fun updateSignUpButtonState() {
        val isSignUpButtonEnabled =
            _emailOrPhoneState.value!!.isNotBlank() &&
                    _usernameState.value!!.isNotBlank() &&
                    _fullNameState.value!!.isNotBlank() &&
                    _passwordState.value!!.isNotBlank() &&
                    passwordMeetsTheRules()

        _isSignUpButtonEnabled.value = isSignUpButtonEnabled
    }

    private fun passwordMeetsTheRules(): Boolean {
        val passwordPattern = Pattern.compile("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}\$")
        return passwordPattern.matcher(_passwordState.value!!).matches()
    }

    private fun validateInput(): Boolean {
        return (
                _emailOrPhoneState.value!!.isNotBlank() &&
                        _usernameState.value!!.isNotBlank() &&
                        _fullNameState.value!!.isNotBlank() &&
                        _passwordState.value!!.isNotBlank() &&
                        passwordMeetsTheRules()
                )
    }

    fun onSignUpButtonClicked(navController: NavController, id: Int, username: String) {
        viewModelScope.launch {
            try {
                //val result = yourApiService.signUp(
                //  _emailOrPhoneState.value,
                // _usernameState.value,
                // _fullNameState.value,
                // _passwordState.value
                // )

                // if (result.isSuccessful) {
                //navController.navigate(Routes.HomeScreen.createRoute(id, username))
                navController.navigate(Routes.HomeScreen.route)
                //} else {
                //  println("Error en el registro: ${result.message()}")
                // }

            } catch (e: IOException) {
                println("Error de red: ${e.message}")

            } catch (e: HttpException) {
                println("Error HTTP: ${e.message}")
            } finally {
                _isLoading.value = false
            }
        }
    }


    private fun simulateSignUp() {

    }
}


