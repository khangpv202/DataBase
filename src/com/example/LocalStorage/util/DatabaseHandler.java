package com.example.LocalStorage.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.LocalStorage.model.Student;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/21/13
 * Time: 3:49 PM
 * To change this template use FileActivity | Settings | FileActivity Templates.
 */
public class DatabaseHandler extends SQLiteOpenHelper
{
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "studentManager";

    // Contacts table name
    private static final String TABLE_STUDENT = "students";

    // Contacts Table Columns names
    private static final String KEY_ID = "studentId";
    private static final String KEY_NAME = "studentName";
    private static final String KEY_AGE = "studentAge";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_STUDENT_TABLE = "CREATE TABLE " + TABLE_STUDENT + "(" + KEY_ID + " INTEGER PRIMARY KEY,"
                + KEY_NAME + " TEXT," + KEY_AGE + " TEXT)";
        db.execSQL(CREATE_STUDENT_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);
        onCreate(db);
    }

    public void addStudent(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_AGE, student.get_age());
        values.put(KEY_NAME, student.get_name());

        db.insert(TABLE_STUDENT, null, values);
        db.close();
    }

    public Student getStudent(int id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_STUDENT, new String[]{KEY_ID,
                KEY_NAME, KEY_AGE}, KEY_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if (cursor != null)
        {
            cursor.moveToFirst();
        }

        Student student = new Student(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2));
        return student;
    }

    public List<Student> getAllStudent()
    {
        List<Student> studentList = new ArrayList<Student>();
        String selectQuery = "Select * from " + TABLE_STUDENT;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst())
        {
            do
            {
                Student student = new Student();
                student.set_id(Integer.parseInt(cursor.getString(0)));
                student.set_name(cursor.getString(1));
                student.set_age(cursor.getString(2));
                studentList.add(student);
            } while (cursor.moveToNext());
        }
        return studentList;
    }

    public int updateStudent(Student student)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student.get_name());
        values.put(KEY_AGE, student.get_age());
        return db.update(TABLE_STUDENT, values, KEY_ID + " =?", new String[]{String.valueOf(student.get_id())});
    }

    public void deleteStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_STUDENT,KEY_ID+"=?",new String[]{String.valueOf(student.get_id())});
        db.close();
    }

    public  int getStudentCount(){
        String countQuery = "Select * from "+TABLE_STUDENT;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor= db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }

}
