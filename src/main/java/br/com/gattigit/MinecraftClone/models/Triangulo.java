package br.com.gattigit.MinecraftClone.models;

public class Triangulo implements IFormato{
    private static final float[] vertices = {
     0.5f,  0.5f, 0.0f,
    -0.5f, -0.5f, 0.0f,
     0.5f, -0.5f, 0.0f
    };

    private static final int[] indices = {
    0, 1, 2//x, y e z
    };

    private float[] posA;

    public Triangulo(){
        posA = vertices.clone();
    }


    @Override
    public float[] getVertices() {
        return posA;
    }

    @Override
    public int[] getIndices() {
        return indices;
    }

    @Override
    public void translacao(Float[] pos) {
        int contador = 0;
        for (int i = 0; i < posA.length; i++) {
            System.out.println(i);
            posA[i] += pos[contador];
            contador = (contador + 1) % 3; 
        }
    }
}
