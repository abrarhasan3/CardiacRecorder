package android.example.cardiacrecorder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import userDefinedClass.AddNewData;

public class AddNewDataUnitTest {

    /**
     * testing addData method
     */
    @Test
    public void testAddData() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(75,130,72,"Id1","New Comment1",date);
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        AddNewData data2 = new AddNewData(80,140,67,"Id2","New Comment2",date);
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));
    }

    /**
     * testing deleteData method
     */
    @Test
    public void testDeleteData() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(77,132,74,"Id3","New Comment3",date);
        dataList.addData(data1);
        assertEquals(1, dataList.getData().size());

        AddNewData data2 = new AddNewData(82,138,65,"Id4","New Comment4",date);
        dataList.addData(data2);
        assertEquals(2, dataList.getData().size());

        assertTrue(dataList.getData().contains(data1));
        assertTrue(dataList.getData().contains(data2));

        dataList.deleteData(data1);
        assertEquals(1, dataList.getData().size());
        assertFalse(dataList.getData().contains(data1));

        dataList.deleteData(data2);
        assertEquals(0, dataList.getData().size());
        assertFalse(dataList.getData().contains(data2));
    }

    /**
     * testing addData method for exceptions
     */
    @Test
    public void testAddRecordException() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(76,133,72,"Id5","New Comment5",date);
        dataList.addData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.addData(data1));
    }

    /**
     * testing deleteData method for exceptions
     */
    @Test
    public void testDeleteRecordException() {
        Date date = Calendar.getInstance().getTime();

        AddNewDataClass dataList = new AddNewDataClass();
        AddNewData data1 = new AddNewData(79,136,74,"Id6","New Comment6",date);
        dataList.addData(data1);

        dataList.deleteData(data1);

        assertThrows(IllegalArgumentException.class, () -> dataList.deleteData(data1));
    }
}
