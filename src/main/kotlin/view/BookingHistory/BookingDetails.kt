package view

import androidx.compose.desktop.AppManager
import androidx.compose.desktop.Window
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.PathNode
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp


fun BookingDetails() = Window(

  title = "Order Details",
  size = IntSize(400, 700),
  centered = true

) {


  val scroll = rememberScrollState()
  Column(
    horizontalAlignment = Alignment.CenterHorizontally,
    modifier = Modifier.verticalScroll(scroll)

  ) {

    BDText("Origin", "WeTix")
    BDText("Booking ID", "123456")
    BDText("Booking Date", "31/12/2021 23:59:59")

    //Customer
    BDText("Customer Name", "CUS TEST NAME")
    BDText("Customer Email", "123@gmail.com")
    BDText("Customer Ph.No", "1234567890")
    BDText("Customer IC.NO", "1234567890")


    //Facility
    BDText("Court Name", "Court 1")
    BDText("Category", "Badminton")
    BDText("Type", "WoodenCourt")
    BDText("Play Time", "3.5 Hours")

    Button(onClick = {
      AppManager.windows[1].close()
    },modifier = Modifier.fillMaxWidth().padding(8.dp)){
      Text("Complete")
    }


  }


}


@Composable
fun BDText(title: String, value: String) {

  Row(
    modifier = Modifier.height(42.dp),
    verticalAlignment = Alignment.CenterVertically
  ) {

    Text(
      title,
      Modifier
        .weight(1F)
        .padding(start = 24.dp),
      textAlign = TextAlign.Start,
      fontWeight = FontWeight.Bold
    )

    Text(
      value,
      Modifier
        .weight(1F)
        .padding(end = 24.dp),
      textAlign = TextAlign.End
    )

  }
  Divider()


}
