package userDefinedClass;

import android.content.ClipData;

import java.util.Comparator;
import java.util.Date;

public class AddNewData {
    int systolic,diastolic, heartRate;
    String id,comment;
    Date date;

    public AddNewData(int systolic, int diastolic, int heartRate, String id, String comment, Date date) {
        this.systolic = systolic;
        this.diastolic = diastolic;
        this.heartRate = heartRate;
        this.id = id;
        this.comment = comment;
        this.date = date;
    }

    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getSystolic() {
        return systolic;
    }

    public int getDiastolic() {
        return diastolic;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public String getId() {
        return id;
    }

    public String getComment() {
        return comment;
    }

    public Date getDate() {
        return date;
    }


}
