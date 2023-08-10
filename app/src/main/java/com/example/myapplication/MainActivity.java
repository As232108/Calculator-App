package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


import com.google.android.material.button.MaterialButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView result,userinput;
    MaterialButton ButtonC,ButtonOpenBrasket,ButtonCloseBrasket;
    MaterialButton ButtonDivide,ButtonMultiply,ButtonPlus,ButtonSubstract,ButtonEqual;
    MaterialButton Button0,Button1, Button2,Button3,Button4,Button5,Button6,Button7,Button8,Button9;
    MaterialButton ButtonAc,ButtonDot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        result=findViewById(R.id.result);
        userinput=findViewById(R.id.userinput);



        assignId(ButtonC,R.id.button_c);
        assignId(ButtonOpenBrasket,R.id.button_open);
        assignId(ButtonCloseBrasket,R.id.button_close);
        assignId(ButtonDivide,R.id.button_Divide);
        assignId(ButtonMultiply,R.id.button_Multiply);
        assignId(ButtonPlus,R.id.button_plus);
        assignId(ButtonSubstract,R.id.button_subtarct);
        assignId(ButtonEqual,R.id.button_eqal);
        assignId(Button0,R.id.button_0);
        assignId(Button1,R.id.button_1);
        assignId(Button2,R.id.button_2);
        assignId(Button3,R.id.button_3);
        assignId(Button4,R.id.button_4);
        assignId(Button5,R.id.button_5);
        assignId(Button6,R.id.button_6);
        assignId(Button7,R.id.button_7);
        assignId(Button8,R.id.button_8);
        assignId(Button9,R.id.button_9);
        assignId(ButtonAc,R.id.button_ac);
        assignId(ButtonDot,R.id.button_dot);




    }
    void assignId(MaterialButton btn,int id){
        btn=findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        MaterialButton button = (MaterialButton) view;
        String buttonText = button.getText().toString();
        String dataToCalculate = userinput.getText().toString();


        if (buttonText.equals("ac")) {
            userinput.setText("");
            result.setText("0");
            return;
        }
        if (buttonText.equals("=")) {
            userinput.setText(result.getText());
            return;
        }
        if (buttonText.equals("C")) {

           dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
        } else {
            dataToCalculate = dataToCalculate+buttonText;
        }

        userinput.setText(dataToCalculate);
        String finalResult=getResult(dataToCalculate);


        if(!finalResult.equals("Error"))
        {
            result.setText(finalResult);
        }
    }

    String getResult(String data){
        try {
            Context context=Context.enter();
            context.setOptimizationLevel(-1);
            Scriptable scriptable=context.initStandardObjects();
            String finalResult=
                    context.evaluateString(scriptable,data,"JavaScript",1,null).toString();
            if(finalResult.endsWith(".0")){
                finalResult=finalResult.replace(".0","");
            }
            return finalResult;
        }
        catch (Exception e){
            return "Error";
        }
    }

}