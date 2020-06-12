package com.example.polisiapp

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import android.view.ViewParent
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_on_boarding_screen.*

class OnBoardingScreen : AppCompatActivity() {
//    define fragments
    val fragment1 = SlideFragment()
    val fragment2 = SlideFragment()
    val fragment3 = SlideFragment()

    lateinit var adapter : SlideAdapter
    lateinit var activity: Activity
    lateinit var preference : SharedPreferences
    val pref_show_intro = "Intro"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_on_boarding_screen)

        activity = this

        preference = getSharedPreferences("IntroSlider", Context.MODE_PRIVATE)


        if(!preference.getBoolean(pref_show_intro, true)){
            startActivity(Intent(activity, MainActivity::class.java))
            finish()
        }

//        set img
        fragment1.setImage(R.drawable.img_frag1)
        fragment2.setImage(R.drawable.img_frag2)
        fragment3.setImage(R.drawable.img_frag4)

//        set title
        fragment1.setTitle(getString(R.string.fragment_title1))
        fragment2.setTitle(getString(R.string.fragment_title2))
        fragment3.setTitle(getString(R.string.fragment_title3))

//        set description
        fragment1.setDescription(getString(R.string.fragment_desc1))
        fragment2.setDescription(getString(R.string.fragment_desc2))
        fragment3.setDescription(getString(R.string.fragment_desc3))

        adapter = SlideAdapter(supportFragmentManager)
        adapter.list.add(fragment1)
        adapter.list.add(fragment2)
        adapter.list.add(fragment3)

        view_pager.adapter = adapter

        btn_next.setOnClickListener{
            view_pager.currentItem++
        }
        btn_skip.setOnClickListener{
            gotToDashboard()
        }
        view_pager.addOnPageChangeListener(object: ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {

            }

            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {

            }

            override fun onPageSelected(position: Int) {
                if(position== adapter.list.size - 1){
                    btn_next.text = "Done"
                    btn_next.setOnClickListener{
                        gotToDashboard()

                    }
                }else{
//                    go to next
                    btn_next.text = "Next"
                    btn_next.setOnClickListener{
                        view_pager.currentItem ++
                    }
                }

                when(view_pager.currentItem){
                    0 -> {
                        first_indicator.setTextColor(Color.BLACK)
                        second_indicator.setTextColor(Color.GRAY)
                        third_indicator.setTextColor(Color.GRAY)
                    }
                    1 -> {
                        first_indicator.setTextColor(Color.GRAY)
                        second_indicator.setTextColor(Color.BLACK)
                        third_indicator.setTextColor(Color.GRAY)
                    }
                    2 ->{
                        first_indicator.setTextColor(Color.GRAY)
                        second_indicator.setTextColor(Color.GRAY)
                        third_indicator.setTextColor(Color.BLACK)
                    }
                }
            }
        })
    }
    fun gotToDashboard(){
        startActivity(Intent(activity, MainActivity::class.java))
        finish()
        val editor = preference.edit()
        editor.putBoolean(pref_show_intro, false)
        editor.apply()
    }
}