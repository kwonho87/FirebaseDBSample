package kr.kwonho87.firebasedbsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.google.firebase.FirebaseApp
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.*
import kr.kwonho87.firebasedbsample.widget.MyAdapter

/**
 * kwonho87@gmail.com
 * 2019-03-07
 */
class MainActivity : AppCompatActivity() {

    companion object {
        var myAdapter = MyAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // init FirebaseApp
        FirebaseApp.initializeApp(this)

        // make RecycleView
        listView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = myAdapter
        }

        // init RecycleView data
        updateListView()

        // insert button
        btnInsert.setOnClickListener{
            insert()
        }
    }

    /**
     * Get insert value and request Firebase Service.
     */
    private fun insert() {
        var data = Data()
        data.apply {
            id = editId.editableText.toString()
            name = editName.editableText.toString()
            age = editAge.editableText.toString()
            gender = editGender.editableText.toString()
        }
        post(data)
    }

    /**
     * Request Firebase Realtime Database.
     * And insert value.
     */
    private fun post(data: Data) {
        var requestData = HashMap<String, Any>()
        requestData["/list/${data.id}"] = data.toMap()

        var task = FirebaseDatabase.getInstance().reference.updateChildren(requestData)
        task.addOnCompleteListener {
            Toast.makeText(baseContext, "Success", Toast.LENGTH_SHORT).show()
            updateListView()
        }
    }

    /**
     * After request, update listview.
     */
    private fun updateListView() {
        var query = FirebaseDatabase.getInstance().reference.child("list").orderByChild("id")
        query.addListenerForSingleValueEvent(ValueListener())
    }

    /**
     * Firebase Database query listener.
     */
    class ValueListener: ValueEventListener {
        override fun onDataChange(dataSnapShot: DataSnapshot) {
            var list = ArrayList<String>()
            list.clear()

            for(postSnapshot in dataSnapShot.children) {
                var key = postSnapshot.key

                var get = postSnapshot.getValue(Data::class.java)
                list.add(get?.id!!)
            }

            myAdapter.setData(list)
            myAdapter.notifyDataSetChanged()
        }

        override fun onCancelled(error: DatabaseError) {}
    }
}
