package com.mohitbhavsar.familyspotter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.mohitbhavsar.familyspotter.R
import com.mohitbhavsar.familyspotter.databinding.FragmentBottomNavBinding
import com.mohitbhavsar.familyspotter.fragments.bottomnav.Home
import com.mohitbhavsar.familyspotter.fragments.bottomnav.Notifications
import com.mohitbhavsar.familyspotter.fragments.bottomnav.Profile


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeScreen.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeScreen : Fragment(R.layout.fragment_bottom_nav) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null


    //    intialize binding
    private var _binding: FragmentBottomNavBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


//        when user press back button replace fragment with vendor Home Fragment
        activity?.onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {

                val fragment = HomeScreen()
                val transaction: FragmentTransaction = requireFragmentManager().beginTransaction()
                transaction.replace(R.id.frameLayout,fragment)
                transaction.commit()

            }
        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val mViewPager = view.findViewById<View>(R.id.viewPager) as ViewPager
//        binding.viewPager.adapter = MyAdapter(childFragmentManager)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBottomNavBinding.inflate(inflater, container, false)


//        binding.tvTest.setText("Mohit Bhavsar")

        replaceFragment(Home(),"")

        binding.chipnavBottom.setOnItemSelectedListener({
            when (it) {
                R.id.profile -> {
//                    Toast.makeText(activity,"Profile",Toast.LENGTH_SHORT).show()
//                    replaceFragment(Profile(),"it.title.toString()")
                    replaceFragment(Profile(),"Profile")
8
                };
                R.id.home -> {
//                    Toast.makeText(activity,"Home",Toast.LENGTH_SHORT).show()
                    replaceFragment(Home(),"Home")
                }
                R.id.notifications -> {
//                    Toast.makeText(activity,"Notifications",Toast.LENGTH_SHORT).show()
                    replaceFragment(Notifications(),"Notifications")
                }
            }

        })

//        binding.viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
//            override fun onPageScrolled(
//                position: Int,
//                positionOffset: Float,
//                positionOffsetPixels: Int
//            ) {
//
//            }
//
//            override fun onPageSelected(position: Int) {
//                binding.bubbleTabBar.setSelected(position, false)
//                SingleToast.show(activity, "Position:"+position, Toast.LENGTH_LONG);
//            }
//
//            override fun onPageScrollStateChanged(state: Int) {
//            }
//        })

        setupViewPager()


        return binding.root

    }

    // function to replace fragment
    fun replaceFragment(fragment: Fragment,title : String){
//        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout_bottomnav,fragment)
        fragmentTransaction?.commit()
//        binding.drawerLayout.closeDrawers()

//        if(title=="Home" || title=="Terms and Conditions"){
//            setTitle("Salon Ease")
//        }else{
//            setTitle(title)
//        }
//        setTitle(title)

//        TITLE_OBJ.setTitle(title)
    }

    private fun setupViewPager() {
//        val viewPager = binding.viewPager
//        viewPager.adapter = ViewPagerAdapter(supportFragmentManager
    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeScreen.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeScreen().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}