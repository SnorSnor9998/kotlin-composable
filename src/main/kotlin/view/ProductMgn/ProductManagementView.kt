package view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import composable.BookingHistoryList
import composable.ProductList
import composable.RoundButton
import view.ProductMgn.ProductDialog

@Composable
fun ProductManagementView() {

  Box(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth(1f)
  ) {


    Row {

      Column {

        Row(modifier = Modifier
          .fillMaxWidth()
          .fillMaxHeight(0.05f),
          verticalAlignment = Alignment.CenterVertically) {


            RoundButton("CREATE", onClick = {
                                            ProductDialog(false)
            },Modifier.padding(start = 16.dp))



        }


        Row(
          modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(1f)
        ) {

          val scroll = rememberScrollState()
          Box(Modifier.fillMaxSize()) {
            Column(Modifier.verticalScroll(scroll)
            ) {

              ProductList()
            }
          }
        }
      }
    }
  }
}