package com.tarikkeskin.simplifiedinstagramstoryplayer.data

object StoryList {

    val story_list_1 = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckna61sei006a0uo56txresa3.jpg",false),
        Story("https://cdn.impala.travel/properties/ckna6389z006c0uo53gyeb9oq.jpg",false),
        Story("https://cdn.impala.travel/properties/ckna64p8r006f0uo51csrhy1v.jpg",false),
        Story("http://clips.vorwaerts-gmbh.de/big_buck_bunny.mp4",true),
    )
    val story_list_2 = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckn8rjsxa00r40snnc9tyaxox.jpg",false),
        Story("https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4",true),
    )
    val story_list_3 = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckn8rjxqf00r70snned0v16db.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8rk0g400r90snn51qg23na.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8rk35500ra0snn64zd22q4.jpg",false),
    )
    val story_list_4 = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckn8puw1e00pc0snn2hxyaorh.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8pr34h00p60snne5c6dsw6.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8prgvi00p70snnfggc3xzt.jpg",false),
    )
    val story_list_5 = arrayListOf(
        Story("https://cdn.impala.travel/properties/ckn8wzjcw00z00snnhdlr9t7l.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8wy06n00yv0snn50x5034j.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8wyoux00yw0snn5bh19uwg.jpg",false),
        Story("https://cdn.impala.travel/properties/ckn8q92uw00pl0snncm7106z5.jpg",false),
    )

    val user_list = arrayListOf(
        User(1,"tarikkeskin","profile_1", story_list_1),
        User(2,"bugraakin","profile_2", story_list_2),
        User(3,"ahmet1995","profile_3", story_list_3),
        User(4,"betül_14","profile_4", story_list_4),
        User(5,"ilayda_","profile_5", story_list_5),
    )
}