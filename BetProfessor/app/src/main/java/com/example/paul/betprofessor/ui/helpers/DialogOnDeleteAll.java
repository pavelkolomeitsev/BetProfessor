package com.example.paul.betprofessor.ui.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;

import com.example.paul.betprofessor.R;

public class DialogOnDeleteAll extends AppCompatDialogFragment {

    private DialogOnDeleteAllListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(R.string.dialog_title)
                .setMessage(R.string.dialog_message)
                .setNegativeButton(R.string.dialog_cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton(R.string.dialog_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onYesClickedButton();
                    }
                });

        return builder.create();
    }

    // implement an interface for communicating between Dialog class and MainActivity class
    public interface DialogOnDeleteAllListener{
        void onYesClickedButton();
    }

    // override onAttach-method
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogOnDeleteAllListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement DialogOnDeleteAllListener");
        }
    }

}
