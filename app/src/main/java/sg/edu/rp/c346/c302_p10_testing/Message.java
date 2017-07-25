package sg.edu.rp.c346.c302_p10_testing;

import java.io.Serializable;

/**
 * Created by 15017608 on 25/7/2017.
 */

public class Message implements Serializable {

    private String title;
    private String date;
    private int numDays;
    private boolean complete;

    public Message(){

    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getNumDays() {
        return numDays;
    }

    public boolean isComplete() {
        return complete;
    }

    public Message(String title, String date, int numDays, boolean complete) {

        this.title = title;
        this.date = date;
        this.numDays = numDays;
        this.complete = complete;
    }
}
