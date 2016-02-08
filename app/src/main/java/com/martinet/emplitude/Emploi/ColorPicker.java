package com.martinet.emplitude.Emploi;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;
import com.martinet.emplitude.MainActivity;
import com.martinet.emplitude.R;

public class ColorPicker extends DialogFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.color_picker, container);
        Button annuler = (Button)view.findViewById(R.id.cancelButton);
        annuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        final ColorPickerView mColorPickerView = (ColorPickerView) view.findViewById(R.id.colorpickerview__color_picker_view);
        Button ok = (Button) view.findViewById(R.id.okButton);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Emploi)((MainActivity)getActivity()).getFragment()).getFragment().setColorButton(mColorPickerView.getColor());
                dismiss();
            }
        });
        getDialog().setTitle("Selectionner une couleur");

        return view;
    }
}