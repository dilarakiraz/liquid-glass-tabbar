package com.yourpackage.liquidglass

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import dev.chrisbanes.haze.HazeState
import com.yourpackage.liquidglass.models.GlassConfig
import com.yourpackage.liquidglass.models.SpacingConfig
import com.yourpackage.liquidglass.models.TabItem

/**
 * iOS LiquidGlassTabBar'ın Android eşdeğeri
 * 
 * @param hazeState Blur efekti için HazeState (zorunlu)
 * @param selectedTab Seçili tab index
 * @param tabs Tab listesi
 * @param onTabSelected Tab seçim callback
 * @param onSearchClick Search button click callback
 * @param modifier Compose modifier
 * @param glassConfig Glass efekt yapılandırması
 * @param spacingConfig Spacing yapılandırması
 * @param barHeight Tab bar yüksekliği (varsayılan: 62.dp - iOS ile aynı)
 * @param showSearchButton Search button gösterilsin mi (varsayılan: true)
 * @param searchIcon Search button içinde gösterilecek icon (opsiyonel)
 */
@Composable
fun LiquidGlassTabBar(
    hazeState: HazeState,
    selectedTab: Int,
    tabs: List<TabItem>,
    onTabSelected: (Int) -> Unit,
    onSearchClick: () -> Unit = {},
    modifier: Modifier = Modifier,
    glassConfig: GlassConfig = GlassConfig.default(),
    searchButtonGlassConfig: GlassConfig? = null, // Search button için özel config (null ise GlassConfig.forCircle() kullanılır)
    spacingConfig: SpacingConfig = SpacingConfig.default(),
    barHeight: Dp = 62.dp,
    showSearchButton: Boolean = true,
    searchIcon: androidx.compose.ui.graphics.vector.ImageVector? = null,
) {
    val shape = RoundedCornerShape(999.dp)
    
    Row(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding()
            .padding(
                horizontal = spacingConfig.horizontalPadding,
                vertical = spacingConfig.topPadding
            ),
        horizontalArrangement = Arrangement.spacedBy(spacingConfig.tabSearchSpacing),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Tab Buttons Container
        LiquidGlassRectangle(
            hazeState = hazeState,
            modifier = Modifier
                .weight(1f)
                .height(barHeight),
            shape = shape,
            glassConfig = glassConfig
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 4.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.spacedBy(spacingConfig.tabButtonSpacing),
                verticalAlignment = Alignment.CenterVertically
            ) {
                tabs.forEachIndexed { index, tab ->
                    val isSelected = index == selectedTab
                    val color = if (isSelected) tab.selectedColor else tab.unselectedColor
                    val icon = if (isSelected && tab.selectedIcon != null) {
                        tab.selectedIcon
                    } else {
                        tab.icon
                    }
                    
                    // Selected tab background rengi
                    val selectedBackground = glassConfig.selectedTabBackground
                        ?: Color.White.copy(alpha = glassConfig.selectedTabBackgroundAlpha)
                    
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .fillMaxHeight()
                            .then(
                                if (isSelected) {
                                    Modifier
                                        .background(
                                            color = selectedBackground,
                                            shape = RoundedCornerShape(999.dp)
                                        )
                                        .padding(4.dp)
                                } else {
                                    Modifier
                                }
                            )
                            .clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }
                            ) {
                                onTabSelected(index)
                            },
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(spacingConfig.iconTextSpacing)
                    ) {
                        Icon(
                            imageVector = icon,
                            contentDescription = tab.title,
                            tint = color,
                            modifier = Modifier.size(24.dp)
                        )
                        Text(
                            text = tab.title,
                            color = color,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium,
                            maxLines = 1
                        )
                    }
                }
            }
        }
        
        // Search Button
        if (showSearchButton) {
            Box(
                modifier = Modifier
                    .size(barHeight)
                    .clip(CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onSearchClick()
                    }
            ) {
                LiquidGlassCircle(
                    hazeState = hazeState,
                    modifier = Modifier.fillMaxSize(),
                    glassConfig = searchButtonGlassConfig ?: GlassConfig.forCircle()
                ) {
                    // Search icon buraya eklenecek
                    // Kullanıcı kendi icon'unu sağlamalı
                    searchIcon?.let { icon ->
                        Icon(
                            imageVector = icon,
                            contentDescription = "Search",
                            modifier = Modifier.size(24.dp),
                            tint = Color.White
                        )
                    }
                }
            }
        }
    }
}


