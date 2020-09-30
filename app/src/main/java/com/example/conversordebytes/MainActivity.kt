package com.example.conversordebytes

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var cantidad: Double = 0.00;
    var origen: Int = 0
    var destino: Int = 0

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
                        //CALCULAR
                    }
                }
            }
        }
    }

    fun obtenerCantidad(){
        if(textInputCantidad.text.toString() == null || textInputCantidad.text.toString().toDouble() == 0.00 ){
            textViewResultado.setText("Debe introducir una cantidad a convertir.")
        }else{
            cantidad = textInputCantidad.text.toString().toDouble()
        }
    }

    fun obtenerUnidadOrigen(){
        spinnerUnidadesOrigen.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected (parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                if (parent?.getItemIdAtPosition(position) == 0.toLong()){
                    textViewResultado.setText("Debe seleccionar una unidad de origen.")
                    println("Debe seleccionar una unidad de origen.")
                }else{
                    Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                    origen = position
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
                    println("Debe seleccionar una unidad de destino.")
                } else {
                    Toast.makeText(this@MainActivity, "Seleccionado: ${parent?.getItemAtPosition(position).toString()}", Toast.LENGTH_LONG).show()
                    destino = position
                }
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {

            }
        }
    }
}