package com.example.paul.betprofessor.model;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

@Database(entities = {OneResult.class}, version = 1, exportSchema = false)
public abstract class OneResultDataBase extends RoomDatabase {

    private static OneResultDataBase instance;

    public abstract OneResultDao oneResultDao();

    public static synchronized OneResultDataBase getInstance(Context context){

        if (instance == null){

            instance = Room.databaseBuilder(context.getApplicationContext()
            , OneResultDataBase.class, "one_result_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDBAsyncTask(instance).execute();
        }
    };

    private static class PopulateDBAsyncTask extends AsyncTask<Void, Void, Void>{

        private OneResultDao oneResultDao;

        public PopulateDBAsyncTask(OneResultDataBase db) {
            oneResultDao = db.oneResultDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            oneResultDao.insert(new OneResult("17-10-2018", "Boston Celtics", "107"
                    , "-7.5", "1.32", "Philadelphia 76ers", "99"
                    , "+7.5", "3.4", "105", "87"));
            oneResultDao.insert(new OneResult("17-10-2018", "Golden State Warriors", "117"
                    , "-11.5", "1.13", "Oklahoma City Thunder", "105"
                    , "+11.5", "6.0", "108", "100"));

            return null;
        }
    }
}
