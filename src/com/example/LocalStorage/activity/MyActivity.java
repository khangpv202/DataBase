package com.example.LocalStorage.activity;

import android.app.Activity;
import android.content.ContentResolver;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.example.LocalStorage.R;
import com.example.LocalStorage.model.Student;
import com.example.LocalStorage.util.CustomeListViewAdapter;
import com.example.LocalStorage.util.DatabaseHandler;

import java.io.*;

public class MyActivity extends Activity
{
    private ListView main_lvStudentList;
    private final String TAG = "MyActivity";
    private CustomeListViewAdapter customeListViewAdapter;
    DatabaseHandler db;
    ContentResolver contentResolver;


    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        main_lvStudentList = (ListView) findViewById(R.id.main_lvStudentList);
        contentResolver= getContentResolver();

        //db= new DatabaseHandler(this);
        //db.eraseDB();

      /*  db.addStudent(new Student("Ravi", "20"));
        db.addStudent(new Student("Ravi2", "24"));
        db.addStudent(new Student("Ravi3", "20"));
        db.addStudent(new Student("Ravi4", "20"));
        db.addStudent(new Student("Srinivas1", "20"));
        db.addStudent(new Student("Srinivas2", "26"));
        db.addStudent(new Student("Srinivas3", "29"));
        db.addStudent(new Student("khang","23"));*/

     /*   List<Student> studentList = db.getAllStudent();
        for (Student i : studentList)
        {
            Log.i(TAG, i.get_name() + "  " + i.get_age());
        }

        db.deleteStudent(studentList.get(studentList.size()-1));
        Student student = studentList.get(studentList.size() - 2);
        student.set_name("11111111111111");
        db.updateStudent(student);

        studentList = db.getAllStudent();

        Log.i(TAG,"deleted end student of the list ");
        for (Student i : studentList)
        {
            Log.i(TAG,i.get_name()+"  "+i.get_age());
        }
        customeListViewAdapter = new CustomeListViewAdapter(this,studentList);  */
        main_lvStudentList.setAdapter(customeListViewAdapter);
        main_lvStudentList.setOnItemClickListener(itemSelectListener);
        main_lvStudentList.setOnItemLongClickListener(itemLongSelectListener);
        copyFile("/data/data/com.example.LocalStorage/databases/studentManager",
                Environment.getExternalStorageDirectory() + "/Android/data.db");
    }

    private AdapterView.OnItemLongClickListener itemLongSelectListener = new AdapterView.OnItemLongClickListener()
    {
        @Override
        public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
        {

            customeListViewAdapter.getListData().add(customeListViewAdapter.getListData().get(position));
            customeListViewAdapter.notifyDataSetChanged();
            db.addStudent(customeListViewAdapter.getListData().get(position));
            return false;
        }
    };
    private AdapterView.OnItemClickListener itemSelectListener= new AdapterView.OnItemClickListener()
    {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id)
        {
            Student student = customeListViewAdapter.getListData().get(position);
            customeListViewAdapter.deleteStudent(position);
            customeListViewAdapter.notifyDataSetChanged();
            db.deleteStudent(student);
//            JSONObject jsonObject= URLReader.readFromURL("http://androidexample.com/media/webservice/JsonReturn.php");
//            System.out.println("jsonObject: "+jsonObject);
        }
    };

    private void copyFile(String fromFile, String toFile)
    {
        try
        {
            Log.e("fromFile", " " + fromFile);
            Log.e("toFile", " " + toFile);
            File f1 = new File(fromFile);
            File f2 = new File(toFile);
            InputStream in = new FileInputStream(f1);

            // For Append the file.
            // OutputStream out = new FileOutputStream(f2,true);
            // For Overwrite the file.
            OutputStream out = new FileOutputStream(f2);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0)
            {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            Log.e("============Okkk1111", "FileActivity copied.");
        }
        catch (FileNotFoundException ex)
        {
            Log.e("============FileNotFoundException", ex.getMessage()
                    + " in the specified directory.");
        }
        catch (IOException e)
        {
            Log.e("============IOException", e.getMessage());
        }
    }
}
