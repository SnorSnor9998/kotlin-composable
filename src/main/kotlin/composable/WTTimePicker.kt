package composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun WTTimePicker(selectedValue: MutableState<String>, start:Int, end:Int) {

  Column() {

    val items = arrayListOf<String>()


    for (i in start until end) {
      if (i >= 13) {
        items.add("${String.format("%02d", i - 12)}:${String.format("%02d", 0)} PM")
        items.add("${String.format("%02d", i - 12)}:${String.format("%02d", 30)} PM")
      } else {
        items.add("${String.format("%02d", i)}:${String.format("%02d", 0)} AM")
        items.add("${String.format("%02d", i)}:${String.format("%02d", 30)} AM")
      }
    }

    // val selectedValue = remember { mutableStateOf(currentTime) }
    val isVisible = remember { mutableStateOf(false) }


    RoundButton(
      selectedValue.value,
      onClick = { isVisible.value = !isVisible.value },
      Modifier.fillMaxWidth().padding(8.dp)
        .height(40.dp)
    )

    AnimatedVisibility(visible = isVisible.value) {
      DropdownListing(items, visible = isVisible,selectedValue)
    }

  }
}
