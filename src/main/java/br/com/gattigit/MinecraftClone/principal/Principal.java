package br.com.gattigit.MinecraftClone.principal;

import br.com.gattigit.MinecraftClone.render.Render;
import br.com.gattigit.MinecraftClone.models.Triangulo;
import br.com.gattigit.MinecraftClone.models.Troangulo;

public class Principal {
    Render render;

    public Principal(Render render){
        this.render = render;
    }
    
    public void work(){
        render.addObjeto(new Triangulo());
        Troangulo tri = new Troangulo();
        render.addObjeto(tri);
    }    
}