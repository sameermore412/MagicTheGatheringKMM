package com.more.mtg.sharedui.widgets

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.cos
import kotlin.math.sin

val radiansList = listOf(180f, 252f, 108f, 324f, 36f).map { it.toRadians() }
val trigList = radiansList.map { radians ->  Pair(cos(radians), sin(radians)) }

@Composable
fun MagicLoadingIndicator(modifier: Modifier = Modifier, size: Dp = 75.dp) {
    val infiniteTransition = rememberInfiniteTransition(label = "")
    val rotation by infiniteTransition.animateFloat(initialValue = 0f, targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ), label = ""
    )

    Canvas(modifier = modifier
        .size(size)
        .graphicsLayer {
            rotationZ = rotation
        }) {
        val (width) = this.size
        val radius = width * .1f
        val getCenter = getCenterFunc(this.center, width * .4f)

        drawCircle(
            color = Color.White,
            center = getCenter(trigList[0]),
            radius = radius)
        drawCircle(
            color = Color.Green,
            center = getCenter(trigList[1]),
            radius = radius)
        drawCircle(
            color = Color.Blue,
            center = getCenter(trigList[2]),
            radius = radius)
        drawCircle(
            color = Color.Red,
            center = getCenter(trigList[3]),
            radius = radius)
        drawCircle(
            color = Color.Black,
            center = getCenter(trigList[4]),
            radius = radius)
    }
}

private fun getCenterFunc(center: Offset, radius: Float): (trigPair: Pair<Float, Float>) -> Offset {
    return { trigPair ->
        val y = (radius * trigPair.first) + center.y
        val x = (radius * trigPair.second) + center.x
        Offset(x, y)
    }
}

fun Float.toRadians() = (this * (kotlin.math.PI/180)).toFloat()