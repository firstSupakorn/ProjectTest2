package com.example.projecttest2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

class IphoneFragment : Fragment() {
    private lateinit var viewModel: IphoneViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        viewModel = ViewModelProviders.of(this).get(IphoneViewModel::class.java)
//        viewModel.score.observe( this, Observer { NewScore ->
//            biding.scoreText.text = NewScore.toString()
//        })


        Log.i("IphoneFragment","call view model")
        var args = IphoneFragmentArgs.fromBundle(arguments)
        Toast.makeText(context,"string:" + args.strArg + viewModel.score, Toast.LENGTH_LONG).show()
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_iphone, container, false)
    }


}