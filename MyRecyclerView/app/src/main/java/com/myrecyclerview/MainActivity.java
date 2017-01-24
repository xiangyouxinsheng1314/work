package com.myrecyclerview;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.res.Configuration;
import android.os.IBinder;
import android.os.Message;
import android.os.PersistableBundle;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;

import com.myrecyclerview.services.WeatherServices;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Log.i("MainActivity ","onConfigurationChanged()");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i("MainActivity ","onSaveInstanceState()");
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i("MainActivity ","onPostCreate()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("MainActivity ","onPause()");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i("MainActivity ","onPostResume()");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i("MainActivity ","onRestoreInstanceState()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("MainActivity ","onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("MainActivity ","onRestart()");
    }
    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.i("MainActivity ","onSaveInstanceState()");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i("MainActivity ","onActivityResult()");
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        Log.i("MainActivity ","onWindowFocusChanged()");
    }

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private String mCity;
    public final static int FEEDBACK_SUCCESS = 1;
    private boolean feedback = false;
    private WeatherReporter mWeatherReporter;
    private IBinder binderServices;
    private static WeatherReporterManager countService;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private ServiceConnection serviceConnection = new ServiceConnection() {
        // @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Log.i("xiangkezhu ", "onServiceConnected start");
            binderServices = service;
            countService = WeatherReporterManager.Stub.asInterface(binderServices);
            Message msg = new Message();
            msg.what = FEEDBACK_SUCCESS;
            MyHandler myHandler = new MyHandler();
            myHandler.sendMessage(msg);
        }

        // @Override
        public void onServiceDisconnected(ComponentName name) {
            binderServices = null;
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.unbindService(serviceConnection);
        Log.i("MainActivity ","onDestroy()");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("MainActivity ","onCreate()");
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        this.bindService(new Intent(this,
                        WeatherServices.class),
                this.serviceConnection, BIND_AUTO_CREATE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("MainActivity ","onStart()");
    }

    /**
     * 返回当前日期时间字符串<br>
     * 默认格式:yyyymmddhhmmss
     *
     * @return String 返回当前字符串型日期时间
     */


    @Override
    protected void onStop() {
        super.onStop();
        Log.i("MainActivity ","onStop()");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("MainActivity ","onCreateOptionsMenu()");
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Log.i("MainActivity ","onOptionsItemSelected()");
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

    private class MyHandler extends Handler {

        public MyHandler() {
            super();
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == FEEDBACK_SUCCESS) {
                Log.i("xiangkezhu ", "doInit start");
                doInit();
            }
        }
    }

    private void doInit() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private int mAreaId;
        public String mStartTime;
        private String mEndTime;


        public void getCurrentTimeAsNumber() {
            SimpleDateFormat f = new SimpleDateFormat("yyyyMMddHH");
            Date date = new Date();
            mStartTime = f.format(date);
            date.setHours(date.getHours() + 24);
            mEndTime = f.format(date);
            Log.i("xiangkezhu ", "mStartTime" + mStartTime);
            Log.i("xiangkezhu ", "mEndTime" + mEndTime);
        }

        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String ARG_IBINDER_SERVICE = "ibinder_service";
        private RecyclerView mRecyclerView;
        private List<DisplayReporter> mDatas;
        private HomeAdapter mAdapter;


        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            //args.putBinder(ARG_IBINDER_SERVICE, binderServices);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            mAreaId = 101010100;
            getCurrentTimeAsNumber();
            WeatherReporter mWeatherReporter = new WeatherReporter();
            try {
                countService.getReporter(mAreaId, mStartTime, mEndTime, mWeatherReporter);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            initData(mWeatherReporter);
            Log.i("xiangkezhu ", "onCreateView");
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            //TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            //textView.setText(getString(R.string.section_format, getArguments().getInt
            //(ARG_SECTION_NUMBER)));
            mRecyclerView = (RecyclerView) rootView.findViewById(R.id.my_recycler_view);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            linearLayoutManager.setOrientation(linearLayoutManager.HORIZONTAL);
            mRecyclerView.setLayoutManager(linearLayoutManager);
            mRecyclerView.setAdapter(mAdapter = new HomeAdapter());
            return rootView;
        }

        protected void initData(WeatherReporter weatherReporter) {
            mDatas = new ArrayList<DisplayReporter>();
            Log.i("xiangkezhu ", "init data start");
            WeatherReporter.ResultBean rb = weatherReporter.getResult();
            int startTime = Integer.parseInt(rb.getStartTime().substring(4, 5));
            for (int i = 0; i < 24; i++) {
                DisplayReporter displayReporter = new DisplayReporter();
                displayReporter.setTimes(String.valueOf(startTime + i));
                displayReporter.setTem(rb.getSeries().get(i).getTmp());
                displayReporter.setImage(weatherStatus(rb.getSeries().get(i).getW()));
                Log.i("xiangkezhu ", "weatherStatus(rb.getSeries()[i].getW()) = " + weatherStatus
                        (rb.getSeries().get(i).getW()) + " i = " + i);
                mDatas.add(displayReporter);
            }
            Log.i("xiangkezhu ", "init data end");
        }

        private int weatherStatus(String status) {
            if ("霾".equals(status)) {
                return R.drawable.ic_haze_big;
            } else if ("雾".equals(status)) {
                return R.drawable.ic_fog_big;
            } else if ("多云".equals(status)) {
                return R.drawable.ic_cloudy_big;
            } else {
                return R.drawable.ic_default_big;
            }

        }

        class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {

            @Override
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                MyViewHolder holder = new MyViewHolder(LayoutInflater.from(getContext()).inflate
                        (R.layout.item_home, parent, false));
                return holder;
            }

            @Override
            public void onBindViewHolder(MyViewHolder holder, int position) {
                Log.i("xiangkezhu ", "mDatas.get(position).getTimes() = " + mDatas.get(position)
                        .getTimes());
                holder.tv1.setText(mDatas.get(position).getTimes());
                holder.iv.setImageResource(mDatas.get(position).getImage());
                holder.tv2.setText(String.valueOf(mDatas.get(position).getTem()) + String.valueOf
                        ((char) 176));
            }

            @Override
            public int getItemCount() {
                return mDatas.size();
            }

            class MyViewHolder extends RecyclerView.ViewHolder {

                TextView tv1;
                ImageView iv;
                TextView tv2;

                public MyViewHolder(View view) {
                    super(view);
                    tv1 = (TextView) view.findViewById(R.id.id_num);
                    iv = (ImageView) view.findViewById(R.id.id_num2);
                    tv2 = (TextView) view.findViewById(R.id.id_num3);
                }
            }
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
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
