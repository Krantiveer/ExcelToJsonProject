package com.example.excellankit;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import jxl.CellType;
import jxl.Workbook;
import jxl.Cell;
import jxl.Sheet;


public class MainActivity extends AppCompatActivity {
    //***********variable of writeExternalStorage
    String Message;
    File sdCard = Environment.getExternalStorageDirectory();
    String writeExtenalpath = sdCard.getAbsolutePath() + "/excelToJSON.txt";
    File writeExtFile = new File(writeExtenalpath);
    String version = "},\n \"version\":" + " \"0.1\"\n}";
    public final static String MESSAGE_KEY ="616";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String filepath = intent.getStringExtra(MESSAGE_KEY);
       /* TextView textView = new TextView(this);
        textView.setTextSize(25);
        textView.setText(message);
        setContentView(textView);
*/
        Log.d("messagefafa",""+filepath);

        int start = 1, end;
        Gson gson = new Gson();

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(writeExtFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int numOfbcell=1;
        try {
            File sdCard = Environment.getExternalStorageDirectory();
            String filePathgroups = filepath;
            File filegroups = new File(filePathgroups);
            if (filegroups.exists()) {
                Log.e("Filepath", "im exit" + filegroups);
            } else {
                Log.e("Filepath", "not exit " + filegroups);
            }

            Workbook wb = Workbook.getWorkbook(filegroups);
            Sheet s = wb.getSheet(1);

            int row = s.getRows();//1099 //
            int endvalraw = 1;
            Map propertieskey = new LinkedHashMap();


            String contentPlayed = " ";
            String contentStarted = " ";
            String contentDetailView = "";
            String tabView = "";
            String searched = "";
            String promoVideoShowed = "";
            String preferredLanguage = "";
            String logout = "";
            String singUp = "";
            ;
            String login = "";
            String downloadinitiated = "";
            String favourite = "";
            String share = "";
            String subscriptionPackSelected = "";
            String paymentInitiated = "";
            String paymentStatus = "";
            String errorEncountered = "";
            String adPlayed = "";

            Map contentPlayedtwo = new HashMap();
            String contentplaytwo = "";

            String demo1 = " ";
            String demo2 = " ";
            String propertiesval = " ";

//******* this for loop is used to couting no. of row in the given excel file

            for (int k = 1; k < row; k++) {
//********* by this we get Cell value s.getCell(column , row )
               Cell b = s.getCell(5, k);
                if (b.getType() != CellType.EMPTY) {
                    endvalraw++;
                    Log.e("Excel1", "Cell Row Column -- " + endvalraw + " ");
                }
            }
            Log.d("endvalueis", ":" + endvalraw);
//*****XXXX----mainForLoop
            for (int i = 1; i < endvalraw; i++) {
                Log.d("For Loop END Number", String.valueOf(i));
                Cell c = s.getCell(2, i);
                Cell cCell = s.getCell(2, start);

                Cell bCell = s.getCell(1, start);

                if (c.getType() != CellType.EMPTY || i == endvalraw - 1) {

                    Cell priority = s.getCell(0, start);
                    Log.e("Excel1", "Cell Value priority -- " + priority.getContents());

                    Log.e("Excel1full", "Cell Value -- " + c.getContents());

                    if (start < i) {
                        String bCellString = String.format(" \"%s\": ", bCell.getContents());

                        Log.e("Excel2", "Cell Row Column -- " + i + " ");

                        end = i;

                        if (i == endvalraw - 1) {
                            end = i + 1;
                        }
// for getting "datatype":string
                        for (i = start; i < end; i++) {
//*****************  Here you receive all properties values
// :{"content id":{"datatype":"String","property_name":"user id"}

// no. of b cell
                            Log.d("testvalueofnumofbcell", "" + numOfbcell);
                            Cell datatypeCell = s.getCell(5, i);
                            Cell propertiesNameCell = s.getCell(3, i);
                            Cell propertiesKeyCell = s.getCell(4, i);
                            Log.d("valeofi==", " " + i);
                            Log.d("datatype", "" + datatypeCell.getContents() + "property_name" + propertiesNameCell.getContents());

                            if (numOfbcell == 1) {

                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                Log.d("testpropertieskeyjson", "=" + gson.toJson(propertieskey) + "i=" + i); //it coming 17time
                                //{datatype=String, property_name=content name}i=1
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                contentPlayed = contentPlayed + demo1;

                                Log.d("testpropertieskeyjsonn", "=" + demo2 + "i=" + i); //it coming 17time

                            }

                            if (numOfbcell == 2) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                Log.d("testpropertiespro", "=" + gson.toJson(propertieskey) + "i=" + i); //it coming 17time
                                //{datatype=String, property_name=content name}i=1
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                Log.d("testpropertieskeyjsonn2", "=" + demo2 + "i=" + i); //it coming 17time
                                contentStarted = contentStarted + demo1;
                                Log.d("testpropertieskeyjson22", "=" + contentplaytwo + "i=" + i); //it coming 17time


                            }

                            if (numOfbcell == 3) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                contentDetailView = contentDetailView + demo1;


                            }
                            if (numOfbcell == 4) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                tabView = tabView + demo1;


                            }
                            if (numOfbcell == 5) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                searched = searched + demo1;


                            }
                            if (numOfbcell == 6) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                promoVideoShowed = promoVideoShowed + demo1;


                            }
                            if (numOfbcell == 7) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                preferredLanguage = preferredLanguage + demo1;


                            }
                            if (numOfbcell == 8) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                logout = logout + demo1;


                            }
                            if (numOfbcell == 9) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                singUp = singUp + demo1;


                            }
                            if (numOfbcell == 10) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                login = login + demo1;


                            }
                            if (numOfbcell == 11) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                downloadinitiated = downloadinitiated + demo1;


                            }
                            if (numOfbcell == 12) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                favourite = favourite + demo1;
                            }
                            if (numOfbcell == 13) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                share = share + demo1;

                            }
                            if (numOfbcell == 14) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                subscriptionPackSelected = subscriptionPackSelected + demo1;
                            }
                            if (numOfbcell == 15) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                paymentInitiated = paymentInitiated + demo1;


                            }
                            if (numOfbcell == 16) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                paymentStatus = paymentStatus + demo1;

                            }
                            if (numOfbcell == 17) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());
                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                errorEncountered = errorEncountered + demo1;


                            }
                            if (numOfbcell == 18) {


                                propertieskey.put("datatype", datatypeCell.getContents());
                                propertieskey.put("property_name", propertiesKeyCell.getContents());

                                propertiesval = String.format(" \"%s\": ", propertiesNameCell.getContents());
                                if (i == end - 1) {
                                    demo1 = propertiesval + gson.toJson(propertieskey);

                                } else {
                                    demo1 = propertiesval + gson.toJson(propertieskey) + ",";
                                }
                                adPlayed = adPlayed + demo1;


                            }


                        }
                        Log.d("valueof ==", "" + numOfbcell);

                        Log.d("testpropertieskeynew", "=" + propertieskey + "i=" + i);
//-------------------------------Here you will get  {"name":"content played","priority":"High","properties": }


//--- this value you can put outside also
//--- String valuef = "{"+" \"name\": "+String.format(" \"%s\",", cCell.getContents())+"\n \"priority\": "+String.format(" \"%s\",", priority.getContents())+"\n \"properties\": {\n";
                        if (numOfbcell == 1) {


                            Log.d("testpropertieskeyjsonn", "=" + contentPlayed + "i=" + i); //it coming 17time
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + contentPlayed + "}}";
                            String value = "{\n \"Events\": {\n";

                            Message = value + bCellString + propertiesval;
                            writeExternalStorage();

                            Log.d("testmessage", "" + Message);
                            Log.d("Content_Played", "" + contentPlayedtwo);
                            // writing JSON to file:"JSONExample.json" in cwd

                        }
                        if (numOfbcell == 2) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + contentStarted + "}}";
                            Message = ",\n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }


                        if (numOfbcell == 3) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + contentDetailView + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 4) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + tabView + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 5) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + searched + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 6) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + promoVideoShowed + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 7) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + preferredLanguage + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 8) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + logout + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }

                        if (numOfbcell == 9) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + singUp + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 10) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + login + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 11) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + downloadinitiated + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 12) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + favourite + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 13) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + share + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 14) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + subscriptionPackSelected + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();
                        }
                        if (numOfbcell == 15) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + paymentInitiated + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 16) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + paymentStatus + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();


                        }
                        if (numOfbcell == 17) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + errorEncountered + "}}";
                            Message = ", \n" + bCellString + propertiesval;
                            writeExternalStorage();

                        }
                        if (numOfbcell == 18) {
                            String valuef = "{" + " \"name\": " + String.format(" \"%s\",", cCell.getContents()) + "\n \"priority\": " + String.format(" \"%s\",", priority.getContents()) + "\n \"properties\": {\n";
                            propertiesval = valuef + adPlayed + "}}";
                            Message = ", \n" + bCellString + propertiesval + version;
                            writeExternalStorage();

                        }


                        Log.d("valeofnumofbcell", "" + numOfbcell);

                        Log.d("valeAstart", "" + start);

                        numOfbcell++;
                        start = end;


                    }
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }


    }


    public void writeExternalStorage() {


        try {
            FileOutputStream fileOutputStream = new FileOutputStream(writeExtFile, true);
            fileOutputStream.write(Message.getBytes());
            fileOutputStream.close();
            // editText.setText("");
            Toast.makeText(getApplicationContext(), "Your Json File is Ready !!!", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}