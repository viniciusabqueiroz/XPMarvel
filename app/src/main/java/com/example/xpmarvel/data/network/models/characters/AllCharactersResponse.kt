package com.example.xpmarvel.data.network.models.characters

import android.annotation.SuppressLint
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@SuppressLint("ParcelCreator")
@Parcelize
data class AllCharactersResponse(
    @SerializedName("attributionHTML") val attributionHTML: String? = null,
    @SerializedName("attributionText") val attributionText: String? = null,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("copyright") val copyright: String? = null,
    @SerializedName("data") val `data`: Data? = null,
    @SerializedName("etag") val etag: String? = null,
    @SerializedName("status") val status: String? = null
) : Parcelable {
    @SuppressLint("ParcelCreator")
    @Parcelize
    data class Data(
        @SerializedName("count") val count: Int? = null,
        @SerializedName("limit") val limit: Int? = null,
        @SerializedName("offset") val offset: Int? = null,
        @SerializedName("results") val results: List<Result?>? = null,
        @SerializedName("total") val total: Int? = null
    ) : Parcelable {
        @SuppressLint("ParcelCreator")
        @Parcelize
        data class Result(
            @SerializedName("comics") val comics: Comics? = null,
            @SerializedName("description") val description: String? = null,
            @SerializedName("events") val events: Events? = null,
            @SerializedName("id") val id: Int? = null,
            @SerializedName("modified") val modified: String? = null,
            @SerializedName("name") val name: String? = null,
            @SerializedName("resourceURI") val resourceURI: String? = null,
            @SerializedName("series") val series: Series? = null,
            @SerializedName("stories") val stories: Stories? = null,
            @SerializedName("thumbnail") val thumbnail: Thumbnail? = null,
            @SerializedName("urls") val urls: List<Url?>? = null
        ) : Parcelable {
            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Comics(
                @SerializedName("available") val available: Int? = null,
                @SerializedName("collectionURI") val collectionURI: String? = null,
                @SerializedName("items") val items: List<Item?>? = null,
                @SerializedName("returned") val returned: Int? = null
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Item(
                    @SerializedName("name") val name: String? = null,
                    @SerializedName("resourceURI") val resourceURI: String? = null
                ) : Parcelable
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Events(
                @SerializedName("available") val available: Int? = null,
                @SerializedName("collectionURI") val collectionURI: String? = null,
                @SerializedName("items") val items: List<Item?>? = null,
                @SerializedName("returned") val returned: Int? = null
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Item(
                    @SerializedName("name") val name: String? = null,
                    @SerializedName("resourceURI") val resourceURI: String? = null
                ) : Parcelable
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Series(
                @SerializedName("available") val available: Int? = null,
                @SerializedName("collectionURI") val collectionURI: String? = null,
                @SerializedName("items") val items: List<Item?>? = null,
                @SerializedName("returned") val returned: Int? = null
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Item(
                    @SerializedName("name") val name: String? = null,
                    @SerializedName("resourceURI") val resourceURI: String? = null
                ) : Parcelable
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Stories(
                @SerializedName("available") val available: Int? = null,
                @SerializedName("collectionURI") val collectionURI: String? = null,
                @SerializedName("items") val items: List<Item?>? = null,
                @SerializedName("returned") val returned: Int? = null
            ) : Parcelable {
                @SuppressLint("ParcelCreator")
                @Parcelize
                data class Item(
                    @SerializedName("name") val name: String? = null,
                    @SerializedName("resourceURI") val resourceURI: String? = null,
                    @SerializedName("type") val type: String? = null
                ) : Parcelable
            }

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Thumbnail(
                @SerializedName("extension") val extension: String? = null,
                @SerializedName("path") val path: String? = null
            ) : Parcelable

            @SuppressLint("ParcelCreator")
            @Parcelize
            data class Url(
                @SerializedName("type") val type: String? = null,
                @SerializedName("url") val url: String? = null
            ) : Parcelable
        }
    }
}