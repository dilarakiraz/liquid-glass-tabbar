package com.yourpackage.liquidglass.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Glass efekt yapılandırması
 * 
 * iOS'taki BlurView ve gradient border ayarlarını kontrol eder
 */
data class GlassConfig(
    /**
     * Base tint color (varsayılan: Transparent)
     */
    val baseTint: Color = Color.Transparent,
    
    /**
     * Gradient tint color (varsayılan: 0xABFFFFFF)
     * iOS'taki BlurView'ın gradient overlay'i için
     * iOS spesifikasyonuna göre
     */
    val gradientTint: Color = Color(0xABFFFFFF),
    
    /**
     * Overlay tint color (varsayılan: Transparent)
     */
    val overlayTint: Color = Color.Transparent,
    
    /**
     * Border genişliği
     * iOS spesifikasyonuna göre:
     * Tab buttons için: 0.8dp (iOS: 0.8px)
     * Search button için: 1.dp (iOS: 1px)
     */
    val borderWidth: Dp = 0.8.dp,
    
    /**
     * Border gradient yapılandırması
     */
    val borderGradient: BorderGradient = BorderGradient.default(),
    
    /**
     * Blur radius (Haze için)
     * iOS'taki systemUltraThinMaterial'a eşdeğer
     */
    val blurRadius: Dp = 7.dp,
    
    /**
     * Container opacity (iOS: 0.99)
     */
    val containerOpacity: Float = 0.99f,
    
    /**
     * Seçili tab background rengi
     * null ise otomatik hesaplanır (primaryBackground + blendMode simülasyonu)
     */
    val selectedTabBackground: Color? = null,
    
    /**
     * Seçili tab background alpha değeri
     * iOS'taki blendMode(.plusLighter) efektini simüle eder
     */
    val selectedTabBackgroundAlpha: Float = 0.24f
) {
    companion object {
        /**
         * Varsayılan config (Tab buttons için)
         */
        fun default() = GlassConfig()
        
        /**
         * Search button için config
         */
        fun forCircle() = GlassConfig(
            borderWidth = 1.dp,
            borderGradient = BorderGradient.defaultForCircle()
        )
        
        /**
         * Özelleştirilebilir border ile config
         * 
         * @param borderWidth Border genişliği (varsayılan: 0.5.dp)
         * @param maxOpacity Border'ın maksimum opacity değeri (varsayılan: 0.5f)
         * @param minOpacity Border'ın minimum opacity değeri (varsayılan: 0.05f)
         * @param midOpacity Border'ın orta kısım opacity değeri (varsayılan: 0.2f)
         */
        fun withCustomBorder(
            borderWidth: Dp = 0.5.dp,
            maxOpacity: Float = 0.5f,
            minOpacity: Float = 0.05f,
            midOpacity: Float = 0.2f
        ) = GlassConfig(
            borderWidth = borderWidth,
            borderGradient = BorderGradient.custom(
                maxOpacity = maxOpacity,
                minOpacity = minOpacity,
                midOpacity = midOpacity
            )
        )
        
        /**
         * Özelleştirilebilir border ile config (Circle için)
         */
        fun withCustomBorderForCircle(
            borderWidth: Dp = 0.6.dp,
            maxOpacity: Float = 0.7f,
            minOpacity: Float = 0.05f,
            midOpacity: Float = 0.2f
        ) = GlassConfig(
            borderWidth = borderWidth,
            borderGradient = BorderGradient.customForCircle(
                maxOpacity = maxOpacity,
                minOpacity = minOpacity,
                midOpacity = midOpacity
            )
        )
    }
}

