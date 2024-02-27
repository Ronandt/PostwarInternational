import com.squareup.moshi.Moshi


object JsonConverter {
    val adapter: Moshi = Moshi.Builder().build()
}