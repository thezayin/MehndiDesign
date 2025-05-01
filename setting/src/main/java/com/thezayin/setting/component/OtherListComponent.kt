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
import com.thezayin.framework.utils.Constants.ABOUT_US_URL
import com.thezayin.values.R
import ir.kaaveh.sdpcompose.sdp
import ir.kaaveh.sdpcompose.ssp

@Preview
@Composable
fun OtherListComponent() {
    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 20.sdp, horizontal = 10.sdp)
    ) {
        Text(
            text = stringResource(id = R.string.others),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 5.sdp),
            fontSize = 10.ssp,
            fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
            color = colorResource(id = R.color.text_color),
        )

        Card(
            shape = RoundedCornerShape(10.sdp), colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_star),
                    contentDescription = "",
                    modifier = Modifier.size(14.sdp),
                    alignment = Alignment.Center
                )
                Text(
                    text = stringResource(id = R.string.leave_rating_review),
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier.padding(start = 20.sdp)
                )
            }
        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(5.sdp)
        )

        Card(
            shape = RoundedCornerShape(10.sdp), colors = CardDefaults.cardColors(
                containerColor = colorResource(id = R.color.card_background)
            ), onClick = {
                context.openLink(ABOUT_US_URL)
            }) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.sdp, vertical = 12.sdp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_profile),
                    contentDescription = "",
                    modifier = Modifier.size(12.sdp),
                    alignment = Alignment.Center
                )
                Text(
                    text = stringResource(id = R.string.about_us),
                    color = colorResource(id = R.color.text_color),
                    fontFamily = FontFamily(Font(R.font.noto_sans_regular)),
                    fontSize = 8.ssp,
                    modifier = Modifier.padding(start = 20.sdp)
                )
            }
        }
    }
}
