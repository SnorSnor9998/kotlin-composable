package view

import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.dp
import composable.OutlineTextField
import composable.WTButton

@Composable
fun LoginView(navController: MutableState<Int>) {


  val window = LocalAppWindow.current
  window.setTitle("Login")
  window.setSize(350,700)
  window.setWindowCentered()


  val email = remember { mutableStateOf("") }
  val password = remember { mutableStateOf("") }


  Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {

    Box(
      modifier = Modifier
        .fillMaxSize()
        .paddingFromBaseline(100.dp, 100.dp)
        .background(Color.Transparent),
      contentAlignment = Alignment.Center
    ) {

      val image = imageFromResource("wetixlogo.png")
      Image(image, "")

    }


    Column(
      modifier = Modifier.padding(8.dp,0.dp)
    ){


      OutlineTextField(email, "Email Address", false)

      OutlineTextField(password, "Password", true)

      Spacer(modifier = Modifier.padding(4.dp))

      WTButton("Login", modifier = Modifier
        .sizeIn(minHeight = 50.dp)
        .fillMaxWidth()
        .padding(0.dp, 4.dp),
        onClick = {

          if (email.value == "123" && password.value == "123") {
            navController.value = Config.Navigate.BOOKING
          }

        }, false
      )


    }

  }

}

