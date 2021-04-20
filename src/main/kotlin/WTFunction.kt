import java.awt.image.BufferedImage
import java.io.File
import java.util.*
import javax.imageio.ImageIO

object WTFunction {


  fun getWeTixIcon(): BufferedImage {
    var image: BufferedImage? = null
    try {
      image = ImageIO.read(File("src/main/resources/icon.png"))
    } catch (e: Exception) {
      // image file does not exist
    }

    if (image == null) {
      image = BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB)
    }

    return image
  }

  fun getNextBookingTime(): String {
    val cal = Calendar.getInstance()
    var currentHour = cal.get(Calendar.HOUR_OF_DAY)
    var currentMin = cal.get(Calendar.MINUTE)

    if (currentMin in 1..29) {
      currentMin = 30
    } else if (currentMin in 31..59) {
      currentHour += 1
      currentMin = 0
    }

    return if (currentHour > 12) {
      "${String.format("%02d", currentHour - 12)}:${String.format("%02d", currentMin)} PM"
    } else {
      "${String.format("%02d", currentHour)}:${String.format("%02d", currentMin)} AM"
    }
  }



}