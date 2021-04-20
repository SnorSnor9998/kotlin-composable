package composable

import Config
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate


@ExperimentalAnimationApi
@Composable
fun WTDatePicker(
    date: MutableState<String>
) {
    val isVisible = remember { mutableStateOf(false) }
    Column() {

        RoundButton(
            date.value,
            onClick = { isVisible.value = !isVisible.value },
            Modifier.fillMaxWidth().padding(8.dp)
                .height(40.dp)
        )

        AnimatedVisibility(visible = isVisible.value) {
            WTCalendar(date, visible = isVisible)
        }
    }
}

@Composable
fun WTCalendar(
    date: MutableState<String>,
    visible: MutableState<Boolean>,
) {
    Box(
        Modifier
            .clip(RoundedCornerShape(10.dp))
            .padding(10.dp)
            .width(400.dp)
            .height(360.dp)
            .shadow(elevation = 13.dp, shape = RoundedCornerShape(10.dp))
            .background(color = Config.Color.WHITE, shape = RoundedCornerShape(10.dp))
            .padding(10.dp)
    ) {
        val weeks = arrayListOf(arrayListOf<Int>())
        var currIndex = 0
        val today = LocalDate.now()
        val year = remember { mutableStateOf(today.year) }
        val month = remember { mutableStateOf(today.month) }
        val currentDate = LocalDate.of(year.value, month.value, 1)

        val dayOfWeek = currentDate.dayOfWeek.value
        val daysInMonth = Array(currentDate.lengthOfMonth()) { it + 1 }
        val blankDays = Array(dayOfWeek) { 0 }

        val arrayOfDays  = if (blankDays.size == 7){
            daysInMonth
        }else{
            blankDays + daysInMonth
        }


        for ((idx, value) in arrayOfDays.withIndex()) {
            if (value != 0 && LocalDate.of(year.value, month.value, value).dayOfWeek.value == 7) {
                    weeks.add(arrayListOf())
                    currIndex += 1
            }
            weeks[currIndex].add(value)
            if (idx == arrayOfDays.size - 1) {
                while (weeks[currIndex].size < 7) {
                    weeks[currIndex].add(0)
                }
            }
        }

        Column() {
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                BasicText(
                    "${month.value} ${year.value}",
                    modifier = Modifier.padding(5.dp),
                    style = TextStyle(
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold
                    )
                )
                Row {
                    IconButton(
                        onClick = {
                            if (month.value.value <= 1) {
                                year.value -= 1
                            }
                            month.value = month.value.minus(1)
                        },
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowLeft,
                            modifier = Modifier.size(40.dp),
                            contentDescription = "Previous",
                            tint = Config.Color.PRIMARY,
                        )
                    }
                    IconButton(
                        onClick = {
                            if (month.value.value >= 12) {
                                year.value += 1
                            }
                            month.value = month.value.plus(1)
                        }
                    ) {
                        Icon(
                            Icons.Default.KeyboardArrowRight,
                            modifier = Modifier.size(40.dp),
                            contentDescription = "Next",
                            tint = Config.Color.PRIMARY,
                        )
                    }
                }
            }

            Row(modifier = Modifier.fillMaxWidth()){
                val nameOfDays = arrayListOf("Su","Mo","Tu","We","Th","Fr","Sa")
                nameOfDays.forEach {
                    Box(modifier = Modifier.weight(1f)
                        .size(40.dp)
                        .padding(top = 8.dp),
                        contentAlignment = Alignment.Center){
                        BasicText(it)
                    }
                }
            }

            weeks.forEach { week ->
                Row(
                    modifier =
                    Modifier.fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        week.forEach { day ->
                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .size(40.dp)
                                    .clickable(
                                        onClick = {
                                            date.value =
                                                formatDate(year.value, month.value.value, day)
                                            visible.value = false
                                        },
                                        enabled = day != 0,
                                        role = Role.Button,
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = rememberRipple(bounded = false, color = Config.Color.PRIMARY.copy(alpha = 0.3f), radius = 15.dp)
                                    ).then(
                                        if (date.value == formatDate(year.value, month.value.value, day))
                                            Modifier.clip(CircleShape).background(color = Config.Color.PRIMARY, shape = CircleShape)
                                        else
                                            Modifier

                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                BasicText(
                                    day.toString(),
                                    modifier = Modifier.padding(10.dp),
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        textAlign = TextAlign.Center,
                                        color =
                                        when {
                                            date.value == formatDate(year.value, month.value.value, day) -> Config.Color.WHITE
                                            day != 0 -> Config.Color.BLACK
                                            else -> Color.Transparent
                                        }
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

fun formatDate(year: Int, month: Int, day: Int): String {
    return "$year-${month.toString().padStart(2, '0')}-${day.toString().padStart(2, '0')}"
}
