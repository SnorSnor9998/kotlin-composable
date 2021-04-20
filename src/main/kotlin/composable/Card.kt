package composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp

@Composable
fun Card(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Box(modifier.absolutePadding(bottom = 15.dp).fillMaxWidth()) {
        Box(
            Modifier.graphicsLayer(shadowElevation = 3f, shape = RoundedCornerShape(5.dp))
                .background(
                    color = Config.Color.WHITE,
                    shape = RoundedCornerShape(5.dp),
                )
                .fillMaxWidth()
                .clip(RoundedCornerShape(5.dp)),
            propagateMinConstraints = true,
        ) {
            Row(
                Modifier.padding(10.dp)
            ) {
                content()
            }
        }
    }
}
