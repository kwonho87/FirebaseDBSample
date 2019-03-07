package kr.kwonho87.firebasedbsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*

/**
 * kwonho87@gmail.com
 * 2019-03-07
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        FirebaseApp.initializeApp(this)

        btnInsert.setOnClickListener{
            insert()
        }
    }

    fun initListView() {

    }

    fun insert() {
        var id = editId.editableText.toString()
        var name = editName.editableText.toString()
        var age = editAge.editableText.toString()
        var gender = editGender.editableText.toString()

        post(Data(id, name, age, gender))
    }

    fun post(data: Data) {
        var requestData = HashMap<String, Any>()
        requestData["/list/${data.id}"] = data.toMap()

        var task = FirebaseDatabase.getInstance().reference.updateChildren(requestData)
        task.run {
            addOnCompleteListener {
                Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            }
            addOnCanceledListener {
                Toast.makeText(baseContext, "Fail", Toast.LENGTH_SHORT).show()
            }
            updateListView()
        }
    }

    fun updateListView() {
        var query = FirebaseDatabase.getInstance().reference.child("list").orderByChild("id")
        query.addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onCancelled(dataBaseError: DatabaseError) {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

                override fun onDataChange(dataSnapShot: DataSnapshot) {
                    var children = dataSnapShot.children

                    for(postSnapshot in children ) {
                        var key = postSnapshot.key

                        var get = postSnapshot.getValue(Data::class.java)
                    }
                }
            }
        )
    }
}
