package fr.app.myludo.data

import com.squareup.moshi.Json

open class Game (
    var id : String = "",
    var title : String = "",
    // used to map img_src from the JSON to imgSrcUrl in our class
    @Json(name = "img_src") val imgSrcUrl: String = "https://www.myludo.fr/img/jeux/1664459680/jpg/ch/59045.jpg"
)