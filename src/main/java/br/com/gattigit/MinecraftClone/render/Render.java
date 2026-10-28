package br.com.gattigit.MinecraftClone.render;

import java.util.ArrayList;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;
import org.springframework.stereotype.Service;

import br.com.gattigit.MinecraftClone.models.RObjetoRenderizavel;
import br.com.gattigit.MinecraftClone.models.Triangulo;

@Service
public class Render {

    private ArrayList<RObjetoRenderizavel> objetos = new ArrayList<>();
    
    public Render(){
        
        //vertices
        int VAO = GL30.glGenVertexArrays();
        GL30.glBindVertexArray(VAO);
        int VBO = GL15.glGenBuffers();
        GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, VBO);
        GL15.glBufferData(GL15.GL_ARRAY_BUFFER, new Triangulo().getVertices(), GL15.GL_DYNAMIC_DRAW);

        //configs
        GL20.glVertexAttribPointer(0, 3, GL30.GL_FLOAT, false, 3 * Float.BYTES, 0L);
        GL20.glEnableVertexAttribArray(0);

    }

    public void draw(){

    }

    public void addObjeto(RObjetoRenderizavel objeto){
        objetos.add(objeto);
    }
}
