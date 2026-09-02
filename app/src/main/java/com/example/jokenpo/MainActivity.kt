package com.example.jokenpo

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.random.Random

// https://github.com/etecsebrae/jokenpo/tree/main/imagens (IMAGEM)
class MainActivity : AppCompatActivity() {

    // 27/08/2026

    // private = escopo da variável, só visível nesse arquivo | lateinit = inicialização tardia | var + nome : classe/tipo
    private lateinit var textResultado : TextView

    private lateinit var textAdversario : TextView
    private lateinit var imageComputador : ImageView

    private lateinit var imageAlegria : ImageView

    // fun main() { soma(10, 20) }
    // fun soma(var x, var y) { x + y }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imageComputador = findViewById<ImageView>(R.id.ImagePadrao)
        imageAlegria = findViewById<ImageView>(R.id.ImageAlegria)
        textResultado = findViewById<TextView>(R.id.TextResultado)
        textAdversario = findViewById<TextView>(R.id.TextAdversario)

        val pedra = findViewById<ImageView>(R.id.ImagePedra)
        pedra.setOnClickListener {
            jogar("PEDRA")
        }

        val papel = findViewById<ImageView>(R.id.ImagePapel)
        papel.setOnClickListener {
            jogar("PAPEL")
        }

        val tesoura = findViewById<ImageView>(R.id.ImageTesoura)
        tesoura.setOnClickListener {
            jogar("TESOURA")
        }
    }
    fun jogar(jogador : String) {

        val opcoes = arrayOf("PEDRA", "PAPEL", "TESOURA") // Array

        val computador = opcoes[Random.nextInt(opcoes.size)] // Sorteio de um número entre 0 e 2

        when(computador) {
            // Atualiza a imagem padrão para a imagem do número sorteado
            "PEDRA" -> {
                imageComputador.setImageResource(R.drawable.pedra)
                textAdversario.text = "Adversário escolheu PEDRA"
            }
            "PAPEL" -> {
                imageComputador.setImageResource(R.drawable.papel)
                textAdversario.text = "Adversário escolheu PAPEL"
            }
            "TESOURA" -> {
                imageComputador.setImageResource(R.drawable.tesoura)
                textAdversario.text = "Adversário escolheu TESOURA"
            }
        }

        when {
            (jogador == computador) -> {textResultado.text = "Empate!"}
            (jogador == "PEDRA" && computador == "TESOURA") -> {
                textResultado.text = "Você venceu!"

            }
            (jogador == "PAPEL" && computador == "PEDRA") -> {textResultado.text = "Você venceu!"}
            (jogador == "TESOURA" && computador == "PAPEL") -> {textResultado.text = "Você venceu!"}
            else -> {textResultado.text = "Seu adversário ganhou a rodada"}
        }
    }
}