package com.example.json_app


import com.google.gson.annotations.SerializedName

data class CUR(
    @SerializedName("date")
    val date: String, // 2021-11-08
    @SerializedName("eur")
    val eur: Eur
) {
    data class Eur(
        @SerializedName("inr")
        val inr: String?, // 0.572221
        @SerializedName("usd")
        val usd: String?, // 4.248593
        @SerializedName("aud")
        val aud: String?, // 105.261773
        @SerializedName("SAR")
        val SAR: String?, // 123.798511
        @SerializedName("cny")
        val cny: String?, // 550.496962
        @SerializedName("jpy")
        val jpy: String?, // 2.084619
        @SerializedName("aoa")
        val aoa: String?, // 690.565124
        @SerializedName("ars")
        val ars: String?, // 115.566598
        @SerializedName("awg")
        val awg: String?, // 2.082105
        @SerializedName("azn")
        val azn: String?, // 1.963041
        @SerializedName("bam")
        val bam: String?, // 1.961477
        @SerializedName("bbd")
        val bbd: String?, // 2.335464
        @SerializedName("bch")
        val bch: String?, // 0.001912


    )
}

