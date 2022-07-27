package userDefinedClass;

import android.content.ClipData;

import java.util.Comparator;
import java.util.Date;

public class AddNewData{

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

    /**
     * sets the systolic pressure of user
     * @param systolic
     */
    public void setSystolic(int systolic) {
        this.systolic = systolic;
    }

    /**
     * Sets the diastolic Pressure of Users
     * @param diastolic
     */

    public void setDiastolic(int diastolic) {
        this.diastolic = diastolic;
    }

    /**
     * sets heartRate of users
     * @param heartRate
     */

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    /**
     * Sets the Id of user
     * @param id
     */

    public void setId(String id) {
        this.id = id;
    }

    /**
     * Sets the comment of users
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * sets the date
     * @param date
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * get the systolic pressure of user
     * @return
     */
    public int getSystolic() {
        return systolic;
    }

    /**
     * get the diastolic pressure of user
     * @return
     */
    public int getDiastolic() {
        return diastolic;
    }

    /**
     * get the HeartRate of User
     * @return
     */

    public int getHeartRate() {
        return heartRate;
    }

    /**
     * get the id of users
     * @return
     */

    public String getId() {
        return id;
    }

    /**
     * get comments of users
     * @return
     */

    public String getComment() {
        return comment;
    }

    /**
     * Returns the date of users
     * @return
     */
    public Date getDate() {
        return date;
    }
}
