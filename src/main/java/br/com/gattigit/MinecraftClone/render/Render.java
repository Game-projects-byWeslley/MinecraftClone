package br.com.gattigit.MinecraftClone.render;

import org.lwjgl.glfw.GLFW;

public class Render{
	private int height, width;

	public Render(int height, int width){
		this.height = height;
		this.width = width;
	}

	public void generateWindow(){
		if(!GLFW.glfwInit()){
			throw new RuntimeException("GLFW nao conseguiu inicializar.");
		}
		long win = GLFW.glfwCreateWindow(width, height, "Minecraft Clone", 0L, 0L);
		while (!GLFW.glfwWindowShouldClose(win)) {
			GLFW.glfwPollEvents();
		}
	}
}