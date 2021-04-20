package view.ProductMgn

import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.dp
import composable.OutlineTextField
import composable.WTButton


fun ProductDialog(


  isEdit : Boolean
) = Window{

  val window = LocalAppWindow.current
  window.setSize(350,700)
  window.setWindowCentered()


  if (isEdit){
    window.setTitle("Edit Product")
  }else{
    window.setTitle("Create Product")
  }



  val productId = remember { mutableStateOf("") }
  val productName = remember { mutableStateOf("") }
  val productPrice = remember { mutableStateOf("") }



  Box(modifier = Modifier.fillMaxSize(),contentAlignment = Alignment.Center) {

    Column(
      modifier = Modifier.padding(8.dp,0.dp).fillMaxHeight()
    ){


      OutlineTextField(productId, "Product ID", false)

      OutlineTextField(productName, "Product Name", false)

      OutlineTextField(productPrice, "Product Price", false)

      Spacer(modifier = Modifier.padding(4.dp))

      WTButton("Create", modifier = Modifier
        .sizeIn(minHeight = 50.dp)
        .fillMaxWidth()
        .padding(0.dp, 4.dp),
        onClick = {


        }, false
      )


    }

  }


}