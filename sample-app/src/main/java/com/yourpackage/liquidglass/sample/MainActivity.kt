package com.yourpackage.liquidglass.sample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yourpackage.liquidglass.LiquidGlassStyle
import com.yourpackage.liquidglass.LiquidGlassTabBar
import com.yourpackage.liquidglass.LiquidTabItem
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SampleAppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SampleApp()
                }
            }
        }
    }
}

@Composable
private fun SampleApp() {
    var selectedTab by remember { mutableIntStateOf(0) }
    var currentStyle by remember { mutableIntStateOf(0) }
    val hazeState = remember { HazeState() }
    
    val styles = listOf(
        "iOS Style" to LiquidGlassStyle.Default,
        "Neon Style" to LiquidGlassStyle.neon(),
        "Minimal Style" to LiquidGlassStyle.minimal()
    )
    
    val tabs = listOf(
        LiquidTabItem(
            icon = Icons.Default.Home,
            label = "Ana Sayfa",
            selectedColor = Color(0xFF82DBF7),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        LiquidTabItem(
            icon = Icons.Default.List,
            label = "Listem",
            selectedColor = Color(0xFF82DBF7),
            unselectedColor = Color(0xFFD3DCE6)
        ),
        LiquidTabItem(
            icon = Icons.Default.Settings,
            label = "Ayarlar",
            selectedColor = Color(0xFF82DBF7),
            unselectedColor = Color(0xFFD3DCE6)
        )
    )
    
    val currentStyleConfig = styles[currentStyle].second
    
    Box(modifier = Modifier.fillMaxSize()) {
        // Background with gradient
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState) // Important: This enables blur effect
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(
                            Color(0xFF1A1A2E),
                            Color(0xFF16213E),
                            Color(0xFF0F3460)
                        )
                    )
                )
        ) {
            // Content
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(16.dp)
            ) {
                Text(
                    text = "Liquid Glass Tab Bar",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                
                Text(
                    text = "Selected Tab: ${tabs[selectedTab].label ?: "Tab ${selectedTab + 1}"}",
                    fontSize = 18.sp,
                    color = Color.White.copy(alpha = 0.8f),
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                
                Text(
                    text = "Current Style: ${styles[currentStyle].first}",
                    fontSize = 16.sp,
                    color = Color.White.copy(alpha = 0.7f),
                    modifier = Modifier.padding(bottom = 32.dp)
                )
                
                // Sample content items
                repeat(20) { index ->
                    SampleContentItem(index = index)
                }
            }
        }
        
        // Tab Bar at bottom
        LiquidGlassTabBar(
            items = tabs,
            selectedIndex = selectedTab,
            onTabSelected = { index ->
                selectedTab = index
            },
            style = currentStyleConfig,
            onSearchClick = {
                // Cycle through styles
                currentStyle = (currentStyle + 1) % styles.size
            },
            showSearchButton = true,
            searchIcon = Icons.Default.Search,
            hazeState = hazeState,
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}

@Composable
private fun SampleContentItem(index: Int) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .background(
                color = Color.White.copy(alpha = 0.1f),
                shape = androidx.compose.foundation.shape.RoundedCornerShape(12.dp)
            )
            .padding(16.dp)
    ) {
        Text(
            text = "Content Item ${index + 1}",
            color = Color.White,
            fontSize = 16.sp
        )
    }
}

@Composable
private fun SampleAppTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = MaterialTheme.colorScheme.copy(
            background = Color(0xFF1A1A2E)
        ),
        content = content
    )
}

