package composable

import androidx.compose.foundation.ScrollbarStyle
import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp



@Composable
fun DropdownListing(items: ArrayList<String>, visible: MutableState<Boolean>, selectedValue: MutableState<String>) {

  val scroll = rememberScrollState()
  Box(
    Modifier
      .clip(RoundedCornerShape(10.dp))
      .padding(10.dp)
      .fillMaxWidth()
      .height(350.dp)
      .shadow(elevation = 13.dp, shape = RoundedCornerShape(10.dp))
      .background(color = Config.Color.WHITE, shape = RoundedCornerShape(10.dp))
      .padding(10.dp)
  ) {

    // Column(Modifier.verticalScroll(scroll)) {
    Column(Modifier.verticalScroll(scroll)) {
      items.forEach {

        Box(
          modifier =
          Modifier
            .fillMaxWidth()
            .clickable {
              selectedValue.value = it
              visible.value = false
            }
        ) {

          Row(
            modifier = Modifier.fillMaxWidth(),
          ) {

            Text(
              it,
              modifier = Modifier.padding(10.dp),
              style = TextStyle(
                fontSize = 16.sp,
                textAlign = TextAlign.Center
              )
            )
          }

        }

      }
    }


    // VerticalScrollbar(
    //   rememberScrollbarAdapter(scroll),
    //   modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
    //   style = ScrollbarStyle(
    //     minimalHeight = 42.dp,
    //     thickness = 16.dp,
    //     shape = RectangleShape,
    //     hoverDurationMillis = 0,
    //     unhoverColor = Color.Black.copy(alpha = 0.12f),
    //     hoverColor = Color.Black.copy(alpha = 0.12f)
    //   )
    // )
  }
}