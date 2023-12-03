package com.angelmascarell.deliveryApp.signin.presentation

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.angelmascarell.deliveryApp.R
import com.angelmascarell.deliveryApp.core.routes.Routes
import com.angelmascarell.deliveryApp.signin.presentation.composables.BasicTextField
import com.angelmascarell.deliveryApp.signin.presentation.composables.CircularButton
import com.angelmascarell.deliveryApp.signin.presentation.composables.PasswordTextField
import com.angelmascarell.deliveryApp.signin.presentation.composables.RoundedButton
import com.angelmascarell.deliveryApp.ui.theme.RedApp
import com.angelmascarell.deliveryApp.ui.theme.GrayApp

@Composable
fun SignInScreen(navController: NavHostController, signInViewModel: SignInViewModel) {
    val isLoading: Boolean by signInViewModel.isLoading.observeAsState(initial = false)

    if (isLoading) {
        Loading()
    } else {
        View(navController, signInViewModel)
    }
}

@Composable
fun Loading() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
    }
}

@Composable
fun View(navController: NavHostController, signInViewModel: SignInViewModel) {
    ConstraintLayout(
        modifier = Modifier
            .padding(horizontal = 30.dp, vertical = 15.dp)
            .fillMaxSize(),
    ) {
        val (header, body, footer) = createRefs()
        val topGuide = createGuidelineFromTop(0.38f)

        Header(modifier = Modifier.constrainAs(header) {
            top.linkTo(parent.top)
        })
        Body(modifier = Modifier.constrainAs(body) {
            top.linkTo(topGuide)
        }, signInViewModel = signInViewModel, navController)
        Footer(modifier = Modifier.constrainAs(footer) {
            bottom.linkTo(parent.bottom)
        }, navController)
    }
}

@Composable
fun Header(modifier: Modifier) {
    val activity = LocalContext.current as Activity
    Box(modifier.fillMaxWidth()) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = stringResource(id = R.string.close_app),
            modifier = Modifier
                .align(Alignment.TopEnd)
                .clickable { activity.finish() },
            tint = RedApp
        )
        ImageLogo(Modifier.align(Alignment.TopCenter))
    }

}

@Composable
fun ImageLogo(modifier: Modifier) {
    Image(
        painter = painterResource(id = R.drawable.icon_user),
        contentScale = ContentScale.Crop,
        contentDescription = stringResource(id = R.string.app_name),
        modifier = modifier
            .size(110.dp)
            .clip(CircleShape)
        //.offset(y = 10.dp)
    )
}


@Composable
fun Body(modifier: Modifier, signInViewModel: SignInViewModel, navController: NavHostController) {
    val emailOrPhone: String by signInViewModel.emailOrPhone.observeAsState(initial = "")
    val password: String by signInViewModel.password.observeAsState(initial = "")
    val isSignInButtonEnable: Boolean
            by signInViewModel.isSignInButtonEnable.observeAsState(initial = false)

    Column(modifier = modifier.fillMaxWidth()) {
        GreenTextBackground(text = "Inicio de sesión")
        Spacer(modifier = Modifier.size(16.dp))
        PhoneOrEmailInputText(emailOrPhone) {
            signInViewModel.onSignInChanged(emailOrPhone = it, password = password)
        }
        Spacer(modifier = Modifier.size(16.dp))
        PasswordInputText(password) {
            signInViewModel.onSignInChanged(emailOrPhone = emailOrPhone, password = it)
        }
        Spacer(modifier = Modifier.size(16.dp))
        //ForgotPassword(Modifier.align(Alignment.End))
        Spacer(modifier = Modifier.size(16.dp))
        SignInButton(isSignInButtonEnable, signInViewModel, navController)
        Spacer(modifier = Modifier.size(32.dp))
        OauthButtons(signInViewModel = signInViewModel)
    }
}

@Composable
fun GreenTextBackground(text: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset(y = (-35).dp),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(40.dp)
                .background(
                    color = GrayApp,
                    shape = RoundedCornerShape(8.dp)
                )
                .border(1.dp, RedApp, shape = RoundedCornerShape(8.dp)),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = text,
                color = RedApp
            )
        }
    }
}

@Composable
fun PhoneOrEmailInputText(value: String, onTextChanged: (String) -> Unit) {
    BasicTextField(
        value = value,
        placeholder = stringResource(id = R.string.user),
        label = stringResource(id = R.string.email_or_phone),
        onTextChanged = onTextChanged
    )
}

@Composable
fun PasswordInputText(password: String, onTextChanged: (String) -> Unit) {
    PasswordTextField(
        password = password,
        onTextChanged = onTextChanged
    )
}

@Composable
fun SignInButton(
    loginEnable: Boolean,
    signInViewModel: SignInViewModel,
    navController: NavHostController
) {
    RoundedButton(
        text = stringResource(id = R.string.login),
        onClick = {
            //signInViewModel.onSignInButtonCLicked()
            navController.navigate(Routes.HomeScreen.route)
        },
        enabled = loginEnable
    )
}


@Composable
fun OauthButtons(signInViewModel: SignInViewModel) {
    Row(horizontalArrangement = Arrangement.SpaceAround, modifier = Modifier.fillMaxSize()) {
        CircularButton(
            icon = painterResource(id = R.drawable.ic_facebook),
            text = "Facebook",
            color = Color.Blue
        ) {

        }
        CircularButton(
            icon = painterResource(id = R.drawable.ic_google),
            text = "Google",
            color = Color.Red
        ) {

        }
        CircularButton(
            icon = painterResource(id = R.drawable.ic_github),
            text = "Github",
            color = Color.Black
        ) {

        }
    }
}

@Composable
fun Footer(modifier: Modifier, navController: NavHostController) {
    Column(modifier = modifier.fillMaxWidth()) {
        SignUpButton(navController)
    }
}

@Composable
fun SignUpButton(navController: NavHostController) {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        Text(
            text = stringResource(id = R.string.sign_up_question),
            fontSize = 16.sp,
            color = RedApp
        )
        Text(
            text = stringResource(id = R.string.sign_up),
            Modifier
                .padding(horizontal = 8.dp)
                .clickable { navController.navigate(Routes.SignUpScreen.route) },
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = RedApp,
        )
    }
}