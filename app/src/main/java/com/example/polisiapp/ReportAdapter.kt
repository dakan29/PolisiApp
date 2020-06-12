package com.example.polisiapp

import android.database.DataSetObserver
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ListAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.report_item.view.*

class ReportAdapter(private val reportitem:List<ReportDataItem>): RecyclerView.Adapter<ReportAdapter.ReportViewHolder>(),
    ListAdapter {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val itemview = LayoutInflater.from(parent.context).inflate(R.layout.report_item,parent,false)
        return ReportViewHolder(itemview)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val currentItem = reportitem[position]
        holder.imageView.setImageResource(currentItem.imageResource)
        holder.timeview.text = currentItem.time
        holder.titleview.text = currentItem.title
        holder.descview.text = currentItem.description

    }
    override fun getItemCount(): Int {
        return reportitem.size
    }

    class ReportViewHolder(itemview: View):RecyclerView.ViewHolder(itemview){
        val imageView : ImageView = itemview.img_report
        val timeview : TextView = itemview.tv_time_report
        val titleview : TextView = itemview.tv_time_report
        val descview : TextView = itemview.tv_desc_report

    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        TODO("Not yet implemented")
    }

    override fun registerDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getItem(position: Int): Any {
        TODO("Not yet implemented")
    }

    override fun getViewTypeCount(): Int {
        TODO("Not yet implemented")
    }

    override fun isEnabled(position: Int): Boolean {
        TODO("Not yet implemented")
    }

    override fun areAllItemsEnabled(): Boolean {
        TODO("Not yet implemented")
    }

    override fun unregisterDataSetObserver(observer: DataSetObserver?) {
        TODO("Not yet implemented")
    }

    override fun getCount(): Int {
        TODO("Not yet implemented")
    }
}