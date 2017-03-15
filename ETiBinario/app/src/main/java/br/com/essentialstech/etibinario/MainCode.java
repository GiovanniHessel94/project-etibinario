package br.com.essentialstech.etibinario;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;

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
    private Toast toast;
    private int numConv;
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
        //Inicializa o botão de conversão com um "sensor" de click
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
        //Verifica qual conversão será feita
        //Conversão de Decimal
        if(convTipo == EnumNum.Dec){
            //Verifica se a caixa de texto está vazia
            //Verifica se o número inserido está dentro do limite suportado
            if(editDec.getEditableText().toString().length() == 0){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Decimal.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else if(editDec.getEditableText().toString().length() > 9){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Decimal até 999.999.999.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else{
                //Recupera o valor da editbox Decimal, e passa para avariável
                numConv = Integer.valueOf(editDec.getEditableText().toString());
                //Aciona o metodo de Conversão
                decBase(numConv, (byte)2);
                //Mostra o resultado na editbox Binário
                editBin.setText(result.toString());
                //Processamento necessário para transformar o número Decimal em Octal
                if(numConv >= 0 && numConv <= 7) {
                    //Mostra o resultado na editbox Octal
                    editOct.setText(String.valueOf(numConv));
                }else{
                    //Aciona o método de Conversão
                    decBase(numConv, (byte)8);
                    //Mostra o resultado na editbox Octal
                    editOct.setText(result.toString());
                }//Processamento necessário para transformar o número Decimal em Hexadecimal
                if(numConv >= 0 && numConv <= 9) {
                    //Mostra o resultado na editbox Octal
                    editHex.setText(String.valueOf(numConv));
                }else{
                    //Aciona o método de Conversão
                    decBase(numConv, (byte)16);
                    //Mostra o resultado na editbox Hexadecimal
                    editHex.setText(result.toString());
                }
            }
        }//Conversão de Binário
        else if(convTipo == EnumNum.Bin){
            //Verifica se a caixa de texto está vazia
            //Verifica se o número inserido está dentro do limite suportado
            if(editBin.getEditableText().toString().length() == 0){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Binário.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else if(editBin.getEditableText().toString().length() > 30){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Binário de até 30 digitos.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else {
                //Recupera o valor da editBox Binário e transforma em Decimal
                baseDec(editBin.getEditableText().toString(), (byte)2);
                //Coloca o valor da nossa String de resultado para váriável
                numConv = Integer.valueOf(result.toString());
                //Mostra o resultado na editbox Decimal
                editDec.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em Octal
                decBase(numConv, (byte)8);
                //Mostra o resultado na editbox Octal
                editOct.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em HexaDecimal
                decBase(numConv, (byte)16);
                //Mostra o resultado na editBox Hexadecimal
                editHex.setText(result.toString(), TextView.BufferType.EDITABLE);
            }
        }else if(convTipo == EnumNum.Oct){
            //Verifica se a caixa de texto está vazia
            //Verifica se o número inserido está dentro do limite suportado
            if(editOct.getEditableText().toString().length() == 0){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Octal.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else if(editOct.getEditableText().toString().length() > 11){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Octa de até 11 digitos.", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else {
                //Recupera o valor da editBox Octal e transforma em Decimal
                baseDec(editOct.getEditableText().toString(), (byte)8);
                //Coloca o valor da nossa String de resultado para váriável
                numConv = Integer.valueOf(result.toString());
                //Mostra o resultado na editbox Decimal
                editDec.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em Binário
                decBase(numConv, (byte)2);
                //Mostra o resultado na editbox Binário
                editBin.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em HexaDecimal
                decBase(numConv, (byte)16);
                //Mostra o resultado na editBox Hexadecimal
                editHex.setText(result.toString(), TextView.BufferType.EDITABLE);
            }
        }else{
            //Verifica se a caixa de texto está vazia
            //Verifica se o número inserido está dentro do limite suportado
            if(editHex.getEditableText().toString().length() == 0){
                toast = Toast.makeText(getApplicationContext(), "Insíra um valor Hexadecimal", Toast.LENGTH_SHORT);
                toast.show();
                return;
            }else{
                //Recupera o valor da editBox Hexadecimal e transforma em Decimal
                baseDec(editHex.getEditableText().toString(), (byte)16);
                //Coloca o valor da nossa String de resultado para váriável
                numConv = Integer.valueOf(result.toString());
                //Mostra o resultado na editbox Decimal
                editDec.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em Binário
                decBase(numConv, (byte)2);
                //Mostra o resultado na editbox Binário
                editBin.setText(result.toString(), TextView.BufferType.EDITABLE);
                //Transforma o valor da varíavel em Octal
                decBase(numConv, (byte)8);
                //Mostra o resultado na editBox Octal
                editOct.setText(result.toString(), TextView.BufferType.EDITABLE);
            }
        };
    }
    //Teorema fundamental da numeração
    //Converte da base(2, 8, 16) para número Decimal
    private void baseDec(String num, byte base){
        /*Verifica o tamanho do número e se é Hexadecimal
        /Caso seja igual a oito, o resultado ultrapassa o limite de um inteiro
        então usamos um método com uma váriavel do tipo long*/
        if(num.length() == 8 && base == 16){
            baseDecMax(num, (byte)16);
        }
        else {
            //Declaração das variáveis do método
            byte dig, x;
            int[] soma = new int[num.length() + 1];
            //Lógica
            limpaResult();
            for (x = 0; x < num.length(); x++) {
                dig = tratarDig(String.valueOf(num.charAt(num.length() - (x + 1))));
                soma[x] = (dig * ((int) Math.pow(base, x)));
            }
            for (x = 0; x < soma.length - 1; x++) {
                soma[soma.length - 1] += soma[x];
            }
            result.append(soma[soma.length - 1]);
        }
    }
    //Teorema fundamental da numeração
    //Converte da base(2, 8, 16) para número Decimal(Conversão 8 digitos Hexadecimal)
    private void baseDecMax(String num, byte base){
        //Declaração das variáveis do método
        byte dig, x;
        long[] soma = new long[num.length()+1];
        //Lógica
        limpaResult();
        for(x = 0; x < num.length(); x++){
            dig = tratarDig(String.valueOf(num.charAt(num.length()-(x+1))));
            soma[x] = (dig*((long)Math.pow(base, x)));
        }
        for(x = 0; x < soma.length-1 ; x++){
            soma[soma.length-1] += soma[x];
        }
        result.append(soma[soma.length-1]);
    }
    //Converte os números Decimais para as bases 2, 8, 16
    private void decBase(int num, byte base){
        limpaResult();
        do{
            this.result.append(tratarNum(num%base));
            num /= base;
            if(num < base && num != 0){
                this.result.append(tratarNum(num));
            }
        }while(num >= base);
        result.reverse();
    }
    //Trata o digito p/ caso ele seja Hexadecimal
    private byte tratarDig(String dig){
        try{
            return Byte.valueOf(dig);
        }catch (Exception ex){
            if(dig.toUpperCase().equals("A")){
                return 10;
            }else if(dig.toUpperCase().equals("B")){
                return 11;
            }else if(dig.toUpperCase().equals("C")){
                return 12;
            }else if(dig.toUpperCase().equals("D")){
                return 13;
            }else if(dig.toUpperCase().equals("E")){
                return 14;
            }else{
                return 15;
            }
        }
    }
    //Trata o numero caso ele seja Hexadecimal
    private String tratarNum(int num){
        if(num > 9){
            if (num == 10) {
                return "A";
            } else if (num == 11) {
                return "B";
            } else if (num == 12) {
                return "C";
            } else if (num == 13) {
                return "D";
            } else if (num == 14) {
                return "E";
            } else {
                return "F";
            }
        }else{
            return String.valueOf(num);
        }
    }
    //Limpa nossa string que guarda o resultado;
    private void limpaResult(){
        this.result.delete(0, result.length());
    }
}
