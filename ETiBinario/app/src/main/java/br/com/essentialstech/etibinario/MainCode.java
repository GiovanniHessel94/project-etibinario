package br.com.essentialstech.etibinario;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by Giovanni Hessel on 05/03/2017.
 */

public class MainCode extends Activity {
    private EnumNum convTipo;
    private EditText editDec;
    private EditText editBin;
    private EditText editOct;
    private EditText editHex;
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
        //Inicializa a editbox Decimal com um "sensor" de mudança de foco
        editDec = (EditText)findViewById(R.id.etxtDec);
        editDec.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Decimal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Dec;
                }
            }
        });
        //Inicializa a editbox Binario com um "sensor" de mudança de foco
        editBin = (EditText)findViewById(R.id.etxtBin);
        editBin.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Binário para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Bin;
                }
            }
        });
        //Inicializa a editbox Octal com um "sensor" de mudança de foco
        editOct = (EditText)findViewById(R.id.etxtOct);
        editOct.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Octal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Oct;
                }
            }
        });
        //Inicializa a editbox Hexadecimal com um "sensor" de mudança de foco
        editHex = (EditText)findViewById(R.id.etxtHex);
        editHex.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                //Se houver foco, é atribuido o valor correspondente
                //a Hexadecimal para a váriavel da classe EnumNum
                if(hasFocus){
                    convTipo = EnumNum.Hex;
                }
            }
        });
        //Iinicializa o botão de conversão com um "sensor" de click
        //que aciona o método converter
        Button btnConv = (Button)findViewById(R.id.btnConv);
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
            }while((numResult%2) != 0 || (numResult/2) != 0);
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
                result.append(numResult/16);
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
