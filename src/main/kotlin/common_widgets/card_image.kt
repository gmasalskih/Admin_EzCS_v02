package common_widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ui.dark
import ui.greyAccent
import ui.verdanaBold

@Composable
fun CardImage(
    modifier: Modifier = Modifier,
    pathToImage: String,
    fileName: String,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier.size(100.dp, 150.dp).then(modifier).clickable(onClick = onClick),
        shape = RoundedCornerShape(5.dp),
        elevation = 6.dp,
        backgroundColor = greyAccent
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            Box(
                modifier = Modifier.fillMaxWidth().aspectRatio(1f)
            ) {
                ImageUrl(
                    url = pathToImage,
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Box(
                modifier = Modifier.weight(1f, true)
            ) {
                Text(
                    text = fileName.toLowerCase(),
                    fontFamily = verdanaBold,
                    fontSize = 10.sp,
                    modifier = Modifier.align(Alignment.Center),
                    color = dark
                )
            }
        }
    }
}