package composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun Label(label: String = "", content: @Composable () -> Unit) {
    Column() {
        BasicText(
            label,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp, vertical = 6.dp),
            style = TextStyle(
                color = Color(0xff3b3b3b),
            )
        )
        content()
    }
}


@Composable
fun TableTitleAndContent(text: String, mModifier: Modifier) {
    Spacer(modifier = Modifier.width(8.dp))

    Text(
        text = AnnotatedString(text),
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = mModifier
    )

}