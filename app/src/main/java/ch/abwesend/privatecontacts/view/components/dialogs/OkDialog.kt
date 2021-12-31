package ch.abwesend.privatecontacts.view.components.dialogs

import androidx.annotation.StringRes
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import ch.abwesend.privatecontacts.R

@Composable
fun OkDialog(
    @StringRes title: Int,
    @StringRes text: Int,
    onClose: () -> Unit,
) {
    AlertDialog(
        title = { Text(stringResource(id = title)) },
        text = { Text(stringResource(id = text)) },
        onDismissRequest = onClose,
        confirmButton = {
            Button(onClick = onClose) {
                Text(stringResource(id = R.string.yes))
            }
        },
        dismissButton = {},
    )
}
