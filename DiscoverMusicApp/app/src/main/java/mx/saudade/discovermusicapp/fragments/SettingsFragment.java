package mx.saudade.discovermusicapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.appyvet.rangebar.RangeBar;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.handlers.LocationHandler;
import mx.saudade.discovermusicapp.models.Country;
import mx.saudade.discovermusicapp.utils.ConnectivityUtils;
import mx.saudade.discovermusicapp.utils.PreferencesUtils;

import static mx.saudade.discovermusicapp.utils.SettingsUtils.getCountries;
import static mx.saudade.discovermusicapp.utils.SettingsUtils.getResultsNumber;

/**
 * Created by angie on 6/30/16.
 */
public class SettingsFragment extends Fragment {

    private LocationHandler locationHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container
            , @Nullable Bundle savedInstanceState) {
        ConnectivityUtils.checkLocationEnabled(getActivity());
        return inflater.inflate(R.layout.settings_fragment, null);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        locationHandler = new LocationHandler(getActivity());
        locationHandler.onCreate();
        initViews();
    }

    @Override
    public void onStart() {
        locationHandler.onStart();
        super.onStart();
    }

    @Override
    public void onStop() {
        locationHandler.onStop();
        super.onStop();
    }

    private void initViews() {
        setRangeBar();
        populateCountrySpinner();
        setInitValueCountrySpinner();
        populateResultsNumberSpinner();
        setInitValueResultsNumberSpinner();
        setCurrentLocationButton();
        setSavePreferencesButton();
    }

    private void setRangeBar() {
        int minPopularity = PreferencesUtils.getMinPopularity(getContext());
        int maxPopularity = PreferencesUtils.getMaxPopularity(getContext());
        getRangeBar().setRangePinsByValue(minPopularity, maxPopularity);
    }

    private void populateCountrySpinner() {
        ArrayAdapter<Country> dataAdapter = new ArrayAdapter<Country>(getContext()
                , android.R.layout.simple_spinner_item, getCountries());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSpinner(R.id.settings_listener_country).setAdapter(dataAdapter);
    }

    private void setInitValueCountrySpinner() {
        String countryCode = PreferencesUtils.getLocation(getActivity());
        setValueCountrySpinner(countryCode);
    }

    private void setCurrentLocationCountrySpinner() {
        String countryCode = locationHandler.getCountryCode();
        setValueCountrySpinner(countryCode);
    }

    private void setValueCountrySpinner(String countryCode) {
        int index = getCountries().indexOf(new Country(countryCode, null));
        getSpinner(R.id.settings_listener_country).setSelection(index);
    }

    private void populateResultsNumberSpinner() {
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<Integer>(getContext()
                , android.R.layout.simple_spinner_item, getResultsNumber());
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        getSpinner(R.id.settings_results_number).setAdapter(dataAdapter);
    }

    private void setInitValueResultsNumberSpinner() {
        int resultsNumber = PreferencesUtils.getResultsNumber(getContext());
        getSpinner(R.id.settings_results_number).setSelection(resultsNumber - 1);
    }

    private void setCurrentLocationButton() {
        getButton(R.id.settings_current_location).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCurrentLocationCountrySpinner();
            }
        });
    }

    private void setSavePreferencesButton() {
        getButton(R.id.settings_save_changes).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferences();
            }
        });
    }

    private void savePreferences() {
        int minPopularity = getRangeBar().getLeftIndex();
        PreferencesUtils.setMinPopularity(getContext(), minPopularity);

        int maxPopularity = getRangeBar().getRightIndex();
        PreferencesUtils.setMaxPopularity(getContext(), maxPopularity);

        String location = ((Country) getSpinner(R.id.settings_listener_country).getSelectedItem())
                .getCode();
        PreferencesUtils.setLocation(getContext(), location);

        int resultsNumber = (Integer) getSpinner(R.id.settings_results_number).getSelectedItem();
        PreferencesUtils.setResultsNumber(getContext(), resultsNumber);
    }

    private Spinner getSpinner(int id) {
        return (Spinner) getView().findViewById(id);
    }

    private RangeBar getRangeBar() {
        return (RangeBar) getView().findViewById(R.id.popularity_rangebar);
    }

    private Button getButton(int id) {
        return (Button) getView().findViewById(id);
    }

}
