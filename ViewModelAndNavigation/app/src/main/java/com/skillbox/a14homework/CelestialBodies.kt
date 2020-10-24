package com.skillbox.a14homework

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

    //  Класс делается Parcelable, чтобы его было возможно выбрать в качестве аргумента в action
    //  Все классы наследники получают анотацию @Parcelize
sealed class CelestialBodies : Parcelable {

    //    Для передачи аргументов создается отдельный открытй класс с открытыми переменными, которые и будут передаваться.
    //    Класс наследуется от sealed class
    @Parcelize
    open class IdAndName(open val id: Int, open val name: String) : CelestialBodies()

    //    Классы, из которых будут браться данные, наследуются от открытого класа. Аргументы,
    //    которые будут передаваться, делаются с модификатором override
    @Parcelize
    data class Planet(
        override val id: Int,
        override val name: String,
        val diameter: Int,
        val avatarLink: String,
        val dayLength: Double
    ) : IdAndName(id, name)

    @Parcelize
    data class Star(
        override val id: Int,
        override val name: String,
        val surfaceTemperature: Int,
        val avatarLink: String
    ) : IdAndName(id, name)

    @Parcelize
    data class ImagePlanet(
        val avatarLink: String
    ) : CelestialBodies()
}