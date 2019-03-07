package kr.kwonho87.firebasedbsample

import com.google.firebase.database.Exclude

class Data constructor(id: String, name: String, age: String, gender: String) {
    var id: String = id
    var name: String = name
    var age: String = age
    var gender: String = gender

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