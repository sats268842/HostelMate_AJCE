package com.ajce.hostelmate.reportissue

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.support.v7.widget.RecyclerView
import android.util.Base64
import android.view.LayoutInflater
import android.view.ViewGroup
import com.ajce.hostelmate.R
import com.ajce.hostelmate.RecyclerViewClickListener

/**
 * Created by JithinJude on 15-03-2018.
 */
class ReceptionIssueRecyclerViewAdapter internal constructor(var context: Context?, issueList: MutableList<Issue?>?) : RecyclerView.Adapter<ReceptionIssueRecyclerViewHolder?>() {
    private val issueList: MutableList<Issue?>?
    private val mInflater: LayoutInflater?
    private val mClickListener: RecyclerViewClickListener? = null

    // inflates the row layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): ReceptionIssueRecyclerViewHolder {
        val view = mInflater?.inflate(R.layout.recyclerview_row, parent, false)
        return ReceptionIssueRecyclerViewHolder(view)
    }

    // binds the data to the TextView in each row
    override fun onBindViewHolder(holder: ReceptionIssueRecyclerViewHolder, pos: Int) {
        holder.title?.text = issueList?.get(pos)?.issueTitle
        val issueLocation = issueList?.get(pos)?.issueBlock + ", " + issueList?.get(pos)?.issueRoom
        holder.blockAndRoom?.text = issueLocation
        holder.date?.text = issueList?.get(pos)?.issueDate
        holder.issueStatus?.text = issueList?.get(pos)?.issueStatus
        if (issueList?.get(pos)?.issueStatus == "Fixed") {
            context?.resources?.getColor(R.color.green)?.let { holder.issueStatus?.setTextColor(it) }
        } else {
            context?.getResources()?.getColor(R.color.red)?.let { holder.issueStatus?.setTextColor(it) }
        }
        try {
            val imageBitmap = decodeFromFirebaseBase64(ReportedIssuesActivity.Companion.issueList?.get(pos)?.imageEncoded)
            holder.imageView?.setImageBitmap(imageBitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        holder.setItemClickListener(object : RecyclerViewClickListener {
            override fun onItemClick(pos: Int) {

            }
        })
    }

    // total number of rows
    override fun getItemCount(): Int {
        return issueList?.size!!
        //return mCategoryRecyclerviewData.size();
    }

    companion object {
        fun decodeFromFirebaseBase64(image: String?): Bitmap? {
            val decodedByteArray = Base64.decode(image, Base64.DEFAULT)
            return BitmapFactory.decodeByteArray(decodedByteArray, 0, decodedByteArray.size)
        }
    }

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        this.issueList = issueList
    }
}