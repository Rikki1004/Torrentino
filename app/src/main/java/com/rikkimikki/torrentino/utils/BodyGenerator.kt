package com.rikkimikki.torrentino.utils

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject

fun generateBody(operationName:String,variables:String,query:String): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        jsonObject.put("operationName", operationName)
        jsonObject.put("variables",JSONObject(variables))
        jsonObject.put("query",query)

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

fun getFilmsBody(filmID:Int): RequestBody{
    return generateBody(
        "FilmBaseInfo",
        String.format("{\"filmId\":%s,\"isAuthorized\":\"false\",\"kpCityId\":1,\"promotionsConfig\":{\"configVersion\":\"1.0\",\"platform\":\"kp\"},\"actorsLimit\":10,\"voiceOverActorsLimit\":5,\"relatedMoviesLimit\":14,\"mediaBillingTarget\":\"kp-0822\"}", filmID),
        "query FilmBaseInfo(\$filmId: Long!, \$isAuthorized: Boolean!, \$kpCityId: Int!, \$promotionsConfig: PromotionConfigFilterInput!, \$mediaBillingTarget: String!, \$actorsLimit: Int, \$voiceOverActorsLimit: Int, \$relatedMoviesLimit: Int) { film(id: \$filmId) { id contentId type isTvOnly top250 top10 shortDescription synopsis title { russian english original __typename } productionYear productionStatus productionStatusUpdateDate genres { id name slug __typename } ...FilmIsTicketsAvailable ott { preview { availableMetadata(filter: {isSupportedByClient: null}) { audio subtitles videoDescriptorNames __typename } ... on OttPreview_AbstractVideo { duration timing @include(if: \$isAuthorized) { current maximum __typename } __typename } __typename } promoTrailers: trailers(onlyPromo: true, limit: 1) { items { streamUrl __typename } __typename } ... on Ott_AbstractVideo { skippableFragments { startTime endTime type __typename } __typename } __typename } editorAnnotation countries { id name __typename } restriction { age mpaa __typename } mainTrailer { id title preview { avatarsUrl fallbackUrl __typename } duration createdAt isEmbedded streamUrl __typename } releaseOptions { isImax is3d __typename } cover { image { avatarsUrl fallbackUrl __typename } __typename } onlineViewOptions { descriptionWithPlaceholders descriptionSubtext subscriptionOffersBatchId subscriptionOffersPositionId __typename } viewOption { ...ViewOption __typename } actors: members(limit: \$actorsLimit, role: [ACTOR, CAMEO, UNCREDITED]) { items { person { id name originalName __typename } __typename } total __typename } voiceOverActors: members(limit: \$voiceOverActorsLimit, role: VOICEOVER) { items { person { id name originalName __typename } __typename } total __typename } tagline directors: members(role: DIRECTOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } writers: members(role: WRITER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } producers: members(role: PRODUCER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } operators: members(role: OPERATOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } composers: members(role: COMPOSER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } designers: members(role: [DESIGN, ART, COSTUMER, DECORATOR], limit: 13) { items { person { id name originalName __typename } __typename } __typename } filmEditors: members(role: EDITOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } boxOffice { budget { amount currency { symbol __typename } __typename } rusBox { amount currency { symbol __typename } __typename } usaBox { amount currency { symbol __typename } __typename } worldBox { amount currency { symbol __typename } __typename } marketing { amount currency { symbol __typename } __typename } __typename } ...MoviePoster rating { expectation { value count isActive __typename } imdb { value count isActive __typename } kinopoisk { value count isActive __typename } russianCritics { value count isActive __typename } worldwideCritics { value percent count isActive positiveCount negativeCount __typename } reviewCount { value count isActive __typename } __typename } duration keywords(limit: 0) { total __typename } awards(limit: 0) { total __typename } premieres(limit: 0) { total __typename } relatedMovies(limit: 0) { total __typename } images(limit: 0) { total __typename } ...MovieImagesStats sites(limit: 0) { total __typename } soundtrack(limit: 0) { total __typename } production(limit: 0) { total __typename } negativeCriticReviews: criticReviews(types: NEGATIVE, limit: 0) { total __typename } positiveCriticReviews: criticReviews(types: POSITIVE, limit: 0) { total __typename } audience(limit: 3) { total items { count country { id name __typename } __typename } __typename } releases { date releasers { id name __typename } type __typename } worldPremiere { incompleteDate { accuracy date __typename } __typename } distribution { rusRelease: releases(types: [CINEMA], rerelease: false, countryId: 2, limit: 1) { ...releasesInfoFragment __typename } digitalRelease: releases(types: [DIGITAL], limit: 1) { ...releasesInfoFragment __typename } reRelease: releases(types: [CINEMA], rerelease: true, countryId: 2, limit: 1) { ...releasesInfoFragment __typename } originals: releases(original: true, types: [DIGITAL], limit: 1) { ...OriginalsFragment __typename } __typename } filmMainAward: awards(isMain: true, limit: 15) { items { nomination { award { title slug year __typename } title __typename } win __typename } total __typename } filmAwardWins: awards(isMain: true, isWin: true, limit: 0) { total __typename } ...FilmUserData @include(if: \$isAuthorized) sequelsPrequels: relatedMovies(limit: \$relatedMoviesLimit, type: [BEFORE, AFTER], orderBy: PREMIERE_DATE_ASC) { total limit offset items { relationType movie { id title { russian english original __typename } countries { id name __typename } ...MoviePoster genres { id name slug __typename } rating { expectation { value count isActive __typename } kinopoisk { value count isActive __typename } __typename } viewOption { buttonText isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) purchasabilityStatus subscriptionPurchaseTag type posterWithRightholderLogo __typename } userData @include(if: \$isAuthorized) { voting { value __typename } __typename } ... on Film { productionYear __typename } ... on Video { productionYear __typename } ... on TvSeries { releaseYears { start end __typename } __typename } ... on TvShow { releaseYears { start end __typename } __typename } ... on MiniSeries { releaseYears { start end __typename } __typename } __typename } __typename } __typename } watchability { items { platform { name logo { avatarsUrl __typename } __typename } url __typename } __typename } ...MovieFactsCount ...MovieBloopersCount ...MovieUsersReviewsCount ...MovieMediaPostsCount ...MovieTrailersCount ...MovieCriticReviewsCount ...MovieSimilarMoviesCount ...MovieOriginalMoviesCount __typename } tvSeries(id: \$filmId) { id __typename } webPage(platform: DESKTOP) { kpFilmPage(filmId: \$filmId) { additionalInfoBlocks { ...BlocksConfigFragment __typename } sidebarBlocks { ...BlocksConfigFragment __typename } footer { ...FooterConfigData __typename } htmlMeta { ...OgImage __typename } __typename } __typename } ...Promotions @include(if: \$isAuthorized) } fragment FilmIsTicketsAvailable on Film { isTicketsAvailable: isTicketsAvailableByKpCityId(kpCityId: \$kpCityId) __typename } fragment ViewOption on ViewOption { type purchasabilityStatus buttonText originalButtonText subscriptionPurchaseTag promotionActionType rightholderLogoUrlForPoster posterWithRightholderLogo isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) isWatchable(filter: {anyDevice: true, anyRegion: false}) watchabilityStatus promotionIcons { avatarsUrl fallbackUrl __typename } mainPromotionAbsoluteAmount { amount __typename } mastercardPromotionAbsoluteAmount { amount __typename } availabilityAnnounce { ...AvailabilityAnnounce __typename } subscriptionCompositeOffers(mediaBillingTarget: \$mediaBillingTarget) { ...SubscriptionCompositeOffers __typename } __typename } fragment AvailabilityAnnounce on AvailabilityAnnounce { availabilityDate groupPeriodType type announcePromise __typename } fragment SubscriptionCompositeOffers on SubscriptionCompositeOffers { batchPositionId offers { compositeOffer { structureType positionId optionOffers { text additionText name option { name __typename } __typename } tariffOffer { text additionText name tariff { name __typename } __typename } __typename } customPayload { text: overridedText additionText: overridedAdditionalText overridedTarget __typename } __typename } __typename } fragment MoviePoster on Movie { poster { avatarsUrl fallbackUrl __typename } __typename } fragment MovieImagesStats on Movie { concepts: images(types: [CONCEPT], limit: 0) { total __typename } covers: images(types: [COVER], limit: 0) { total __typename } fanarts: images(types: [FAN_ART], limit: 0) { total __typename } posters: images(types: [POSTER], limit: 0) { total __typename } promos: images(types: [PROMO], limit: 0) { total __typename } screenshots: images(types: [SCREENSHOT], limit: 0) { total __typename } shootings: images(types: [SHOOTING], limit: 0) { total __typename } stills: images(types: [STILL], limit: 0) { total __typename } wallpapers: images(types: [WALLPAPER], limit: 0) { total __typename } __typename } fragment releasesInfoFragment on PagingList_Release { items { date { accuracy date __typename } companies { id slugId slug displayName __typename } __typename } __typename } fragment OriginalsFragment on PagingList_Release { items { companies { displayName id originalsMovieList { id url __typename } __typename } __typename } __typename } fragment FilmUserData on Film { userData { folders { id name __typename } voting { value votedAt __typename } expectation { value __typename } note { value makeDate __typename } watchStatuses { notInterested { value __typename } watched { value __typename } __typename } __typename } __typename } fragment MovieFactsCount on Movie { factsCount: trivias(type: FACT, limit: 0) { total __typename } __typename } fragment MovieBloopersCount on Movie { bloopersCount: trivias(type: BLOOPER, limit: 0) { total __typename } __typename } fragment MovieUsersReviewsCount on Movie { usersReviewsCount: userReviews(limit: 0) { total __typename } __typename } fragment MovieMediaPostsCount on Movie { mediaPostsCount: post(limit: 0) { total __typename } __typename } fragment MovieTrailersCount on Movie { trailersCount: trailers(limit: 0) { total __typename } __typename } fragment MovieCriticReviewsCount on Movie { criticReviewsCount: criticReviews(limit: 0) { total __typename } __typename } fragment MovieSimilarMoviesCount on Movie { similarMoviesCount: userRecommendations(limit: 0) { total __typename } __typename } fragment MovieOriginalMoviesCount on Movie { distribution { releases(original: true, types: [DIGITAL], limit: 1) { items { companies { originalsMovieList { movies(supportedItemTypes: [MOVIE_LIST_ITEM], limit: 0) { total __typename } __typename } __typename } __typename } __typename } __typename } __typename } fragment BlocksConfigFragment on BlockConfiguration { type params { useClientRender __typename } __typename } fragment FooterConfigData on FooterConfiguration { socialNetworkLinks { icon { avatarsUrl __typename } url __typename } appMarketLinks { icon { avatarsUrl __typename } url __typename } links { title url __typename } __typename } fragment OgImage on HtmlMeta { openGraph { image { avatarsUrl __typename } __typename } __typename } fragment Promotions on Query { promotions(promotionConfigFilter: \$promotionsConfig) { config id type __typename } __typename } "
    )
}
fun getTvSerieBody(tvSerieID:Int): RequestBody{
    return generateBody(
        "TvSeriesBaseInfo",
        String.format("{\"tvSeriesId\":%s,\"isAuthorized\":false,\"promotionsConfig\":{\"configVersion\":\"1.0\",\"platform\":\"kp\"},\"actorsLimit\":10,\"voiceOverActorsLimit\":5,\"relatedMoviesLimit\":14,\"mediaBillingTarget\":\"kp-0822\"}", tvSerieID),
        "query TvSeriesBaseInfo(\$tvSeriesId: Long!, \$isAuthorized: Boolean!, \$promotionsConfig: PromotionConfigFilterInput!, \$mediaBillingTarget: String!, \$actorsLimit: Int, \$voiceOverActorsLimit: Int, \$relatedMoviesLimit: Int) { tvSeries(id: \$tvSeriesId) { id contentId title { russian original __typename } productionYear productionStatus shortDescription productionStatusUpdateDate top250 top10 synopsis releaseYears { start end __typename } genres { id name slug __typename } countries { id name __typename } seasons { total __typename } restriction { age mpaa __typename } types { name __typename } cover { image { avatarsUrl fallbackUrl __typename } __typename } onlineViewOptions { descriptionWithPlaceholders descriptionSubtext subscriptionOffersBatchId subscriptionOffersPositionId __typename } viewOption { ...ViewOption __typename } ott { preview { availableMetadata(filter: {isSupportedByClient: null}) { audio subtitles videoDescriptorNames __typename } ...OttPreviewAbstractSeries __typename } promoTrailers: trailers(onlyPromo: true, limit: 1) { items { streamUrl __typename } __typename } __typename } editorAnnotation mainTrailer { id title preview { avatarsUrl fallbackUrl __typename } duration createdAt isEmbedded streamUrl __typename } actors: members(limit: \$actorsLimit, role: [ACTOR, CAMEO, UNCREDITED]) { items { person { id name originalName __typename } __typename } total __typename } voiceOverActors: members(limit: \$voiceOverActorsLimit, role: VOICEOVER) { items { person { id name originalName __typename } __typename } total __typename } tagline directors: members(role: DIRECTOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } writers: members(role: WRITER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } producers: members(role: PRODUCER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } operators: members(role: OPERATOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } composers: members(role: COMPOSER, limit: 4) { items { person { id name originalName __typename } __typename } __typename } designers: members(role: [DESIGN, ART, COSTUMER, DECORATOR], limit: 4) { items { person { id name originalName __typename } __typename } __typename } filmEditors: members(role: EDITOR, limit: 4) { items { person { id name originalName __typename } __typename } __typename } boxOffice { budget { amount currency { symbol __typename } __typename } rusBox { amount currency { symbol __typename } __typename } usaBox { amount currency { symbol __typename } __typename } worldBox { amount currency { symbol __typename } __typename } marketing { amount currency { symbol __typename } __typename } __typename } ...MoviePoster rating { expectation { value count isActive __typename } imdb { value count isActive __typename } kinopoisk { value count isActive __typename } russianCritics { value count isActive __typename } worldwideCritics { value count isActive __typename } reviewCount { value count isActive __typename } __typename } totalDuration seriesDuration keywords { total __typename } awards(limit: 0) { total __typename } premieres(limit: 0) { total __typename } relatedMovies(limit: 0) { total __typename } images(limit: 0) { total __typename } ...MovieImagesStats sites(limit: 0) { total __typename } soundtrack(limit: 0) { total __typename } production(limit: 0) { total __typename } episodes(limit: 0) { total __typename } negativeCriticReviews: criticReviews(types: NEGATIVE, limit: 0) { total __typename } positiveCriticReviews: criticReviews(types: POSITIVE, limit: 0) { total __typename } releases { date releasers { id name __typename } type __typename } worldPremiere { incompleteDate { accuracy date __typename } __typename } distribution { rusRelease: releases(types: [CINEMA], rerelease: false, countryId: 2, limit: 1) { ...releasesInfoFragment __typename } digitalRelease: releases(types: [DIGITAL], limit: 1) { ...releasesInfoFragment __typename } reRelease: releases(types: [CINEMA], rerelease: true, countryId: 2, limit: 1) { ...releasesInfoFragment __typename } originals: releases(original: true, types: [DIGITAL], limit: 1) { ...OriginalsFragment __typename } __typename } userReviews { total __typename } filmMainAward: awards(isMain: true, limit: 15) { items { nomination { award { title slug year __typename } title __typename } win __typename } total __typename } filmMainAwardWins: awards(limit: 0, isMain: true, isWin: true) { total __typename } ...TvSeriesUserData @include(if: \$isAuthorized) sequelsPrequels: relatedMovies(limit: \$relatedMoviesLimit, type: [BEFORE, AFTER], orderBy: PREMIERE_DATE_ASC) { total limit offset items { relationType movie { id title { russian english original __typename } countries { id name __typename } ...MoviePoster genres { id name slug __typename } rating { expectation { value count isActive __typename } kinopoisk { value count isActive __typename } __typename } viewOption { buttonText isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) purchasabilityStatus subscriptionPurchaseTag type posterWithRightholderLogo __typename } userData @include(if: \$isAuthorized) { voting { value __typename } __typename } ... on Film { productionYear __typename } ... on Video { productionYear __typename } ... on TvSeries { releaseYears { start end __typename } __typename } ... on TvShow { releaseYears { start end __typename } __typename } ... on MiniSeries { releaseYears { start end __typename } __typename } __typename } __typename } __typename } watchability { items { platform { name logo { avatarsUrl __typename } __typename } url __typename } __typename } ...MovieFactsCount ...MovieBloopersCount ...MovieUsersReviewsCount ...MovieMediaPostsCount ...MovieTrailersCount ...MovieCriticReviewsCount ...MovieSimilarMoviesCount ...MovieOriginalMoviesCount __typename } webPage(platform: DESKTOP) { kpTvSeriesPage(tvSeriesId: \$tvSeriesId) { additionalInfoBlocks { ...BlocksConfigFragment __typename } sidebarBlocks { ...BlocksConfigFragment __typename } footer { ...FooterConfigData __typename } htmlMeta { ...OgImage __typename } __typename } __typename } ...Promotions @include(if: \$isAuthorized) } fragment ViewOption on ViewOption { type purchasabilityStatus buttonText originalButtonText subscriptionPurchaseTag promotionActionType rightholderLogoUrlForPoster posterWithRightholderLogo isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) isWatchable(filter: {anyDevice: true, anyRegion: false}) watchabilityStatus promotionIcons { avatarsUrl fallbackUrl __typename } mainPromotionAbsoluteAmount { amount __typename } mastercardPromotionAbsoluteAmount { amount __typename } availabilityAnnounce { ...AvailabilityAnnounce __typename } subscriptionCompositeOffers(mediaBillingTarget: \$mediaBillingTarget) { ...SubscriptionCompositeOffers __typename } __typename } fragment AvailabilityAnnounce on AvailabilityAnnounce { availabilityDate groupPeriodType type announcePromise __typename } fragment SubscriptionCompositeOffers on SubscriptionCompositeOffers { batchPositionId offers { compositeOffer { structureType positionId optionOffers { text additionText name option { name __typename } __typename } tariffOffer { text additionText name tariff { name __typename } __typename } __typename } customPayload { text: overridedText additionText: overridedAdditionalText overridedTarget __typename } __typename } __typename } fragment OttPreviewAbstractSeries on OttPreview_AbstractSeries { firstEpisode { seasonNumber episodeNumber __typename } nextEpisode(fallbackToFirstEpisode: false) @include(if: \$isAuthorized) { title { original russian __typename } seasonNumber episodeNumber duration timing { current maximum __typename } __typename } __typename } fragment MoviePoster on Movie { poster { avatarsUrl fallbackUrl __typename } __typename } fragment MovieImagesStats on Movie { concepts: images(types: [CONCEPT], limit: 0) { total __typename } covers: images(types: [COVER], limit: 0) { total __typename } fanarts: images(types: [FAN_ART], limit: 0) { total __typename } posters: images(types: [POSTER], limit: 0) { total __typename } promos: images(types: [PROMO], limit: 0) { total __typename } screenshots: images(types: [SCREENSHOT], limit: 0) { total __typename } shootings: images(types: [SHOOTING], limit: 0) { total __typename } stills: images(types: [STILL], limit: 0) { total __typename } wallpapers: images(types: [WALLPAPER], limit: 0) { total __typename } __typename } fragment releasesInfoFragment on PagingList_Release { items { date { accuracy date __typename } companies { id slugId slug displayName __typename } __typename } __typename } fragment OriginalsFragment on PagingList_Release { items { companies { displayName id originalsMovieList { id url __typename } __typename } __typename } __typename } fragment TvSeriesUserData on TvSeries { userData { folders { id name __typename } voting { value votedAt __typename } expectation { value __typename } note { value makeDate __typename } watchStatuses { notInterested { value __typename } watched { value __typename } __typename } __typename } __typename } fragment MovieFactsCount on Movie { factsCount: trivias(type: FACT, limit: 0) { total __typename } __typename } fragment MovieBloopersCount on Movie { bloopersCount: trivias(type: BLOOPER, limit: 0) { total __typename } __typename } fragment MovieUsersReviewsCount on Movie { usersReviewsCount: userReviews(limit: 0) { total __typename } __typename } fragment MovieMediaPostsCount on Movie { mediaPostsCount: post(limit: 0) { total __typename } __typename } fragment MovieTrailersCount on Movie { trailersCount: trailers(limit: 0) { total __typename } __typename } fragment MovieCriticReviewsCount on Movie { criticReviewsCount: criticReviews(limit: 0) { total __typename } __typename } fragment MovieSimilarMoviesCount on Movie { similarMoviesCount: userRecommendations(limit: 0) { total __typename } __typename } fragment MovieOriginalMoviesCount on Movie { distribution { releases(original: true, types: [DIGITAL], limit: 1) { items { companies { originalsMovieList { movies(supportedItemTypes: [MOVIE_LIST_ITEM], limit: 0) { total __typename } __typename } __typename } __typename } __typename } __typename } __typename } fragment BlocksConfigFragment on BlockConfiguration { type params { useClientRender __typename } __typename } fragment FooterConfigData on FooterConfiguration { socialNetworkLinks { icon { avatarsUrl __typename } url __typename } appMarketLinks { icon { avatarsUrl __typename } url __typename } links { title url __typename } __typename } fragment OgImage on HtmlMeta { openGraph { image { avatarsUrl __typename } __typename } __typename } fragment Promotions on Query { promotions(promotionConfigFilter: \$promotionsConfig) { config id type __typename } __typename } "
    )
}
fun getSearchBody(q:String):RequestBody{
    return generateBody(
        "SuggestSearch",
        String.format("{keyword: \"%s\", yandexCityId: 213, limit: 10}", q),
        "query SuggestSearch(\$keyword: String!, \$yandexCityId: Int, \$limit: Int) { suggest(keyword: \$keyword) { top(yandexCityId: \$yandexCityId, limit: \$limit) { topResult { global { ...SuggestMovieItem ...SuggestPersonItem ...SuggestCinemaItem ...SuggestMovieListItem __typename } __typename } movies { movie { ...SuggestMovieItem __typename } __typename } persons { person { ...SuggestPersonItem __typename } __typename } cinemas { cinema { ...SuggestCinemaItem __typename } __typename } movieLists { movieList { ...SuggestMovieListItem __typename } __typename } __typename } __typename } } fragment SuggestMovieItem on Movie { id title { russian original __typename } rating { kinopoisk { isActive value __typename } __typename } poster { avatarsUrl fallbackUrl __typename } viewOption { buttonText isAvailableOnline: isWatchable(filter: {anyDevice: false, anyRegion: false}) purchasabilityStatus subscriptionPurchaseTag type availabilityAnnounce { groupPeriodType announcePromise availabilityDate type __typename } __typename } ... on Film { type productionYear __typename } ... on TvSeries { releaseYears { end start __typename } __typename } ... on TvShow { releaseYears { end start __typename } __typename } ... on MiniSeries { releaseYears { end start __typename } __typename } __typename } fragment SuggestPersonItem on Person { id name originalName birthDate poster { avatarsUrl fallbackUrl __typename } __typename } fragment SuggestCinemaItem on Cinema { id ctitle: title city { id name geoId __typename } __typename } fragment SuggestMovieListItem on MovieListMeta { id cover { avatarsUrl __typename } coverBackground { avatarsUrl __typename } name url description movies(limit: 0) { total __typename } __typename } "
    )
}


fun getAddTorrentBody(magnet: String,poster:String,title:String): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        jsonObject.put("action", "add")
        jsonObject.put("link", magnet)
        jsonObject.put("poster", poster)
        jsonObject.put("save_to_db", true)
        jsonObject.put("title", title)
        body = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}

fun getControllerBody(action: String,position: String=""): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        jsonObject.put("action", action)
        jsonObject.put("position", position)
        body = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}

fun getTorrentsBody(): RequestBody {
    return generateCustomBody("action" to "list")
}
fun getDeleteTorrentsBody(hash: String): RequestBody {
    return generateCustomBody("action" to "rem","hash" to hash)
}

fun getPlayTorrentBody(title: String, chosenSeries: Int, url:String, hash:String): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        jsonObject.put("playlist", "")
        jsonObject.put("action", "play")
        jsonObject.put("ids", "" + chosenSeries)
        jsonObject.put("name", title)
        jsonObject.put("hash", hash)
        jsonObject.put("m3u", ("http://$url:$BASE_TORRSERVER_PORT/stream/no$chosenSeries?index=$chosenSeries&play&save&link=$hash"))

        body = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}

fun generateCustomBody(vararg args: Pair<String,String>): RequestBody {
    var body: RequestBody? = null
    val jsonObject = JSONObject()
    try {
        for (i in args){
            jsonObject.put(i.first,i.second)
        }
        body = jsonObject.toString()
            .toRequestBody("application/json; charset=utf-8".toMediaTypeOrNull())
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return body!!
}