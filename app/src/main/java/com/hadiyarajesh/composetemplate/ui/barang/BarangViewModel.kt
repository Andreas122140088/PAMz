package com.hadiyarajesh.composetemplate.ui.barang

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hadiyarajesh.composetemplate.repository.FirebaseDatabaseRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class BarangUiState(
    val daftarBarang: List<BarangLab> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)

@HiltViewModel
class BarangViewModel @Inject constructor(
    private val firebaseDatabaseRepository: FirebaseDatabaseRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(BarangUiState())
    val uiState: StateFlow<BarangUiState> = _uiState.asStateFlow()

    fun loadBarang() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            try {
                val barangList = firebaseDatabaseRepository.readData("barang/list", List::class.java) as? List<BarangLab> ?: emptyList()
                _uiState.value = _uiState.value.copy(daftarBarang = barangList, isLoading = false)
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message, isLoading = false)
            }
        }
    }

    fun tambahBarang(barang: BarangLab) {
        viewModelScope.launch {
            try {
                // Assuming "barang/list" is a list, you might want to implement add logic properly
                // For simplicity, just write the new barang at a unique path
                firebaseDatabaseRepository.writeData("barang/list/${barang.nama}", barang)
                loadBarang() // reload after adding
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(errorMessage = e.message)
            }
        }
    }
}
