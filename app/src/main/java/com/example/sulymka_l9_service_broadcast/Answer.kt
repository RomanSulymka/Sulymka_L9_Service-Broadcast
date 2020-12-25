package com.example.sulymka_l9_service_broadcast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.result_fragment.*


class Answer : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState == null)
            tvResultText.text =
                if (arguments != null) "Multiplication: ${arguments?.getDouble(pmFragmentParameter).toString()}"
                else ""

    }


    companion object {
        fun newInstance(number: Double = 0.0): Answer {
            val fragment = Answer()
            val bundle = Bundle()
            bundle.putDouble(pmFragmentParameter, number)
            fragment.arguments = bundle
            return fragment
        }
    }
}