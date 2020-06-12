package com.example.polisiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_report.*
import android.widget.RelativeLayout as RelativeLayout1

class ReportActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)




        val ReportList = arrayListOf<ReportDataItem>()
        val ref = FirebaseDatabase.getInstance().reference.child("cases")

        val reportList = generateReportList(ReportList.size)
        list_view.adapter = ReportAdapter(reportList)
        list_view.layoutManager = LinearLayoutManager(this)
        list_view.setHasFixedSize(true)

//        ref.addChildEventListener(object : ValueEventListener, ChildEventListener {
//            override fun onCancelled(p0: DatabaseError) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildMoved(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildChanged(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildAdded(p0: DataSnapshot, p1: String?) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onChildRemoved(p0: DataSnapshot) {
//                TODO("Not yet implemented")
//            }
//
//            override fun onDataChange(p0: DataSnapshot) {
//                if(p0!!.exists()){
//                    for (h in p0.children){
//                        val case = h.getValue(ReportDataItem::class.java)
//                        ReportList?.add(case!!)
//                    }
//                    val adapter = ReportAdapter(ReportList)
//                    list_view?.setAdapter(adapter)
//                }
//            }
//        })


    }


    private fun  generateReportList(size:Int):List<ReportDataItem>{
        val list = ArrayList<ReportDataItem>()
        for(i in 0 until size){
            val drawabale = when (i % 3 ){
                0 -> R.drawable.ic_baseline_cast_24
                1 -> R.drawable.ic_call_police
                else -> R.drawable.ic_call_police
            }
            val item = ReportDataItem(drawabale, "Time $i","Title", "Description" )
            list += item
        }
        return list
    }

    private fun initCases(firebaseData: DatabaseReference) {
        val cases: MutableList<ReportDataItem> = mutableListOf()
        val menuListener = object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                cases.clear()
                dataSnapshot.children.mapNotNullTo(cases) { it.getValue<ReportDataItem>(ReportDataItem::class.java) }
            }

            override fun onCancelled(databaseError: DatabaseError) {
                println("loadPost:onCancelled ${databaseError.toException()}")
            }
        }
        firebaseData.child("cases").addListenerForSingleValueEvent(menuListener)
    }


}