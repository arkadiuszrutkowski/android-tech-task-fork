package com.nordpass.tt.usecase.common

import org.threeten.bp.OffsetDateTime

object Time {
    fun now() = OffsetDateTime.now().toString()
}