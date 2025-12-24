package com.dilara.liquid_glass_tabbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.yourpackage.liquidglass.LiquidGlassStyle
import com.yourpackage.liquidglass.LiquidTabItem

/**
 * Ortak sabitler ve yardımcı fonksiyonlar
 */
object TabBarConstants {
    /**
     * Varsayılan tab listesi
     * GitHub için özelleştirilmiş tab'lar
     */
    val defaultTabs = listOf(
        LiquidTabItem(
            label = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7),    // Açık mavi (Home için)
            unselectedColor = Color(0xFFD3DCE6)   // Açık gri-mavi
        ),
        LiquidTabItem(
            label = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4),     // Turkuaz (Liste için)
            unselectedColor = Color(0xFFD3DCE6)   // Açık gri-mavi
        ),
        LiquidTabItem(
            label = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B),     // Gri (Ayarlar için)
            unselectedColor = Color(0xFFD3DCE6)   // Açık gri-mavi
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
     * Dark theme için Glass Style
     */
    val darkThemeStyle = LiquidGlassStyle.Default
    
    /**
     * Light theme için gradient background renkleri
     */
    val lightThemeGradientColors = listOf(
        Color(0xFFF5F5F5),  // Açık gri
        Color(0xFFFFFFFF),  // Beyaz
        Color(0xFFF0F0F0)   // Açık gri
    )
    
    /**
     * Light theme için Glass Style
     */
    val lightThemeStyle = LiquidGlassStyle(
        blurRadius = androidx.compose.ui.unit.dp(10f),
        backgroundAlpha = 0.95f,
        borderWidth = androidx.compose.ui.unit.dp(0.75f),
        borderOpacity = 0.3f, // Light theme için daha düşük opacity
        cornerRadius = androidx.compose.ui.unit.dp(999f),
        barHeight = androidx.compose.ui.unit.dp(62f),
        horizontalPadding = androidx.compose.ui.unit.dp(20f),
        topPadding = androidx.compose.ui.unit.dp(16f),
        tabSearchSpacing = androidx.compose.ui.unit.dp(16f),
        selectedTabBackgroundAlpha = 0.3f
    )
    
    /**
     * Light theme için tab renkleri (koyu renkler - açık temada görünür)
     */
    val lightThemeTabs = listOf(
        LiquidTabItem(
            label = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF1976D2),    // Koyu mavi (açık temada görünür)
            unselectedColor = Color(0xFF757575)   // Koyu gri
        ),
        LiquidTabItem(
            label = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF00796B),     // Koyu turkuaz
            unselectedColor = Color(0xFF757575)
        ),
        LiquidTabItem(
            label = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF424242),     // Koyu gri
            unselectedColor = Color(0xFF757575)
        )
    )
    
    /**
     * Özel tab renkleri örneği
     */
    val customColoredTabs = listOf(
        LiquidTabItem(
            label = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7), // Açık mavi
            unselectedColor = Color(0xFF95A5A6) // Gri
        ),
        LiquidTabItem(
            label = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4), // Turkuaz
            unselectedColor = Color(0xFF95A5A6)
        ),
        LiquidTabItem(
            label = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B), // Gri
            unselectedColor = Color(0xFF95A5A6)
        )
    )
}
