package com.rikkimikki.torrentino.utils

import com.rikkimikki.torrentino.domain.pojo.category.Category
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun generateBody(operationName:String,variables:String,query:String): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        jsonObject.put("operationName", operationName)
        jsonObject.put(
            "variables",
            JSONObject(variables)
        )
        jsonObject.put(
            "query",
            query
        )
        body = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}

fun getCategoriesBody(): RequestBody{
    return generateBody(
        "MovieListCategory",
        "{\"id\":8,\"limit\":30,\"offset\":0,\"withUserData\":\"false\"}",
        "query MovieListCategory(\$id: Int!, \$limit: Int, \$offset: Int, \$withUserData: Boolean!) { movieListCategory(id: \$id) { id name movieLists(limit: \$limit, offset: \$offset) { total items { name slug movies { total __typename } cover { avatarsUrl __typename } coverBackground { avatarsUrl __typename } currentFilters { booleanFilterValues { filterId value __typename } singleSelectFilterValues { filterId value __typename } __typename } availableFilters(limit: 1, offset: 0, filter: {filterIds: [\"top\"]}) { items { ... on BooleanFilter { enabled __typename } __typename } __typename } userData @include(if: \$withUserData) { watched __typename } __typename } __typename } __typename } } "
    )
}

fun getFilmsFromCategoryBody(category: String,offset:Int): RequestBody{
    return generateBody(
        "MovieDesktopListPage",
        String.format("{\"slug\": \"\", \"platform\": \"DESKTOP\", \"regionId\": 213, \"withUserData\": \"false\", \"supportedFilterTypes\": [\"BOOLEAN\", \"SINGLE_SELECT\"], \"filters\": {\"booleanFilterValues\": [], \"intRangeFilterValues\": [], \"singleSelectFilterValues\": [{\"filterId\": \"genre\", \"value\": \"%s\"}], \"multiSelectFilterValues\": [], \"realRangeFilterValues\": []}, \"singleSelectFiltersLimit\": 250, \"singleSelectFiltersOffset\": 0, \"moviesLimit\": 50, \"moviesOffset\": %s, \"moviesOrder\": \"POSITION_ASC\", \"supportedItemTypes\": [\"COMING_SOON_MOVIE_LIST_ITEM\", \"MOVIE_LIST_ITEM\", \"TOP_MOVIE_LIST_ITEM\", \"POPULAR_MOVIE_LIST_ITEM\", \"MOST_PROFITABLE_MOVIE_LIST_ITEM\", \"MOST_EXPENSIVE_MOVIE_LIST_ITEM\", \"BOX_OFFICE_MOVIE_LIST_ITEM\", \"OFFLINE_AUDIENCE_MOVIE_LIST_ITEM\", \"RECOMMENDATION_MOVIE_LIST_ITEM\"]}", category,offset),
        "query MovieDesktopListPage(\$slug: String!, \$platform: WebClientPlatform!, \$withUserData: Boolean!, \$regionId: Int!, \$supportedFilterTypes: [FilterType]!, \$filters: FilterValuesInput, \$singleSelectFiltersLimit: Int!, \$singleSelectFiltersOffset: Int!, \$moviesLimit: Int, \$moviesOffset: Int, \$moviesOrder: MovieListItemOrderBy, \$supportedItemTypes: [MovieListItemType]) { movieListBySlug(slug: \$slug, supportedFilterTypes: \$supportedFilterTypes, filters: \$filters) { id name description cover { avatarsUrl __typename } ...MovieListCompositeName ...MovieListAvailableFilters ...MovieList ...DescriptionLink __typename } webPage(platform: \$platform) { kpMovieListPage(movieListSlug: \$slug) { htmlMeta { ...OgImage __typename } footer { ...FooterConfigData __typename } featuring { ...MovieListFeaturingData __typename } __typename } __typename } } fragment MovieListCompositeName on MovieListMeta { compositeName { parts { ... on FilterReferencedMovieListNamePart { filterValue { ... on SingleSelectFilterValue { filterId __typename } __typename } name __typename } ... on StaticMovieListNamePart { name __typename } __typename } __typename } __typename } fragment MovieListAvailableFilters on MovieListMeta { availableFilters { items { ... on BooleanFilter { ...ToggleFilter __typename } ... on SingleSelectFilter { ...SingleSelectFilters __typename } __typename } __typename } __typename } fragment ToggleFilter on BooleanFilter { id enabled name { russian __typename } __typename } fragment SingleSelectFilters on SingleSelectFilter { id name { russian __typename } hint { russian __typename } values(offset: \$singleSelectFiltersOffset, limit: \$singleSelectFiltersLimit) { items { name { russian __typename } selectable value __typename } __typename } __typename } fragment MovieList on MovieListMeta { movies(limit: \$moviesLimit, offset: \$moviesOffset, orderBy: \$moviesOrder, supportedItemTypes: \$supportedItemTypes) { total items { movie { id title { russian original __typename } poster { avatarsUrl fallbackUrl __typename } countries { id name __typename } genres { id name __typename } cast: members(role: [ACTOR], limit: 3) { items { details person { name originalName __typename } __typename } __typename } directors: members(role: [DIRECTOR], limit: 1) { items { details person { name originalName __typename } __typename } __typename } url rating { kinopoisk { isActive count value __typename } expectation { isActive count value __typename } __typename } mainTrailer { id __typename } viewOption { buttonText originalButtonText promotionIcons { avatarsUrl fallbackUrl __typename } isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) purchasabilityStatus subscriptionPurchaseTag type rightholderLogoUrlForPoster availabilityAnnounce { availabilityDate type groupPeriodType announcePromise __typename } __typename } isTicketsAvailable(regionId: \$regionId) ... on Film { productionYear duration isShortFilm top250 __typename } ... on TvSeries { releaseYears { start end __typename } seriesDuration totalDuration top250 __typename } ... on MiniSeries { releaseYears { start end __typename } seriesDuration totalDuration top250 __typename } ... on TvShow { releaseYears { start end __typename } seriesDuration totalDuration top250 __typename } ... on Video { productionYear duration isShortFilm __typename } ...MovieListUserData @include(if: \$withUserData) __typename } ... on TopMovieListItem { position positionDiff rate votes __typename } ... on MostProfitableMovieListItem { boxOffice { amount __typename } budget { amount __typename } ratio __typename } ... on MostExpensiveMovieListItem { budget { amount __typename } __typename } ... on OfflineAudienceMovieListItem { viewers __typename } ... on PopularMovieListItem { positionDiff __typename } ... on BoxOfficeMovieListItem { boxOffice { amount __typename } __typename } ... on RecommendationMovieListItem { __typename } ... on ComingSoonMovieListItem { releaseDate { date accuracy __typename } __typename } __typename } __typename } __typename } fragment MovieListUserData on Movie { userData { folders { id name public __typename } watchStatuses { notInterested { value __typename } watched { value __typename } __typename } voting { value votedAt __typename } __typename } __typename } fragment DescriptionLink on MovieListMeta { descriptionLink { title url __typename } __typename } fragment OgImage on HtmlMeta { openGraph { image { avatarsUrl __typename } __typename } __typename } fragment FooterConfigData on FooterConfiguration { socialNetworkLinks { icon { avatarsUrl __typename } url __typename } appMarketLinks { icon { avatarsUrl __typename } url __typename } links { title url __typename } __typename } fragment MovieListFeaturingData on MovieListFeaturing { items { title url __typename } __typename } "
    )
}

fun generateCustomBody(vararg args: Pair<String,String>): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        for (i in args){
            jsonObject.put(i.first,i.second)
        }
        body = RequestBody.create(
            "application/json; charset=utf-8".toMediaTypeOrNull(),
            jsonObject.toString()
        )
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}