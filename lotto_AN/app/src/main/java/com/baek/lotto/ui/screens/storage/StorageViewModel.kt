package com.baek.lotto.ui.screens.storage

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baek.lotto.domain.model.LottoRecord
import com.baek.lotto.domain.repository.StorageRepository
import com.baek.lotto.ui.model.StorageGroupUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.baek.lotto.common.Result
import com.baek.lotto.ui.mapper.UiMapper.toUiGroup
import kotlinx.coroutines.launch

@HiltViewModel
class StorageViewModel @Inject constructor(
    private val storageRepository: StorageRepository
) : ViewModel() {
    var isEditing by mutableStateOf<Boolean>(false)
        private set

    var loadState by mutableStateOf<Result<Unit>>(Result.None)
        private set

    private var originalGroups: List<LottoRecord> = emptyList()

    var groupList by mutableStateOf<List<StorageGroupUiModel>>(emptyList())
        private set

    private val deletedIds: MutableList<Long> = mutableListOf()

    var saveState by mutableStateOf<Result<Unit>>(Result.None)
        private set

    var dialogState by mutableStateOf<StorageDialog>(StorageDialog.None)
        private set

    init {
        load()
    }

    fun onEvent(event: StorageEvent) {
        when (event) {
            is StorageEvent.OnClickEdit -> {
                deletedIds.clear()
                isEditing = true
            }

            is StorageEvent.OnClickEditCancel -> {
                isEditing = false
                deletedIds.clear()
                groupList = originalGroups.toUiGroup()
            }

            is StorageEvent.OnClickEditDone -> {
                dialogState = StorageDialog.ConfirmEdit
            }

            is StorageEvent.OnClickReload -> {
                if (!isEditing) {
                    load()
                }
            }

            is StorageEvent.OnToggleGroup -> {
                groupList = groupList.map { group ->
                    if (group.id == event.groupId) {
                        group.copy(isExpanded = !group.isExpanded)
                    } else {
                        group
                    }
                }
            }

            is StorageEvent.OnDeleteItem -> {
                deletedIds.add(event.itemId)
                groupList = groupList.map { group ->
                    if (group.id == event.groupId) {
                        group.copy(
                            items = group.items.filterNot { it.id == event.itemId }
                        )
                    } else {
                        group
                    }
                }
            }

            is StorageEvent.OnDeleteAll -> {
                dialogState = StorageDialog.ConfirmDeleteAll
            }

            is StorageEvent.OnConfirmEdit -> {
                dialogState = StorageDialog.None
                finishEditing()
            }

            is StorageEvent.OnConfirmDeleteAll -> {
                dialogState = StorageDialog.None
                deleteAll()
            }

            is StorageEvent.OnDismissDialog -> {
                dialogState = StorageDialog.None
            }

            is StorageEvent.OnClearSaveState -> {
                saveState = Result.None
            }

            else -> Unit
        }
    }

    private fun load() {
        loadState = Result.Loading

        viewModelScope.launch {

            when (val records = storageRepository.getLottoHistory()) {
                is Result.Success -> {
                    originalGroups = records.data
                    groupList = originalGroups.toUiGroup()

                    loadState = Result.Success(Unit)
                }

                is Result.Error -> {
                    loadState = Result.Error(records.message ?: "데이터를 불러오지 못했습니다.")
                }

                else -> {}
            }
        }
    }

    private fun finishEditing() {

        if (deletedIds.isEmpty()) {
            isEditing = false
            saveState = Result.Success(Unit)
            return
        }
        saveState = Result.Loading

        val idsToDelete = deletedIds.toList()

        viewModelScope.launch {
            when (val result = storageRepository.deleteLotto(idsToDelete)) {
                is Result.Success -> {
                    isEditing = false
                    deletedIds.clear()
                    saveState = Result.Success(Unit)
                    load()
                }

                is Result.Error -> {
                    saveState = Result.Error(result.message ?: "저장을 실패했습니다.")
                }

                else -> {}
            }
        }
    }

    private fun deleteAll() {
        saveState = Result.Loading

        viewModelScope.launch {
            when (val result = storageRepository.deleteAll()) {
                is Result.Success -> {
                    isEditing = false
                    deletedIds.clear()
                    saveState = Result.Success(Unit)
                    load()
                }

                is Result.Error -> {
                    saveState = Result.Error(result.message ?: "삭제를 실패했습니다.")
                }

                else -> {}
            }
        }
    }
}