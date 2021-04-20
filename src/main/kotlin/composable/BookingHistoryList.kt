package composable


import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import view.BookingDetails

@Composable
fun BookingHistoryList() {

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
        "ID",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Customer", Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Category",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Court Name",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Time",
        Modifier.weight(1F).align(Alignment.CenterVertically)
      )

      TableTitleAndContent(
        "Duration", Modifier.weight(1F).align(Alignment.CenterVertically)
      )




    }




    //content
    repeat(30) {

      Row(
        modifier = Modifier
          .height(48.dp)
          .clickable(onClick = {
            BookingDetails()
          })
      ) {

        TableTitleAndContent(
          (it * 1234212356).toString(),
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "TESTING",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "BADMINTON",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "Rubber Court 888",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "12:00PM",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

        TableTitleAndContent(
          "3.0 Hours",
          Modifier.weight(1F).align(Alignment.CenterVertically)
        )

      }

      Divider()
    }
  }
}

