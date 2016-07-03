package mx.saudade.discovermusicapp.utils;

import android.content.Context;
import android.support.v4.content.ContextCompat;

import org.apache.commons.lang3.StringUtils;

import mx.saudade.discovermusicapp.R;

/**
 * Created by angie on 7/2/16.
 */
public final class AdapterUtils {

    private AdapterUtils() { }

    public static int getColorFromGenre(Context context, String genre) {
        int color = ContextCompat.getColor(context, R.color.all_none);
        if(genre == null) {
            return color;
        }

        if (StringUtils.equalsIgnoreCase(genre, context.getString(R.string.service_genre_rock))) {
            color = ContextCompat.getColor(context, R.color.rock);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_pop))) {
            color = ContextCompat.getColor(context, R.color.pop);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_folk))) {
            color = ContextCompat.getColor(context, R.color.folk);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_electro))) {
            color = ContextCompat.getColor(context, R.color.electro);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_r_b))) {
            color = ContextCompat.getColor(context, R.color.r_b);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_hip_hop))) {
            color = ContextCompat.getColor(context, R.color.hip_hop);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_vocal_pop))) {
            color = ContextCompat.getColor(context, R.color.vocal);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_soundtrack))) {
            color = ContextCompat.getColor(context, R.color.soundtrack);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_classical))) {
            color = ContextCompat.getColor(context, R.color.classical);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_latin))) {
            color = ContextCompat.getColor(context, R.color.latin);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_world))) {
            color = ContextCompat.getColor(context, R.color.world);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_reggae))) {
            color = ContextCompat.getColor(context, R.color.reggae);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_blues))) {
            color = ContextCompat.getColor(context, R.color.blues);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_jazz))) {
            color = ContextCompat.getColor(context, R.color.jazz);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_country))) {
            color = ContextCompat.getColor(context, R.color.country);
        } else if (StringUtils.equalsIgnoreCase(genre
                , context.getString(R.string.service_genre_metal))) {
            color = ContextCompat.getColor(context, R.color.metal);
        }

        return color;
    }
}
