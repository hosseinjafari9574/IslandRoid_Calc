package ir.sarinapp.islandroid_calc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView txt_result,txt_hint;
    boolean mustReset = false;
    String oprand = "";
    float curentResult = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_result= (TextView) findViewById(R.id.txt_result);
        txt_hint= (TextView) findViewById(R.id.txt_hint);

    }

    public void onClick(View view){

        String cheakOnclickTag=view.getTag().toString();

        if (cheakOnclickTag.equals("/")||cheakOnclickTag.equals("*")||cheakOnclickTag.equals("+")
          ||cheakOnclickTag.equals("-")){
            doOprand(view.getTag().toString());
        }else if (!cheakOnclickTag.equals("/")&&!cheakOnclickTag.equals("*")&&!cheakOnclickTag.equals("+")
          &&!cheakOnclickTag.equals("-")){
            int numAppend = Integer.parseInt(view.getTag().toString());
            setNum(numAppend);
        }
    }

    public void onclick(View view){
        String cheakOnclickTag=view.getTag().toString();
        if (cheakOnclickTag.equals("eq")){
            prossesorEqul();
        }else if (cheakOnclickTag.equals("d")){
            addDot();
        }else if(cheakOnclickTag.equals("c")){
            txt_result.setText("0");
            txt_hint.setText("");
            curentResult = 0;
            mustReset = false;

        }else if(cheakOnclickTag.equals("ce")){
            txt_result.setText("0");

        }else if(cheakOnclickTag.equals("del")){

            backOnLatter();

        }
    }



    private void setNum(int numAppend) {
        if (mustReset) {
            txt_result.setText("");
            mustReset = false;
        }
        String oldValue = txt_result.getText().toString();

        if (oldValue.length() > 9) {
            return;
        }
        if (oldValue.equals("0")) {
            if (numAppend == 0) {
                return;
            } else {
                oldValue = "";
            }


        }

        txt_result.setText(oldValue + numAppend);

    }

    private void coumput(String operandNext) {

        String resultvalue = txt_result.getText().toString();
        float resultNumber = Float.valueOf(resultvalue);

        if (oprand.equals("+")) {
            curentResult += resultNumber;
            //mustOnClick = false;
        } else if (oprand.equals("-")) {
            curentResult -= resultNumber;


        } else if (oprand.equals("*")) {
            curentResult *= resultNumber;


        } else if (oprand.equals("/")) {
            curentResult /= resultNumber;


        } else if (oprand.equals("")) {
            curentResult = resultNumber;
        }

        String oldHistory = txt_hint.getText().toString();
        txt_result.setText(" " + curentResult);
        txt_hint.setText(oldHistory + " " + resultNumber + " " + operandNext);
        oprand = operandNext;
        mustReset = true;
    }

    private void doOprand(String operandNext) {

        coumput(operandNext);

    }

    private void prossesorEqul() {

        coumput("");
        txt_hint.setText("");
    }

    public void addDot() {

        if (mustReset) {
            txt_result.setText("0");
            mustReset = false;
        }
        String oldValue = txt_result.getText().toString();

        if (oldValue.contains(".")) {
            return;
        }
        if (oldValue.length() > 9) {
            return;
        }


        txt_result.setText(oldValue + ".");


    }

    public void backOnLatter() {

        if (txt_result.getText().length() > 0) {
            String value = txt_result.getText().toString().trim();
            String newValue = value.substring(0, value.length() - 1);
            if (!value.equals("0")) {
                if (value.length() == 1) {
                    txt_result.setText("0");
                } else {
                    txt_result.setText(newValue);
                }

            }


        }

    }
}
