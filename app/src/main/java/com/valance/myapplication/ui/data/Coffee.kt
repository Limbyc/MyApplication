import android.os.Parcel
import android.os.Parcelable

data class Coffee(
    val type: String,
    val rating: Double,
    val name: String,
    val description: String,
    val price: Double,
    val imageResourceId: Int,
    val category: String,
    val id: Int,
    val count: Int
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(rating)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeDouble(price)
        parcel.writeInt(imageResourceId)
        parcel.writeString(category)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Coffee> {
        @JvmStatic
        override fun createFromParcel(parcel: Parcel): Coffee {
            return Coffee(parcel)
        }

        @JvmStatic
        override fun newArray(size: Int): Array<Coffee?> {
            return arrayOfNulls(size)
        }
    }

}
