package com.bosta.ahmedkhaled.data.model.response

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


data class GetUsersResponse(
    @Expose
    @SerializedName("id")
    val id: Int,
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("username")
    val username: String,
    @Expose
    @SerializedName("email")
    val email: String,
    @Expose
    @SerializedName("address")
    val address: Address,
    @Expose
    @SerializedName("phone")
    val phone: String,
    @Expose
    @SerializedName("website")
    val website: String,
    @Expose
    @SerializedName("company")
    val company: Company
)

data class Company(
    @Expose
    @SerializedName("name")
    val name: String,
    @Expose
    @SerializedName("catchPhrase")
    val catchPhrase: String,
    @Expose
    @SerializedName("bs")
    val bs: String
)

data class Address(
    @Expose
    @SerializedName("street")
    val street: String,
    @Expose
    @SerializedName("suite")
    val suite: String,
    @Expose
    @SerializedName("city")
    val city: String,
    @Expose
    @SerializedName("zipcode")
    val zipcode: String,
    @Expose
    @SerializedName("geo")
    val geo: Geo
)

data class Geo(
    @Expose
    @SerializedName("lat")
    val lat: String,
    @Expose
    @SerializedName("lng")
    val lng: String
)


