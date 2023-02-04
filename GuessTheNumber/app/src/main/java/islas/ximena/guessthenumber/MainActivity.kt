package islas.ximena.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Button
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    var minValue=0
    var maxValue=100
    var num:Int=0
    var won=false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //instancia al objeto del layout guessings
        var guessing: TextView = findViewById(R.id.guessings)
        var down: Button =findViewById(R.id.down)
        var up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        //Metodo del clic del boton generate
        generate.setOnClickListener(){
            num= Random.nextInt(minValue,maxValue)
            guessing.setText(num.toString())
            generate.visibility= View.INVISIBLE
            guessed.visibility=View.VISIBLE
        }

        up.setOnClickListener(){
            minValue=num
            if(checkingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessing.setText(num.toString())
            }else{
                guessing.setText("No puede ser ;( me ganaste")
            }
        }

        down.setOnClickListener(){
            maxValue=num
            if(checkingLimits()){
                num=Random.nextInt(minValue,maxValue)
                guessing.setText(num.toString())
            }else{
                guessing.setText(num.toString())
            }
        }

        guessed.setOnClickListener(){
            if(!won){
                guessing.setText("Adiviné, tu número es el " + num)
                guessed.setText("Volver a jugar")
                won=true
            }else{
                generate.visibility= View.VISIBLE
                guessing.setText("Tap on generate to start")
                guessed.visibility= View.GONE
                resetValues()
            }
        }
    }

    fun resetValues(){
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }

    fun checkingLimits(): Boolean{
        return minValue!=maxValue
    }
}