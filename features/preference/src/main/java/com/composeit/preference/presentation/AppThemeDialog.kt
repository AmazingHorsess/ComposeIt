package com.composeit.preference.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.composeit.design.ComposeItTheme
import com.composeit.preference.R
import com.composeit.preference.model.AppThemeOptions

@Composable
internal fun AppThemeDialog(
    isDialogOpen: Boolean,
    onDismissRequest: () -> Unit,
    currentTheme:AppThemeOptions,
    onThemeUpdate: (AppThemeOptions) -> Unit,
){
    if (isDialogOpen){
        AlertDialog(
            onDismissRequest = onDismissRequest,
            title = { Text(text = stringResource(id = R.string.preference_title_app_theme)) },
            text = {
                   Column {
                       AppThemeOptions.entries.forEach { item ->
                           val isSelected = currentTheme.id == item.id

                           Row(
                               modifier = Modifier
                                   .fillMaxWidth()
                                   .height(48.dp)
                                   .padding(8.dp)
                                   .selectable(
                                       selected = isSelected,
                                       onClick = {
                                           onThemeUpdate(item)
                                           onDismissRequest()
                                       },
                                   ),
                               verticalAlignment = Alignment.CenterVertically,
                           ) {
                               RadioButton(
                                   selected = isSelected,
                                   onClick = {
                                       onThemeUpdate(item)
                                       onDismissRequest()
                                   }
                               )
                               Text(
                                   text = stringResource(id = item.titleRes),
                                   style = MaterialTheme.typography.bodySmall,
                                   modifier = Modifier.padding(start = 16.dp),
                               )

                           }
                       }
                   }
            },
            confirmButton = { },
            dismissButton = {}
        )
    }

}
@Composable
@Preview
private fun AppThemeDialogPreview(){
    ComposeItTheme {
            AppThemeDialog(isDialogOpen = true , onDismissRequest = { /*TODO*/ }, currentTheme = AppThemeOptions.LIGHT ) {

            }


    }
}