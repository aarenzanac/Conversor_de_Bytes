package com.example.conversordebytes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    var cantidad: Double = 0.00;
    var origen: Int = -1
    var unidadOrigen: String = ""
    var destino: Int = -1
    var unidadDestino: String = ""
    var resultado: Double = 0.00

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertir.setOnClickListener {
            cantidad = obtenerCantidad()
            if (cantidad != 0.00){
                obtenerUnidadOrigen()
                if(origen < 1) {
                    textViewResultado.setText("Debe seleccionar una unidad de origen.")
                }else{
                    textViewResultado.text = ""
                    obtenerUnidadDestino()
                    if(destino < 1){
                        textViewResultado.setText("Debe seleccionar una unidad de destino.")
                    }else{
                        textViewResultado.text = ""
                        calcular(cantidad, origen, destino)
                    }
                }
            }
        }
    }

    fun obtenerCantidad(): Double{
        if(textInputCantidad.text.isNullOrBlank() || textInputCantidad.text.toString().toDouble() == 0.00 ){
            textViewResultado.setText("Debe introducir una cantidad a convertir.")
            return 0.00
        }else{
            var input: Double = textInputCantidad.text.toString().toDouble()
            textViewResultado.setText("")
            return input
        }
    }

    fun obtenerUnidadOrigen(){
        spinnerUnidadesOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected (parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
               // Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                origen = position
                unidadOrigen = spinnerUnidadesOrigen.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

    }

    fun obtenerUnidadDestino(){
        spinnerUnidadesDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                //Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                destino = position
                unidadDestino = spinnerUnidadesDestino.getItemAtPosition(position).toString()
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    fun calcular(cantidad: Double, origen: Int, destino: Int){

        if(origen == destino){
            textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${cantidad} ${unidadDestino}.")
        }else if(origen > destino ){
            if(origen == 2 && destino == 1){
                resultado = cantidad * 8
                textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${resultado} ${unidadDestino}.")
                resultado = 0.00
            }else{
                if(origen - destino == 1){
                    resultado = 1024.00
                    textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${resultado} ${unidadDestino}.")
                }else{
                    resultado = (1024.00.pow(origen - destino))*cantidad
                    textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${resultado} ${unidadDestino}.")
                    resultado = 0.00
                }

            }

        }else{
            if(origen == 1 && destino == 2){
                resultado = cantidad / 8
                textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${resultado} ${unidadDestino}.")
                resultado = 0.00
            }else{
                //AQUI LA CONVERSION DESDE GEOPBYTE HASTA EL PRINCIPIO
                resultado = cantidad.pow(origen-destino)
                textViewResultado.text = ("${cantidad} ${unidadOrigen} es igual a ${resultado} ${unidadDestino}.")
                resultado = 0.00
            }

        }
    }
}

//var resultado: Double = 0.00
//textViewResultado.text = ("${cantidad} ${unidadOrigen} son igual a ${resultado} ${unidadDestino}.")