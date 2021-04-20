package composable

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import view.BookingDetails

@Composable
fun ProductList(){


  Column(
    modifier = Modifier
      .fillMaxWidth(),
    verticalArrangement = Arrangement.Center,
  ) {

    //title
    Row(
      modifier = Modifier
        .fillMaxWidth()
        .padding(vertical = 8.dp)
    ) {

      TableTitleAndContent(
        "Product ID",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Product Name", Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Price",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

    }




    //content
    repeat(10) {

      Row(
        modifier = Modifier
          .height(48.dp)
          .clickable(onClick = {

          })
      ) {


        TableTitleAndContent(
          "W123",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "100 PLUS", Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "2.50",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )
      }

      Divider()
    }
  }



}