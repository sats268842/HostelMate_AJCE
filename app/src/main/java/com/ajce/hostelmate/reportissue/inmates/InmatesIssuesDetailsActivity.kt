package com.ajce.hostelmate.reportissue.inmates

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ajce.hostelmate.R
import com.ajce.hostelmate.reportissue.Issue
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_reported_issues_details_for_inmates.*

class InmatesIssuesDetailsActivity : AppCompatActivity() {

    val SELECTED_ISSUE: String = "selected_issue"
    val SELECTED_POSITION: String = "selected_position"

    lateinit var issue: Issue
    var selectedPosition: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reported_issues_details_for_inmates)

        //actionbar
        val actionbar = supportActionBar
        //set back button
        actionbar?.setDisplayHomeAsUpEnabled(true)

        issue = intent.getParcelableExtra(SELECTED_ISSUE)
        selectedPosition = intent.getIntExtra(SELECTED_POSITION, 0)

        title = issue.issueTitle
        tvBlock.text = issue.issueBlock
        tvRoom.text = issue.issueRoom
        tvDescription.text = issue.issueDescription
        tvStatusInmates.text = issue.issueStatus

        Glide.with(this)
                .load(issue.issueImageUrl)
                .into(ivImgIssueInmates)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}