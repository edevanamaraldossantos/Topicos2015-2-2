package br.grupointegrado.ads.flappyBird;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

/**
 * Created by Edevan on 26/10/2015.
 */
public class Obstaculo {

    private World mundo;
    private OrthographicCamera camera;
    private Body corpo_cima, corpo_baixo;
    private float posX;
    private float posYCima, posYBaixo;
    private float largura, altura;
    private boolean passou;

    private Obstaculo ultimoObstaculo;//ultimo antes do atual

    public Obstaculo(World mundo, OrthographicCamera camera, Obstaculo ultimoObstaculo){
        this.mundo = mundo;
        this.camera = camera;
        this.ultimoObstaculo = ultimoObstaculo;

        initPosicao();
        initCorpoCima();
        initCorpoBaixo();
    }

    private void initCorpoBaixo() {
        corpo_baixo = Util.criarCorpo(mundo,BodyDef.BodyType.StaticBody, posX, posYBaixo);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, altura / 2);

        Util.criarForma(corpo_baixo, shape,"OBSTACULO_BAIXO" );
        shape.dispose();
    }

    private void initCorpoCima() {
        corpo_cima = Util.criarCorpo(mundo, BodyDef.BodyType.StaticBody, posX, posYCima);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(largura / 2, altura / 2);

        Util.criarForma(corpo_cima, shape, "OBSTACULO_CIMA");
        shape.dispose();
    }

    private void initPosicao() {
        largura = 40 / Util.PIXEL_METRO;
        altura = camera.viewportHeight / Util.PIXEL_METRO;

        float xInicial = largura;
        if (ultimoObstaculo != null)
            xInicial = ultimoObstaculo.getPosX();

        posX = xInicial + 8; // 8 e o espa�o entre os obstaculos
        // Parcela e o tamanho da tela dividido por 6, para encontrar a
        // posi��o y do obstaculo
        float parcela = (altura - Util.ALTURA_CHAO) / 6;
        int multiplicador = MathUtils.random(1,3);//gera numero aleatorio  entre 1 e 3

        posYBaixo = Util.ALTURA_CHAO +(parcela * multiplicador) - (altura/2);

        posYCima = posYBaixo + altura + 2f; // 2f espa�o entre os canos


    }
    public float getPosX(){
        return this.posX;
    }

    public void remover(){
        mundo.destroyBody(corpo_cima);
        mundo.destroyBody(corpo_baixo);
    }




}