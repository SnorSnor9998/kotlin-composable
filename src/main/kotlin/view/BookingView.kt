package view

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.desktop.AppManager
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import view.Pos.PosHost
import viewModel.CartViewModel

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun BookingView(navControl: MutableState<Int>) {


  val window = LocalAppWindow.current
  AppManager.focusedWindow?.maximize()
  window.setTitle("WeTix Booking Engine")


  val cartViewModel = remember { mutableStateOf(CartViewModel()) }


  Row {

    val innerNavControl = remember { mutableStateOf(Config.Navigate.POS) }

    SiteNavigationView(innerNavControl)

    when (innerNavControl.value) {
      Config.Navigate.BOOKINGPANEL -> BookingPanelView(cartViewModel)
      Config.Navigate.BOOKINGHISTORY -> BookingHistoryView()
      Config.Navigate.COURT_MGN -> CourtMangementView()
      Config.Navigate.MERCHANT_PROFILE -> MerchantProfileView()
      Config.Navigate.SIGNOUT -> navControl.value = Config.Navigate.LOGIN
      Config.Navigate.PRODUCT_MGN -> ProductManagementView()
      Config.Navigate.POS -> PosHost()
    }


  }
}