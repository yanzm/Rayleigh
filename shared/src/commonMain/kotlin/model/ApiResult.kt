package model

sealed interface ApiResult<out Data, out Error> {
    data class Success<Data>(val data: Data) : ApiResult<Data, Nothing>
    data class Error<Error>(val e: Error) : ApiResult<Nothing, Error>
}
