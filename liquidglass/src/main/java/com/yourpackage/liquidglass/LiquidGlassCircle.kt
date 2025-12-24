package com.yourpackage.liquidglass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect

/**
 * Internal: Glass efektli dairesel container
 * iOS'taki searchButtonBackground'ın Android eşdeğeri
 * 
 * @param hazeState Blur efekti için HazeState
 * @param modifier Compose modifier
 * @param glassConfig Glass efekt yapılandırması (varsayılan: GlassConfig.forCircle())
 * @param content İçerik
 */
@Composable
internal fun LiquidGlassCircle(
    hazeState: HazeState? = null,
    modifier: Modifier = Modifier,
    glassConfig: GlassConfig = GlassConfig.forCircle(),
    content: @Composable (() -> Unit)? = null,
) {
    var size by remember { mutableStateOf(IntSize.Zero) }
    val width = size.width.toFloat()
    val height = size.height.toFloat()
    
    val gradientEnd = when (val gradient = glassConfig.borderGradient) {
        is BorderGradient.Linear -> {
            gradient.end ?: Offset(width, height)
        }
        else -> Offset.Zero
    }
    
    val borderBrush = when (val gradient = glassConfig.borderGradient) {
        is BorderGradient.Linear -> {
            Brush.linearGradient(
                colorStops = gradient.stops.toTypedArray(),
                start = gradient.start,
                end = gradientEnd
            )
        }
        is BorderGradient.Sweep -> {
            Brush.sweepGradient(
                colorStops = gradient.stops.toTypedArray(),
                center = androidx.compose.ui.geometry.Offset(width / 2f, height / 2f)
            )
        }
    }
    
    Box(
        modifier = modifier
            .onSizeChanged { size = it }  // Boyut değişikliklerini yakala
                // iOS'taki cam efekt hissi için hafif shadow/glow
                .shadow(
                    elevation = 2.dp,
                    shape = CircleShape,
                    ambientColor = Color.White.copy(alpha = 0.1f),
                    spotColor = Color.White.copy(alpha = 0.05f)
                )
                .clip(CircleShape)
                .background(
                    color = glassConfig.baseTint,
                    shape = CircleShape
                )
                // Gradient tint layer (iOS'taki BlurView gradient overlay)
                // iOS tasarımına daha yakın olması için alpha optimize edildi
                .background(
                    brush = Brush.sweepGradient(
                        0.1f to glassConfig.gradientTint,
                        0.3f to glassConfig.gradientTint,
                        0.7f to glassConfig.gradientTint,
                        1.0f to glassConfig.gradientTint,
                    ),
                    alpha = 0.5f,  // Daha şeffaf (iOS'a daha yakın)
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

