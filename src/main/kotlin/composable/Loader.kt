package composable

import Config
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Loader() {
    CircularProgressIndicator(
        modifier = Modifier.wrapContentWidth(Alignment.CenterHorizontally),
        color = Config.Color.PRIMARY
    )
}
