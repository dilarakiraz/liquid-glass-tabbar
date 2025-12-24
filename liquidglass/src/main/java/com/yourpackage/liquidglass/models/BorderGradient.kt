package com.yourpackage.liquidglass.models

import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

/**
 * Internal: Border gradient tipi
 * 
 * Linear: Çapraz geçiş (iOS ile uyumlu)
 * Sweep: Dairesel geçiş (alternatif)
 */
internal sealed class BorderGradient {
    /**
     * Linear gradient - iOS ile aynı çapraz geçiş efekti
     * 
     * @param stops Gradient renk durakları (location, color) çiftleri
     * @param start Başlangıç noktası (varsayılan: Offset(0f, 0f) - topLeading)
     * @param end Bitiş noktası (null ise container boyutuna göre hesaplanır - bottomTrailing)
     */
    data class Linear(
        val stops: List<Pair<Float, Color>>,
        val start: Offset = Offset(0f, 0f),
        val end: Offset? = null
    ) : BorderGradient()
    
    /**
     * Sweep gradient - Dairesel geçiş
     */
    data class Sweep(
        val stops: List<Pair<Float, Color>>
    ) : BorderGradient()
    
    companion object {
        /**
         * iOS Tab Buttons için varsayılan gradient
         * iOS spesifikasyonuna göre tam değerler
         * iOS tasarımına daha yakın olması için optimize edildi
         */
        fun default() = Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = 0.5f),   // Sol üst - çok yoğun
                0.2f to Color.White.copy(alpha = 0.2f),   // Hızlı azalma
                0.4f to Color.White.copy(alpha = 0.05f),  // Çok ince (iOS ile aynı)
                0.6f to Color.White.copy(alpha = 0.05f),  // Çok ince (iOS ile aynı)
                0.8f to Color.White.copy(alpha = 0.2f),   // Artış başlangıcı
                1.0f to Color.White.copy(alpha = 0.5f)   // Sağ alt - yoğun
            )
        )
        
        /**
         * iOS Search Button için varsayılan gradient
         * iOS spesifikasyonuna göre tam değerler
         * iOS tasarımına daha yakın olması için optimize edildi
         */
        fun defaultForCircle() = Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = 0.7f),   // Sol üst - çok yoğun
                0.2f to Color.White.copy(alpha = 0.2f),   // Hızlı azalma
                0.4f to Color.White.copy(alpha = 0.05f),  // Çok ince (iOS ile aynı)
                0.6f to Color.White.copy(alpha = 0.05f),  // Çok ince (iOS ile aynı)
                0.8f to Color.White.copy(alpha = 0.2f),   // Artış başlangıcı
                1.0f to Color.White.copy(alpha = 0.8f)    // Sağ alt - yoğun
            )
        )
        
        /**
         * Özelleştirilebilir border gradient oluşturma helper'ı
         * 
         * @param maxOpacity Maksimum opacity değeri (varsayılan: 0.5)
         * @param minOpacity Minimum opacity değeri (varsayılan: 0.05)
         * @param midOpacity Orta kısım opacity değeri (varsayılan: 0.2)
         */
        fun custom(
            maxOpacity: Float = 0.5f,
            minOpacity: Float = 0.05f,
            midOpacity: Float = 0.2f
        ) = Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = maxOpacity),
                0.2f to Color.White.copy(alpha = midOpacity),
                0.4f to Color.White.copy(alpha = minOpacity),
                0.6f to Color.White.copy(alpha = minOpacity),
                0.8f to Color.White.copy(alpha = midOpacity),
                1.0f to Color.White.copy(alpha = maxOpacity)
            )
        )
        
        /**
         * Özelleştirilebilir border gradient (Circle için)
         */
        fun customForCircle(
            maxOpacity: Float = 0.7f,
            minOpacity: Float = 0.05f,
            midOpacity: Float = 0.2f
        ) = Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = maxOpacity),
                0.2f to Color.White.copy(alpha = midOpacity),
                0.4f to Color.White.copy(alpha = minOpacity),
                0.6f to Color.White.copy(alpha = minOpacity),
                0.8f to Color.White.copy(alpha = midOpacity),
                1.0f to Color.White.copy(alpha = maxOpacity * 1.1f) // Biraz daha yoğun
            )
        )
    }
}

