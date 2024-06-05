package com.composeit.design.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Diamond
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewDynamicColors
import androidx.compose.ui.unit.dp

@Composable
fun DefaultIconTextContent(
    icon: ImageVector,
    @StringRes text:Int,
    @StringRes iconContentDescription: Int,
    modifier: Modifier = Modifier
){
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Icon(
            imageVector = icon,
            contentDescription = stringResource(id = iconContentDescription),
            modifier = Modifier.size(64.dp),
            tint = MaterialTheme.colorScheme.primary
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = text),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.outline
        )
    }
}

@Composable
fun ComposeItLoadingContent(modifier: Modifier = Modifier){
    Box(modifier = modifier.fillMaxSize(),
        content = {

        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeItTopAppBar(
    onPress: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {},
        navigationIcon = {
            IconButton(
                onClick = onPress
            ){
                Icon(
                    imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                    contentDescription = "Back"
                )
            }
        },
        modifier = modifier
    )
    
}

@Composable
fun ComposeItFloatingButton(
    onClick: () -> Unit,
    contentDescription: String,
    modifier: Modifier = Modifier,
) {
    FloatingActionButton(
        containerColor = MaterialTheme.colorScheme.primary,
        onClick = onClick,
        modifier = modifier,
    ){
        Icon(
            imageVector = Icons.Outlined.Add,
            contentDescription = contentDescription
        )
    }
}

@Composable
fun ComposeItTextField(
    label:String,
    text: String,
    onTextChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    OutlinedTextField(
        label = { Text(text = label) },
        value = text,
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() }),
        modifier = modifier
    )
}
//@Composable
//@Preview
//fun ComposeItDefaultTextIconPreview(){
//    ComposeItTheme {
//        DefaultIconTextContent(
//            icon = Icons.Default.Diamond,
//            text = "Test Text",
//            iconContentDescription = "Test Icon"
//        )
//    }
//}
//@Composable
//@Preview
//fun ComposeItTextFieldPreview(){
//    ComposeItTheme {
//        ComposeItTextField(
//            label = "Lable",
//            text = "text",
//            onTextChange = {},
//        )
//    }
//}
//@Composable
//@Preview
//fun ComposeItFloatingButtonPreview(){
//    ComposeItTheme {
//        ComposeItFloatingButton(
//            contentDescription = "test",
//            onClick = {}
//        )
//    }
//}
//@Composable
//@Preview
//fun ComposeItTopAppBarPreview(){
//    ComposeItTheme {
//       ComposeItTopAppBar(
//           onPress = {}
//       )
//    }
//}
//@Composable
//@Preview
//fun ComposeItLoadingPreview(){
//    ComposeItTheme {
//        ComposeItLoadingContent()
//    }
//}