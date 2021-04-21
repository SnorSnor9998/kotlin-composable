package viewModel

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class CartViewModel() {

  private val cartList = arrayListOf<Cart>()

  private val bookingList = arrayListOf<Booking>()





  fun insertProduct(){

  }


  fun insertBooking(i: Int) {

    var found = false
    var index = 0
    var selectedIndex = 0
    bookingList.forEach {
        if (it.courtName == i.toString()){
          found = true
          selectedIndex = index
        }
      index++
    }

    if (!found){
      bookingList.add(Booking(i.toString(),1.00,20.00))
    }else{
      bookingList.removeAt(selectedIndex)
    }


  }

  //testing only
  fun getSelectedBooking() : ArrayList<String> {

    val arrayString = arrayListOf<String>()

    bookingList.forEach {
      arrayString.add(it.courtName)
    }

    return arrayString
  }


  fun searchCourt(date: String,time:String ,duration : String , category:String ){

    val mDate = date
    val mDuration = duration.substringBefore( " ")
    val mCategory = category

    val temp = time.substringBefore(" ")
    val hour = temp.substringBefore(":")
    val min = temp.substringAfter(":")
    val mTime = if (time.substringAfter(" ") == "PM"){
      (hour.toInt()+12).toString() + min
    }else{
      hour + min
    }


    println(mDate)
    println(mTime)
    println(mDuration)
    println(mCategory)
  }


}


private data class Booking(

  val courtName : String,
  val hour : Double,
  val price : Double,
)



private data class Cart(

  val selected : Boolean,
  val itemName : String,
  val qty : Int,
  val price : Double,
  val isBooking : Boolean

)