package com.thezayin.mehndidesign.presentation.search.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.presentation.search.viewmodel.SearchViewModel

@Composable
fun TopSearchBarComponent(modifier: Modifier, searchViewModel: SearchViewModel) {
    val searchQuery by searchViewModel.searchQuery
    val query = remember { mutableStateOf(TextFieldValue()) }
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            TextField(
                value = query.value,
                onValueChange = {
                    query.value = it
                },
                placeholder = {
                    Text(
                        text = "Search",
                        color = colorResource(id = R.color.grey),
                        fontSize = 12.sp,
                        fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    )
                },
                singleLine = true,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
                    .weight(0.8f),
                shape = RoundedCornerShape(8.dp),
                leadingIcon = {

                },
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = colorResource(id = R.color.ed_background),
                    unfocusedContainerColor = colorResource(id = R.color.ed_background),
                    disabledLabelColor = colorResource(id = R.color.red),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedTextColor = colorResource(id = R.color.black),
                    unfocusedTextColor = colorResource(id = R.color.black)
                )
            )
            Button(
                onClick = {

                }, colors = ButtonDefaults.buttonColors(
                    containerColor = colorResource(id = R.color.black),
                    contentColor = colorResource(id = R.color.white)
                ),
                shape = RoundedCornerShape(10.dp),
                modifier = Modifier
                    .height(45.dp)
                    .weight(0.3f)
                    .padding(start = 10.dp)
            ) {
                Text(text = "Search", color = colorResource(id = R.color.white))
            }
        }
    }
}