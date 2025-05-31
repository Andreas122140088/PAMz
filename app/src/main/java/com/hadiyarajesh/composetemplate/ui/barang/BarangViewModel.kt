package com.hadiyarajesh.composetemplate.ui.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class BarangViewModel : ViewModel() {
    private val _barangList = MutableStateFlow<List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>>(emptyList())
    val barangList: StateFlow<List<com.hadiyarajesh.composetemplate.ui.barang.dummy.BarangLab>> = _barangList.asStateFlow()

    init {
        listenBarangList()
    }

    private fun listenBarangList() {
        viewModelScope.launch {
            BarangRepository.listenBarangList().collect { list ->
                _barangList.value = list
            }
        }
    }
}
