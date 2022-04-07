package com.mohitbhavsar.familyspotter


import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.mohitbhavsar.familyspotter.R
import com.mohitbhavsar.familyspotter.databinding.ActivityMainBinding
import com.mohitbhavsar.familyspotter.fragments.AddMembers
import com.mohitbhavsar.familyspotter.fragments.HomeScreen
import com.mohitbhavsar.familyspotter.fragments.RemoveMembers

class MainActivity : AppCompatActivity() {

    //    view binding
    private lateinit var binding: ActivityMainBinding

//
//    private lateinit var auth: FirebaseAuth
//    var databaseReference : DatabaseReference? = null
//    var database : FirebaseDatabase? = null

    lateinit var number: String
    var name: String? = null


    private lateinit var auth: FirebaseAuth;


    //
    lateinit var toggle: ActionBarDrawerToggle

//    lateinit var drawerLayout: DrawerLayout
//    lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_dashboard_vendor)
//        supportActionBar?.hide()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


//        ============
//        initial remove top bar
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            val w: Window = window
//            w.setFlags(
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
//                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
//            )
//        }
//        supportActionBar?.hide()


//        ============

//
//        auth = FirebaseAuth.getInstance()
//        database = FirebaseDatabase.getInstance()
//        databaseReference = database?.reference!!.child("profile")

//        loadProfile()



//        checkUser()

//        logout button click logout the user
//        binding.logoutBtn.setOnClickListener{
//            firebaseAuth.signOut()
//            checkUser()
//        }

//        drawerLayout = findViewById(R.id.vendor_drawerLayout)
//        navView = findViewById(R.id.navigationView)


        // Initialize Firebase Auth
        auth = Firebase.auth


        toggle = ActionBarDrawerToggle(this, binding.drawerLayout, R.string.open, R.string.close)

        binding.drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        replaceFragment(HomeScreen(),"Family Spotter")

        binding.navigationView.setNavigationItemSelectedListener {
            when(it.itemId){

//                R.id.nav_home -> replaceFragment(vendor_Home(),it.title.toString())
                R.id.nav_addMembers -> replaceFragment(AddMembers(),it.title.toString())
                R.id.nav_removeMembers -> replaceFragment(RemoveMembers(),it.title.toString())
//                R.id.nav_editProfile -> replaceFragment(vendor_EditProfile(),it.title.toString())
//                R.id.nav_editSalonProfile -> replaceFragment(vendor_Salon_EditProfile(),it.title.toString())

//                R.id.nav_editProfile -> {
//                intent = Intent(this, editProfile::class.java)
//                startActivity(intent) }

                R.id.nav_share -> {
////                    share the app
//                    try {
//                        val shareIntent = Intent(Intent.ACTION_SEND)
//                        shareIntent.type = "text/plain"
//                        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Salon Ease")
//                        var shareMessage = "\nLet me recommend you Salon Ease application, try it \n\n"
////                        shareMessage =
////                            """
////                            ${shareMessage}https://play.google.com/store/apps/details?id=${BuildConfig.APPLICATION_ID}
////
////
////                            """.trimIndent()
//                        shareMessage =
//                            """
//                            ${shareMessage}https://play.google.com/store/apps/details?id=com.caprusdigi.salon_ease
//
//
//                            """.trimIndent()
//                        shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage)
//                        startActivity(Intent.createChooser(shareIntent, "choose one"))
//                    } catch (e: Exception) {
//                        //e.toString();
//                    }
                }

//                sign out firebase authentication
                R.id.nav_signOut -> {
                    Firebase.auth.signOut()
//                    Auth.GoogleSignInApi.signOut(apiClient);

//                    auth.signOut()
                    intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
//                    checkUser()
                }

//                R.id.nav_forCustomerOpen -> {
////                    openCustomerDashboard()
////                    SingleToast.show(this, "Locked!", Toast.LENGTH_LONG)
//
//                }

//                R.id.nav_notifications -> replaceFragment(vendor_Notifications(),it.title.toString())
//                R.id.nav_termsAndConditions -> replaceFragment(vendor_termsAndConditions(),it.title.toString())

//                R.id.nav_home -> replaceFragment(vendor_termsAndConditions(),it.title.toString())
//                R.id.nav_home -> replaceFragment(vendor_termsAndConditions(),it.title.toString())


            }
            true
        }

//        nav_toogle_menu_custom.setOnClickListener {
//            openCloseNavigationDrawer()
//        }


// receives logged in user mobile
//        val fireCustomDef = FirebaseCustomDefinitions()
//        val numberFir = fireCustomDef.getLoggedInProfileMobile()

        // receives logged in user mobile
//        val usrName = fireCustomDef.getReceiveUserCustomDataFirebase(numberFir+"/Name")

//        update ui data from firebase to nav header
//        val navHeader_mob: TextView = binding.navigationView.getHeaderView(0).findViewById(R.id.tv_mobile)
//        navHeader_mob.setText(numberFir)




    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }


    // function to replace fragment
    fun replaceFragment(fragment: Fragment,title : String){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.commit()
        binding.drawerLayout.closeDrawers()

//        if(title=="Home" || title=="Terms and Conditions"){
//            setTitle("Salon Ease")
//        }else{
//            setTitle(title)
//        }
        setTitle(title)

//        TITLE_OBJ.setTitle(title)
    }

    private fun checkUser(){
//        get current user
        val firebaseUser = auth.currentUser
        if(firebaseUser == null){
//            logged out
            SingleToast.show(this, "User needs Login!", Toast.LENGTH_LONG);
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }else{
//            logged in get phone number of user
//            val phone = firebaseUser.phoneNumber
//            set phone number
//            binding.mobiletv.text = phone
            SingleToast.show(this, "User is Logged In", Toast.LENGTH_LONG);

        }
    }

    override fun onStart() {
        super.onStart()
        checkUser()
    }

//    double back tap to exit======================
//    private var backPressedTime:Long = 0
////    lateinit var backToast:Toast
//    override fun onBackPressed() {
//
////        backToast = Toast.makeText(this, "Press back again to leave the app.", Toast.LENGTH_LONG)
//        if (backPressedTime + 2000 > System.currentTimeMillis()) {
////            backToast.cancel()
//            super.onBackPressed()
//            return
//        } else {
//            SingleToast.show(this, "Press back again to leave the app", Toast.LENGTH_LONG);
//
////            backToast.show()
//        }
//        backPressedTime = System.currentTimeMillis()
//    }
//    ======================
}