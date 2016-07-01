package mx.saudade.discovermusicapp.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mx.saudade.discovermusicapp.models.Country;

/**
 * Created by angie on 6/30/16.
 */
public final class SettingsUtils {

    private static final List<Country> countries;

    private static final List<Integer> resultsNumber;

    static {
        countries = new ArrayList<>();
        String[] isoCountryCodes = Locale.getISOCountries();
        for (String countryCode : isoCountryCodes) {
            Locale locale = new Locale(StringUtils.EMPTY, countryCode);
            countries.add(new Country(countryCode, locale.getDisplayCountry()));
        }
    }

    static {
        final int MAX_VALUES = 50;
        resultsNumber = new ArrayList<>(MAX_VALUES);
        for (int i = 1; i <= MAX_VALUES; i++) {
            resultsNumber.add(i);
        }
    }

    public static List<Country> getCountries() {
        return countries;
    }

    public static List<Integer> getResultsNumber() {
        return resultsNumber;
    }
}
