package com.baek.lotto.common.error

class LottoException(val errorType: ErrorType) : RuntimeException(errorType.message)
