package com.example.paul.betprofessor.viewModel;

import android.graphics.Color;
import android.os.AsyncTask;

import com.example.paul.betprofessor.ui.TeamTipsActivity;

import java.lang.ref.WeakReference;

public class TeamTipsAsyncTask extends AsyncTask<TeamTipsViewModel, Void, String[]> {

    WeakReference<TeamTipsActivity> weakReference;

    public TeamTipsAsyncTask(TeamTipsActivity activity) {

        weakReference = new WeakReference<TeamTipsActivity>(activity);
    }

    @Override
    protected String[] doInBackground(TeamTipsViewModel... viewModels) {

        String[] array = new String[37];
        array[0] = viewModels[0].getAmountOfTeamGames();
        array[1] = viewModels[0].getAverageTeamTotal();
        array[2] = viewModels[0].getMiddlemostMeasure();

        array[3] = viewModels[0].getFirstTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getFirstTeamResult())){
            array[4] = "red";
        }else array[4] = "green";

        array[5] = viewModels[0].getSecondTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getSecondTeamResult())){
            array[6] = "red";
        }else array[6] = "green";

        array[7] = viewModels[0].getThirdTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getThirdTeamResult())){
            array[8] = "red";
        }else array[8] = "green";

        array[9] = viewModels[0].getFourthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getFourthTeamResult())){
            array[10] = "red";
        }else array[10] = "green";

        array[11] = viewModels[0].getFifthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getFifthTeamResult())){
            array[12] = "red";
        }else array[12] = "green";

        array[13] = viewModels[0].getSixthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getSixthTeamResult())){
            array[14] = "red";
        }else array[14] = "green";

        array[15] = viewModels[0].getSeventhTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getSeventhTeamResult())){
            array[16] = "red";
        }else array[16] = "green";

        array[17] = viewModels[0].getEighthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getEighthTeamResult())){
            array[18] = "red";
        }else array[18] = "green";

        array[19] = viewModels[0].getNinthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getNinthTeamResult())){
            array[20] = "red";
        }else array[20] = "green";

        array[21] = viewModels[0].getTenthTeamResult();
        if (Float.parseFloat(viewModels[0].getMiddlemostMeasure()) > Float.parseFloat(viewModels[0].getTenthTeamResult())){
            array[22] = "red";
        }else array[22] = "green";

        array[23] = viewModels[0].getFirstHandicap();
        array[24] = viewModels[0].getSecondHandicap();
        array[25] = viewModels[0].getThirdHandicap();
        array[26] = viewModels[0].getFourthHandicap();
        array[27] = viewModels[0].getFifthHandicap();
        array[28] = viewModels[0].getSixthHandicap();
        array[29] = viewModels[0].getSeventhHandicap();
        array[30] = viewModels[0].getEighthHandicap();
        array[31] = viewModels[0].getNinthHandicap();
        array[32] = viewModels[0].getTenthHandicap();

        String[] middlemostSerialNUmbers;
        middlemostSerialNUmbers = viewModels[0].getMiddlemostResultSerialNumber();

        array[33] = middlemostSerialNUmbers[0];
        array[34] = middlemostSerialNUmbers[1];

        String[] middlemostResults;
        middlemostResults = viewModels[0].getMiddlemostResult();

        array[35] = middlemostResults[0];
        array[36] = middlemostResults[1];

        return array;
    }

    @Override
    protected void onPostExecute(String[] array) {
        super.onPostExecute(array);

        TeamTipsActivity activity = weakReference.get();
        if (activity == null || activity.isFinishing()){
            return;
        }

        activity.numberOfGames.setText(array[0]);
        activity.averageTeamTotal.setText(array[1]);
        activity.middlemostMeasure.setText(array[2]);

        activity.firstResult.setText(array[3]);
        if (array[4].contains("red")){
            activity.firstResult.setTextColor(Color.RED);
        }else activity.firstResult.setTextColor(Color.GREEN);

        activity.secondResult.setText(array[5]);
        if (array[6].contains("red")){
            activity.secondResult.setTextColor(Color.RED);
        }else activity.secondResult.setTextColor(Color.GREEN);

        activity.thirdResult.setText(array[7]);
        if (array[8].contains("red")){
            activity.thirdResult.setTextColor(Color.RED);
        }else activity.thirdResult.setTextColor(Color.GREEN);

        activity.fourthResult.setText(array[9]);
        if (array[10].contains("red")){
            activity.fourthResult.setTextColor(Color.RED);
        }else activity.fourthResult.setTextColor(Color.GREEN);

        activity.fifthResult.setText(array[11]);
        if (array[12].contains("red")){
            activity.fifthResult.setTextColor(Color.RED);
        }else activity.fifthResult.setTextColor(Color.GREEN);

        activity.sixthResult.setText(array[13]);
        if (array[14].contains("red")){
            activity.sixthResult.setTextColor(Color.RED);
        }else activity.sixthResult.setTextColor(Color.GREEN);

        activity.seventhResult.setText(array[15]);
        if (array[16].contains("red")){
            activity.seventhResult.setTextColor(Color.RED);
        }else activity.seventhResult.setTextColor(Color.GREEN);

        activity.eighthResult.setText(array[17]);
        if (array[18].contains("red")){
            activity.eighthResult.setTextColor(Color.RED);
        }else activity.eighthResult.setTextColor(Color.GREEN);

        activity.ninthResult.setText(array[19]);
        if (array[20].contains("red")){
            activity.ninthResult.setTextColor(Color.RED);
        }else activity.ninthResult.setTextColor(Color.GREEN);

        activity.tenthResult.setText(array[21]);
        if (array[22].contains("red")){
            activity.tenthResult.setTextColor(Color.RED);
        }else activity.tenthResult.setTextColor(Color.GREEN);

        activity.firstGameHandicap.setText(array[23]);
        if (array[23].contains("-")){
            activity.firstGameHandicap.setTextColor(Color.RED);
        }else if (array[23].contains("+")) activity.firstGameHandicap.setTextColor(Color.GREEN);

        activity.secondGameHandicap.setText(array[24]);
        if (array[24].contains("-")){
            activity.secondGameHandicap.setTextColor(Color.RED);
        }else if (array[24].contains("+")) activity.secondGameHandicap.setTextColor(Color.GREEN);

        activity.thirdGameHandicap.setText(array[25]);
        if (array[25].contains("-")){
            activity.thirdGameHandicap.setTextColor(Color.RED);
        }else if (array[25].contains("+")) activity.thirdGameHandicap.setTextColor(Color.GREEN);

        activity.fourthGameHandicap.setText(array[26]);
        if (array[26].contains("-")){
            activity.fourthGameHandicap.setTextColor(Color.RED);
        }else if (array[26].contains("+")) activity.fourthGameHandicap.setTextColor(Color.GREEN);

        activity.fifthGameHandicap.setText(array[27]);
        if (array[27].contains("-")){
            activity.fifthGameHandicap.setTextColor(Color.RED);
        }else if (array[27].contains("+")) activity.fifthGameHandicap.setTextColor(Color.GREEN);

        activity.sixthGameHandicap.setText(array[28]);
        if (array[28].contains("-")){
            activity.sixthGameHandicap.setTextColor(Color.RED);
        }else if (array[28].contains("+")) activity.sixthGameHandicap.setTextColor(Color.GREEN);

        activity.seventhGameHandicap.setText(array[29]);
        if (array[29].contains("-")){
            activity.seventhGameHandicap.setTextColor(Color.RED);
        }else if (array[29].contains("+")) activity.seventhGameHandicap.setTextColor(Color.GREEN);

        activity.eighthGameHandicap.setText(array[30]);
        if (array[30].contains("-")){
            activity.eighthGameHandicap.setTextColor(Color.RED);
        }else if (array[30].contains("+")) activity.eighthGameHandicap.setTextColor(Color.GREEN);

        activity.ninthGameHandicap.setText(array[31]);
        if (array[31].contains("-")){
            activity.ninthGameHandicap.setTextColor(Color.RED);
        }else if (array[31].contains("+")) activity.ninthGameHandicap.setTextColor(Color.GREEN);

        activity.tenthGameHandicap.setText(array[32]);
        if (array[32].contains("-")){
            activity.tenthGameHandicap.setTextColor(Color.RED);
        }else if (array[32].contains("+")) activity.tenthGameHandicap.setTextColor(Color.GREEN);

        if (array[34].equals("")){ // the length of array with results is odd number
            activity.serialNumberOfResult1.setText(array[33]);
            activity.serialNumberOfResult2.setText(array[34]);
        }else {
            activity.serialNumberOfResult1.setText(array[34]);
            activity.serialNumberOfResult2.setText(array[33]);
        }

        if (array[36].equals("")){ // the length of array with results is odd number
            activity.middlemostTeamTotal1.setText(array[35]);
            activity.middlemostTeamTotal2.setText(array[36]);
        }else {
            activity.middlemostTeamTotal1.setText(array[36]);
            activity.middlemostTeamTotal2.setText(array[35]);
        }
    }
}
