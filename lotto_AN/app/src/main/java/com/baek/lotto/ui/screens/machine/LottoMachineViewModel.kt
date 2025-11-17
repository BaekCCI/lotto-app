package com.baek.lotto.ui.screens.machine

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baek.lotto.domain.repository.LottoRepository
import com.baek.lotto.ui.model.RandomLottoUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.baek.lotto.common.Result
import com.baek.lotto.ui.mapper.UiMapper.toUi
import kotlinx.coroutines.launch

@HiltViewModel
class LottoMachineViewModel @Inject constructor(
    private val lottoRepository: LottoRepository
) : ViewModel() {

    var selectedCount by mutableStateOf<Int?>(null)
        private set

    var drawResult by mutableStateOf<Result<List<RandomLottoUiModel>>>(Result.None)
        private set

    fun onEvent(event: LottoMachineEvent) {
        when (event) {
            is LottoMachineEvent.OnBackClick -> {}

            is LottoMachineEvent.OnSelectCount -> {
                selectedCount = event.count
                drawResult = Result.None
            }

            is LottoMachineEvent.OnClickDraw -> {
                requestRandomLotto()
            }

            is LottoMachineEvent.OnClickRetry -> {
                drawResult = Result.None
            }

            is LottoMachineEvent.OnClickSave -> {
                saveResult()
            }
        }
    }

    private fun requestRandomLotto() {
        val count = selectedCount ?: return
        drawResult = Result.Loading
        viewModelScope.launch {
            when (val result = lottoRepository.createRandomLotto(count)) {

                is Result.Success -> {
                    val uiList = result.data.map { it.toUi() }
                    drawResult = Result.Success(uiList)
                }

                is Result.Error -> {
                    drawResult = Result.Error(result.message, result.throwable)
                }

                else -> {
                    drawResult = Result.None
                }
            }
        }
    }

    private fun saveResult() {
        val result = drawResult
        if (result !is Result.Success) return

        //TODO: 저장 로직
    }
}