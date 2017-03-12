package br.com.essentialstech.etibinario;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static br.com.essentialstech.etibinario.R.id.btnConv;

/**
 * Created by Giovanni Hessel on 05/03/2017.
 */

public class MainCode extends Activity {
    private EnumNum convTipo;
    private EditText editDec;
    private TextView txtDec;
    private EditText editBin;
    private TextView txtBin;
    private EditText editOct;
    private TextView txtOct;
    private EditText editHex;
    private TextView txtHex;
    private Button btnConv;
    private int numConv;
    private int numResult;
    private StringBuilder result = new StringBuilder();
    //
    //Método OnCreate
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tela_principal);
        iniciaComponentes();
    }
    //Método de inicialização dos componenetes
    private void iniciaComponentes(){
        //Inicializa os componentes da parte Decimal com um "sensor" de mudança de foco
        txtDec = (TextView)findViewById(R.id.txtDec);
        editDec = (EditText)findViewById(R.id.editDec);
        editDec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Decimal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Dec;
                    editDec.setTextSize(19);
                    txtDec.setTextSize(24);
                }else{
                    editDec.setTextSize(15);
                    txtDec.setTextSize(20);
                }
            }
        });
        //Inicializaos os componentes da parte Binaria com um "sensor" de mudança de foco
        txtBin = (TextView)findViewById(R.id.txtBin);
        editBin = (EditText)findViewById(R.id.editBin);
        editBin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Binário para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Bin;
                    editBin.setTextSize(19);
                    txtBin.setTextSize(24);
                }else
                {
                    editBin.setTextSize(15);
                    txtBin.setTextSize(20);
                }
            }
        });
        //Inicializa os componentes da parte Octal com um "sensor" de mudança de foco
        txtOct = (TextView)findViewById(R.id.txtOct);
        editOct = (EditText)findViewById(R.id.editOct);
        editOct.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Octal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Oct;
                    editOct.setTextSize(19);
                    txtOct.setTextSize(24);
                }else
                {
                    editOct.setTextSize(15);
                    txtOct.setTextSize(20);
                }
            }
        });
        //Inicializa os componentes da parte Hexadecimal com um "sensor" de mudança de foco
        txtHex = (TextView)findViewById(R.id.txtHex);
        editHex = (EditText)findViewById(R.id.editHex);
        editHex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Hexadecimal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Hex;
                    editHex.setTextSize(19);
                    txtHex.setTextSize(24);
                }else
                {
                    editHex.setTextSize(15);
                    txtHex.setTextSize(20);
                }
            }
        });
        //Iinicializa o botão de conversão com um "sensor" de click
        //que aciona o método converter
        btnConv = (Button)findViewById(R.id.btnConv);
        btnConv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                converter();
            }
        });
    }

    private void converter(){
        //Limpa a String Resultado
        result.delete(0, result.length());
        //Verifica qual conversão será feita
        if(convTipo == EnumNum.Dec){
            //Recupera o valor da editbox Decimal, e passa para as variaveis
            numConv = Integer.valueOf(editDec.getEditableText().toString());
            numResult = numConv;
            //Processamento necessário para transformar o número em Binário
            do{
                result.append(numResult%2);
                numResult /= 2;
                if(numResult == 1){
                    result.append(numResult);
                }
            }while(numResult > 1);
            //Mostra o resultado na editbox Binário
            editBin.setText(result.reverse().toString());
            //Processamento necessário para transformar o número em Octal
            if(numConv >= 0 && numConv<= 7){
                //Mostra o resultado na editbox Binário
                editOct.setText(String.valueOf(numConv));
            }else{
                result.delete(0,result.length());
                numResult = numConv;
                result.append(numResult/8);
                result.append(numResult%8);
                //Mostra o resultado na editbox Octal
                editOct.setText(result.toString());
            }
            if(numConv >= 0 && numConv <= 9){
                editHex.setText(String.valueOf(numConv));
            }else{
                result.delete(0, result.length());
                numResult = numConv;
                if(numResult > 15) {
                    result.append(numResult / 16);
                }
                numResult%=16;
                if(numResult > 9 && numResult <= 15){
                    if(numResult == 10){
                        result.append("A");
                    }else if(numResult == 11){
                        result.append("B");
                    }else if(numResult == 12){
                        result.append("C");
                    }else if(numResult == 13){
                        result.append("D");
                    }else if(numResult == 14){
                        result.append("E");
                    }else{
                        result.append("F");
                    }
                }else{
                    result.append(numResult);
                }
                //Mostra o resultado na editbox Hexadecimal
                editHex.setText(result.toString());
            }
        }else if(convTipo == EnumNum.Bin){
            editBin.setText("FunfouBin", TextView.BufferType.EDITABLE);
        }else if(convTipo == EnumNum.Oct){
            editOct.setText("FunfouOct", TextView.BufferType.EDITABLE);
        }else{
            editHex.setText("FunfouHex", TextView.BufferType.EDITABLE);
        };
    }
}
