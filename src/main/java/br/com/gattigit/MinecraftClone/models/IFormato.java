package br.com.gattigit.MinecraftClone.models;

public interface IFormato {
    //formato a ser desenhado no render
    public void formatoObjeto();

    public float[] getVertices();

    public Integer[] getIndices();
}
