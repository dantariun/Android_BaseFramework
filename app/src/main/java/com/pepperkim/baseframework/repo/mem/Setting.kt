package com.pepperkim.baseframework.repo.mem

import java.util.*

class Setting {
    val language: String get() = if (Locale.getDefault().language == Locale.ENGLISH.language) Locale.ENGLISH.language else Locale.KOREA.language
}