package com.example.paul.betprofessor.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.paul.betprofessor.model.OneResult;
import com.example.paul.betprofessor.model.Repository;

import java.util.List;

public class OneResultViewModel extends AndroidViewModel {

    private Repository repository;
    private LiveData<List<OneResult>> getLastTwentyResults;

    public OneResultViewModel(@NonNull Application application) {
        super(application);

        repository = new Repository(application);
        getLastTwentyResults = repository.getGetLastTwentyResults();
    }

    public void insert(OneResult oneResult){
        repository.insert(oneResult);
    }

    public void update(OneResult oneResult){
        repository.update(oneResult);
    }

    public void delete(OneResult oneResult){
        repository.delete(oneResult);
    }

    public void deleteAllResults(){
        repository.deleteAllResults();
    }

    public LiveData<List<OneResult>> getGetLastTwentyResults(){
        return getLastTwentyResults;
    }
}
