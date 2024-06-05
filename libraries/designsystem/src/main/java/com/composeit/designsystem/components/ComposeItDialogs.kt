package com.composeit.designsystem.components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import com.composeit.designsystem.ComposeItTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ComposeItDialog(
    arguments: DialogArguments,
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit
) {
    if (isDialogOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = arguments.title) },
            text = { Text(text = arguments.text) },
            confirmButton = {
                Button(onClick = arguments.onConfirmAction) {
                    Text(text = arguments.confirmText)
                }
            },
            dismissButton = {
                OutlinedButton(onClick = onDismissRequest) {
                    Text(text = arguments.dismissText)
                }
            },
        )
    }
}



data class DialogArguments(
    val title: String,
    val text: String,
    val confirmText: String,
    val dismissText: String,
    val onConfirmAction: () -> Unit
    )

//@Composable
//@Preview
//fun ComposeItDialogPreview(){
//    ComposeItTheme {
//        val arguments = DialogArguments(
//            title = "Test title",
//            text = "Test Text",
//            confirmText = "Confirm",
//            dismissText = "Dismiss",
//            onConfirmAction = {}
//        )
//        ComposeItDialog(
//            arguments,
//            isDialogOpen = true,
//            onDismissRequest = {}
//        )
//    }
//}

