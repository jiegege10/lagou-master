package com.goach.mdstudy

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.lagou.hyj.lagou.R

/**
 * Created by Administrator on 2019/2/12.
 */
class RecycAdapter(private val context: Context) : RecyclerView.Adapter<RecycAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecycAdapter.ViewHolder {
        var view = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return 100
    }

    override fun onBindViewHolder(holder: RecycAdapter.ViewHolder, position: Int) {
        holder.name.text = "高级安卓工程师"
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val name: TextView

        init {
            name = itemView.findViewById(R.id.name)

        }
    }


}