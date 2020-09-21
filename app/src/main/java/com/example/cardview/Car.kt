package com.example.cardview

import java.io.Serializable

class Car: Serializable {
    var name: String = ""
    var description: String = ""
    var price: String = ""
    var imgUrl: String = ""
    var carPackages: ArrayList<CarPackage> = ArrayList<CarPackage>()
}