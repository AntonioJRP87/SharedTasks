package com.antonio.sharedtasks.data

import com.antonio.sharedtasks.data.dto.SmokeDto
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.firestore.firestore

suspend fun saveAndReadSmoke(): String{
    val doc = Firebase.firestore.collection("smoke").document("test")
    doc.set(SmokeDto("Manuel", 2026))
    val smoke = doc.get().data<SmokeDto>()
    return "${smoke.text} - ${smoke.num}"
}