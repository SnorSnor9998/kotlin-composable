package view.Pos

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composable.ProductList

@Composable
fun PosHost(){

  Row(
    modifier = Modifier
      .fillMaxWidth()
      .fillMaxHeight()

  ) {


    Column(modifier = Modifier.weight(1f).fillMaxHeight().fillMaxWidth()) {
        ProductList()
    }

    Divider(Modifier.fillMaxHeight().width(2.dp))

    Column(modifier = Modifier.weight(1f).fillMaxHeight().fillMaxWidth()) {

    }



  }






}

