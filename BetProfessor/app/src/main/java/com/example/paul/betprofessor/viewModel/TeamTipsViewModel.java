package com.example.paul.betprofessor.viewModel;

import android.app.Application;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.model.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class TeamTipsViewModel {

    private Repository repository;
    private String mTeamName;
    public List<OneResult> temp;
    public List<OneResult> findAllTeamGames;
    public Integer[] allTeamResults;

    public TeamTipsViewModel(@NonNull Application application, String teamName) {

        repository = new Repository(application);
        mTeamName = teamName;
        temp = repository.findAllTeamGames(mTeamName);
        new TeamTipsViewModelAsyncTask().execute();
    }

    // fill int-array with values
    private void getAllTeamResults() {

        int i = 0;

        for (OneResult item : findAllTeamGames) {

            if ((item.getHomeTeamName().equals(mTeamName))) {
                allTeamResults[i] = Integer.parseInt(item.getHomeResult());
                i++;
            }
            if (item.getGuestTeamName().equals(mTeamName)) {
                allTeamResults[i] = Integer.parseInt(item.getGuestResult());
                i++;
            }
        }
    }

    class TeamTipsViewModelAsyncTask extends AsyncTask<Void, Void, Void> {

        // prepare list of results for work with it
        @Override
        protected Void doInBackground(Void... voids) {

            if (temp == null) {
                findAllTeamGames = null;
                allTeamResults = new Integer[]{0};
            } else {

                if (temp.size() == 0){
                    allTeamResults = new Integer[]{0};
                    findAllTeamGames = null;
                }else {
                    Collections.reverse(temp);

                    findAllTeamGames = new ArrayList<>();

                    for (OneResult item: temp) {
                        if (!(item.getHomeResult().equals("")) || !(item.getGuestResult().equals(""))){
                            findAllTeamGames.add(item);
                        }
                    }

                    allTeamResults = new Integer[findAllTeamGames.size()];

                    getAllTeamResults();
                }
            }
            return null;
        }
    }

    public String getAmountOfTeamGames() {

        if (findAllTeamGames == null) return "0";
        else return String.valueOf(findAllTeamGames.size());
    }

    public String getAverageTeamTotal() {

        float sum = 0;
        float averageTeamTotal;

        if (findAllTeamGames == null) return "0";
        else {

            if (findAllTeamGames.size() <= 1) return "1";
            else {
                for (int i = 0; i < findAllTeamGames.size(); i++) {
                    sum += (float) (allTeamResults[i]);
                }

                averageTeamTotal = sum / findAllTeamGames.size();

                int temp = (int) (averageTeamTotal * 10);

                return String.valueOf(temp / 10.0);
            }
        }
    }

    public String[] getMiddlemostResultSerialNumber() {

        String[] middlemostResultSerialNumber = new String[2];

        if (findAllTeamGames == null){
            middlemostResultSerialNumber[0] = "0";
            middlemostResultSerialNumber[1] = "";
        }
        else {
            if (findAllTeamGames.size() <= 1){
                middlemostResultSerialNumber[0] = "1";
                middlemostResultSerialNumber[1] = "";
            }else {
                Integer[] temp = allTeamResults.clone();

                Arrays.sort(temp);

                // if length of array is even, we fill two serial numbers near the middle of array
                if ((temp.length % 2) == 0) {
                    middlemostResultSerialNumber[0] = String.valueOf((temp.length / 2));
                    middlemostResultSerialNumber[1] = String.valueOf((temp.length / 2) + 1);
                } else {
                    middlemostResultSerialNumber[0] = String.valueOf((temp.length / 2) + 1);
                    middlemostResultSerialNumber[1] = "";
                }
            }
        }
        return middlemostResultSerialNumber;
    }

    public String[] getMiddlemostResult() {

        String[] middlemostResult = new String[2];

        if (findAllTeamGames == null){
            middlemostResult[0] = "0";
            middlemostResult[1] = "";
        }
        else {
            if (findAllTeamGames.size() <= 1){
                middlemostResult[0] = "1";
                middlemostResult[1] = "";
            }else {
                Integer[] temp = allTeamResults.clone();

                Arrays.sort(temp);
                // if length of array is even, we fill two values
                if ((temp.length % 2) == 0) {
                    middlemostResult[0] = String.valueOf(temp[(temp.length / 2) - 1]);
                    middlemostResult[1] = String.valueOf(temp[temp.length / 2]);
                } else {
                    middlemostResult[0] = String.valueOf(temp[temp.length / 2]);
                    middlemostResult[1] = "";
                }
            }
        }
        return middlemostResult;
    }

    public String getMiddlemostMeasure() {

        Integer[] temp = new Integer[2];
        Integer[] sortedArray = allTeamResults.clone();
        Arrays.sort(sortedArray);
        float middlemostMeasure;

        if (findAllTeamGames == null) middlemostMeasure = 0F;
        else {

            if (sortedArray.length <= 1){
                middlemostMeasure = 0F;
            }else {
                if ((sortedArray.length % 2) == 0) {
                    temp[0] = sortedArray[(sortedArray.length / 2)];
                    temp[1] = sortedArray[((sortedArray.length / 2) - 1)];
                    middlemostMeasure = (Float.parseFloat(getAverageTeamTotal()) + temp[0] + temp[1]) / 3;
                } else {
                    temp[0] = sortedArray[sortedArray.length / 2];
                    temp[1] = 0;
                    middlemostMeasure = (Float.parseFloat(getAverageTeamTotal()) + temp[0]) / 2;
                }
            }


        }

        int tempMeasure = (int) (middlemostMeasure * 10);

        return String.valueOf(tempMeasure / 10.0);
    }

    public String getFirstTeamResult(){

        if (findAllTeamGames == null) return "0";
        else return String.valueOf(allTeamResults[0]);
    }

    public String getSecondTeamResult(){

        if (allTeamResults.length < 2) return "0";
        else return String.valueOf(allTeamResults[1]);
    }

    public String getThirdTeamResult(){

        if (allTeamResults.length < 3) return "0";
        else return String.valueOf(allTeamResults[2]);
    }

    public String getFourthTeamResult(){

        if (allTeamResults.length < 4) return "0";
        else return String.valueOf(allTeamResults[3]);
    }

    public String getFifthTeamResult(){

        if (allTeamResults.length < 5) return "0";
        else return String.valueOf(allTeamResults[4]);
    }

    public String getSixthTeamResult(){

        if (allTeamResults.length < 6) return "0";
        else return String.valueOf(allTeamResults[5]);
    }

    public String getSeventhTeamResult(){

        if (allTeamResults.length < 7) return "0";
        else return String.valueOf(allTeamResults[6]);
    }

    public String getEighthTeamResult(){

        if (allTeamResults.length < 8) return "0";
        else return String.valueOf(allTeamResults[7]);
    }

    public String getNinthTeamResult(){

        if (allTeamResults.length < 9) return "0";
        else return String.valueOf(allTeamResults[8]);
    }

    public String getTenthTeamResult(){

        if (allTeamResults.length < 10) return "0";
        else return String.valueOf(allTeamResults[9]);
    }

    public String getFirstHandicap(){

        if (findAllTeamGames == null) return "?";
        else {
            OneResult item = findAllTeamGames.get(0);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getSecondHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 2) return "?";
        else {
            OneResult item = findAllTeamGames.get(1);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getThirdHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 3) return "?";
        else {
            OneResult item = findAllTeamGames.get(2);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getFourthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 4) return "?";
        else {
            OneResult item = findAllTeamGames.get(3);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getFifthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 5) return "?";
        else {
            OneResult item = findAllTeamGames.get(4);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getSixthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 6) return "?";
        else {
            OneResult item = findAllTeamGames.get(5);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getSeventhHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 7) return "?";
        else {
            OneResult item = findAllTeamGames.get(6);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getEighthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 8) return "?";
        else {
            OneResult item = findAllTeamGames.get(7);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getNinthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 9) return "?";
        else {
            OneResult item = findAllTeamGames.get(8);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }

    public String getTenthHandicap(){

        if (findAllTeamGames == null || findAllTeamGames.size() < 10) return "?";
        else {
            OneResult item = findAllTeamGames.get(9);
            String handicap = "?";

            if (item.getHomeTeamName().equals(mTeamName)){
                if (item.getHomeResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getHomeResult())
                        + Float.parseFloat(item.getHomeHandicap())) > Float.parseFloat(item.getGuestResult())) handicap = "+";
                else handicap = "-";
            }else if (item.getGuestTeamName().equals(mTeamName)){
                if (item.getGuestResult().equals("")) handicap = "?";
                else if ((Float.parseFloat(item.getGuestResult())
                        + Float.parseFloat(item.getGuestHandicap())) > Float.parseFloat(item.getHomeResult())) handicap = "+";
                else handicap = "-";
            }

            return handicap;
        }
    }
}
