package com.project.taskmanager.common

import androidx.annotation.RawRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.airbnb.lottie.compose.*
import com.project.taskmanager.R

@Composable
fun AnimatedPreloader(
    modifier: Modifier = Modifier,
    @RawRes rawResId: Int,
    iterations: Int = LottieConstants.IterateForever,
    isPlaying: Boolean = true
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(rawResId)
    )

    val preloaderProgress by animateLottieCompositionAsState(
        preloaderLottieComposition,
        iterations = iterations,
        isPlaying = isPlaying
    )

    LottieAnimation(
        composition = preloaderLottieComposition,
        progress = preloaderProgress,
        modifier = modifier
    )
}
