package view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.imageFromResource
import androidx.compose.ui.unit.dp
import composable.IconButton
import composable.NavButton
import composable.NavIconButton

@ExperimentalAnimationApi
@Composable
fun SiteNavigationView(nav: MutableState<Int>) {


  val boxWidth = remember { mutableStateOf(0.05f) }
  val hide = remember { mutableStateOf(true) }

  Box(
    modifier = Modifier
      .fillMaxHeight()
      .fillMaxWidth(boxWidth.value)

      .padding(16.dp),
    contentAlignment = Alignment.TopCenter
  ) {


    Column(verticalArrangement = Arrangement.Top, modifier = Modifier.fillMaxHeight()) {


      IconButton(
        onClick = {
          hide.value = !hide.value

          if (!hide.value){
            boxWidth.value = 0.15f
          }else{
            boxWidth.value = 0.05f
          }

        },

        modifier = if (!hide.value) Modifier else Modifier.fillMaxWidth()
      ) {
        Icon(
          if (!hide.value) Icons.Default.KeyboardArrowLeft else Icons.Default.List,
          modifier = Modifier.size(50.dp),
          contentDescription = "Navigation Drawer",
          tint = Config.Color.PRIMARY,
        )
      }



      Column(horizontalAlignment = Alignment.CenterHorizontally) {

        if (hide.value){

          Spacer(Modifier.padding(vertical = 24.dp))
          NavIconButton("POS","cashier.svg",onClick = {
            nav.value = Config.Navigate.POS
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("Booking","booking_b.svg",onClick = {
            nav.value = Config.Navigate.BOOKINGPANEL
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("History","history.svg",onClick = {
            nav.value = Config.Navigate.BOOKINGHISTORY
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("Product Management","open_box.svg",onClick = {
            nav.value = Config.Navigate.PRODUCT_MGN
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("Court Management","ic_courts.svg",onClick = {
            nav.value = Config.Navigate.COURT_MGN
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("Merchant Profile","shop.svg",onClick = {
            nav.value = Config.Navigate.MERCHANT_PROFILE
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavIconButton("Sign Out","logout.svg",onClick = {
            nav.value = Config.Navigate.SIGNOUT
          })

        }else{

          Spacer(Modifier.padding(vertical = 24.dp))
          NavButton("POS","cashier.svg", onClick = {
            nav.value = Config.Navigate.POS
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("Booking","booking_b.svg", onClick = {
            nav.value = Config.Navigate.BOOKINGPANEL
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("History","history.svg", onClick = {
            nav.value = Config.Navigate.BOOKINGHISTORY
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("Product Mgn.","open_box.svg", onClick = {
            nav.value = Config.Navigate.PRODUCT_MGN
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("Court Mgn.","ic_courts.svg", onClick = {
            nav.value = Config.Navigate.COURT_MGN
          })
          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("Merchant Profile","shop.svg",onClick = {
            nav.value = Config.Navigate.MERCHANT_PROFILE
          })

          Divider(Modifier.padding(vertical = 8.dp))
          NavButton("Sign Out","logout.svg",onClick = {
            nav.value = Config.Navigate.SIGNOUT
          })
        }
      }
    }

    Column(modifier = Modifier.fillMaxWidth().fillMaxHeight(),verticalArrangement = Arrangement.Bottom) {


      Column {
        Box(modifier = Modifier
          .padding(0.dp,8.dp)
          .background(Color.Transparent),
          contentAlignment = Alignment.Center){

          val image = if(!hide.value) imageFromResource("wetixlogo.png") else imageFromResource("icon.png")
          Image(image,"")

        }
      }
    }
  }
}