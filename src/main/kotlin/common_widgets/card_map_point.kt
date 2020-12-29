package common_widgets

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import data.types.ContentSourceType
import data.types.GrenadeType
import data.types.TickrateType
import ui.*
import ui.Icons

@Composable
fun CardMapPoint(
    modifier: Modifier = Modifier,
    mapPointName: String,
    mapName: String,
    previewStart: ContentSourceType,
    previewEnd: ContentSourceType,
    tickrateTypes: List<TickrateType>,
    grenadeType: GrenadeType,
    isCompetitive: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier).clickable(onClick = onClick),
        shape = roundedCorner5dp,
        border = BorderStroke(1.dp, orangeAccent),
        elevation = elevation6dp
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize().zIndex(1f)
            ) {
                ImageLoader.Image(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    content = previewStart
                )
                ImageLoader.Image(
                    modifier = Modifier.weight(1f).fillMaxSize(),
                    contentScale = ContentScale.Crop,
                    content = previewEnd
                )
            }
            Surface(
                modifier = Modifier.fillMaxSize().zIndex(2f),
                color = Color.Black.copy(alpha = 0.6f),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize().padding(5.dp)
                ) {
                    Icon(
                        modifier = Modifier.preferredHeight(20.dp).align(Alignment.TopStart),
                        tint = greyAccent,
                        bitmap = when (grenadeType) {
                            GrenadeType.FLASH -> {
                                imageResource(Icons.Flash)
                            }
                            GrenadeType.MOLOTOV -> {
                                imageResource(Icons.Molotov)
                            }
                            GrenadeType.SMOKE -> {
                                imageResource(Icons.Smoke)
                            }
                        }
                    )
                    Row(
                        modifier = Modifier.align(Alignment.TopCenter),
                        horizontalArrangement = spacedBy5dp
                    ) {
                        tickrateTypes.forEach {
                            TextApp(
                                text = it.value.toString(),
                                fontFamily = verdanaBold,
                                fontSize = fontSize8sp
                            )
                        }
                    }
                    if (isCompetitive) {
                        Icon(
                            modifier = Modifier.size(10.dp).align(Alignment.TopEnd),
                            bitmap = imageResource(Icons.Competitive),
                            tint = greyAccent
                        )
                    }
                    TextApp(
                        modifier = Modifier.align(Alignment.Center),
                        text = mapName,
                        textAlign = TextAlign.Center,
                        fontFamily = verdanaBold,
                    )
                    TextApp(
                        modifier = Modifier.align(Alignment.BottomCenter),
                        text = mapPointName,
                        textAlign = TextAlign.Center,
                    )
                }
            }
        }
    }
}