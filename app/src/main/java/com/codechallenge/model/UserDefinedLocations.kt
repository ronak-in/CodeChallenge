package com.codechallenge.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserDefinedLocations() : Parcelable {

    @PrimaryKey
    @ColumnInfo(name = "id")
    var id: Long = 0

    @ColumnInfo(name = "lat")
    var lat: Double = 0.0

    @ColumnInfo(name = "longitude")
    var longitude: Double = 0.0

    @ColumnInfo(name = "address")
    var address: String = ""


    constructor(parcel: Parcel) : this() {
        id = parcel.readLong()
        lat = parcel.readDouble()
        longitude = parcel.readDouble()
        address = parcel.readString().toString()
    }

    constructor(dto: UserDefinedLocations) : this() {
        this.id = dto.id
        this.lat = dto.lat
        this.longitude = dto.longitude
        this.address = dto.address
    }

    constructor(id: Long, lat: Double, long: Double, address: String) : this() {
        this.id = id
        this.lat = lat
        this.longitude = long
        this.address = address
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(id)
        parcel.writeDouble(lat)
        parcel.writeDouble(longitude)
        parcel.writeString(address)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<UserDefinedLocations> {
        override fun createFromParcel(parcel: Parcel): UserDefinedLocations {
            return UserDefinedLocations(parcel)
        }

        override fun newArray(size: Int): Array<UserDefinedLocations?> {
            return arrayOfNulls(size)
        }
    }


}