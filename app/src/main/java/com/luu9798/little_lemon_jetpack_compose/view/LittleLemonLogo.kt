package com.luu9798.little_lemon_jetpack_compose.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.luu9798.little_lemon_jetpack_compose.R

@Composable
fun LittleLemonLogo() {
    Image(
        painter = painterResource(R.mipmap.little_lemon),
        contentDescription = "Little Lemon Logo",
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(16.dp),
        contentScale = ContentScale.Fit
    )
}