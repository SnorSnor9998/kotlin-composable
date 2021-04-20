package viewModel

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


  fun getSelectedBooking() : ArrayList<String> {

    val arrayString = arrayListOf<String>()

    bookingList.forEach {
      arrayString.add(it.courtName)
    }

    return arrayString
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