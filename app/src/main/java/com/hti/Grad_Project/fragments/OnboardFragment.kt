package com.hti.Grad_Project.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.fragment.app.Fragment
import com.hti.Grad_Project.R
import kotlinx.android.synthetic.main.fragment_onboarding.view.*


class OnboardFragment : Fragment() {
    private lateinit var title: String
    private lateinit var description: String
    private var imageResource = 0
    private lateinit var tvTitle: AppCompatTextView
    private lateinit var image: ImageView
    private lateinit var tvDescription: AppCompatTextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null) {
            title = requireArguments().getString(ARG_PARAM1)!!
            description = requireArguments().getString(ARG_PARAM2)!!
            imageResource = requireArguments().getInt(ARG_PARAM3)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val rootLayout: View =
            inflater.inflate(R.layout.fragment_onboarding, container, false)
        tvTitle = rootLayout.text_onboarding_title
        tvDescription = rootLayout.text_onboarding_description
        image = rootLayout.image_onboarding
        image.setImageResource(imageResource)
        tvTitle.text = title
        tvDescription.text = description
        return rootLayout
    }

    companion object {
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private const val ARG_PARAM1 = "param1"
        private const val ARG_PARAM2 = "param2"
        private const val ARG_PARAM3 = "param3"
        fun newInstance(
            title: String?,
            description: String?,
            imageResource: Int
        ): OnboardFragment {
            val fragment = OnboardFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, title)
            args.putString(ARG_PARAM2, description)
            args.putInt(ARG_PARAM3, imageResource)
            fragment.arguments = args
            return fragment
        }
    }
}