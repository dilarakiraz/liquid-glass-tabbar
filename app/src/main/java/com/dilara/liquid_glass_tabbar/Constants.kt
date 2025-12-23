package com.dilara.liquid_glass_tabbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
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
     * GitHub için özelleştirilmiş tab'lar
     */
    val defaultTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7),    // Açık mavi (Home için)
            unselectedColor = Color(0xFFD3DCE6)   // Açık gri-mavi
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4),     // Turkuaz (Liste için)
            unselectedColor = Color(0xFFD3DCE6)   // Açık gri-mavi
        ),
        TabItem(
            title = "Ayarlar",
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
     * Light theme için gradient background renkleri
     */
    val lightThemeGradientColors = listOf(
        Color(0xFFF5F5F5),  // Açık gri
        Color(0xFFFFFFFF),  // Beyaz
        Color(0xFFF0F0F0)   // Açık gri
    )
    
    /**
     * Light theme için Glass Config
     * Açık tema için koyu border ve renkler
     */
    val lightThemeGlassConfig = com.yourpackage.liquidglass.models.GlassConfig(
        baseTint = Color(0x0D000000),      // Base tint (%5 siyah)
        gradientTint = Color(0x99FFFFFF),  // Gradient tint (%60 beyaz)
        overlayTint = Color(0x33FFFFFF),    // Overlay tint (%20 beyaz)
        containerOpacity = 0.95f,
        borderGradient = com.yourpackage.liquidglass.models.BorderGradient.Linear(
            stops = listOf(
                0.0f to Color.Black.copy(alpha = 0.3f),   // Koyu border (açık temada görünür)
                0.2f to Color.Black.copy(alpha = 0.15f),
                0.4f to Color.Black.copy(alpha = 0.08f),
                0.6f to Color.Black.copy(alpha = 0.08f),
                0.8f to Color.Black.copy(alpha = 0.15f),
                1.0f to Color.Black.copy(alpha = 0.3f)
            )
        )
    )
    
    /**
     * Light theme için Search Button Glass Config
     */
    val lightThemeGlassConfigForCircle = com.yourpackage.liquidglass.models.GlassConfig(
        baseTint = Color(0x0D000000),
        gradientTint = Color(0x99FFFFFF),
        overlayTint = Color(0x33FFFFFF),
        borderWidth = 1.dp,
        containerOpacity = 0.95f,
        borderGradient = com.yourpackage.liquidglass.models.BorderGradient.Linear(
            stops = listOf(
                0.0f to Color.Black.copy(alpha = 0.4f),   // Koyu border
                0.2f to Color.Black.copy(alpha = 0.15f),
                0.4f to Color.Black.copy(alpha = 0.08f),
                0.6f to Color.Black.copy(alpha = 0.08f),
                0.8f to Color.Black.copy(alpha = 0.15f),
                1.0f to Color.Black.copy(alpha = 0.4f)
            )
        )
    )
    
    /**
     * Light theme için tab renkleri (koyu renkler - açık temada görünür)
     */
    val lightThemeTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF1976D2),    // Koyu mavi (açık temada görünür)
            unselectedColor = Color(0xFF757575)   // Koyu gri
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF00796B),     // Koyu turkuaz
            unselectedColor = Color(0xFF757575)
        ),
        TabItem(
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF424242),     // Koyu gri
            unselectedColor = Color(0xFF757575)
        )
    )
    
    /**
     * Özel tab renkleri örneği
     */
    val customColoredTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7), // Açık mavi
            unselectedColor = Color(0xFF95A5A6) // Gri
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4), // Turkuaz
            unselectedColor = Color(0xFF95A5A6)
        ),
        TabItem(
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B), // Gri
            unselectedColor = Color(0xFF95A5A6)
        )
    )
}

