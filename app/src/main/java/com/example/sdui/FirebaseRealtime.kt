package com.example.sdui

import com.example.sdui.data.FeaturedContent
import com.example.sdui.data.UiComponent
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.getValue

class FirebaseRealtime {

    private val database = FirebaseDatabase.getInstance().reference

    fun observerFeatureContent(
        onDataChange: (FeaturedContent) -> Unit,
        onError: (Exception) -> Unit
    ){

        database.child("featured_content")
            .addValueEventListener(object :ValueEventListener{
                override fun onDataChange(snapshot: DataSnapshot) {
                   val layout = mutableListOf<UiComponent>()
                    // Loop through each component in layout array
                    snapshot.child("layout").children.forEach{
                        // Extract component data
                        val type = it.child("type").getValue(String::class.java)?:""
                        val properties = it.child("properties").getValue<Map<String,Any>>() ?: emptyMap()

                        // Create component object
                        layout.add(UiComponent(type,properties))

                        // Send updated content
                        onDataChange(FeaturedContent(layout))
                    }
                }

                override fun onCancelled(error: DatabaseError) {
                    onError(error.toException())
                }

            })
    }
}