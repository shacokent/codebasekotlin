package com.dataoceanai.malllibrary.net

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*
import java.util.WeakHashMap

/**
 * Restful
 * 之所以使用String返回值，是为了通用
 */
interface RestService {

    @GET
    fun get(@Url url:String?,@QueryMap params: WeakHashMap<String,Any>?): Call<String>

    @FormUrlEncoded
    @POST
    fun post(@Url url:String?,@QueryMap params: WeakHashMap<String,Any>?): Call<String>

    @FormUrlEncoded
    @PUT
    fun put(@Url url:String?,@QueryMap params: WeakHashMap<String,Any>?): Call<String>

    @DELETE
    fun delete(@Url url:String?,@QueryMap params: WeakHashMap<String,Any>?): Call<String>

    //加@Streaming标签 ，是为了不会一次性把文件下载到内存里，而是下载一部分就写一部分
    @Streaming
    @GET
    fun download(@Url url:String?,@QueryMap params: WeakHashMap<String,Any>?): Call<ResponseBody>

    fun upload(@Url url:String?,@Part file: MultipartBody.Part?): Call<String>
}