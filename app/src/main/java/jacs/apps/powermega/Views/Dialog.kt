package jacs.apps.powermega.Views

import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import jacs.apps.powermega.R
import jacs.apps.powermega.ui.theme.PowerMegaTheme

@Composable
fun ErrorDialog(onDismiss: () -> Unit){
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = {
            Text(text = stringResource(id = R.string.error))
        },
        text = {
            Text(stringResource(id = R.string.invalid_ticket))
        },
        confirmButton = {
            Button(

                onClick = onDismiss) {
                Text(stringResource(id = R.string.ok))
            }
        },

    )
}
