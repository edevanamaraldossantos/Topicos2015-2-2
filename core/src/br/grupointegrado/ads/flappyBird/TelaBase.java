package br.grupointegrado.ads.flappyBird;

import com.badlogic.gdx.Screen;

/**
 * Created by Edevan on 28/09/2015.
 */
public abstract class TelaBase implements Screen {

    protected MainGame game;

    public TelaBase(MainGame game){

    }

    @Override
    public void hide() {
        dispose();
    }
}