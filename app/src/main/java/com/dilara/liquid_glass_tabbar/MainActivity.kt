package com.dilara.liquid_glass_tabbar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dilara.liquid_glass_tabbar.ui.theme.LiquidglasstabbarTheme
import com.yourpackage.liquidglass.LiquidGlassTabBar
import dev.chrisbanes.haze.HazeState
import dev.chrisbanes.haze.hazeSource

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LiquidglasstabbarTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var selectedTab by remember { mutableIntStateOf(0) }
    val hazeState = remember { HazeState() }
    
    Scaffold(
        bottomBar = {
            // Tab bar şeffaf arka plan üzerinde (iOS gibi)
            // Arka plan şeffaf - blur efekti arka plandaki içeriği gösterir
            LiquidGlassTabBar(
                items = TabBarConstants.defaultTabs,
                selectedIndex = selectedTab,
                onTabSelected = { selectedTab = it },
                onSearchClick = { /* Search action */ },
                style = TabBarConstants.darkThemeStyle,
                searchIcon = TabBarConstants.defaultSearchIcon,
                hazeState = hazeState
            )
        }
    ) { paddingValues ->
        // Content with hazeSource - ÖNEMLİ: Blur için gerekli
        // Arka plan şeffaf - blur efekti arka plandaki içeriği gösterir
        // İçerik tab bar'ın altına kadar uzar ki blur efekti çalışsın
        Box(
            modifier = Modifier
                .fillMaxSize()
                .hazeSource(state = hazeState) // Blur efekti için gerekli - EN ÖNEMLİ!
                .background(
                    brush = Brush.verticalGradient(
                        colors = TabBarConstants.previewGradientColors
                    )
                )
        ) {
            val layoutDirection = LocalLayoutDirection.current
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = paddingValues.calculateStartPadding(layoutDirection),
                        top = paddingValues.calculateTopPadding(),
                        end = paddingValues.calculateEndPadding(layoutDirection)
                        // bottom padding yok - içerik tab bar'ın arkasına kadar uzanır
                    )
                    .verticalScroll(rememberScrollState())
            ) {
            // Tab içeriği - Her tab için farklı içerik gösterilir
            // İçerik tab bar'ın altına kadar uzar ki blur efekti çalışsın
            when (selectedTab) {
                0 -> {
                    // Sayfam Tab
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Sayfam",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Bu Liquid Glass Tab Bar kütüphanesinin demo sayfasıdır.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "Tab'ları değiştirerek farklı içerikleri görebilirsiniz.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                        // Tab bar'ın arkasında görünmesi için daha fazla içerik
                        repeat(30) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = "İçerik ${index + 1} - Tab bar'ın arkasında blur efektiyle görünecek",
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                1 -> {
                    // Listem Tab
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Listem",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Glass efektli tab bar örneği",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "iOS tasarımından ilham alınarak Android'e uyarlandı.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                        // Tab bar'ın arkasında görünmesi için daha fazla içerik
                        repeat(30) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Liste Öğesi ${index + 1} - Blur efektiyle görünür",
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
                2 -> {
                    // Ayarlar Tab
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp)
                    ) {
                        Text(
                            text = "Ayarlar",
                            color = Color.White,
                            fontSize = 32.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                        Text(
                            text = "Blur efekti ve gradient border ile",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                        Text(
                            text = "modern bir görünüm sunar.",
                            color = Color.White.copy(alpha = 0.9f),
                            fontSize = 16.sp,
                            modifier = Modifier.padding(bottom = 32.dp)
                        )
                        // Tab bar'ın arkasında görünmesi için daha fazla içerik
                        repeat(30) { index ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp)
                            ) {
                                Text(
                                    text = "Ayar ${index + 1} - Şeffaf tab bar üzerinde",
                                    color = Color.White.copy(alpha = 0.8f),
                                    fontSize = 14.sp
                                )
                            }
                        }
                    }
                }
            }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    LiquidglasstabbarTheme {
        MainScreen()
    }
}
