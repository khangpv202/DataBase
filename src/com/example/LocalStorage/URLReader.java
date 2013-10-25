package com.example.LocalStorage;


import com.example.LocalStorage.model.Student;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: khangpv
 * Date: 10/23/13
 * Time: 2:58 PM
 * To change this template use File | Settings | File Templates.
 */
public class URLReader
{
    /*public static JSONObject readFromURL(String url){
        JSONObject jsonObject = null;
        try{
//            URL oracle = new URL("http://androidexample.com/media/webservice/JsonReturn.php");
            URL oracle = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            String total = "";
            while ((inputLine = in.readLine()) != null)  {
                //System.out.println(inputLine);
                total+=inputLine;
            }

            in.close();
            try{
                jsonObject = new JSONObject(total);
                //jsonObject.
                System.out.println(jsonObject);
            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  jsonObject;

    }*/
    public static void main(String[] args)
    {
        try{
//            URL oracle = new URL("http://androidexample.com/media/webservice/JsonReturn.php");
            URL oracle = new URL("http://androidexample.com/media/webservice/JsonReturn.php");
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(oracle.openStream()));

            String inputLine;
            String total = "";
            while ((inputLine = in.readLine()) != null)  {
                total+=inputLine;
            }
            in.close();
            try{
                Gson gson= new Gson();
                List<Student> studentList = new ArrayList<Student>();
                Student student = new Student(1,"surren","20");
                studentList.add(student);
                studentList.add(student);
                studentList.add(student);
                JsonParser jsonParser = new JsonParser();
                JsonArray jsonObject = (JsonArray) jsonParser.parse(gson.toJson(studentList));
                //System.out.println(jsonObject);
                JsonObject jsonObject1 = (JsonObject) jsonObject.get(0);
                //System.out.println(jsonObject1);
                JsonObject object = gson.fromJson(total,JsonObject.class);
                System.out.println(object);
                System.out.println(object.get("Android"));
                System.out.println(object.getAsJsonArray("Android"));
                System.out.println(object.getAsJsonArray("Android").get(0));

                Contact contact = gson.fromJson(gson.toJson(object.getAsJsonArray("Android").get(0)),Contact.class);
                Contact contact1 = gson.fromJson(object.getAsJsonArray("Android").get(0), Contact.class);
                JsonObject jsonObject2 = gson.fromJson(object.getAsJsonArray("Android").get(0), JsonObject.class);
                System.out.println("contact: "+contact);
                System.out.println("contact: "+contact1);
                System.out.println("contact: "+jsonObject2);
                Contact contact2 = gson.fromJson(jsonObject2,Contact.class);
                System.out.println(contact2);

            }catch (Exception e){
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private class Contact
    {
        String name;
        int number;
        String date_added;

        public String getName()
        {
            return name;
        }

        public void setName(String name)
        {
            this.name = name;
        }

        public int getNumber()
        {
            return number;
        }

        public void setNumber(int number)
        {
            this.number = number;
        }

        public String getDate_added()
        {
            return date_added;
        }

        public void setDate_added(String date_added)
        {
            this.date_added = date_added;
        }

        @Override
        public String toString()
        {
            return name+" "+number+" "+date_added+"\n";
        }
    }
}
