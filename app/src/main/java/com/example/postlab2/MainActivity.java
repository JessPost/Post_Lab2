package com.example.postlab2;

import android.os.Bundle; // handles activity state
import android.app.Activity; //base class for activities
import android.view.View; //handles view events
import android.widget.EditText; // for user input
import android.widget.Toast; //for displaying short messages
import java.io.FileInputStream; //for reading files
import java.io.FileOutputStream; //for writing files
import java.io.InputStreamReader; // for converting bytes to characters
import java.io.OutputStreamWriter; //for writing character streams to output

public class MainActivity extends Activity {

    EditText textmsg; //EditText to capture user input
    static final int READ_BLOCK_SIZE = 100; //size of buffer for reading text

    @Override
    protected void onCreate(Bundle savedInstanceState) { // called when the activity is first created
        super.onCreate(savedInstanceState); // call the super class onCreate method
        setContentView(R.layout.activity_main); //set the content view to the layout file

        //initialize EditText by finding it in the layout file
        textmsg=(EditText)findViewById(R.id.editText1);
    }

    // method to write text to file when the button is clicked
    public void WriteBtn(View v) {
        // add-write text into file
        try {
            // open a file output stream to write to "mytextfile.txt"
            FileOutputStream fileout=openFileOutput("mytextfile.txt", MODE_PRIVATE);
            OutputStreamWriter outputWriter=new OutputStreamWriter(fileout); //writer to handle character data

            //write the text from EditText to the file
            outputWriter.write(textmsg.getText().toString());
            outputWriter.close(); //close the writer

            //display a message indicating that the file was saved successfully message
            Toast.makeText(getBaseContext(), "File saved successfully!",
                    Toast.LENGTH_SHORT).show();

        } catch (Exception e) {
            e.printStackTrace(); //print any exceptions that occur
        }
    }

    // method to Read text from file when button is clicked
    public void ReadBtn(View v) {
        //reading text from file
        try {
            // open the file input stream to read from "mytextfile.txt"
            FileInputStream fileIn=openFileInput("mytextfile.txt");
            InputStreamReader InputRead= new InputStreamReader(fileIn); //reader to handle character data

            char[] inputBuffer= new char[READ_BLOCK_SIZE]; //buffer for reading characters
            String s=""; // string to hold the read data
            int charRead; // variable to hold the number of characters read

            //read data from the file until there are no more character
            while ((charRead=InputRead.read(inputBuffer))>0) {
                // convert the character array to a string and append it
                String readstring=String.copyValueOf(inputBuffer,0,charRead);
                s +=readstring;
            }
            InputRead.close(); //close the reader

            //set the text in the EditText to the text read from the file
            textmsg.setText(s);


        } catch (Exception e) {
            e.printStackTrace(); //print any exceptions that occurS
        }
    }
}