package com.example.calci;

import static com.example.calci.R.*;

import static com.example.calci.R.id.*;

import static android.os.Build.VERSION_CODES.R;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView textview,textview2;
    Button button,button2,button3,button4,button5,button6,button7,button8;
    Button button9,button10,button11,button12,button13,button14,button15,button16;
    public static String res="";
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);

        textview=findViewById(id.exp);textview2=findViewById(id.result);
        assign(button, id.button);assign(button2, id.button2);assign(button3, id.button3);
        assign(button4, id.button4);assign(button5, id.button5);assign(button6, id.button6);
        assign(button7, id.button7);assign(button8, id.button8);assign(button9, id.button9);
        assign(button10, id.button10);assign(button11, id.button11);assign(button12, id.button12);
        assign(button13, id.button13);assign(button14, id.button14);assign(button15, id.button15);
        assign(button16, id.button16);

    }


    void assign(Button bt,int Id)
    {
        bt=findViewById(Id);
        bt.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
    Button b=(Button) v;
    String btext=b.getText().toString();
    String bcal=textview.getText().toString();

    if(b.getText().equals("AC"))
    {
        textview.setText("");
        textview2.setText("0");
        return;
    }
    else if(b.getText().equals("="))
    {
        textview.setText(textview2.getText());
        return;
    }
    else
    {
    bcal+=btext;
    }
    textview.setText(bcal);

    String finalr=getresult(bcal);

    if(!finalr.equals("ERROR"))
        textview2.setText(finalr);
    }

    String getresult(String data)
    {
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String fres=context.evaluateString(scriptable,data,"Javascript",1,null).toString();
            if(fres.endsWith(".0"))
                fres=fres.replace(".0","");
            return fres;
        }catch (Exception e)
        {
            return "ERROR";
        }
    }
}
