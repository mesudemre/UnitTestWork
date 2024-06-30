package com.mesutemre.unittestwork.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.mesutemre.namazvakitleri.ui.components.UnitTestWorkSurface
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.main.components.TarihteBugunDivider
import com.mesutemre.unittestwork.main.components.TarihteBugunItem

@Composable
fun MainScreen(
    state: MainScreenState
) {
    UnitTestWorkSurface(modifier = Modifier.fillMaxSize()) {
        Column(modifier = Modifier.fillMaxSize()) {
            when (state.tarihteBugunList) {
                is BaseResourceEvent.Loading -> {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .padding(top = 24.dp)
                            .size(48.dp)
                            .align(Alignment.CenterHorizontally)
                    )
                }

                is BaseResourceEvent.Success -> {
                    state.tarihteBugunList.data?.let { list->
                        val listSize by remember {
                            mutableIntStateOf(list.size)
                        }
                        LazyColumn(modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)) {
                            itemsIndexed(list,key = { index, item ->
                                index
                            }) { index,item->
                                val hasDivider by remember {
                                    derivedStateOf {
                                        mutableStateOf(
                                            index < listSize - 1
                                        )
                                    }
                                }
                                TarihteBugunItem(item = item)
                                if (hasDivider.value) {
                                    TarihteBugunDivider()
                                }
                            }
                        }
                    }
                }

                else -> Unit
            }
        }
    }
}