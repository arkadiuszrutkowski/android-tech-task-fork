package com.nordpass.tt.usecase.common

import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

class TimeProvider @Inject constructor() {
    fun now(): OffsetDateTime = OffsetDateTime.now()
}