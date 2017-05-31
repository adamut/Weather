package com.weather.weather;

import android.support.test.rule.ActivityTestRule;
import android.view.View;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

public class SearchCityTest
{
    @Rule
    public ActivityTestRule<SearchCity> mActivityTestRule = new ActivityTestRule<>(SearchCity.class);
    private SearchCity mActivity= null;

    @Before
    public void setUp() throws Exception
    {
        mActivity= mActivityTestRule.getActivity();
    }
    @Test

    public void testLaunch()
    {
        View view=mActivity.findViewById(R.id.editTextSearch);
        assertNotNull(view);

    }
    @After
    public void tearDown() throws Exception {
        mActivity=null;
    }
}