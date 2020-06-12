package com.example.polisiapp

class ReportSchema {
    var case_title: String = ""
    var case_time: String = ""
    var case_description: String = ""

    constructor(title:String,time:String, description:String){
        this.case_title = title
        this.case_time = title
        this.case_description = description
    }

    constructor(){}
}