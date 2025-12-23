package com.dilara.liquid_glass_tabbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableIntState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

/**
 * Preview'lar için ortak helper fonksiyonlar
 */
object PreviewHelpers {
    /**
     * Preview için hazırlanmış tab bar container
     * Blur efekti için gradient background ve hazeSource içerir
     */
    @Composable
    fun PreviewTabBarContainer(
        hazeState: HazeState,
        content: @Composable () -> Unit
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = TabBarConstants.previewGradientColors
                    )
                )
        ) {
            Scaffold(
                bottomBar = content
            ) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .hazeSource(state = hazeState)
                )
            }
        }
    }
    
    /**
     * Preview için hazırlanmış state'ler
     */
    @Composable
    fun rememberPreviewState(): Pair<HazeState, MutableIntState> {
        val hazeState = remember { HazeState() }
        val selectedTab = remember { mutableIntStateOf(0) }
        return hazeState to selectedTab
    }
}

