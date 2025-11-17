package com.baek.lotto.ui.screens.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baek.lotto.domain.repository.LottoRepository
import com.baek.lotto.ui.model.DrawInfoUiModel
import com.baek.lotto.ui.model.DrawUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.baek.lotto.common.Result
import com.baek.lotto.domain.model.DrawInfo
import com.baek.lotto.domain.util.DrawNoCalculator
import com.baek.lotto.ui.mapper.UiMapper.toUi

@HiltViewModel
class MainViewModel @Inject constructor(
    private val lottoRepository: LottoRepository
) : ViewModel() {

    var selectedDraw by mutableStateOf<Result<DrawUiModel>>(Result.None)
        private set

    var drawInfoList by mutableStateOf<List<DrawInfoUiModel>>(emptyList())
        private set

    var isBottomSheetVisible by mutableStateOf(false)
        private set

    private var currentDrawNo: Int? = null

    init {
        load()
    }

    fun onEvent(event: MainEvent) {
        when (event) {
            is MainEvent.OnClickDrawTitle -> {
                isBottomSheetVisible = true
            }

            is MainEvent.OnSelectDraw -> {
                getDraw(event.drawNo)
                isBottomSheetVisible = false
            }

            is MainEvent.OnClickReload -> {
                val drawNo = currentDrawNo
                if (drawNo == null) {
                    load()
                } else {
                    getDraw(drawNo)
                }
            }

            is MainEvent.OnDismissBottomSheet -> {
                isBottomSheetVisible = false
            }

            else -> Unit
        }

    }

    private fun load() {
        selectedDraw = Result.Loading

        viewModelScope.launch {
            when (val syncResult = lottoRepository.sync()) {
                is Result.Error -> {
                    selectedDraw = Result.Error(
                        message = syncResult.message ?: "동기화에 실패했습니다.",
                        throwable = syncResult.throwable
                    )
                    return@launch
                }

                else -> Unit
            }
            when (val latestResult = lottoRepository.getLatest()) {
                is Result.Success -> {
                    val latest = latestResult.data

                    selectedDraw = Result.Success(latest.toUi())

                    val drawInfoDomainList: List<DrawInfo> =
                        DrawNoCalculator.buildDrawInfoList(latest.drawNo)

                    drawInfoList = drawInfoDomainList.map { it.toUi() }
                }

                is Result.Error -> {
                    selectedDraw = Result.Error(
                        message = latestResult.message ?: "최신 회차 정보를 불러오는데 실패했습니다.",
                        throwable = latestResult.throwable
                    )
                }

                else -> Unit
            }
        }
    }

    private fun getDraw(drawNo: Int) {
        selectedDraw = Result.Loading
        currentDrawNo = drawNo

        viewModelScope.launch {
            when (val result = lottoRepository.getDraw(drawNo)) {
                is Result.Success -> {
                    val drawUi = result.data.toUi()
                    selectedDraw = Result.Success(drawUi)
                }

                is Result.Error -> {
                    selectedDraw = Result.Error(
                        result.message ?: "${drawNo}회 정보를 불러오는데 실패했습니다.",
                        result.throwable
                    )
                }

                else -> {}
            }
        }
    }
}