package com.angelmascarell.deliveryApp.signup.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Password
import androidx.compose.material.icons.filled.PhoneAndroid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import com.angelmascarell.deliveryApp.R
import com.angelmascarell.deliveryApp.core.routes.Routes
import com.angelmascarell.deliveryApp.signup.presentation.composables.BasicTextField
import com.angelmascarell.deliveryApp.signup.presentation.composables.PasswordTextField
import com.angelmascarell.deliveryApp.signup.presentation.composables.RoundedButton
import com.angelmascarell.deliveryApp.ui.theme.RedApp
import com.angelmascarell.deliveryApp.ui.theme.TitleTextStyle
import SignUpViewModel
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun SignUpScreen(navController: NavHostController, signUpViewModel: SignUpViewModel) {

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp, vertical = 15.dp)
    ) {
        val (header, body, footer) = createRefs()

        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
            bottom.linkTo(body.top)
        })
        Body(modifier = Modifier.constrainAs(body) {
            top.linkTo(header.bottom)
            bottom.linkTo(footer.top)
        }, navController, signUpViewModel)
        Footer(modifier = Modifier.constrainAs(footer) {
            top.linkTo(body.bottom)
            bottom.linkTo(parent.bottom)
        }, navController, signUpViewModel)

        createVerticalChain(header, body, footer, chainStyle = ChainStyle.SpreadInside)
    }
}

@Composable
fun Header(modifier: Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        ImageLogo()
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.welcome),
            style = TitleTextStyle,
            fontSize = 20.sp
        )
    }
}

@Composable
fun ImageLogo() {
    Image(
        painter = painterResource(id = R.drawable.tastylogo),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = Modifier
            .size(150.dp)
            .clip(CircleShape)
    )
}

@Composable
fun Body(modifier: Modifier, navController: NavHostController, signUpViewModel: SignUpViewModel) {
    val isLoginEnable: Boolean = signUpViewModel.isSignUpButtonEnabled.value!!
    val id: Int by signUpViewModel.idState.observeAsState(initial = 0)
    val username: String by signUpViewModel.usernameState.observeAsState(initial = "")
    val emailOrPhone: String by signUpViewModel.emailOrPhoneState.observeAsState(initial = "")
    val fullName: String by signUpViewModel.fullNameState.observeAsState(initial = "")
    val password: String by signUpViewModel.passwordState.observeAsState(initial = "")


    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        PhoneOrEmailTextField(
            value = emailOrPhone,
            onTextChanged = { signUpViewModel.onEmailOrPhoneChanged(it) }
        )
        UserTextField(
            value = username,
            onTextChanged = { signUpViewModel.onUsernameChanged(it) }
        )
        FullNameTextField(
            value = fullName,
            onTextChanged = { signUpViewModel.onFullNameChanged(it) }
        )
        KeyWordTextField(
            password = password,
            onTextChanged = { signUpViewModel.onPasswordChanged(it) }
        )
        Spacer(modifier = Modifier.height(4.dp))
        SignUpButton(navController, id, username, isLoginEnable, signUpViewModel)
    }
}

@Composable
fun PhoneOrEmailTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.user),
        label = stringResource(id = R.string.email_or_phone),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.PhoneAndroid
    )
}

@Composable
fun UserTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.username),
        label = stringResource(id = R.string.username),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.AccountCircle
    )
}

@Composable
fun FullNameTextField(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.full_name),
        label = stringResource(id = R.string.name_and_surname),
        onTextChanged = onTextChanged,
        imageVector = Icons.Filled.Face
    )
}

@Composable
fun KeyWordTextField(password: String, onTextChanged: (String) -> Unit) {
    PasswordTextField(
        password = password,
        imageVector = Icons.Filled.Password,
        onTextChanged = onTextChanged
    )
}

@Composable
fun SignUpButton(
    navController: NavHostController,
    id: Int,
    username: String,
    loginEnable: Boolean,
    signUpViewModel: SignUpViewModel
) {
    RoundedButton(
        text = stringResource(id = R.string.register),
        onClick = {
            //signUpViewModel.onSignUpButtonClicked(navController, id, username)
            navController.navigate(Routes.HomeScreen.route)
        },
        enabled = true
    )
}


@Composable
fun Footer(modifier: Modifier, navController: NavController, signUpViewModel: SignUpViewModel) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SignInButton(navController)
    }
}

@Composable
fun SignInButton(navController: NavController) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = stringResource(id = R.string.sign_in_question),
            fontSize = 16.sp,
            color = RedApp
        )
        Text(
            text = stringResource(id = R.string.sign_in),
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Routes.SignInScreen.route) },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RedApp,
        )
    }
}