package com.example.foodrecipeapp.model

import com.google.gson.annotations.SerializedName

data class Datum(
        @SerializedName("id")
        var id:Int = 0,
        @SerializedName("employee_name")
        var employeeName: String? = null,
        @SerializedName("employee_salary")
        var employeealary:Int = 0,
        @SerializedName("employee_age")
        var employeeage:Int = 0,
        @SerializedName("profile_image")
        var profileImage: String? = null
)


data class Root(
        @SerializedName("status")
        var status: String? = null,
        @SerializedName("Datum")
        var data: List<Datum> ,
        @SerializedName("message")
        var message: String? = null
)