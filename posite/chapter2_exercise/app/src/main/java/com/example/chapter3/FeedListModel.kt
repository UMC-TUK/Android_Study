package com.example.chapter3

data class FeedListModel(
    val images: ArrayList<FeedImgModel>,
    val profile: Int,
    val id: String,
    val title: String,
    val commenterId: String?,
    val commentContent: String?
)