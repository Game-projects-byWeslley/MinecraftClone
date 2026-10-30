package br.com.gattigit.MinecraftClone.models;

public interface IFormato {
    //formato a ser desenhado no render
    public void translacao(Float[] pos);

    public float[] getVertices();

    public int[] getIndices();
}
