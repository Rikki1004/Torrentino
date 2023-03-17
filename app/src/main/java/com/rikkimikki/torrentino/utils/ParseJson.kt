package com.rikkimikki.torrentino.utils

import com.rikkimikki.torrentino.domain.pojo.server.MiniTorrent
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject

fun dataToMiniTorrents(data:String):List<MiniTorrent>{
    val result = arrayListOf<MiniTorrent>()
    try {
        val torrents: JSONArray = JSONObject(data).getJSONObject("TorrServer").getJSONArray("Files")
        if (torrents.length() == 0)
            result.add(MiniTorrent("film",1))
        for (i in 0 until torrents.length()) {
            result.add(
                MiniTorrent(
                    torrents.getJSONObject(i)["path"].toString(),
                    torrents.getJSONObject(i)["id"].toString().toInt()
            ))
        }
    } catch (e: JSONException) {
        e.printStackTrace()
    }
    return result
}
//{"TorrServer":{"Files":[{"id":1,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[01]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":292855150},{"id":2,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[02]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":421388434},{"id":3,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[03]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":458005319},{"id":4,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[04]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":391612363},{"id":5,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[05]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":380765006},{"id":6,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[06]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":378617408},{"id":7,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[07]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":361819144},{"id":8,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[08]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":402068408},{"id":9,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[09]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":372594745},{"id":10,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[10]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":236482702},{"id":11,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[11]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":315161022},{"id":12,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[12]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":329637371},{"id":13,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[13]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":443049138},{"id":14,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[14]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":339350087},{"id":15,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[15]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":398398653},{"id":16,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[16]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":474281387},{"id":17,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[17]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":403843781},{"id":18,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[18]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":333054050},{"id":19,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[19]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":404023632},{"id":20,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[20]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":409947552},{"id":21,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[21]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":291937191},{"id":22,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[22]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":428114850},{"id":23,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[23]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":382927398},{"id":24,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[24]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":380739762},{"id":25,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[25]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":428494703},{"id":26,"path":"JoJo no Kimyou na Bouken - AniLibria.TV [BDRip 1080p HEVC]/JoJo_no_Kimyou_na_Bouken_[26]_[AniLibria_TV]_[BDRip_1080p_HEVC].mkv","length":356408358}]}}
