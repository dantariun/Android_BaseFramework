package com.pepperkim.baseframework.api.model.common

import com.pepperkim.baseframework.repo.MemStore


open class ApiRequestCommon(
    val lang: String = MemStore.Setting.language,
)