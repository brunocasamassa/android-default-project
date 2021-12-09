package br.com.lea.easyvendas.domain.model.catalog
import android.os.Parcelable
import br.com.lea.easyvendas.domain.model.BaseResponse
import com.google.gson.annotations.SerializedName


data class Catalog(
    @SerializedName("content")
    val catalogContent: List<CatalogContent>,
    @SerializedName("messages")
    val messages: List<String>,
    @SerializedName("statusCode")
    val statusCode: Int
) : BaseResponse()

data class CatalogContent(
    @SerializedName("code")
    val code: String,
    @SerializedName("discount")
    val discount: Int,
    @SerializedName("discountDuoDate")
    val discountDuoDate: String,
    @SerializedName("images")
    val images: List<Image>,
    @SerializedName("name")
    var name: String,
    @SerializedName("package")
    val packageX: Package,
    @SerializedName("weight")
    val weight: Weight,
    var category:String = ""
)

data class Image(
    @SerializedName("description")
    val description: String,
    @SerializedName("url")
    val url: String
)


data class Package(
    @SerializedName("packageUnity")
    val packageUnity: String,
    @SerializedName("value")
    val value: Int
)


data class Weight(
    @SerializedName("measureUnity")
    val measureUnity: String,
    @SerializedName("value")
    val value: Int
)