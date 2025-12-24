package com.yourpackage.liquidglass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig
import com.yourpackage.liquidglass.models.SpacingConfig

/**
 * Public API: Style configuration for LiquidGlassTabBar
 * 
 * Provides a clean, SDK-friendly way to customize the tab bar appearance.
 * Internally converts to [GlassConfig] and [SpacingConfig].
 */
public data class LiquidGlassStyle(
    /**
     * Blur radius for the glass effect
     */
    val blurRadius: Dp = 10.dp,
    
    /**
     * Background opacity (0.0 - 1.0)
     */
    val backgroundAlpha: Float = 0.95f,
    
    /**
     * Border width
     */
    val borderWidth: Dp = 0.75.dp,
    
    /**
     * Border opacity (0.0 - 1.0)
     * Controls the intensity of the gradient border
     */
    val borderOpacity: Float = 0.5f,
    
    /**
     * Corner radius (999.dp = pill shape)
     */
    val cornerRadius: Dp = 999.dp,
    
    /**
     * Tab bar height
     */
    val barHeight: Dp = 62.dp,
    
    /**
     * Horizontal padding
     */
    val horizontalPadding: Dp = 20.dp,
    
    /**
     * Top padding
     */
    val topPadding: Dp = 16.dp,
    
    /**
     * Spacing between tab container and search button
     */
    val tabSearchSpacing: Dp = 16.dp,
    
    /**
     * Selected tab background alpha
     */
    val selectedTabBackgroundAlpha: Float = 0.3f,
    
    /**
     * Selected tab background color (null = auto)
     */
    val selectedTabBackground: Color? = null
) {
    public companion object {
        /**
         * Default iOS-style liquid glass effect
         */
        public val Default: LiquidGlassStyle = ios()
        
        /**
         * iOS-style liquid glass effect
         */
        public fun ios(): LiquidGlassStyle = LiquidGlassStyle(
            blurRadius = 10.dp,
            backgroundAlpha = 0.95f,
            borderWidth = 0.75.dp,
            borderOpacity = 0.5f,
            cornerRadius = 999.dp,
            barHeight = 62.dp,
            horizontalPadding = 20.dp,
            topPadding = 16.dp,
            tabSearchSpacing = 16.dp,
            selectedTabBackgroundAlpha = 0.3f
        )
        
        /**
         * Neon-style with stronger blur and brighter borders
         */
        public fun neon(): LiquidGlassStyle = LiquidGlassStyle(
            blurRadius = 15.dp,
            backgroundAlpha = 0.8f,
            borderWidth = 1.dp,
            borderOpacity = 0.8f,
            cornerRadius = 999.dp,
            barHeight = 62.dp,
            horizontalPadding = 20.dp,
            topPadding = 16.dp,
            tabSearchSpacing = 16.dp,
            selectedTabBackgroundAlpha = 0.4f
        )
        
        /**
         * Minimal style with subtle effects
         */
        public fun minimal(): LiquidGlassStyle = LiquidGlassStyle(
            blurRadius = 5.dp,
            backgroundAlpha = 0.9f,
            borderWidth = 0.5.dp,
            borderOpacity = 0.3f,
            cornerRadius = 999.dp,
            barHeight = 56.dp,
            horizontalPadding = 20.dp,
            topPadding = 16.dp,
            tabSearchSpacing = 16.dp,
            selectedTabBackgroundAlpha = 0.2f
        )
    }
    
    /**
     * Internal: Convert to GlassConfig
     */
    internal fun toGlassConfig(): GlassConfig {
        val borderGradient = BorderGradient.custom(
            maxOpacity = borderOpacity,
            minOpacity = borderOpacity * 0.1f,
            midOpacity = borderOpacity * 0.4f
        )
        
        // Dark theme için eski değerleri koru (iOS uyumlu)
        return GlassConfig(
            baseTint = Color(0x0DFFFFFF),      // Base tint (%5 beyaz)
            gradientTint = Color(0x990A1926),  // Gradient tint (%60 koyu gri-mavi)
            overlayTint = Color(0x33000000),    // Overlay tint (%20 siyah)
            blurRadius = blurRadius,
            containerOpacity = backgroundAlpha,
            borderWidth = borderWidth,
            borderGradient = borderGradient,
            selectedTabBackgroundAlpha = selectedTabBackgroundAlpha,
            selectedTabBackground = selectedTabBackground
        )
    }
    
    /**
     * Internal: Convert to SpacingConfig
     */
    internal fun toSpacingConfig(): SpacingConfig {
        return SpacingConfig(
            horizontalPadding = horizontalPadding,
            topPadding = topPadding,
            tabSearchSpacing = tabSearchSpacing
        )
    }
    
    /**
     * Internal: Convert to GlassConfig for circle (search button)
     */
    internal fun toGlassConfigForCircle(): GlassConfig {
        val borderGradient = BorderGradient.customForCircle(
            maxOpacity = borderOpacity * 1.4f,
            minOpacity = borderOpacity * 0.1f,
            midOpacity = borderOpacity * 0.4f
        )
        
        // Dark theme için eski değerleri koru (iOS uyumlu)
        return GlassConfig(
            baseTint = Color(0x0DFFFFFF),      // Base tint (%5 beyaz)
            gradientTint = Color(0x990A1926),  // Gradient tint (%60 koyu gri-mavi)
            overlayTint = Color(0x33000000),    // Overlay tint (%20 siyah)
            borderWidth = borderWidth + 0.25.dp, // Slightly thicker for circle
            blurRadius = blurRadius,
            containerOpacity = backgroundAlpha,
            borderGradient = borderGradient,
            selectedTabBackgroundAlpha = selectedTabBackgroundAlpha,
            selectedTabBackground = selectedTabBackground
        )
    }
}

