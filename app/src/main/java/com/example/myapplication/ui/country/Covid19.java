package com.example.myapplication.ui.country;

public class Covid19 {
    String mCovidCountry,mCases,mTodayCases,mDeath,getmTodayrip,mRecovered;

    public Covid19(String mCovidCountry, String mCases) {
        this.mCovidCountry = mCovidCountry;
        this.mCases = mCases;
        this.mTodayCases = mTodayCases;
        this.mDeath = mDeath;
        this.getmTodayrip = getmTodayrip;
        this.mRecovered = mRecovered;
    }

    public String getmCovidCountry() {
        return mCovidCountry;
    }

    public String getmCases() {
        return mCases;
    }
}
