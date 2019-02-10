package com.example.paul.betprofessor.ui.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.paul.betprofessor.R;

public class DialogOnSetNewMeasure extends AppCompatDialogFragment {

    private EditText setNewMeasure;
    private DialogSetNewMeasureListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_on_set_measure, null);

        builder.setView(view)
                .setTitle("Set new measure")
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.apply, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String newMeasure = setNewMeasure.getText().toString();

                        // that`s how we pass a new measure to activity
                        listener.applyNewMeasure(newMeasure);
                    }
                });

        setNewMeasure = view.findViewById(R.id.et_set_new_measure);

        return builder.create();
    }

    // by helping an interface we may pass data between dialog and activity
    public interface DialogSetNewMeasureListener{
        void applyNewMeasure(String measure);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogSetNewMeasureListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement interface");
        }
    }
}
