package mx.saudade.discovermusicapp.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

import org.apache.commons.lang3.StringUtils;

import mx.saudade.discovermusicapp.R;

/**
 * Created by angie on 7/1/16.
 */
public class StateButton extends Button implements View.OnClickListener {

    private boolean stateSelected;

    private int noSelectedColor;

    private int selectedColor;

    private String serviceGenre;

    public StateButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public StateButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        noSelectedColor = ContextCompat.getColor(context, R.color.no_selected_button);
        setOnClickListener(this);
        getAttributeColor(context, attrs);
        getAttributeServiceGenre(context, attrs);
    }

    private void getAttributeColor(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateButton, 0, 0);
        try {
            int idColor = a.getResourceId(R.styleable.StateButton_selected_color, 0);
            selectedColor = ContextCompat.getColor(context, idColor);
        } finally {
            a.recycle();
        }
    }

    private void getAttributeServiceGenre(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.StateButton, 0, 0);
        try {
            serviceGenre = a.getString(R.styleable.StateButton_service_genre);
        } finally {
            a.recycle();
        }
    }

    public String getServiceGenre() {
        return serviceGenre;
    }

    public boolean isStateSelected() {
        return stateSelected;
    }

    @Override
    public void onClick(View v) {
        stateSelected = !stateSelected;
        this.changeBackgroundColor();
    }

    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        stateSelected = selected;
        changeBackgroundColor();
    }

    private void changeBackgroundColor() {
        if (stateSelected) {
            this.setBackgroundColor(selectedColor);
        } else {
            this.setBackgroundColor(noSelectedColor);
        }
    }

}
