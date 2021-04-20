package composable

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import viewModel.CartViewModel

@ExperimentalFoundationApi
@Composable
fun CardList(cartViewModel: MutableState<CartViewModel>) {

  val numbers = (0..15).toList()
  var r = 0

  LazyVerticalGrid(
    cells = GridCells.Fixed(5)
  ) {
    items(numbers.size) {

      Column(horizontalAlignment = Alignment.CenterHorizontally) {

        Box(
          modifier = Modifier
            .height(250.dp)
            .width(185.dp)
            .padding(vertical = 24.dp)
            .background(Config.Color.PRIMARY,shape = RoundedCornerShape(10.dp))
            .border(width = 5.dp,Config.Color.PRIMARY,shape = RoundedCornerShape(10.dp))
            .clickable(
              onClick = {
                cartViewModel.value.insertBooking(it)
                println(cartViewModel.value.getSelectedBooking())
              },
              role = Role.Button,
            ),
          contentAlignment = Alignment.Center
        ){



            Column(horizontalAlignment = Alignment.CenterHorizontally) {

              var path = ""

              when(r){
                0 -> path = "courts/badminton.png"
                1 -> path = "courts/tennis.png"
                2 -> path = "courts/tabletennis.png"
                3 -> path = "courts/fitness.png"
                4 -> path = "courts/other.png"
                5 -> path = "courts/squash.png"
              }


              Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
              ){
                Image(
                  imageFromResource(path),
                  contentDescription = "ICON",
                  modifier = Modifier.fillMaxSize()
                )
              }

              if (r == 5){
                r =0
              }else{
                r++
              }



            }

          Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(bottom = 16.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
          ){

            Text("COURT $it",color = Config.Color.WHITE,fontSize = 16.sp)
          }

        }
      }
    }
  }
}


