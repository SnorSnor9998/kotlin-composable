package viewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDate
import java.time.format.DateTimeFormatter



class SearchViewModel(){


  val date: String = ""
  val time: String = WTFunction.getNextBookingTime()
  val duration : String = "1.0 HOURS"
  val category : String ="ALL"


  //
  // val date = remember { mutableStateOf(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))) }
  // val time = remember { mutableStateOf(WTFunction.getNextBookingTime()) }
  // val duration = remember { mutableStateOf("1.0 HOURS") }
  // val category = remember { mutableStateOf("ALL") }


  fun insert(test:String) {

    println("TEST : " + test)


  }



}