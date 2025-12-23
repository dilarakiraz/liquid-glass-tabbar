package com.dilara.liquid_glass_tabbar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilara.liquid_glass_tabbar.ui.theme.LiquidglasstabbarTheme
import com.yourpackage.liquidglass.LiquidGlassTabBar
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig
import com.yourpackage.liquidglass.models.TabItem

/**
 * LiquidGlassTabBar Preview örnekleri
 * GitHub için çeşitli kullanım senaryoları
 */

@Preview(showBackground = true, name = "1. Varsayılan (iOS Uyumlu)")
@Composable
fun DefaultTabBarPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                glassConfig = TabBarConstants.darkThemeGlassConfig,
                searchButtonGlassConfig = TabBarConstants.darkThemeGlassConfigForCircle,
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "8. 4 Tab (Search Yok)")
@Composable
fun FourTabsPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    val fourTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Favoriler",
            icon = Icons.Default.Favorite,
            selectedColor = Color(0xFFFF6B6B),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B),
            unselectedColor = Color(0xFFD3DCE6)
        )
    )
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = fourTabs,
                onTabSelected = { selectedTabState.intValue = it },
                showSearchButton = false,  // Search button yok
                glassConfig = TabBarConstants.darkThemeGlassConfig
            )
        }
    }
}

@Preview(showBackground = true, name = "9. 5 Tab (Search Yok)")
@Composable
fun FiveTabsPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    val fiveTabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Favoriler",
            icon = Icons.Default.Favorite,
            selectedColor = Color(0xFFFF6B6B),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Yıldızlı",
            icon = Icons.Default.Star,
            selectedColor = Color(0xFFFFE66D),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B),
            unselectedColor = Color(0xFFD3DCE6)
        )
    )
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = fiveTabs,
                onTabSelected = { selectedTabState.intValue = it },
                showSearchButton = false,  // Search button yok
                glassConfig = TabBarConstants.darkThemeGlassConfig
            )
        }
    }
}

@Preview(showBackground = true, name = "10. Özel Yükseklik (Kompakt)")
@Composable
fun CompactHeightPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                barHeight = 56.dp,  // Kompakt yükseklik
                glassConfig = TabBarConstants.darkThemeGlassConfig,
                searchButtonGlassConfig = TabBarConstants.darkThemeGlassConfigForCircle,
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "11. Özel Yükseklik (Geniş)")
@Composable
fun WideHeightPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                barHeight = 80.dp,  // Geniş yükseklik
                glassConfig = TabBarConstants.darkThemeGlassConfig,
                searchButtonGlassConfig = TabBarConstants.darkThemeGlassConfigForCircle,
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "14. Gradient Border (Renkli - Koyu Tema)")
@Composable
fun ColorfulGradientBorderPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                glassConfig = GlassConfig(
                    baseTint = Color(0x0DFFFFFF),      // Dark theme base tint
                    gradientTint = Color(0x990A1926),  // Dark theme gradient tint
                    overlayTint = Color(0x33000000),    // Dark theme overlay tint
                    borderWidth = 1.5.dp,  // Daha kalın border - daha belirgin
                    blurRadius = 10.dp,
                    containerOpacity = 0.95f,
                    borderGradient = BorderGradient.Linear(
                        stops = listOf(
                            0.0f to Color(0xFF82DBF7).copy(alpha = 0.8f),  // Mavi - koyu temada belirgin
                            0.33f to Color(0xFF4ECDC4).copy(alpha = 0.8f),  // Turkuaz - koyu temada belirgin
                            0.66f to Color(0xFFFF6B6B).copy(alpha = 0.8f),  // Kırmızı - koyu temada belirgin
                            1.0f to Color(0xFF82DBF7).copy(alpha = 0.8f)    // Mavi - koyu temada belirgin
                        )
                    )
                ),
                searchButtonGlassConfig = TabBarConstants.darkThemeGlassConfigForCircle,  // Koyu tema için
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}


// ========== LIGHT THEME PREVIEWS ==========

@Preview(showBackground = true, name = "19. Light Theme - Varsayılan")
@Composable
fun LightThemeDefaultPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainerLight(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.lightThemeTabs,
                onTabSelected = { selectedTabState.intValue = it },
                glassConfig = TabBarConstants.lightThemeGlassConfig,
                searchButtonGlassConfig = TabBarConstants.lightThemeGlassConfigForCircle,
                searchIcon = TabBarConstants.defaultSearchIcon,
                searchIconTint = Color(0xFF424242)  // Koyu gri - açık temada görünür
            )
        }
    }
}
