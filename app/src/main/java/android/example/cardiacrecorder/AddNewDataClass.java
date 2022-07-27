package android.example.cardiacrecorder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import userDefinedClass.AddNewData;

/*
 this class represents a list of AddNewData
 */

public class AddNewDataClass {
    public List<AddNewData> records = new ArrayList<>(); //a list of type "AddNewData" is declared

    /**
     * this method is used to add any new data
     * if data already exists,it will throw an exception
     * @param data a new record
     */
    public void addData(AddNewData data)
    {
        if(records.contains(data))
        {
            throw new IllegalArgumentException();
        }
        records.add(data);
    }

    /**
     * this method returns an instance of sorted record list
     * sort is based on first attribute by default
     * @return a list of data
     */

    public List<AddNewData> getData()
    {
        List<AddNewData>dataList = records;
        return dataList;
    }

    public List<AddNewData> getData(int x)
    {
        List<AddNewData> dataList = records;
        return dataList;
    }

    /**
     * this method is used for deleting a particular data
     * if the data doesnt exist,it will throw an exception
     * @param data a data that need to be deleted
     */
    public void deleteData(AddNewData data)
    {
        List<AddNewData> dataList = records;
        if(dataList.contains(data)){
            records.remove(data);
        }
        else
        {
            throw new IllegalArgumentException();
        }
    }

    /**
     * this method returns the size of list
     * @return int
     */
    public int countData()
    {
        return records.size();
    }
}
