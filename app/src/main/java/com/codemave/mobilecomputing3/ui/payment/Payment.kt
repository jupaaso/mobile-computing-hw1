package com.codemave.mobilecomputing3.ui.payment

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import com.google.accompanist.insets.systemBarsPadding

@Composable
// parameters are placed into bracket like hello
fun Payment(
    onBackPress: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .systemBarsPadding()
        ) {
            TopAppBar {
                IconButton(
                    onClick = onBackPress
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null
                    )
                }
                Text(text = "Payment")
            }
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top,
                modifier = Modifier.padding(16.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    //value = payment.value,
                    onValueChange = { },
                    //onValueChange = { data -> password.value = data }, // same to password
                    label = { Text("Recipient name") },
                    modifier = Modifier.fillMaxWidth(),
                    //keyboardOptions = KeyboardOptions(
                    //    keyboardType = KeyboardType.Password
                )
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(
                    value = "",
                    onValueChange = { },
                    label = { Text("Recipient account") },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                Row{
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        label = { Text("Date") },
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    OutlinedTextField(
                        value = "",
                        onValueChange = { },
                        label = { Text("Amount") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
                Spacer(modifier = Modifier.height(10.dp))
                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier.fillMaxWidth().size(55.dp)
                ) {
                    Text("Save payment")
                }
            }
        }
    }
}