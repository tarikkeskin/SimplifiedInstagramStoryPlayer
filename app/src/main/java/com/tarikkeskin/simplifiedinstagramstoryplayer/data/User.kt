package com.tarikkeskin.simplifiedinstagramstoryplayer.data

import java.io.Serializable

data class User(val id:Int,val userName:String,val profilePicture:String,val storyList:ArrayList<Story>):Serializable
