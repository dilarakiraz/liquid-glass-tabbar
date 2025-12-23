package com.dilara.liquid_glass_tabbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilara.liquid_glass_tabbar.ui.theme.LiquidglasstabbarTheme
import com.yourpackage.liquidglass.LiquidGlassTabBar
import com.yourpackage.liquidglass.models.BorderGradient
import com.yourpackage.liquidglass.models.GlassConfig
import com.yourpackage.liquidglass.models.SpacingConfig
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.layout.PaddingValues

/**
 * LiquidGlassTabBar Preview örnekleri
 */

@Preview(showBackground = true, name = "Varsayılan (iOS Uyumlu)")
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
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "Özel Glass Config")
@Composable
fun CustomGlassConfigPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                glassConfig = GlassConfig(
                    borderWidth = 1.dp,
                    blurRadius = 10.dp,
                    containerOpacity = 0.95f,
                    borderGradient = BorderGradient.Linear(
                        stops = listOf(
                            0.0f to Color.White.copy(alpha = 0.8f),
                            0.5f to Color.Transparent,
                            1.0f to Color.White.copy(alpha = 0.8f)
                        )
                    )
                ),
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "Özel Tab Renkleri")
@Composable
fun CustomTabColorsPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.customColoredTabs,
                onTabSelected = { selectedTabState.intValue = it },
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "Search Button Olmadan")
@Composable
fun WithoutSearchButtonPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                showSearchButton = false
            )
        }
    }
}

@Preview(showBackground = true, name = "Özel Yükseklik")
@Composable
fun CustomHeightPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                barHeight = 80.dp,
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

@Preview(showBackground = true, name = "Özel Spacing")
@Composable
fun CustomSpacingPreview() {
    val (hazeState, selectedTabState) = PreviewHelpers.rememberPreviewState()
    
    LiquidglasstabbarTheme {
        PreviewHelpers.PreviewTabBarContainer(hazeState = hazeState) {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTabState.intValue,
                tabs = TabBarConstants.defaultTabs,
                onTabSelected = { selectedTabState.intValue = it },
                spacingConfig = SpacingConfig(
                    horizontalPadding = 24.dp,
                    topPadding = 20.dp,
                    tabSearchSpacing = 20.dp,
                    tabButtonPadding = PaddingValues(
                        start = 12.dp,
                        top = 8.dp,
                        end = 12.dp,
                        bottom = 8.dp
                    )
                ),
                searchIcon = TabBarConstants.defaultSearchIcon
            )
        }
    }
}

