package com.benju.simpletabbarview.ui

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.view.ViewPager
import android.support.v7.app.ActionBar
import android.support.v7.app.AppCompatActivity
import android.view.*
import android.widget.TextView
import com.benju.simpletabbarview.R
import com.benju.simpletabbarview.view.TabBarView

class MainActivity : AppCompatActivity() {
    private val tabs_strings = intArrayOf(R.string.section1, R.string.section2, R.string.section3)
    private val tab_icons = intArrayOf(R.drawable.ic_favorite_white_24dp, R.drawable.ic_favorite_white_24dp, R.drawable.ic_favorite_white_24dp)

    private var mViewPager: ViewPager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mViewPager = findViewById(R.id.pager) as ViewPager
        setupEmbeddedTabs()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.itemId


        if (id == R.id.action_settings) {
            return true
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setupEmbeddedTabs() {
        val layoutInflater = this.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val customView = layoutInflater.inflate(R.layout.tab_bars, null)
        val tabBarView = customView.findViewById(R.id.tab_bar) as TabBarView
        customView.layoutParams = ViewGroup.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.MATCH_PARENT)

        supportActionBar!!.setDisplayShowHomeEnabled(false)
        supportActionBar!!.setDisplayShowTitleEnabled(false)
        supportActionBar!!.setDisplayHomeAsUpEnabled(false)
        supportActionBar!!.setDisplayOptions(0, ActionBar.DISPLAY_SHOW_CUSTOM)
        supportActionBar!!.setDisplayShowCustomEnabled(true)
        supportActionBar!!.customView = customView

        // TODO: 18/12/2016 Use Toolbar to remove left space

        tabBarView.setStripHeight(5)

        val mSectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        mViewPager!!.adapter = mSectionsPagerAdapter
        tabBarView.setViewPager(mViewPager)
    }

    inner class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) : FragmentPagerAdapter(fm), TabBarView.IconTabProvider {

        override fun getItem(position: Int): Fragment {
            // getItem is called to instantiate the fragment for the given page.

            /*Fragment fragment = null;
            switch (position) {
                case MENU_ITEM_1: {
                    break;
                }
                case MENU_ITEM_2: {
                    break;
                }
                case MENU_ITEM_3: {
                    break;
                }
            }
            return fragment;*/

            return PlaceholderFragment.newInstance(position + 1)
        }

        override fun getCount(): Int {
            // Show 3 total pages.
            return tab_icons.size
        }

        override fun getPageIconResId(position: Int): Int {
            return tab_icons[position]
        }

        override fun getPageTitle(position: Int): CharSequence {
            return getString(tabs_strings[position])
        }
    }


    /**
     * A placeholder fragment containing a simple view.
     */
    class PlaceholderFragment : Fragment() {


        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                                  savedInstanceState: Bundle?): View? {
            val rootView = inflater!!.inflate(R.layout.fragment_main, container,
                    false)
            val textView = rootView
                    .findViewById(R.id.section_label) as TextView
            textView.text = Integer.toString(arguments.getInt(
                    ARG_SECTION_NUMBER))
            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"


            /**
             * Returns a new instance of this fragment for the given section number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }

    companion object {

        // Tabs
        val MENU_ITEM_1 = 0
        val MENU_ITEM_2 = 1
        val MENU_ITEM_3 = 2
    }

}