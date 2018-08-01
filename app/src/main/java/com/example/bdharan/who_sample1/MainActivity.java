package com.example.bdharan.who_sample1;

import android.content.Intent;
import java.lang.ref.WeakReference;

import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        TabLayout.Tab firstTab = tabLayout.newTab();
        firstTab.setText("First");
        tabLayout.addTab(firstTab);

        TabLayout.Tab secondTab = tabLayout.newTab();
        secondTab.setText("Second"); // set the Text for the second Tab
        //secondTab.setIcon(R.drawable.ic_launcher); // set an icon for the second tab
        tabLayout.addTab(secondTab);

        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));





    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        int[] values={25,35,40};
        String[] names={"Normal","Unavailable","Emergency"};

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);

            PieChart chart = (PieChart) rootView.findViewById(R.id.chart);




                List<PieEntry> pieEntries = new ArrayList<>();
                for (int i = 0; i < values.length; i++) {
                    pieEntries.add(new PieEntry(values[i], names[i]));
                }

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Sample");
                pieDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
                PieData pieData = new PieData(pieDataSet);

                chart.setVisibility(View.VISIBLE);
                chart.setData(pieData);
                chart.animateY(1000);
                chart.getContentDescription();
                chart.getDescription().setEnabled(false);

                chart.invalidate();


            return rootView;

        }

    }



    public static class PatientListFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */

        public static int position;


        public PatientListFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PatientListFragment newInstance(int sectionNumber) {
            PatientListFragment fragment = new PatientListFragment();

            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.list_fragment, container, false);


            String[] values = new String[] { "Akaash","Amarnath","Bala","Aravind","Bharani","Deepica","Divya","Sreeram","Bhuvana" };
            final ArrayList<String> list = new ArrayList<String>();
            for (int i = 0; i < values.length; ++i) {
                list.add(values[i]);
            }

            Collections.sort(list);

            ListView listView=(ListView) rootView.findViewById(R.id.list);
            listView.setAdapter(new ArrayAdapter(rootView.getContext(), android.R.layout.simple_list_item_1, list));

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    position=i;


                    FragmentTransaction trans = getFragmentManager()
                            .beginTransaction();
                    /*
                     * IMPORTANT: We use the "root frame" defined in
                     * "root_fragment.xml" as the reference to replace fragment
                     */
                    trans.replace(R.id.root_frame, new CardFragment());

                    /*
                     * IMPORTANT: The following lines allow us to add the fragment
                     * to the stack and return to it later, by pressing back
                     */
                    trans.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    trans.addToBackStack(null);

                    trans.commit();

                }
            });


            return rootView;

        }


    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            if(position+1==1)
                return MainActivity.PlaceholderFragment.newInstance(position + 1);
            else
                return new RootFragment();


        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }
    }
}
