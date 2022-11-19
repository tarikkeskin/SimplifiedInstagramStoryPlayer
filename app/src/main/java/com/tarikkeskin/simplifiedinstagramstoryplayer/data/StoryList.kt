package com.tarikkeskin.simplifiedinstagramstoryplayer.data

object StoryList {

    val first_story_list = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckna6389z006c0uo53gyeb9oq.jpg"),
        Story("https://cdn.impala.travel/properties/ckna62ckp006b0uo51jq4dou6.jpg"),
        Story("https://cdn.impala.travel/properties/ckna61sei006a0uo56txresa3.jpg"),
        Story("https://cdn.impala.travel/properties/ckna64p8r006f0uo51csrhy1v.jpg"),
    )

    val user_list = arrayListOf(
        User(1,"tarikkeskin","tarik", first_story_list),
        User(1,"ilayda_seril","ilayda", first_story_list),
        User(1,"ahmet1995","ahmet", first_story_list),
    )
}