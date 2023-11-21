package com.safi.data

import com.google.gson.JsonParser
import com.safi.common.base.ApiResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import retrofit2.Response
import timber.log.Timber
import java.io.IOException
import java.net.SocketTimeoutException
import javax.inject.Inject

class NetworkBoundResource @Inject constructor(){
    private  val ioDispatcher: CoroutineDispatcher = Dispatchers.Main

    suspend fun<ResultType> downloadData(api : suspend () -> Response<ResultType>): Flow<ApiResult<ResultType>> {
        return withContext(ioDispatcher) {
            flow {
                emit(ApiResult.Loading(true))
                val response:Response<ResultType> = api()
                emit(ApiResult.Loading(false))
                if (response.isSuccessful){
                    response.body()?.let {
                        emit(ApiResult.Success(data = it))
                    }?: emit(ApiResult.Error(message = "Unknown error occurred", code = 0))
                }else{
                    emit(ApiResult.Error(message = parserErrorBody(response.errorBody()), code = response.code()))
                }

            }.catch { error->
                Timber.e(error.localizedMessage)
                emit(ApiResult.Loading(false))
                delay(5)
                emit(ApiResult.Error(message = getErrorMessage(error), code = getErrorCode(error)))
            }
        }
    }

    private fun parserErrorBody(response: ResponseBody?):String{
        return response?.let {
            val errorMessage = JsonParser.parseString(it.string()).asJsonObject["message"].asString
            errorMessage.ifEmpty { "Whoops! Something went wrong" }
            errorMessage
        }?: "Unknown error occurred"
    }

    private fun getErrorMessage(throwable: Throwable?):String{
        when (throwable) {
            is SocketTimeoutException -> return "Whoops! connection time out, try again!"
            is IOException -> return "No internet connection, try again!"
            is HttpException -> return try {
                val errorJsonString = throwable.response()?.errorBody()?.string()
                val errorMessage = JsonParser.parseString(errorJsonString).asJsonObject["message"].asString
                errorMessage.ifEmpty { "Whoops! Something went wrong" }
            } catch (e: Exception) {
                "Unknown error occur, please try again!"
            }
        }
        return "Unknown error occur, please try again!"
    }

    private fun getErrorCode(throwable: Throwable?):Int{
        return if (throwable is HttpException) (throwable).code()
        else  0
    }

}