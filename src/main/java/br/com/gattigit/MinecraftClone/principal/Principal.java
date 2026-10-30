package br.com.gattigit.MinecraftClone.principal;

import br.com.gattigit.MinecraftClone.render.Render;
import br.com.gattigit.MinecraftClone.models.Triangulo;

public class Principal {
    Render render;

    public Principal(Render render){
        this.render = render;
    }
    
    public void work(){
        render.addObjeto(new Triangulo());
        Triangulo tri = new Triangulo();
        tri.translacao(new Float[]{0.5f,0.5f,0.0f});
        render.addObjeto(tri);
    }
}