package mx.edu.ittepic.ladm_u2_ejercicio5canvasanimado

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.view.MotionEvent
import android.view.View

class Lienzo(p:MainActivity) : View(p) {
    // se requiere p para que view sepa cual activity va a trabajar
    var puntero = p
    /*var cx = 150f
    var cy = 150f
    var tx = 733f
    var ty = 818f*/
    var circulo = FiguraGeometrica(150, 150, 120)
    var cuadrado = FiguraGeometrica(733, 818, 200, 200)
    var rectangulo = FiguraGeometrica(400, 1200, 400, 200)
    var circulo2 = FiguraGeometrica(500, 1400, 50)
    var punteroFiguraGeometrica: FiguraGeometrica? = null

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        var paint = Paint()

        canvas.drawColor(Color.BLACK)

        //Dibujando un círculo rojo con contorno blanco
        paint.color = Color.RED
        circulo.pintar(canvas, paint)
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        circulo.pintar(canvas, paint)

        //Dibujando un cuadrado VERDE Contorno AZUL
        paint.style = Paint.Style.FILL
        paint.color = Color.GREEN
        cuadrado.pintar(canvas, paint)
        paint.style = Paint.Style.STROKE
        paint.color = Color.BLUE
        cuadrado.pintar(canvas, paint)

        //Dibujando el rectangulo
        paint.style = Paint.Style.FILL
        paint.color = Color.YELLOW
        rectangulo.pintar(canvas, paint)
        paint.style = Paint.Style.STROKE
        paint.color = Color.GRAY
        rectangulo.pintar(canvas, paint)

        //Dibujando 2do ciculo
        paint.color = Color.MAGENTA
        circulo2.pintar(canvas, paint)
        paint.style = Paint.Style.STROKE
        paint.color = Color.WHITE
        paint.strokeWidth = 10f
        circulo2.pintar(canvas, paint)


    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        puntero.setTitle("")
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                //REVISAMOS QUIEN ESTA EN AREA
                if (circulo.estaEnArea(event)) {
                    punteroFiguraGeometrica = circulo
                    puntero.setTitle("TOCASTE CIRCULO")
                }
                if (cuadrado.estaEnArea(event)) {
                    punteroFiguraGeometrica = cuadrado
                    puntero.setTitle("TOCASTE CUADRADO")
                }
                if (rectangulo.estaEnArea(event)) {
                    punteroFiguraGeometrica = rectangulo
                    puntero.setTitle("TOCASTE RECTANGULO")
                }
                if (circulo2.estaEnArea(event)) {
                    punteroFiguraGeometrica = circulo2
                    puntero.setTitle("TOCASTE CIRCULO2")
                }
            }
            MotionEvent.ACTION_MOVE -> {
                if (punteroFiguraGeometrica != null) {
                    punteroFiguraGeometrica!!.arrastrar(event)
                }

            }
            MotionEvent.ACTION_UP -> {
                punteroFiguraGeometrica = null
            }
        }
        invalidate()
        return true
    }

    fun animarCirculo() {
        circulo2.rebote(width, height)

        invalidate()
    }

}