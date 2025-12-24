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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourpackage.liquidglass.models.TabItem
import dev.chrisbanes.haze.HazeState

/**
 * Public API: iOS-style liquid glass tab bar for Android Jetpack Compose
 * 
 * A beautiful, customizable tab bar with blur effects and gradient borders,
 * inspired by iOS LiquidGlassTabBar design.
 * 
 * @param items List of tab items to display
 * @param selectedIndex Currently selected tab index
 * @param onTabSelected Callback when a tab is selected
 * @param modifier Compose modifier
 * @param style Style configuration (default: LiquidGlassStyle.Default)
 * @param onSearchClick Callback when search button is clicked
 * @param showSearchButton Whether to show the search button (default: true)
 * @param searchIcon Icon to display in search button (optional)
 * 
 * @sample com.yourpackage.liquidglass.samples.BasicUsage
 */
@Composable
public fun LiquidGlassTabBar(
    items: List<LiquidTabItem>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    modifier: Modifier = Modifier,
    style: LiquidGlassStyle = LiquidGlassStyle.Default,
    onSearchClick: () -> Unit = {},
    showSearchButton: Boolean = true,
    searchIcon: ImageVector? = null,
    hazeState: HazeState? = null
) {
    // Internal: Manage hazeState automatically if not provided
    val internalHazeState = hazeState ?: remember { HazeState() }
    
    // Internal: Convert public API to internal models
    val glassConfig = style.toGlassConfig()
    val spacingConfig = style.toSpacingConfig()
    val searchButtonGlassConfig = style.toGlassConfigForCircle()
    
    // Internal: Convert LiquidTabItem to TabItem
    val internalTabs = items.map { liquidTab ->
        TabItem(
            title = liquidTab.label ?: "",
            icon = liquidTab.icon,
            selectedIcon = liquidTab.selectedIcon,
            selectedColor = liquidTab.selectedColor,
            unselectedColor = liquidTab.unselectedColor
        )
    }
    
    val shape = RoundedCornerShape(style.cornerRadius)
    
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
            hazeState = internalHazeState,
            modifier = Modifier
                .weight(1f)
                .height(style.barHeight),
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
                internalTabs.forEachIndexed { index, tab ->
                    val isSelected = index == selectedIndex
                    val color = if (isSelected) tab.selectedColor else tab.unselectedColor
                    val icon = if (isSelected && tab.selectedIcon != null) {
                        tab.selectedIcon
                    } else {
                        tab.icon
                    }
                    
                    // Selected tab background color
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
                        if (tab.title.isNotEmpty()) {
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
        }
        
        // Search Button
        if (showSearchButton) {
            Box(
                modifier = Modifier
                    .size(style.barHeight)
                    .clip(CircleShape)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        onSearchClick()
                    }
            ) {
                LiquidGlassCircle(
                    hazeState = internalHazeState,
                    modifier = Modifier.fillMaxSize(),
                    glassConfig = searchButtonGlassConfig
                ) {
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
