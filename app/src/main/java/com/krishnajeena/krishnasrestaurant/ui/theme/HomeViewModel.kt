package com.krishnajeena.krishnasrestaurant.ui.theme

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.krishnajeena.krishnasrestaurant.KrishnaRestApplicationContainer
import com.krishnajeena.krishnasrestaurant.data.Dish
import com.krishnajeena.krishnasrestaurant.data.KrishnaDataRespository
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

sealed interface KrishnaUiState {
    data class Success(val data: List<Dish>) : KrishnaUiState
    object Error : KrishnaUiState
    object Loading : KrishnaUiState
}

class HomeViewModel(private val krishnaDataRepository: KrishnaDataRespository) : ViewModel() {

    var krishnaUiState: KrishnaUiState by mutableStateOf(KrishnaUiState.Loading)
        private set

    init {
           getDishStateList(1)
    }




    fun getDishStateList(dataT: Int){
        Log.i("AAAF!!!!!!!!@@@@@@@@@@@2", "getDishStateList $dataT")
        viewModelScope.launch{
            krishnaUiState = KrishnaUiState.Loading
            krishnaUiState = try {
              KrishnaUiState.Success(krishnaDataRepository.getKrishnaData(dataT))
            }
            catch (e : IOException){
                KrishnaUiState.Error
            }
            catch (e : HttpException){
                KrishnaUiState.Error
            }

        }
    }

//    fun setDishtList(list: Int){
//        when(list) {
//            1 -> _uiState.value = dishList
//            2 -> _uiState.value = dishList2
//            3 -> _uiState.value = dishList3
//
//        }
//    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as KrishnaRestApplicationContainer)
                val krishnaDataRepository = application.container.krishnaDataRespository
                HomeViewModel(krishnaDataRepository = krishnaDataRepository)

            }
        }
    }

//    fun selectOption(index: Int) {
//        _selectedOption.value = index
//    }


}