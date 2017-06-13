package com.jeongho.jogank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Jeongho on 2017/6/9.
 */

public class Fuli {
    private boolean error;
    private List<Meizi> results = new ArrayList<>();

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<Meizi> getResults() {
        return results;
    }

    public void setResults(List<Meizi> results) {
        this.results = results;
    }
}
