package com.thezayin.setting.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import com.thezayin.framework.extension.openLink
import com.thezayin.framework.extension.sendMail
import com.thezayin.framework.utils.Constants.PRIVATE_POLICY_URL
import com.thezayin.framework.utils.Constants.TERMS_CONDITIONS_URL
import com.thezayin.values.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun LegalListComponent() {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.sdp, horizontal = 10.sdp)
    ) {
        Text(
            text = stringResource(id = R.string.legal),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.sdp),
            fontSize = 10.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(id = R.color.text_color),
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
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
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_privacy),
                    contentDescription = stringResource(id = R.string.privacy_policy_icon),
                    modifier = Modifier.size(12.sdp),
                    alignment = Alignment.Center
                )
                Text(
                    text = stringResource(id = R.string.privacy_policy),
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier
                        .padding(start = 20.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
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
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_terms),
                    contentDescription = stringResource(id = R.string.terms_conditions_icon),
                    modifier = Modifier.size(12.sdp),
                    alignment = Alignment.Center
                )
                Text(
                    text = stringResource(id = R.string.terms_conditions),
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier
                        .padding(start = 20.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp),
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
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_mail),
                    contentDescription = stringResource(id = R.string.contact_us_icon),
                    modifier = Modifier.size(12.sdp),
                    alignment = Alignment.Center
                )
                Text(
                    text = stringResource(id = R.string.contact_us),
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier
                        .padding(start = 20.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )
    }
}
