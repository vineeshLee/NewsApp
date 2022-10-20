
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Request(
    @SerializedName("status"   ) var status   : String?             = null,
    @SerializedName("articles" ) var articles : ArrayList<Articles> = arrayListOf()
): Serializable