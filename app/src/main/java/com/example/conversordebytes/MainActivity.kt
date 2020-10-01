package com.example.conversordebytes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.core.view.get
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cantidad: Double = 0.00;
    var origen: Int = -1
    var destino: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        convertir.setOnClickListener {
            obtenerCantidad()
            if (cantidad != 0.00){
                obtenerUnidadOrigen()
                if(origen != 0){
                    obtenerUnidadDestino()
                    if(destino != 0){
                        calcular(cantidad, origen, destino)
                    }
                }
            }
        }
    }

    fun obtenerCantidad(){
        if(textInputCantidad.text.isNullOrBlank() || textInputCantidad.text.toString().toDouble() == 0.00 ){
            textViewResultado.setText("Debe introducir una cantidad a convertir.")
        }else{
            cantidad = textInputCantidad.text.toString().toDouble()
            textViewResultado.setText("")
        }
    }

    fun obtenerUnidadOrigen(){
        spinnerUnidadesOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected (parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent!!.getItemIdAtPosition(position).toLong().toInt() == 0){
                    textViewResultado.setText("Debe seleccionar una unidad de origen.")
                }else{
                    Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                    origen = position
                    textViewResultado.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun obtenerUnidadDestino(){

        spinnerUnidadesDestino.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent?.getItemIdAtPosition(position) == 0.toLong()) {
                    textViewResultado.setText("Debe seleccionar una unidad de destino.")
                } else {
                    Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                    destino = position
                    textViewResultado.setText("")
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }

    fun calcular(cantidad: Double, origen: Int, destino: Int){

            var resultado: Double = cantidad * 2
            textViewResultado.setText("El resultado de la conversión es: ${resultado}")
    }
}