package br.com.gattigit.MinecraftClone.models;

public class Troangulo implements IFormato{
    private static final float[] vertices = {
    -0.5f, -0.5f, 0.0f,
     0.5f,  0.5f, 0.0f,
    -0.5f,  0.5f, 0.0f
    };

    private static final int[] indices = {
    0, 1, 2//x, y e z
    };

    @Override
    public void formatoObjeto() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'formatoObjeto'");
    }

    @Override
    public float[] getVertices() {
        return vertices;
    }

    @Override
    public int[] getIndices() {
        return indices;
    }
    
}
