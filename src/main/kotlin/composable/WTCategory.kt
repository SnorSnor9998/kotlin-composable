package composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalAnimationApi
@Composable
fun WTCategory(selectedValue: MutableState<String>) {

  Column() {


    val items = arrayListOf("All","BADMINTON","FUTSAL","TABLE TENNIS","FITNESS SPACE")


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

