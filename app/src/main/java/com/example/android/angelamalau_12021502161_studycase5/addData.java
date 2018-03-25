package com.example.android.angelamalau_12021502161_studycase5;

/**
 * Created by Angela Malau on 25/03/2018.
 */

public class addData {
    //deklarasi variable
    String todo, desc, prior;

    //konstruktor
    public addData(String todo, String desc, String prior){
        this.todo = todo;
        this.desc = desc;
        this.prior = prior;
    }

    //method setter dan getter to do
    public String getTodo() {
        return todo;
    }

    public void setTodo(String todo) {
        this.todo = todo;
    }

    //method setter dan getter untuk desc
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    //method setter dan getter untuk prority
    public String getPrior() {
        return prior;
    }

    public void setPrior(String prior) {
        this.prior = prior;
    }
}
