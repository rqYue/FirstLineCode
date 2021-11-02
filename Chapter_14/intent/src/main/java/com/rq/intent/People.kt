package com.rq.intent

import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import kotlinx.android.parcel.Parcelize

class People : Parcelable {
    var name = ""
    var age = 0

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        parcel?.writeString(name)
        parcel?.writeInt(age)
    }

    companion object CREATOR : Parcelable.Creator<People> {
        override fun createFromParcel(parcel: Parcel): People {
            val people = People()
            people.name = parcel.readString() ?: ""
            people.age = parcel.readInt()
            return people
        }

        override fun newArray(size: Int): Array<People?> {
            return arrayOfNulls(size)
        }
    }

}

//@Parcelize
//class People(var name: String, var age: Int) : Parcelable