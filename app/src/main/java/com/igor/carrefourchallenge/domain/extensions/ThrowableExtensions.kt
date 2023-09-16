package com.igor.carrefourchallenge.domain.extensions


import okio.IOException
import retrofit2.HttpException

fun Throwable.handleError(): String = when (this) {
    is IOException -> "Acho que estamos sem internet"
    is HttpException -> "Ops, houve um problema na conexão"
    else -> "Ops, tivemos um problema"
}

