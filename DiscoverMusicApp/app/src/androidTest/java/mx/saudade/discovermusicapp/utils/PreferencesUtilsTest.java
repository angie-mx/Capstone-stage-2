package mx.saudade.discovermusicapp.utils;

import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by angie on 6/20/16.
 */
@RunWith(AndroidJUnit4.class)
public class PreferencesUtilsTest {

    @Test
    public void testSavingMinPopularity() {
        int minPopularity = 34;
        PreferencesUtils.setMinPopularity(InstrumentationRegistry.getTargetContext()
                , minPopularity);

        int minPopularityReceived = PreferencesUtils.getMinPopularity(
                InstrumentationRegistry.getTargetContext());

        Assert.assertEquals("min popularity received is not as expected"
                , minPopularity, minPopularityReceived);
    }

    @Test
    public void testSavingMaxPopularity() {
        int maxPopularity = 78;
        PreferencesUtils.setMaxPopularity(InstrumentationRegistry.getTargetContext()
                , maxPopularity);

        int maxPopularityReceived = PreferencesUtils.getMaxPopularity(
                InstrumentationRegistry.getTargetContext());

        Assert.assertEquals("max popularity received is not as expected"
                , maxPopularity, maxPopularityReceived);
    }

    @Test
    public void testSavingResultsNumber() {
        int resultsNumber = 155;

        PreferencesUtils.setResultsNumber(InstrumentationRegistry.getTargetContext()
                , resultsNumber);

        int resultsNumberReceived = PreferencesUtils.getResultsNumber(
                InstrumentationRegistry.getTargetContext());

        Assert.assertEquals("results number received is not as expected"
                , resultsNumber, resultsNumberReceived);
    }

    @Test
    public void testSavingLocation() {
        String location = "mx";

        PreferencesUtils.setLocation(InstrumentationRegistry.getTargetContext(), location);

        String locationReceived = PreferencesUtils.getLocation(
                InstrumentationRegistry.getTargetContext());

        Assert.assertEquals("location received is not as expected"
                , location, locationReceived);
    }

}