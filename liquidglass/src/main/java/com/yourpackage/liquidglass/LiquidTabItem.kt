package com.yourpackage.liquidglass

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Public API: Tab item model for LiquidGlassTabBar
 * 
 * @param icon Tab icon (ImageVector)
 * @param label Tab label text (optional, null hides the label)
 * @param selectedIcon Icon to show when selected (optional, uses icon if null)
 * @param selectedColor Color when tab is selected (default: #82DBF7)
 * @param unselectedColor Color when tab is not selected (default: #D3DCE6)
 */
public data class LiquidTabItem(
    val icon: ImageVector,
    val label: String? = null,
    val selectedIcon: ImageVector? = null,
    val selectedColor: Color = Color(0xFF82DBF7),
    val unselectedColor: Color = Color(0xFFD3DCE6)
)

