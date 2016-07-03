package mx.saudade.discovermusicapp.controllers;

import android.view.View;
import android.widget.Button;

import mx.saudade.discovermusicapp.R;
import mx.saudade.discovermusicapp.ui.StateButton;

/**
 * Created by angie on 7/2/16.
 */
public class MoodController {

    private static final int GENRES_BUTTON_SIZE = 16;

    private static final int YEARS_BUTTON_SIZE = 8;

    private StateButton[] genresButtons = new StateButton[GENRES_BUTTON_SIZE];

    private StateButton[] yearsButtons = new StateButton[YEARS_BUTTON_SIZE];

    private Button allGenresButton;

    private Button noGenresButton;

    private View view;

    public MoodController(View view) {
        this.view = view;
        initiateButtons();
    }

    public boolean[] getYears() {
        boolean[] years = new boolean[YEARS_BUTTON_SIZE];
        for (int i = 0; i < YEARS_BUTTON_SIZE; i++)
            years[i] = yearsButtons[i].isStateSelected();

        return years;
    }

    public String getMoods() {
        StringBuilder moods = new StringBuilder();
        for (int i = 0; i < GENRES_BUTTON_SIZE; i++) {
            if (genresButtons[i].isStateSelected()) {
                moods.append(genresButtons[i].getServiceGenre());
                moods.append(", ");
            }
        }
        return moods.toString();
    }

    private void initiateButtons() {
        yearsButtons[0] = getSelectedButton(R.id.year_before_50);
        yearsButtons[1] = getSelectedButton(R.id.year_50);
        yearsButtons[2] = getSelectedButton(R.id.year_60);
        yearsButtons[3] = getSelectedButton(R.id.year_70);
        yearsButtons[4] = getSelectedButton(R.id.year_80);
        yearsButtons[5] = getSelectedButton(R.id.year_90);
        yearsButtons[6] = getSelectedButton(R.id.year_00);
        yearsButtons[7] = getSelectedButton(R.id.year_10);

        genresButtons[0] = getSelectedButton(R.id.genre_rock_button);
        genresButtons[1] = getSelectedButton(R.id.genre_pop_button);
        genresButtons[2] = getSelectedButton(R.id.genre_folk_button);
        genresButtons[3] = getSelectedButton(R.id.genre_electro_button);
        genresButtons[4] = getSelectedButton(R.id.genre_r_b_button);
        genresButtons[5] = getSelectedButton(R.id.genre_hip_hop_button);
        genresButtons[6] = getSelectedButton(R.id.genre_vocal_pop_button);
        genresButtons[7] = getSelectedButton(R.id.genre_soundtrack_button);
        genresButtons[8] = getSelectedButton(R.id.genre_classical_button);
        genresButtons[9] = getSelectedButton(R.id.genre_latin_button);
        genresButtons[10] = getSelectedButton(R.id.genre_world_button);
        genresButtons[11] = getSelectedButton(R.id.genre_reggae_button);
        genresButtons[12] = getSelectedButton(R.id.genre_blues_button);
        genresButtons[13] = getSelectedButton(R.id.genre_jazz_button);
        genresButtons[14] = getSelectedButton(R.id.genre_country_button);
        genresButtons[15] = getSelectedButton(R.id.genre_metal_button);

        allGenresButton = getButton(R.id.genre_all_button);
        allGenresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGenreButtonsState(true);
            }
        });
        noGenresButton = getButton(R.id.genre_none_button);
        noGenresButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeGenreButtonsState(false);
            }
        });

        changeYearsButtonsState(true);
        changeGenreButtonsState(true);
    }

    private void changeYearsButtonsState(boolean state) {
        for (int i = 0; i < YEARS_BUTTON_SIZE; i++)
            yearsButtons[i].setSelected(state);
    }

    private void changeGenreButtonsState(boolean state) {
        for (int i = 0; i < GENRES_BUTTON_SIZE; i++)
            genresButtons[i].setSelected(state);
    }

    private StateButton getSelectedButton(int id) {
        return (StateButton) view.findViewById(id);
    }

    private Button getButton(int id) {
        return (Button) view.findViewById(id);
    }

}
