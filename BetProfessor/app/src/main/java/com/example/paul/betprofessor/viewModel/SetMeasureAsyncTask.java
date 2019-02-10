package com.example.paul.betprofessor.viewModel;

import android.graphics.Color;
import android.os.AsyncTask;

import com.example.paul.betprofessor.ui.TeamTipsActivity;

import java.lang.ref.WeakReference;

public class SetMeasureAsyncTask extends AsyncTask<TeamTipsViewModel, Void, String[]> {

    private static final int LENGTH_OF_ARRAY = 20;

    private String newMeasure;

    WeakReference<TeamTipsActivity> weakReference;

    public SetMeasureAsyncTask(TeamTipsActivity activity, String measure){

        weakReference = new WeakReference<TeamTipsActivity>(activity);
        newMeasure = measure;
    }

    @Override
    protected String[] doInBackground(TeamTipsViewModel... viewModels) {

        String[] array = new String[LENGTH_OF_ARRAY];

        array[0] = viewModels[0].getFirstTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getFirstTeamResult())){
            array[1] = "red";
        }else array[1] = "green";

        array[2] = viewModels[0].getSecondTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getSecondTeamResult())){
            array[3] = "red";
        }else array[3] = "green";

        array[4] = viewModels[0].getThirdTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getThirdTeamResult())){
            array[5] = "red";
        }else array[5] = "green";

        array[6] = viewModels[0].getFourthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getFourthTeamResult())){
            array[7] = "red";
        }else array[7] = "green";

        array[8] = viewModels[0].getFifthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getFifthTeamResult())){
            array[9] = "red";
        }else array[9] = "green";

        array[10] = viewModels[0].getSixthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getSixthTeamResult())){
            array[11] = "red";
        }else array[11] = "green";

        array[12] = viewModels[0].getSeventhTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getSeventhTeamResult())){
            array[13] = "red";
        }else array[13] = "green";

        array[14] = viewModels[0].getEighthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getEighthTeamResult())){
            array[15] = "red";
        }else array[15] = "green";

        array[16] = viewModels[0].getNinthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getNinthTeamResult())){
            array[17] = "red";
        }else array[17] = "green";

        array[18] = viewModels[0].getTenthTeamResult();
        if (Float.parseFloat(newMeasure) > Float.parseFloat(viewModels[0].getTenthTeamResult())){
            array[19] = "red";
        }else array[19] = "green";

        return array;
    }

    @Override
    protected void onPostExecute(String[] array) {
        super.onPostExecute(array);

        TeamTipsActivity activity = weakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.firstResult.setText(array[0]);
        if (array[1].contains("red")){
            activity.firstResult.setTextColor(Color.RED);
        }else activity.firstResult.setTextColor(Color.GREEN);

        activity.secondResult.setText(array[2]);
        if (array[3].contains("red")){
            activity.secondResult.setTextColor(Color.RED);
        }else activity.secondResult.setTextColor(Color.GREEN);

        activity.thirdResult.setText(array[4]);
        if (array[5].contains("red")){
            activity.thirdResult.setTextColor(Color.RED);
        }else activity.thirdResult.setTextColor(Color.GREEN);

        activity.fourthResult.setText(array[6]);
        if (array[7].contains("red")){
            activity.fourthResult.setTextColor(Color.RED);
        }else activity.fourthResult.setTextColor(Color.GREEN);

        activity.fifthResult.setText(array[8]);
        if (array[9].contains("red")){
            activity.fifthResult.setTextColor(Color.RED);
        }else activity.fifthResult.setTextColor(Color.GREEN);

        activity.sixthResult.setText(array[10]);
        if (array[11].contains("red")){
            activity.sixthResult.setTextColor(Color.RED);
        }else activity.sixthResult.setTextColor(Color.GREEN);

        activity.seventhResult.setText(array[12]);
        if (array[13].contains("red")){
            activity.seventhResult.setTextColor(Color.RED);
        }else activity.seventhResult.setTextColor(Color.GREEN);

        activity.eighthResult.setText(array[14]);
        if (array[15].contains("red")){
            activity.eighthResult.setTextColor(Color.RED);
        }else activity.eighthResult.setTextColor(Color.GREEN);

        activity.ninthResult.setText(array[16]);
        if (array[17].contains("red")){
            activity.ninthResult.setTextColor(Color.RED);
        }else activity.ninthResult.setTextColor(Color.GREEN);

        activity.tenthResult.setText(array[18]);
        if (array[19].contains("red")){
            activity.tenthResult.setTextColor(Color.RED);
        }else activity.tenthResult.setTextColor(Color.GREEN);
    }
}
