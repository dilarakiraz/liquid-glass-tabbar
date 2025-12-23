package com.yourpackage.liquidglass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig

/**
 * Glass efektli dairesel container
 * iOS'taki searchButtonBackground'ın Android eşdeğeri
 * 
 * @param hazeState Blur efekti için HazeState
 * @param modifier Compose modifier
 * @param glassConfig Glass efekt yapılandırması (varsayılan: GlassConfig.forCircle())
 * @param content İçerik
 */
@Composable
fun LiquidGlassCircle(
    hazeState: HazeState? = null,
    modifier: Modifier = Modifier,
    glassConfig: GlassConfig = GlassConfig.forCircle(),
    content: @Composable (() -> Unit)? = null,
) {
    BoxWithConstraints(modifier = modifier) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        
        val gradientEnd = when (val gradient = glassConfig.borderGradient) {
            is BorderGradient.Linear -> {
                gradient.end ?: Offset(width, height)
            }
            else -> Offset.Zero
        }
        
        val borderBrush = when (glassConfig.borderGradient) {
            is BorderGradient.Linear -> {
                Brush.linearGradient(
                    colorStops = glassConfig.borderGradient.stops.toTypedArray(),
                    start = glassConfig.borderGradient.start,
                    end = gradientEnd
                )
            }
            is BorderGradient.Sweep -> {
                Brush.sweepGradient(
                    colorStops = glassConfig.borderGradient.stops.toTypedArray(),
                    center = androidx.compose.ui.geometry.Offset(width / 2f, height / 2f)
                )
            }
        }
        
        Box(
            modifier = modifier
                .clip(CircleShape)
                .background(
                    color = glassConfig.baseTint,
                    shape = CircleShape
                )
                .background(
                    brush = Brush.sweepGradient(
                        0.1f to glassConfig.gradientTint,
                        0.3f to glassConfig.gradientTint,
                        0.7f to glassConfig.gradientTint,
                        1.0f to glassConfig.gradientTint,
                    ),
                    alpha = 0.67f,
                    shape = CircleShape
                )
                .background(
                    color = glassConfig.overlayTint,
                    shape = CircleShape
                )
                .then(
                    if (hazeState == null) {
                        Modifier
                    } else {
                        Modifier.hazeEffect(
                            state = hazeState,
                            style = HazeStyle(
                                backgroundColor = androidx.compose.ui.graphics.Color.Transparent,
                                blurRadius = glassConfig.blurRadius,
                                tint = HazeTint(glassConfig.gradientTint)
                            )
                        )
                    }
                )
                // Container opacity (iOS: 0.99)
                .alpha(glassConfig.containerOpacity)
                // Gradient border (iOS'taki strokeBorder eşdeğeri)
                // Border en son ekleniyor ki görünür olsun
                .border(
                    width = glassConfig.borderWidth,
                    brush = borderBrush,
                    shape = CircleShape
                ),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            content?.invoke()
        }
    }
}

