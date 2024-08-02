package com.thezayin.mehndidesign.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.thezayin.framework.extension.functions.openLink
import com.thezayin.framework.extension.functions.sendMail
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import com.thezayin.framework.utils.Constants.PRIVATE_POLICY_URL
import com.thezayin.framework.utils.Constants.TERMS_CONDITIONS_URL
import com.thezayin.mehndidesign.R

@Destination
@Composable
fun SettingScreen(
    navigator: DestinationsNavigator
) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .fillMaxSize()
            .navigationBarsPadding()
            .statusBarsPadding()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp)
                .fillMaxSize()
        ) {
            TopComponent(modifier = Modifier, navigator = navigator)
            OtherListComponent(modifier = Modifier, navigator = navigator)
            LegalListComponent(modifier = Modifier)
        }
        Text(
            text = "v ${
                context.packageManager
                    .getPackageInfo(context.packageName, 0).versionName
            }",
            modifier = Modifier
                .padding(10.dp)
                .align(Alignment.BottomCenter)
        )
    }
}

@Composable
fun LegalListComponent(modifier: Modifier) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Text(
            text = "Legal",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp),
            fontSize = 22.sp,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                colorResource(id = R.color.ed_background)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.openLink(PRIVATE_POLICY_URL)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.brown_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_privacy),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Privacy Policy", modifier = Modifier.padding(start = 40.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.grey_level_5))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.openLink(TERMS_CONDITIONS_URL)
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.yellow_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_terms),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Terms & Conditions", modifier = Modifier.padding(start = 40.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.grey_level_5))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.sendMail()
                        },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.blue_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ),
                        shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_mail),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "Contact Us", modifier = Modifier.padding(start = 40.dp))
                }
            }
        }
    }
}


@Composable
fun OtherListComponent(modifier: Modifier, navigator: DestinationsNavigator) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 20.dp)
    ) {
        Button(
            onClick = { /*TODO*/ },
            Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(horizontal = 35.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.green_level_1)
            )
        ) {
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = com.thezayin.core.R.drawable.ic_crown),
                    contentDescription = null,
                    modifier = Modifier.size(25.dp),
                )
                Text(
                    text = "Upgrade to Premium",
                    modifier = Modifier.padding(start = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = colorResource(id = R.color.white)
                )
            }
        }
        Text(
            text = "Other",
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.dp, top = 40.dp),
            fontSize = 22.sp,
            color = colorResource(id = R.color.black),
            fontWeight = FontWeight.Bold
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(10.dp),
            colors = CardDefaults.cardColors(
                colorResource(id = R.color.ed_background)
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp), colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ), shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_star),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(
                        text = "Leave a rating / review", modifier = Modifier.padding(start = 40.dp)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.grey_level_5))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp), colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.green_level_2),
                            contentColor = colorResource(id = R.color.white)
                        ), shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_about),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "More Apps", modifier = Modifier.padding(start = 40.dp))
                }
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(color = colorResource(id = R.color.grey_level_5))
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .clickable {
                            context.openLink(ABOUT_US_URL)
                        }, verticalAlignment = Alignment.CenterVertically
                ) {
                    Card(
                        modifier = Modifier.size(25.dp), colors = CardDefaults.cardColors(
                            containerColor = colorResource(id = R.color.orange_level_1),
                            contentColor = colorResource(id = R.color.white)
                        ), shape = RoundedCornerShape(5.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = com.thezayin.core.R.drawable.ic_about),
                                contentDescription = null,
                                modifier = Modifier.size(15.dp),
                                alignment = Alignment.BottomCenter
                            )
                        }
                    }
                    Text(text = "About Us", modifier = Modifier.padding(start = 40.dp))
                }
            }
        }
    }
}

@Composable
fun TopComponent(
    modifier: Modifier,
    navigator: DestinationsNavigator
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.background))
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                horizontalArrangement = Arrangement.Start
            ) {
                Image(painter = painterResource(id = com.thezayin.core.R.drawable.ic_back),
                    contentDescription = null,
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            navigator.navigateUp()
                        }
                )
            }
            Card(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .size(100.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = null,
                    contentScale = ContentScale.FillBounds
                )
            }
            Text(
                text = "Mehndi Designs and Ideas",
                modifier = Modifier.padding(top = 10.dp),
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
            )
        }
    }
}