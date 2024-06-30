package com.mesutemre.unittestwork.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mesutemre.unittestwork.core.BaseResourceEvent
import com.mesutemre.unittestwork.data.repository.ITarihteBugunRepository
import com.mesutemre.unittestwork.domain.data.TarihteBugunItem
import com.mesutemre.unittestwork.domain.use_case.GetTarihteBugunList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getTarihteBugunList: GetTarihteBugunList
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        loadTarihteBugunList()
    }

    private fun loadTarihteBugunList() {
        viewModelScope.launch {
            getTarihteBugunList().collectLatest { res->
                _state.update {
                    it.copy(
                        tarihteBugunList = res
                    )
                }
            }
        }
    }

    suspend fun getTarihteBugunListFromSource() : Flow<BaseResourceEvent<List<TarihteBugunItem>>> {
        return getTarihteBugunList()
    }
}
/*class MainScreenViewModel @Inject constructor(
    private val repository: ITarihteBugunRepository
) : ViewModel() {

    private val _state = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()

    init {
        loadTarihteBugunList()
    }

    private fun loadTarihteBugunList() {
        viewModelScope.launch {
            repository.getTarihteBugunListFromAPI()
                .flowOn(Dispatchers.IO)
                .collectLatest { response->
                    _state.update {
                        it.copy(
                            tarihteBugunList = response
                        )
                    }
                }
        }
    }

    suspend fun getTarihteBugunListFromAPI() : Flow<BaseResourceEvent<List<TarihteBugunItem>>> {
        return repository.getTarihteBugunListFromAPI()
    }
}*/