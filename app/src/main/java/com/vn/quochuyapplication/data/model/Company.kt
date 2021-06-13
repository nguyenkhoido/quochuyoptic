package com.vn.quochuyapplication.data.model

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

open class Company : RealmObject(), ICompany {

    @PrimaryKey
    var id: String = UUID.randomUUID().toString()
    lateinit var name: String
    lateinit var city: String
    lateinit var frameList: RealmList<Frame>
    lateinit var glassList: RealmList<Glasses>
    lateinit var lenseList: RealmList<Lense>

    override fun city(): String? {
        return city
    }

    override fun name(): String? {
        return name
    }
}