import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

object Config {
    object Color {
        val PRIMARY = Color(0xfffc4451)
        val WHITE = Color(0xffffffff)
        val BLACK = Color(0xff1a1b1c)
        val LIGHT_GREY = Color(0xffb9b9b9)
        val DARK_GREY = Color(0xff505050)
        val GREEN = Color(0xff00b32b)
    }
    val BORDER_RADIUS = RoundedCornerShape(50)


    object Navigate{
        val LOGIN = 1
        val BOOKING = 2

        val BOOKINGPANEL = 3
        val BOOKINGHISTORY = 4
        val COURT_MGN = 5
        val MERCHANT_PROFILE = 6
        val POS = 7
        val PRODUCT_MGN = 8

        val SIGNOUT = -1


    }

}
