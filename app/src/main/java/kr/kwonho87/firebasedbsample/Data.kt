package kr.kwonho87.firebasedbsample

import com.google.firebase.database.Exclude

class Data {
    lateinit var id: String
    lateinit var name: String
    lateinit var age: String
    lateinit var gender: String

    fun setData(id: String, name: String, age: String, gender: String) {
        this.id = id
        this.name = name
        this.age = age
        this.gender = gender
    }

    @Exclude
    fun toMap(): Map<String, Any>  {
        var result = HashMap<String, Any>()
        result["id"] = id
        result["name"] = name
        result["age"] = age
        result["gender"] = gender
        return result
    }
}