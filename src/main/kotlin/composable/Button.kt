package composable

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.svgResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import jdk.jfr.Enabled
import androidx.compose.material.Button as BaseButton

@Composable
fun WTButton(
    text: String,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    outline: Boolean = false
) {
    var bgColor = Config.Color.PRIMARY
    var contentColor = Config.Color.WHITE
    if (outline) {
        bgColor = Config.Color.WHITE
        contentColor = Config.Color.BLACK
    }
    BaseButton(
        shape = RoundedCornerShape(5.dp),
        onClick = onClick,
        colors = ButtonDefaults.outlinedButtonColors(
            backgroundColor = bgColor,
            contentColor = contentColor,
        ),
        contentPadding = PaddingValues(
            15.dp,
            0.dp
        ),
        modifier = modifier
            .padding(0.dp).fillMaxWidth(),
        elevation = null,
        border = if (outline) BorderStroke(1.dp, Config.Color.PRIMARY) else null
    ) {
        Text(text.toUpperCase())
    }
}

@Composable
fun IconButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable () -> Unit
) {
    Box(
        modifier = modifier
            .clickable(
                onClick = onClick,
                enabled = enabled,
                role = Role.Button,
                interactionSource = interactionSource,
                indication = rememberRipple(bounded = false, radius = 15.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        content()
    }
}


@Composable
fun NavButton(
    text: String,
    path: String,
    onClick: () -> Unit
){

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(start = 12.dp,top = 4.dp,bottom = 4.dp)
        .clickable (
            onClick = onClick,
            role = Role.Button,
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple()
            )
    ){
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                svgResource(path),
                modifier = Modifier
                    .size(40.dp),
                contentDescription = text,
            )
            Text(text.toUpperCase(),modifier = Modifier.padding(start = 16.dp))
        }
    }
}




@Composable
fun NavIconButton(
    desc : String,
    path : String,
    onClick: () -> Unit
){

    Box(
        modifier = Modifier
            .size(50.dp)
            .clickable(
                onClick = onClick,
                role = Role.Button,
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(bounded = true, color = Config.Color.PRIMARY.copy(alpha = 0.5f), radius = 35.dp)
            ),
        contentAlignment = Alignment.Center

    ){
        Icon(
            svgResource(path),
            modifier = Modifier
                .size(40.dp),
            contentDescription = desc,
            )
    }

}



@Composable
fun RoundButton(
    text: String,
    onClick: () -> Unit,
    mModifier: Modifier
) {
    BaseButton(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Config.Color.PRIMARY,
            contentColor = Config.Color.WHITE,
        ),
        modifier = mModifier,
        shape = RoundedCornerShape(50)

    ) {
        Text(text.toUpperCase())
    }
}
