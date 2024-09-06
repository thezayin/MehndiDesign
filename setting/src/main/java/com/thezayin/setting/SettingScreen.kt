package com.thezayin.setting

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.thezayin.drawable.R
import com.thezayin.framework.extension.functions.openLink
import com.thezayin.framework.extension.functions.sendMail
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import com.thezayin.framework.utils.Constants.PRIVATE_POLICY_URL
import com.thezayin.framework.utils.Constants.TERMS_CONDITIONS_URL
import com.thezayin.setting.component.TopBar
import kotlinx.coroutines.delay

@Composable
fun SettingScreen(
    onBackClick: () -> Unit
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding(),
        containerColor = colorResource(id = R.color.background),
        topBar = {
            TopBar(
                modifier = Modifier,
                screenTitle = "Setting",
                onBackClick = {
                    onBackClick()
                },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(top = 60.dp)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())

        ) { SettingOptionsList() }
    }
}

@Preview
@Composable
fun OtherListComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Others",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(id = R.color.text_color),
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            onClick = {}
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Leave a rating/review",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            onClick = {
                context.openLink(ABOUT_US_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = null,
                    modifier = Modifier
                        .size(15.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "About Us",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
    }
}

@Preview
@Composable
fun SettingHeader() {
    Column(
        modifier = Modifier
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.size(100.dp),
            shape = RoundedCornerShape(100.dp),
            onClick = {}
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_main),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "Logo"
            )
        }
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 20.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_medium)),
            color = colorResource(id = R.color.text_color),
            modifier = Modifier.padding(top = 20.dp)
        )
    }
}

@Preview
@Composable
fun LegalListComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 20.dp, bottom = 20.dp)
            .padding(horizontal = 24.dp)
    ) {
        Text(
            text = "Legal",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 25.dp),
            fontSize = 16.sp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(id = R.color.text_color),
        )
        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            onClick = {
                context.openLink(PRIVATE_POLICY_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_privacy),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Privacy Policy",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            onClick = {
                context.openLink(TERMS_CONDITIONS_URL)
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_terms),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Terms & Conditions",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(10.dp)
        )

        Card(
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ),
            onClick = {
                context.sendMail()
            }
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = null,
                    modifier = Modifier
                        .size(13.dp),
                    alignment = Alignment.BottomCenter
                )
                Text(
                    text = "Contact Us",
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 13.sp,
                    modifier = Modifier
                        .padding(vertical = 25.dp)
                        .padding(start = 20.dp)
                )
            }
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.dp)
        )
    }
}

@Composable
fun SettingOptionsList() {
    val headerVisible = remember { mutableStateOf(false) }
    val otherListVisible = remember { mutableStateOf(false) }
    val legalListVisible = remember { mutableStateOf(false) }

    // Delaying each component's visibility to create a sequential animation effect
    LaunchedEffect(Unit) {
        delay(300) // Increased delay for a slower start
        headerVisible.value = true
        delay(300)
        otherListVisible.value = true
        delay(300)
        legalListVisible.value = true
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        AnimatedVisibility(
            visible = headerVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing) // Slower animation duration
            ),
        ) {
            SettingHeader()
        }
        AnimatedVisibility(
            visible = otherListVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing) // Slower animation duration
            ),
        ) {
            OtherListComponent()
        }
        AnimatedVisibility(
            visible = legalListVisible.value,
            enter = slideInVertically(
                initialOffsetY = { it },
                animationSpec = tween(durationMillis = 1000, easing = LinearOutSlowInEasing) // Slower animation duration
            ),
        ) {
            LegalListComponent()
        }
    }
}
