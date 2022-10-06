package com.example.parcial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText et1, et2, et3, et4, et5;
    private TextView tv1,tv2,tv3,tv4,tv5,tv6,tv7,tv8,tv9,tv10,tv11;
    private Spinner spinner1;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et1 = (EditText)findViewById(R.id.txt_nombre);
        et2 = (EditText)findViewById(R.id.txt_apellido);
        et3 = (EditText)findViewById(R.id.txt_salario);
        et4 = (EditText)findViewById(R.id.txt_horas);
        et5 = (EditText)findViewById(R.id.txt_descfijo);
        tv1 = (TextView) findViewById(R.id.NOMBRECOMPLETO);//DESDE AQUI SE ENVIAN DATOS AL MAINACTIVITY
        tv2 = (TextView) findViewById(R.id.SALARIOQUINCENAL);
        tv3 = (TextView) findViewById(R.id.SALARIOPORHORA);
        tv4 = (TextView) findViewById(R.id.HORASTRABAJADAS);
        tv5 = (TextView) findViewById(R.id.SALARIOBRUTO);
        tv6 = (TextView) findViewById(R.id.SEGUROSOCIAL);
        tv7 = (TextView) findViewById(R.id.SEGUROEDUCATIVO);
        tv8 = (TextView) findViewById(R.id.IMPUESTOSOBRERENTA);
        tv9 = (TextView) findViewById(R.id.DESCFIJOS);
        tv10 = (TextView) findViewById(R.id.DESCPORTIPO);
        tv11 = (TextView) findViewById(R.id.SALARIONETO);

        spinner1 = (Spinner)findViewById(R.id.spinner);

        String [] opciones ={"Servicios profesionales", "Asalariado", "Otras actividades"};


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, opciones);
        spinner1.setAdapter(adapter);
    }
    //metodo del boton
    public void calcular(View view){
        String valor1 = et3.getText().toString();
        String valor2 = et4.getText().toString();
        String convertir= et5.getText().toString();
        float desc_fijo = 0;
        desc_fijo=Float.parseFloat(convertir);
        float salario = 0;
        salario = Float.parseFloat(valor1);
        int horas = 0;
        horas = Integer.parseInt(valor2);
        float sal_bruto=0;
        if(horas<=104){
            float sal_hora=(salario/104);//salario por hora
            sal_bruto=sal_hora*horas;//salario quincenal

        }else{
            float sal_hora=(salario/104);//salario por hora
            float extras= (float) ((horas-104)*(sal_hora*1.25));//calculo del salario por horas extras
            sal_bruto=(sal_hora*horas)+extras;//salario quincenal + horas extras
        }

        String seleccion = spinner1.getSelectedItem().toString();//calculo del descuento por tipo
        double desc_tipo;
        if(seleccion.equals("Servicios profesionales")){
            desc_tipo=sal_bruto*0.03;
        }
        else if(seleccion.equals("Asalariado")){
            desc_tipo=sal_bruto*0.02;
        }else if(seleccion.equals("Otras actividades")){
            desc_tipo=sal_bruto*0.04;
        }else {
            desc_tipo = 0;
            Toast.makeText(this,"No se selecciono el tipo de actividad",Toast.LENGTH_LONG).show();
        }

        double seg_soc, seg_educ, impuesto;
        seg_soc=sal_bruto*0.09;
        seg_educ=sal_bruto*0.0125;
        if(sal_bruto=>800){//calculo del impuesto sobre la renta
            impuesto=((sal_bruto=-800)*0.15);
        }else{
            impuesto=0;
        }


        //SALIDA DE DATOS
        /*String nombrec = et1.getText().toString();
        String apellidoc= et2.getText().toString();
        String nombreco=(nombrec+" "+apellidoc);*/
        String nombrec=et1.getText().toString();
        String apellidoc=et2.getText().toString();
        tv1.setText(nombrec);

        String sal_15=(salario+"");
        tv2.setText(sal_15);
        String sal_h=((salario/104)+"");
        tv3.setText(sal_h);
        String horast=(horas+"");
        tv4.setText(horast);
        String salariob = (sal_bruto=+"");
        tv5.setText(salariob);
        String segurosocial= (seg_soc+"");
        tv6.setText(segurosocial);
        String seguroeducativo=(seg_educ+"");
        tv7.setText(seguroeducativo);
        String impuestosobrerenta=(impuesto+"");
        tv8.setText(impuestosobrerenta);
        String descuentofijo=(et5+"");
        tv9.setText(descuentofijo);
        String descuentotipo =(desc_tipo+"");
        tv10.setText(descuentotipo);
        double sueldoneto;
        sueldoneto = sal_bruto= (float) (seg_soc - seg_educ - impuesto - desc_fijo - desc_tipo);
        String total=(sueldoneto+"");
        tv11.setText(total);

    }
}
