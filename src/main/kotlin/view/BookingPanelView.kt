package view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import composable.CardList
import composable.RoundButton
import composable.WTCategory

import composable.WTDatePicker
import composable.WTDuration
import composable.WTTimePicker
import viewModel.CartViewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BookingPanelView(cartViewModel: MutableState<CartViewModel>) {


  Box(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth(1f)
  ) {

    Row {

      val scroll = rememberScrollState()

      Divider(
        color = Color.Gray.copy(0.3f),
        modifier = Modifier
          .fillMaxHeight()
          .width(2.dp)
      )


      Column(modifier = Modifier.fillMaxHeight().fillMaxWidth(0.2f).verticalScroll(scroll)) {

        Spacer(Modifier.padding(top = 16.dp))



        val date = remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) }
        val time = remember { mutableStateOf(WTFunction.getNextBookingTime()) }
        val duration = remember { mutableStateOf("1.0 HOURS") }
        val category = remember { mutableStateOf("ALL") }


        Text("DATE", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        WTDatePicker(date)

        Text("TIME", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        //operating hour (in 24 hour format)
        WTTimePicker(time,0,24)

        Text("DURATION", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        WTDuration(duration, 7)

        Text("CATEGORY", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        WTCategory(category)

        Text("", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center)
        RoundButton("Search", onClick = {


          // cartViewModel.value.searchCourt(date.value,time.value,duration.value,category.value)



        }, Modifier.fillMaxWidth().padding(8.dp).height(40.dp))

      }


      Divider(
        color = Color.Gray.copy(0.3f),
        modifier = Modifier
          .fillMaxHeight()
          .width(2.dp)
      )




      Column(modifier = Modifier.fillMaxHeight().fillMaxWidth()) {

        CardList(cartViewModel)

      }



    }



  }
}



