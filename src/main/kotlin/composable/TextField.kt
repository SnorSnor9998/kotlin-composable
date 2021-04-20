package composable

import Config
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex

@Composable
fun TextField(
    value: MutableState<String>,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
    readOnly: Boolean = false,
    paddingLeadingIconEnd: Dp = 0.dp,
    paddingTrailingIconStart: Dp = 0.dp,
    leadingIcon: (@Composable() () -> Unit)? = null,
    trailingIcon: (@Composable() () -> Unit)? = null,
    placeholder: String? = null,
) {
    // val state = savedInstanceState(saver = TextFieldValue.Saver) { TextFieldValue() }

    Box(
        modifier
            .background(
                color = Color.Transparent,
                shape = RoundedCornerShape(5.dp)
            )
            .clip(RoundedCornerShape(5.dp))
    ) {
        Row(
            modifier =
            modifier
                .background(Color(0xfff1f1f1))
                .height(34.dp)
                .fillMaxWidth()
                .focusable(true)
                .padding(horizontal = 10.dp, vertical = 0.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (leadingIcon != null) {
                leadingIcon()
            }
            BasicTextField(
                value = value.value,
                onValueChange = { value.value = it },
                visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
                modifier = modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(
                    color = Config.Color.BLACK,
                    fontSize = 14.sp
                ),
                readOnly = readOnly,
                decorationBox = @Composable {
                    innerTextField ->
                    if (value.value.isEmpty() && !placeholder.isNullOrEmpty()) {
                        BasicText(placeholder, modifier = modifier.zIndex(-1f).alpha(0.3f))
                    }
                    innerTextField()
                }
            )
            if (trailingIcon != null) {
                trailingIcon()
            }
        }
    }
}


@Composable
fun OutlineTextField(
    output: MutableState<String>,
    title: String,
    isPassword: Boolean){

    OutlinedTextField(
        value = output.value,
        onValueChange = { output.value = it },
        label = { Text(text = title) },
        placeholder = { Text(text = title) },
        singleLine = true,
        modifier = Modifier.fillMaxWidth(),
        visualTransformation = if (!isPassword) VisualTransformation.None else PasswordVisualTransformation(),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Config.Color.PRIMARY,
            cursorColor = Config.Color.PRIMARY,
            focusedLabelColor = Config.Color.PRIMARY
        )

    )



}
