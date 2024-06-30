package com.mesutemre.unittestwork.main.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.mesutemre.unittestwork.R
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem


@Composable
fun TarihteBugunItem(
    item: TarihteBugunItem
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp),
    ) {
        Image(
            imageVector = ImageVector.vectorResource(id = R.drawable.ic_researcher),
            modifier = Modifier.size(32.dp),
            contentDescription = item.olay
        )
        Column(
            modifier = Modifier
                .padding(horizontal = 6.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "${item.tarih} , ${item.durum}",
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.error
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = item.olay,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.primary
            )

        }
    }
}


@Composable
fun TarihteBugunDivider() {
    HorizontalDivider(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 6.dp, vertical = 6.dp),
        color = Color.Black
    )
}