package com.tryouts.oneclickdrive

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import com.tryouts.oneclickdrive.databinding.ActivityMainBinding
import java.util.*
import kotlin.math.max

class MainActivity : AppCompatActivity() , OnClickListener {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.continueBtn.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        when( view?.id )
        {
            R.id.continueBtn -> {
                if ( binding.continueBtn.text == "Calculate" )
                    process()
                else
                    reset()
            }
        }
    }

    private fun process(){
        val str1 = binding.box1.text.toString()
        val str2 = binding.box2.text.toString()
        val str3 = binding.box3.text.toString()

        val arr1 = str1.split(",")
        val arr2 = str2.split(",")
        val arr3 = str3.split(",")
        val unionSet : TreeSet<Int> = TreeSet()
        val intersectionSet : TreeSet<Int> = TreeSet()
        var highest = arr1[0].toInt()

        arr1.forEach {
            highest = max(highest , it.toInt())
            unionSet.add(it.toInt())
            if ( arr2.contains(it) && arr3.contains(it) )
                intersectionSet.add(it.toInt())
        }
        arr2.forEach {
            unionSet.add(it.toInt())
            highest = max(highest , it.toInt())
        }
        arr3.forEach {
            unionSet.add(it.toInt())
            highest = max(highest , it.toInt())
        }

        val union = StringBuilder()
        val intersection = StringBuilder()
        unionSet.forEach {
            union.append(it.toString())
            union.append(",")
        }
        intersectionSet.forEach{
            intersection.append(it.toString())
            intersection.append(",")
        }

        binding.intersection.setText(intersection)
        binding.highest.setText(highest.toString())
        binding.continueBtn.setText("Reset")
        binding.union.setText(union)
    }

    private fun reset () {
        binding.intersection.setText("")
        binding.union.setText("")
        binding.highest.setText("")
        binding.box1.setText("")
        binding.box2.setText("")
        binding.box3.setText("")
        binding.continueBtn.setText("Calculate")
    }
}