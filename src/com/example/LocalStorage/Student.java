package com.example.LocalStorage;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/21/13
 * Time: 4:05 PM
 * To change this template use Files | Settings | Files Templates.
 */
public class Student
{
    private int _id;
    private String _name;
    private String _age;

    public Student(int _id, String _name, String _age)
    {
        this._id = _id;
        this._name = _name;
        this._age = _age;
    }

    public Student(String _name, String _age)
    {
        this._name = _name;
        this._age = _age;
    }

    public Student()
    {
    }

    public int get_id()
    {
        return _id;
    }

    public void set_id(int _id)
    {
        this._id = _id;
    }

    public String get_name()
    {
        return _name;
    }

    public void set_name(String _name)
    {
        this._name = _name;
    }

    public String get_age()
    {
        return _age;
    }

    public void set_age(String _age)
    {
        this._age = _age;
    }

    public String toString(){
        return "id "+_id +" name "+ _name+" age "+_age;
    }
}
