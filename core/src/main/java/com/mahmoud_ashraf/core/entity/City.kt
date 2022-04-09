package com.mahmoud_ashraf.core.entity
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


data class CitiesResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("time")
    val time: Int
) {
    data class Data(
        @SerializedName("items")
        val items: List<Item>,
        @SerializedName("pagination")
        val pagination: Pagination
    )

@Parcelize
    data class Item(
        @SerializedName("country")
        val country: Country,
        @SerializedName("id")
        val id: Int,
        @SerializedName("lat")
        val lat: Double?,
        @SerializedName("lng")
        val lng: Double?,
        @SerializedName("name")
        val name: String,
    ) : Parcelable

    data class Pagination(
        @SerializedName("current_page")
        val currentPage: Int,
        @SerializedName("last_page")
        val lastPage: Int,
        @SerializedName("per_page")
        val perPage: Int,
        @SerializedName("total")
        val total: Int
    )

    @Parcelize
    data class Country(
        @SerializedName("name")
        val name: String,
    ) : Parcelable
}

@Entity(tableName = "cities")
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "lat")
    val lat: Double?,
    @ColumnInfo(name = "lng")
    val lng: Double?,
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "country")
    val country:String,
)
