package com.example.polisiapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_slide.*


class SlideFragment : Fragment() {

    var pageImg: Int = 0
    var pageTitle: String = ""
    var pageDesc: String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_slide, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        frag_img.setImageResource(pageImg)
        frag_title.text = pageTitle
        frag_description.text = pageDesc
    }

    fun setImage(img_id:Int){
        pageImg = img_id
    }
    fun setTitle(title:String){
        pageTitle = title
    }
    fun setDescription(description:String){
        pageDesc = description
    }


}