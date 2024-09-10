package com.thezayin.mehndidesign.navigation

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut

fun scaleIntoContainer(): EnterTransition {
    return scaleIn(
        animationSpec = tween(durationMillis = 440, delayMillis = 180),
        initialScale = 1.1f // Slightly larger scale for zoom-in effect
    ) + fadeIn(animationSpec = tween(440, delayMillis = 180))
}

fun scaleOutOfContainer(): ExitTransition {
    return scaleOut(
        animationSpec = tween(
            durationMillis = 440, delayMillis = 180
        ), targetScale = 0.9f // Slightly smaller scale for zoom-out effect
    ) + fadeOut(animationSpec = tween(440, delayMillis = 180))
}