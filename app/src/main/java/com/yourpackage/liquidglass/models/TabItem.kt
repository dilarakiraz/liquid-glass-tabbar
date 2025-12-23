package com.yourpackage.liquidglass.models

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector

/**
 * Tab item modeli
 * 
 * @param title Tab başlığı
 * @param icon Tab ikonu (ImageVector)
 * @param selectedIcon Seçili durumda gösterilecek ikon (opsiyonel, null ise icon kullanılır)
 * @param selectedColor Seçili tab rengi (varsayılan: #82DBF7)
 * @param unselectedColor Seçili olmayan tab rengi (varsayılan: #D3DCE6)
 */
data class TabItem(
    val title: String,
    val icon: ImageVector,
    val selectedIcon: ImageVector? = null,
    val selectedColor: Color = Color(0xFF82DBF7),
    val unselectedColor: Color = Color(0xFFD3DCE6)
)


