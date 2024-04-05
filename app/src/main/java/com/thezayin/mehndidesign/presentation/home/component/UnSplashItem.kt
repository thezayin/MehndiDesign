package com.thezayin.mehndidesign.presentation.home.component

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.thezayin.mehndidesign.R
import com.thezayin.mehndidesign.domain.model.UnsplashImage

@Composable
fun UnSplashItem(unsplashImage: UnsplashImage?) {
    val bitmapState = remember { mutableStateOf<Bitmap?>(null) }
    Glide.with(LocalContext.current).asBitmap().load(unsplashImage?.urls?.small).into(
        object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                bitmapState.value = resource
            }

            override fun onLoadCleared(placeholder: Drawable?) {}
        }
    )

    val context = LocalContext.current
    Card(
        modifier = Modifier
            .clickable {
                val browserIntent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("https://unsplash.com/@${unsplashImage?.user?.userLinks}?utm_source=MehndiDesign&utm_medium=referral")
                )
                startActivity(context, browserIntent, null)
            }
            .wrapContentHeight()
            .fillMaxWidth()
            .padding(horizontal = 1.dp, vertical = 2.dp),
        shape = RoundedCornerShape(10.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        ) {
            bitmapState.value?.let {
                Image(
                    bitmap = it.asImageBitmap(),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth(),
                    contentScale = ContentScale.Crop
                )
            }

            Surface(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp),
                color = colorResource(id = R.color.color_mid)
            ) {}
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                    .padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Photo by ")
                        withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                            append(unsplashImage?.user?.username)
                        }
                        append(" on Unsplash")
                    },
                    color = Color.White,
                    fontSize = 8.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                LikeCounter(
                    modifier = Modifier.weight(1f),
                    painter = painterResource(id = R.drawable.ic_heart),
                    likes = "${unsplashImage?.likes}"
                )
            }
        }
    }
}