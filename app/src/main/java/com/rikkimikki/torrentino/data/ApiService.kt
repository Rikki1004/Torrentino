package com.rikkimikki.torrentino.data

import com.rikkimikki.torrentino.domain.pojo.category.CategoryResponse
import com.rikkimikki.torrentino.domain.pojo.film.FilmsResponse
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.FilmInfoResponse
import com.rikkimikki.torrentino.domain.pojo.search.SearchResponce
import com.rikkimikki.torrentino.domain.pojo.server.*
import com.rikkimikki.torrentino.domain.pojo.torrent.TorrentResponse
import com.rikkimikki.torrentino.domain.pojo.tvSerie.TvSerieInfoResponce
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @Headers("service-id: 25", "Content-Type: application/json") //@Headers("service-id: 25")
    @POST("graphql/")
    fun  //Observable<PreFilm> getFilms(@Body String params, @Query("operationName") String api_key);
            getFilms(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<FilmsResponse>

    //Observable<PreFilm> getFilms(@Body JSONObject json, @Query("operationName") String api_key);
    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getCategories(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<CategoryResponse>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getFilmInfo(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<FilmInfoResponse>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getTvSerieInfo(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<TvSerieInfoResponce>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getSeacrhRequest(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<SearchResponce>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}/api")
    fun getTorrents(
        @Path("ip") ip: String,
        @Query("req") req: String,
        @Query("q") q: String
    ): Observable<TorrentResponse>


    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}/player")
    fun playTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<Response<Void>>

    //Observable<Void> playTorrent(@Body RequestBody params);
    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun remTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<Response<Void>>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun getStore(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<List<GetStoreResponce>>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}/player")
    fun getPosition(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<GetPositionResponce>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}/player")
    fun getVolume(@Path("ip") ip: String, @Body params: RequestBody): Observable<VolumeResponce>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun sendControls(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<Response<Void>>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{server}/ping")
    fun findServer(@Path("server") path: String): Observable<FindServerResponce>




    //@Headers("service-id: 25", "Content-Type: application/json")
    @GET("https://{ip}/get_new.php")
    fun searchTorrents(
        @Path("ip") ip: String,
        @Query("secret") secret: String,
        @Query("hash") hash: String,
        @Query("q") q: String,
    ): Observable<ResponseBody>


    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun addTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<AddTorrentResponse>
}