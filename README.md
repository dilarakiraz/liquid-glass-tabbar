# Liquid Glass Tab Bar

<div align="center">

![Android](https://img.shields.io/badge/Android-3DDC84?style=for-the-badge&logo=android&logoColor=white)
![Kotlin](https://img.shields.io/badge/kotlin-%237F52FF.svg?style=for-the-badge&logo=kotlin&logoColor=white)
![Compose](https://img.shields.io/badge/Compose-1.5.0-blue?style=for-the-badge&logo=jetpack-compose)
![License](https://img.shields.io/badge/license-MIT-green?style=for-the-badge)

iOS LiquidGlassTabBar tasarımını Android Jetpack Compose'a uyarlayan, özelleştirilebilir bir tab bar kütüphanesi.

[Kurulum](#kurulum) • [Kullanım](#hızlı-başlangıç) • [Özelleştirme](#özelleştirme) • [API Referansı](#api-referansı)

</div>

---

## ✨ Özellikler

- 🎨 **iOS Tasarımına Uygun**: iOS'taki liquid glass efektini Android'de simüle eder
- 🌈 **Özelleştirilebilir**: Tüm renkler, spacing, blur ve border ayarları özelleştirilebilir
- 🔍 **Blur Efekti**: Haze kütüphanesi ile gerçekçi blur efekti
- 📱 **Modern UI**: Jetpack Compose ile modern ve performanslı UI
- 🎯 **Kolay Kullanım**: Basit API ile kolay entegrasyon

## 📸 Ekran Görüntüleri

<div align="center">

### Light Theme (Açık Tema)

<img src="assets/screenshots/Screenshot 2025-12-23 at 23.12.51.png" width="280" alt="Light Theme - 5 Tabs"/>

### Dark Theme (Koyu Tema)

<table>
  <tr>
    <td align="center">
      <img src="assets/screenshots/Screenshot_20251223_232033.png" width="280" alt="Dark Theme - List View"/>
      <br/>
    </td>
    <td align="center">
      <img src="assets/screenshots/Screenshot_20251223_235653.png" width="280" alt="Dark Theme - Explore View"/>
      <br/>
    </td>
  </tr>
</table>

</div>

## 📦 Kurulum

### Proje Yapısı

```
liquidglasstabbar/
├── liquidglass/              # ⭐ KÜTÜPHANE MODÜLÜ (Sadece bunu kullanın)
│   └── src/main/java/com/yourpackage/liquidglass/
│       ├── LiquidGlassTabBar.kt
│       ├── LiquidGlassRectangle.kt
│       ├── LiquidGlassCircle.kt
│       └── models/
│           ├── TabItem.kt
│           ├── GlassConfig.kt
│           ├── SpacingConfig.kt
│           └── BorderGradient.kt
└── app/                      # Demo uygulama (opsiyonel - sadece örnekler için)
    └── src/main/java/com/dilara/liquid_glass_tabbar/
        ├── MainActivity.kt
        ├── TabBarPreviews.kt
        └── Constants.kt
```

> **Not:** `app/` modülü sadece demo/örnek amaçlıdır. Kütüphaneyi kullanmak için sadece `liquidglass/` modülüne ihtiyacınız vardır.

### Gradle Setup

**1. Projeyi clone edin veya `liquidglass/` modülünü kopyalayın:**

```bash
git clone https://github.com/dilarakiraz/liquid-glass-tabbar.git
```

**2. settings.gradle.kts** dosyasına modülü ekleyin:

```kotlin
include(":liquidglass")
// app modülüne ihtiyacınız yok - sadece kütüphane için
```

**3. Kendi uygulamanızın build.gradle.kts** dosyasına dependency ekleyin:

```kotlin
dependencies {
    implementation(project(":liquidglass"))
    // ... diğer dependencies
}
```

**4. liquidglass/build.gradle.kts** yapılandırması (zaten hazır):

```kotlin
plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.compose")
}

android {
    namespace = "com.yourpackage.liquidglass"
    compileSdk = 34
    
    defaultConfig {
        minSdk = 24
    }
    
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.haze)  // Blur efekti için
}
```

## 🚀 Hızlı Başlangıç

### Temel Kullanım

```kotlin
@Composable
fun MyScreen() {
    val hazeState = remember { HazeState() }
    var selectedTab by remember { mutableIntStateOf(0) }
    
    val tabs = listOf(
        TabItem(
            title = "Sayfam",
            icon = Icons.Default.Home,
            selectedColor = Color(0xFF82DBF7),    // Açık mavi
            unselectedColor = Color(0xFFD3DCE6)    // Açık gri-mavi
        ),
        TabItem(
            title = "Listem",
            icon = Icons.Default.List,
            selectedColor = Color(0xFF4ECDC4),      // Turkuaz
            unselectedColor = Color(0xFFD3DCE6)
        ),
        TabItem(
            title = "Ayarlar",
            icon = Icons.Default.Settings,
            selectedColor = Color(0xFF9B9B9B),      // Gri
            unselectedColor = Color(0xFFD3DCE6)
        )
    )
    
    Scaffold(
        bottomBar = {
            LiquidGlassTabBar(
                hazeState = hazeState,
                selectedTab = selectedTab,
                tabs = tabs,
                onTabSelected = { selectedTab = it },
                onSearchClick = { /* Search action */ },
                searchIcon = Icons.Default.Search
            )
        }
    ) { paddingValues ->
        // İçerik - ÖNEMLİ: Blur için hazeSource ekleyin
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .hazeSource(state = hazeState) // Blur için gerekli!
        ) {
            // İçerik buraya
        }
    }
}
```

## 🎨 Özelleştirme

### Tab Renkleri

```kotlin
val customTabs = listOf(
    TabItem(
        title = "Home",
        icon = Icons.Default.Home,
        selectedColor = Color(0xFF82DBF7),    // Seçili tab rengi
        unselectedColor = Color(0xFFD3DCE6)  // Seçili olmayan tab rengi
    )
)
```

### Glass Efekt Özelleştirme

```kotlin
val customGlassConfig = GlassConfig(
    baseTint = Color(0x0DFFFFFF),           // Base tint
    gradientTint = Color(0x990A1926),       // Gradient tint
    overlayTint = Color(0x33000000),        // Overlay tint
    borderWidth = 0.75.dp,                  // Border genişliği
    blurRadius = 10.dp,                     // Blur radius
    containerOpacity = 0.95f,                // Container opacity
    selectedTabBackgroundAlpha = 0.3f        // Seçili tab background alpha
)

LiquidGlassTabBar(
    hazeState = hazeState,
    selectedTab = selectedTab,
    tabs = tabs,
    onTabSelected = { selectedTab = it },
    glassConfig = customGlassConfig
)
```

### Dark Theme

```kotlin
val darkThemeGlassConfig = GlassConfig(
    baseTint = Color(0x0DFFFFFF),
    gradientTint = Color(0x990A1926),
    overlayTint = Color(0x33000000),
    containerOpacity = 0.95f
)

LiquidGlassTabBar(
    hazeState = hazeState,
    selectedTab = selectedTab,
    tabs = tabs,
    onTabSelected = { selectedTab = it },
    glassConfig = darkThemeGlassConfig
)
```

### Spacing Özelleştirme

```kotlin
val customSpacing = SpacingConfig(
    horizontalPadding = 24.dp,
    topPadding = 20.dp,
    tabSearchSpacing = 20.dp,
    tabButtonSpacing = 8.dp,
    iconTextSpacing = 4.dp
)

LiquidGlassTabBar(
    hazeState = hazeState,
    selectedTab = selectedTab,
    tabs = tabs,
    onTabSelected = { selectedTab = it },
    spacingConfig = customSpacing
)
```

### Border Gradient Özelleştirme

```kotlin
val customBorderGradient = BorderGradient.Linear(
    stops = listOf(
        0.0f to Color.White.copy(alpha = 0.5f),
        0.2f to Color.White.copy(alpha = 0.2f),
        0.4f to Color.White.copy(alpha = 0.05f),
        0.6f to Color.White.copy(alpha = 0.05f),
        0.8f to Color.White.copy(alpha = 0.2f),
        1.0f to Color.White.copy(alpha = 0.5f)
    )
)

val customGlassConfig = GlassConfig(
    borderGradient = customBorderGradient
)
```

### Search Button Olmadan

```kotlin
LiquidGlassTabBar(
    hazeState = hazeState,
    selectedTab = selectedTab,
    tabs = tabs,
    onTabSelected = { selectedTab = it },
    showSearchButton = false  // Search button gizle
)
```

### Özel Yükseklik

```kotlin
LiquidGlassTabBar(
    hazeState = hazeState,
    selectedTab = selectedTab,
    tabs = tabs,
    onTabSelected = { selectedTab = it },
    barHeight = 72.dp  // Özel yükseklik
)
```

## 📚 API Referansı

### LiquidGlassTabBar

```kotlin
@Composable
fun LiquidGlassTabBar(
    hazeState: HazeState,                    // Blur için HazeState (zorunlu)
    selectedTab: Int,                        // Seçili tab index
    tabs: List<TabItem>,                     // Tab listesi
    onTabSelected: (Int) -> Unit,            // Tab seçim callback
    onSearchClick: () -> Unit = {},         // Search button callback
    modifier: Modifier = Modifier,           // Compose modifier
    glassConfig: GlassConfig = GlassConfig.default(),  // Glass efekt config
    searchButtonGlassConfig: GlassConfig? = null,       // Search button için özel config
    spacingConfig: SpacingConfig = SpacingConfig.default(),  // Spacing config
    barHeight: Dp = 62.dp,                  // Tab bar yüksekliği
    showSearchButton: Boolean = true,        // Search button göster/gizle
    searchIcon: ImageVector? = null,         // Search button icon
    searchIconTint: Color = Color.White      // Search icon rengi (varsayılan: beyaz)
)
```

### TabItem

```kotlin
data class TabItem(
    val title: String,                       // Tab başlığı
    val icon: ImageVector,                  // Tab ikonu
    val selectedIcon: ImageVector? = null,  // Seçili durumda ikon (opsiyonel)
    val selectedColor: Color = Color(0xFF82DBF7),   // Seçili tab rengi
    val unselectedColor: Color = Color(0xFFD3DCE6)  // Seçili olmayan tab rengi
)
```

### GlassConfig

```kotlin
data class GlassConfig(
    val baseTint: Color = Color.Transparent,
    val gradientTint: Color = Color(0xABFFFFFF),
    val overlayTint: Color = Color.Transparent,
    val borderWidth: Dp = 0.75.dp,
    val borderGradient: BorderGradient = BorderGradient.default(),
    val blurRadius: Dp = 10.dp,
    val containerOpacity: Float = 0.95f,
    val selectedTabBackground: Color? = null,
    val selectedTabBackgroundAlpha: Float = 0.3f
)
```

### SpacingConfig

```kotlin
data class SpacingConfig(
    val horizontalPadding: Dp = 20.dp,
    val topPadding: Dp = 16.dp,
    val bottomPadding: Dp = 0.dp,
    val tabSearchSpacing: Dp = 16.dp,
    val tabButtonSpacing: Dp = 0.dp,
    val tabButtonPadding: PaddingValues = PaddingValues(...),
    val iconTextSpacing: Dp = 2.dp
)
```

## ⚠️ Önemli Notlar

1. **Blur Efekti**: Blur efektinin çalışması için içeriğe `hazeSource` modifier'ını eklemelisiniz:
   ```kotlin
   Column(
       modifier = Modifier.hazeSource(state = hazeState)
   ) {
       // İçerik
   }
   ```

2. **HazeState**: `HazeState`'i `remember` ile oluşturun ve tüm ekranda aynı instance'ı kullanın.

3. **Tab Sayısı**: İstediğiniz kadar tab ekleyebilirsiniz. Search button'u gizleyerek tüm alanı tab'lara ayırabilirsiniz.

4. **Import**: Kütüphaneyi kullanmak için:
   ```kotlin
   import com.yourpackage.liquidglass.LiquidGlassTabBar
   import com.yourpackage.liquidglass.models.TabItem
   import com.yourpackage.liquidglass.models.GlassConfig
   import com.yourpackage.liquidglass.models.SpacingConfig
   ```

5. **Light Theme**: Açık tema için `searchIconTint` parametresini koyu renk yapın:
   ```kotlin
   LiquidGlassTabBar(
       // ...
       searchIconTint = Color(0xFF424242)  // Koyu gri - açık temada görünür
   )
   ```

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın.

## 👤 Yazar

**Dilara Kiraz**

- GitHub: [@dilarakiraz](https://github.com/dilarakiraz)

## 🙏 Teşekkürler

- iOS LiquidGlassTabBar tasarımından ilham alınmıştır

---

<div align="center">

⭐ Bu projeyi beğendiyseniz yıldız vermeyi unutmayın!

</div>

