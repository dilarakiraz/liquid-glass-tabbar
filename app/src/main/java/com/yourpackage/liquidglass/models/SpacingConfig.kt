package com.yourpackage.liquidglass.models

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

/**
 * Spacing yapılandırması
 * iOS'taki padding ve spacing değerlerini kontrol eder
 */
data class SpacingConfig(
    /**
     * Yatay padding (iOS: 20px)
     */
    val horizontalPadding: Dp = 20.dp,
    
    /**
     * Üst padding (iOS: 16px)
     */
    val topPadding: Dp = 16.dp,
    
    /**
     * Alt padding (iOS: 0px)
     */
    val bottomPadding: Dp = 0.dp,
    
    /**
     * Tab container ile search button arası spacing (iOS: 16px)
     */
    val tabSearchSpacing: Dp = 16.dp,
    
    /**
     * Tab button'lar arası spacing (iOS: 0px)
     */
    val tabButtonSpacing: Dp = 0.dp,
    
    /**
     * Tab button iç padding (iOS: horizontal=8px, top=6px, bottom=7px)
     */
    val tabButtonPadding: PaddingValues = PaddingValues(
        start = 8.dp,
        top = 6.dp,
        end = 8.dp,
        bottom = 7.dp
    ),
    
    /**
     * Icon ve text arası spacing (iOS: 2px)
     */
    val iconTextSpacing: Dp = 2.dp
) {
    companion object {
        fun default() = SpacingConfig()
    }
}

