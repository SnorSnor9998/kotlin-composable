package view


import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import composable.BookingHistoryList

@Composable
fun BookingHistoryView(){
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

          Text("Booking History")


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


              BookingHistoryList()



            }

          }

        }

      }


    }


  }


}