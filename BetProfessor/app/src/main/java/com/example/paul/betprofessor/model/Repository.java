package com.example.paul.betprofessor.model;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class Repository {

    private OneResultDao oneResultDao;

    private LiveData<List<OneResult>> getLastTwentyResults;

    private String mTeamName;

    public Repository(Application application){

        OneResultDataBase oneResultDataBase = OneResultDataBase.getInstance(application);
        oneResultDao = oneResultDataBase.oneResultDao();
        getLastTwentyResults = oneResultDao.getLastTwentyResults();
    }

    public void insert(OneResult oneResult){

        new InsertOneResultAsyncTask(oneResultDao).execute(oneResult);
    }

    public void update(OneResult oneResult){

        new UpdateOneResultAsyncTask(oneResultDao).execute(oneResult);
    }

    public void delete(OneResult oneResult){

        new DeleteOneResultAsyncTask(oneResultDao).execute(oneResult);
    }

    public void deleteAllResults(){

        new DeleteAllOneResultAsyncTask(oneResultDao).execute();
    }

    public LiveData<List<OneResult>> getGetLastTwentyResults(){
        return getLastTwentyResults;
    }

    public List<OneResult> findAllTeamGames(String teamName){

        List<OneResult> list = null;
        mTeamName = teamName;
        FindAllTeamGamesAsyncTask findAllTeamGames = new FindAllTeamGamesAsyncTask(oneResultDao);
        findAllTeamGames.execute(mTeamName);

        try {
            list = findAllTeamGames.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return list;
    }

    private static class FindAllTeamGamesAsyncTask extends AsyncTask<String, Void, List<OneResult>>{

        private OneResultDao oneResultDao;

        public FindAllTeamGamesAsyncTask(OneResultDao oneResultDao){

            this.oneResultDao = oneResultDao;
        }

        @Override
        protected List<OneResult> doInBackground(String... strings) {

            return oneResultDao.findAllTeamGames(strings[0]);
        }
    }

    private static class InsertOneResultAsyncTask extends AsyncTask<OneResult, Void, Void> {

        private OneResultDao oneResultDao;

        public InsertOneResultAsyncTask(OneResultDao oneResultDao){
            this.oneResultDao = oneResultDao;
        }

        @Override
        protected Void doInBackground(OneResult... oneResults) {
            oneResultDao.insert(oneResults[0]);
            return null;
        }
    }

    private static class UpdateOneResultAsyncTask extends AsyncTask<OneResult, Void, Void>{

        private OneResultDao oneResultDao;

        public UpdateOneResultAsyncTask(OneResultDao oneResultDao){
            this.oneResultDao = oneResultDao;
        }

        @Override
        protected Void doInBackground(OneResult... oneResults) {
            oneResultDao.update(oneResults[0]);
            return null;
        }
    }

    private static class DeleteOneResultAsyncTask extends AsyncTask<OneResult, Void, Void>{

        private OneResultDao oneResultDao;

        public DeleteOneResultAsyncTask(OneResultDao oneResultDao){
            this.oneResultDao = oneResultDao;
        }

        @Override
        protected Void doInBackground(OneResult... oneResults) {
            oneResultDao.delete(oneResults[0]);
            return null;
        }
    }

    private static class DeleteAllOneResultAsyncTask extends AsyncTask<Void, Void, Void>{

        private OneResultDao oneResultDao;

        public DeleteAllOneResultAsyncTask(OneResultDao oneResultDao){
            this.oneResultDao = oneResultDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            oneResultDao.deleteAllResults();
            return null;
        }
    }
}
