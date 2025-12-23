package com.yourpackage.liquidglass

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.HazeStyle
import dev.chrisbanes.haze.HazeTint
import dev.chrisbanes.haze.hazeEffect
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig

/**
 * Glass efektli dikdörtgen container
 * iOS'taki tabButtonsBackground'ın Android eşdeğeri
 * 
 * @param hazeState Blur efekti için HazeState (null ise blur uygulanmaz)
 * @param modifier Compose modifier
 * @param shape Container şekli (varsayılan: RoundedCornerShape(999.dp) - pill shape)
 * @param glassConfig Glass efekt yapılandırması
 * @param content İçerik
 */
@Composable
fun LiquidGlassRectangle(
    hazeState: HazeState? = null,
    modifier: Modifier = Modifier,
    shape: Shape = androidx.compose.foundation.shape.RoundedCornerShape(999.dp),
    glassConfig: GlassConfig = GlassConfig.default(),
    content: @Composable (() -> Unit)? = null,
) {
    BoxWithConstraints(modifier = modifier) {
        val width = constraints.maxWidth.toFloat()
        val height = constraints.maxHeight.toFloat()
        
        // Linear gradient için end offset hesaplama
        val gradientEnd = when (val gradient = glassConfig.borderGradient) {
            is BorderGradient.Linear -> {
                gradient.end ?: Offset(width, height) // null ise bottomTrailing
            }
            else -> Offset.Zero
        }
        
        // Border brush oluşturma
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
                .clip(shape)
                // Base tint layer
                .background(
                    color = glassConfig.baseTint,
                    shape = shape
                )
                // Gradient tint layer (iOS'taki BlurView gradient overlay)
                .background(
                    brush = Brush.sweepGradient(
                        0.1f to glassConfig.gradientTint,
                        0.3f to glassConfig.gradientTint,
                        0.7f to glassConfig.gradientTint,
                        1.0f to glassConfig.gradientTint,
                    ),
                    alpha = 0.67f,
                    shape = shape
                )
                // Overlay tint layer
                .background(
                    color = glassConfig.overlayTint,
                    shape = shape
                )
                // Haze blur effect (iOS'taki BlurView eşdeğeri)
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
                    shape = shape
                ),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            content?.invoke()
        }
    }
}

