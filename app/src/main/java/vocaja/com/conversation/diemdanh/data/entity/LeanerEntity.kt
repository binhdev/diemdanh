package vocaja.com.conversation.diemdanh.data.entity

import com.google.gson.annotations.SerializedName

data class LeanerEntity(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name:String
)
