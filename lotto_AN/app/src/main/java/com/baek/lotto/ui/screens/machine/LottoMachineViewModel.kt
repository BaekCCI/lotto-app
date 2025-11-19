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
import com.baek.lotto.domain.model.RandomLotto
import com.baek.lotto.domain.repository.StorageRepository
import com.baek.lotto.ui.mapper.UiMapper.toUi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class LottoMachineViewModel @Inject constructor(
    private val lottoRepository: LottoRepository,
    private val storageRepository: StorageRepository
) : ViewModel() {

    var selectedCount by mutableStateOf<Int?>(null)
        private set

    var drawResult by mutableStateOf<Result<List<RandomLottoUiModel>>>(Result.None)
        private set

    private var lastRandomLotto: List<RandomLotto> = emptyList()

    var saveState by mutableStateOf<Result<Unit>>(Result.None)

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

            is LottoMachineEvent.OnSaveHandled -> {
                saveState = Result.None
            }
        }
    }

    private fun requestRandomLotto() {
        val count = selectedCount ?: return
        drawResult = Result.Loading
        viewModelScope.launch {
            delay(3000)
            when (val result = lottoRepository.createRandomLotto(count)) {

                is Result.Success -> {
                    lastRandomLotto = result.data

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
        saveState = Result.Loading

        val domainList = lastRandomLotto
        if (domainList.isEmpty()) {
            saveState = Result.Error("저장할 로또가 없습니다.")
            return
        }

        viewModelScope.launch {
            saveState = storageRepository.saveLotto(domainList)
        }
    }
}