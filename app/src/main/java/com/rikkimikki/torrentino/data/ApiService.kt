package com.rikkimikki.torrentino.data

import com.rikkimikki.torrentino.domain.pojo.anime.AnimeResponse
import com.rikkimikki.torrentino.domain.pojo.anime.AnimeResponseMain
import com.rikkimikki.torrentino.domain.pojo.category.CategoryResponse
import com.rikkimikki.torrentino.domain.pojo.film.FilmsResponse
import com.rikkimikki.torrentino.domain.pojo.filmDetailInfo.FilmInfoResponse
import com.rikkimikki.torrentino.domain.pojo.search.SearchResponse
import com.rikkimikki.torrentino.domain.pojo.server.*
import com.rikkimikki.torrentino.domain.pojo.tvSerie.TvSerieInfoResponce
import io.reactivex.Observable
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getFilms(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<FilmsResponse>

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
    fun getFilmInfo2(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<FilmInfoResponse>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getTvSerieInfo(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<TvSerieInfoResponce>

    @Headers("Content-Type: application/json")
    @GET("https://{ip}/v3/getTitle")
    fun getAnimeInfo(
        @Path("ip") ip: String,
        @Query("id") id: Int,
    ): Observable<AnimeResponse>

    @Headers("service-id: 25", "Content-Type: application/json")
    @POST("graphql/")
    fun getSeacrhRequest(
        @Body params: RequestBody,
        @Query("operationName") graph: String
    ): Observable<SearchResponse>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8091/player")
    fun playTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<Response<Void>>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun remTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<Response<Void>>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun getStore(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<List<GetStoreResponse>>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8091/player")
    fun getPosition(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<GetPositionResponse>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8091/player")
    fun getVolume(@Path("ip") ip: String, @Body params: RequestBody): Observable<VolumeResponse>

    @GET("https://{ip}/get_new.php")
    fun searchTorrents(
        @Path("ip") ip: String,
        @Query("secret") secret: String,
        @Query("hash") hash: String,
        @Query("q") q: String,
    ): Observable<ResponseBody>

    @Headers("Content-Type: application/json")
    @POST("http://{ip}:8090/torrents")
    fun addTorrent(
        @Path("ip") ip: String,
        @Body params: RequestBody
    ): Observable<AddTorrentResponse>

    @Headers("Content-Type: application/json")
    @GET("https://{ip}/v3/searchTitles")
    fun searchAnime(
        @Path("ip") ip: String,
        @Query("search") q: String,
    ): Observable<AnimeResponseMain>
}