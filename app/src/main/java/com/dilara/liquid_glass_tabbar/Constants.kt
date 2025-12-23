package com.dilara.liquid_glass_tabbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.yourpackage.liquidglass.models.TabItem

/**
 * Ortak sabitler ve yardımcı fonksiyonlar
 */
object TabBarConstants {
    /**
     * Varsayılan tab listesi
     */
    val defaultTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home
        ),
        TabItem(
            title = "Piyasalar",
            icon = Icons.Default.BarChart
        ),
        TabItem(
            title = "Keşfet",
            icon = Icons.Default.Explore
        )
    )
    
    /**
     * Varsayılan search icon
     */
    val defaultSearchIcon: ImageVector = Icons.Default.Search
    
    /**
     * Preview için gradient background renkleri
     * Dark theme renkleri
     */
    val previewGradientColors = listOf(
        Color(0xFF171C24),  // Primary background - Ana arkaplan (çok koyu gri-mavi)
        Color(0xFF1D242E),  // Secondary background - İkincil arkaplan
        Color(0xFF252D36)   // Tertiary background - Üçüncül arkaplan
    )
    
    /**
     * Dark theme için Glass Config
     * Şeffaf görünüm için opacity değerleri optimize edildi
     */
    val darkThemeGlassConfig = com.yourpackage.liquidglass.models.GlassConfig(
        baseTint = Color(0x0DFFFFFF),      // Base tint (%5 beyaz)
        gradientTint = Color(0x990A1926),  // Gradient tint (%60 koyu gri-mavi)
        overlayTint = Color(0x33000000),    // Overlay tint (%20 siyah)
        containerOpacity = 0.95f,           // Container opacity (iOS: 0.99, biraz daha şeffaf)
        borderGradient = com.yourpackage.liquidglass.models.BorderGradient.Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = 0.5f),   // borderStrong %50 opacity
                0.2f to Color.White.copy(alpha = 0.2f),   // borderSoft %20 opacity
                0.4f to Color.White.copy(alpha = 0.05f),   // borderSoft %5 opacity
                0.6f to Color.White.copy(alpha = 0.05f),   // borderSoft %5 opacity
                0.8f to Color.White.copy(alpha = 0.2f),   // borderSoft %20 opacity
                1.0f to Color.White.copy(alpha = 0.5f)    // borderStrong %50 opacity
            )
        )
    )
    
    /**
     * Dark theme için Search Button Glass Config
     * Şeffaf görünüm için opacity değerleri optimize edildi
     */
    val darkThemeGlassConfigForCircle = com.yourpackage.liquidglass.models.GlassConfig(
        baseTint = Color(0x0DFFFFFF),
        gradientTint = Color(0x990A1926),
        overlayTint = Color(0x33000000),
        borderWidth = 1.dp,
        containerOpacity = 0.95f,           // Container opacity (iOS: 0.99, biraz daha şeffaf)
        borderGradient = com.yourpackage.liquidglass.models.BorderGradient.Linear(
            stops = listOf(
                0.0f to Color.White.copy(alpha = 0.7f),   // borderStrong %70 opacity
                0.2f to Color.White.copy(alpha = 0.2f),   // borderSoft %20 opacity
                0.4f to Color.White.copy(alpha = 0.05f),   // borderSoft %5 opacity
                0.6f to Color.White.copy(alpha = 0.05f),   // borderSoft %5 opacity
                0.8f to Color.White.copy(alpha = 0.2f),   // borderSoft %20 opacity
                1.0f to Color.White.copy(alpha = 0.8f)    // borderStrong %80 opacity
            )
        )
    )
    
    /**
     * Özel tab renkleri örneği
     */
    val customColoredTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFFFF6B6B), // Kırmızı
            unselectedColor = Color(0xFF95A5A6) // Gri
        ),
        TabItem(
            title = "Piyasalar",
            icon = Icons.Default.BarChart,
            selectedColor = Color(0xFF4ECDC4), // Turkuaz
            unselectedColor = Color(0xFF95A5A6)
        ),
        TabItem(
            title = "Keşfet",
            icon = Icons.Default.Explore,
            selectedColor = Color(0xFFFFE66D), // Sarı
            unselectedColor = Color(0xFF95A5A6)
        )
    )
}

