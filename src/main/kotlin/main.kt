import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.desktop.LocalAppWindow
import androidx.compose.desktop.Window
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import view.BookingView
import view.LoginView

@ExperimentalFoundationApi
@ExperimentalAnimationApi
fun main() = Window(title = "Login") {

    LocalAppWindow.current.setIcon(WTFunction.getWeTixIcon())


    val navControl = remember { mutableStateOf(Config.Navigate.BOOKING) }

    when(navControl.value){
        Config.Navigate.LOGIN -> LoginView(navControl)
        Config.Navigate.BOOKING -> BookingView(navControl)
    }



}



