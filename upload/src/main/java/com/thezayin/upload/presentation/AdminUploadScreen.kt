package com.thezayin.upload.presentation

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.thezayin.upload.presentation.event.AdminUploadEvent
import com.thezayin.upload.presentation.state.AdminUploadState
import org.koin.compose.koinInject

@Composable
fun AdminUploadScreen() {
    val viewModel: AdminUploadViewModel = koinInject()
    val state by viewModel.state.collectAsState()
    val context = LocalContext.current

    val tableList = listOf(
        "arabic_table", "bridal_table", "classic_table", "finger_table",
        "foot_table", "home_images_table", "indian_table", "indo_table",
        "moroccan_table", "pakistani_table", "tattoo_table", "tikki_table"
    )

    var selectedUris by remember { mutableStateOf<List<Uri>>(emptyList()) }
    var selectedTable by remember { mutableStateOf<String?>(null) }
    var expanded by remember { mutableStateOf(false) }

    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetMultipleContents()
    ) { uris ->
        if (uris.isNotEmpty()) selectedUris = uris
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(text = "Admin Image Upload", style = MaterialTheme.typography.bodySmall)

        // Dropdown
        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedButton(onClick = { expanded = true }, modifier = Modifier.fillMaxWidth()) {
                Text(text = selectedTable ?: "Select Table")
            }
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                tableList.forEach { table ->
                    DropdownMenuItem(onClick = {
                        selectedTable = table
                        expanded = false
                        viewModel.onEvent(AdminUploadEvent.SelectTable(table))
                    },
                        text = { Text(table) })
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Image Picker
        Button(onClick = { launcher.launch("image/*") }, modifier = Modifier.fillMaxWidth()) {
            Text("Select Images")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Selected Images: ${selectedUris.size}")

        Spacer(modifier = Modifier.height(16.dp))

        // Upload Button
        Button(
            onClick = {
                if (selectedTable != null && selectedUris.isNotEmpty()) {
                    viewModel.onEvent(AdminUploadEvent.UploadImages(selectedUris, selectedTable!!))
                } else {
                    Toast.makeText(context, "Select a table and images.", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = selectedTable != null && selectedUris.isNotEmpty(),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Upload Images")
        }

        Spacer(modifier = Modifier.height(16.dp))

        when (state) {
            is AdminUploadState.Idle -> Text("Awaiting actions.")
            is AdminUploadState.Loading -> CircularProgressIndicator()
            is AdminUploadState.Success -> Text("Uploaded ${(state as AdminUploadState.Success).images.size} images.")
            is AdminUploadState.Error -> Text("Error: ${(state as AdminUploadState.Error).message}")
            is AdminUploadState.TableSelection -> Unit
        }
    }
}