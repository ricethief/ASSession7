package com.example.a001047904.collectiondemos;

import java.io.*;
import java.util.*;

import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;


@SuppressWarnings({"unused", "rawtypes", "unchecked"})
public class CollectionListDemo extends Activity {

    private static TextView txtOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection_list_demo);

        final Button runListDemo;
        txtOutput = (TextView) this.findViewById(R.id.txtOutput);
        txtOutput.setMovementMethod(new ScrollingMovementMethod());
        runListDemo = (Button) this.findViewById(R.id.btnListDemo);
        runListDemo.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                runListDemo();
            }
        });

    }

    public static void runListDemo(){


        txtOutput.setText("");
        output("Running LIST Demo...\n");

        List myList;

        myList = new ArrayList();

        output("Initial size of a1: " + myList.size());

        myList.add("C");
        myList.add("A");
        myList.add("E");
        myList.add("B");
        myList.add(3,"XX");


        output("Contents of a1: " + myList);
        output("Size of a1 after additions:" + myList.size());

        //adding code here

        Address a = new Address("1", "Paterson Cr", "Marion", "SA", "AUST", "5043");
        myList.add(a);
        myList.add(new Address("15", "Funnel St", "Unley", "SA", "AUST", "5011"));
        myList.add(new Address("23", "Hutt St", "Adelaide", "SA", "AUST", "5000"));
        output("Contents of a1: " + myList);

        // this List uses aggregation not composition. hence changing an objects data
        //will be reflected in the list

        a.setCountry("SPAIN");
        output("Contents of a1: " + myList);

        //retrieve
        //getting at elements (indexing starts from 0)
        output("The fourth element is : " + myList.get(3));

        //note that the return type from get() is object therefore we have to typecast to what we know object really is
        //for ex
        a= (Address) myList.get(7);
        output("Country=" + a.getCountry());
        output("country=" + ((Address) (myList.get(7))).getCountry());

        //REPLACE
        //Setting an element to a particular value

        a = new Address("TEST ST NUM","TEST ST NAME", "TEST ST SUB", "TEST ST STATE","TEST ST COUNTRY","TEST ST PCODE");

        myList.set(2,"INSERT TEST");
        output("After Insert at index 2 of 'INSERT TEST': " + myList);

        //DELETE
        //Deleting an element to a particular value
        //Normally we stroe only one type of data so lets delete the Strings we put

        myList.remove(0);
        myList.remove("INSERT TEST");
        myList.remove("A");
        myList.remove("E");
        myList.remove("B");
        myList.remove("XX");
        output("After Deletions. Contents of a1: "+ myList);

        // TRAVERSE ALL ELEMENTS
        // Traverse the list ie do something with every element in the list

        // One way is using get

        int i;
        for(i=0; i < myList.size(); i++){
            a = (Address) myList.get(i);
            output("Do something with element " + i + " which is -> " + a);

        }

        //Better way is to use an Iterator.
//        ListIterator it;
//        it = myList.listIterator();
//        while (it.hasNext()) {
//            a=(Address) it.next();
//            output("Do something with element " + it.previous() + "which is " + a);

//        }


        // The for loop was extended in jdk 1.5 to simplify traversal of a collection.
        // this is commonly known as the 'for each' loop
        //note that (without extra code) we no longer know where we are at in the list
        for (Object aTemp : myList) {
            a = (Address) aTemp;
            output("Do something with element -> " + a);
        }
    }


    private static void output(String message) {
        System.out.print("\n" + message + "\n");
        txtOutput.append("\n" + message + "\n");

    }

}
