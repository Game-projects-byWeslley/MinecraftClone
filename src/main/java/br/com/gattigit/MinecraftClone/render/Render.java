package br.com.gattigit.MinecraftClone.render;

import org.lwjgl.glfw.GLFW;

public class Render{
	private int height, width;
	private Double timer = 0.0;
	private int fps = 0;


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

			//fps
			fps++;
			if(GLFW.glfwGetTime() >= timer + 1.0){
				timer = GLFW.glfwGetTime();
				System.out.println("Timer: "+ timer.intValue()+" | FPS: "+fps);
				fps = 0;
			}
		}
		GLFW.glfwDestroyWindow(win);
		GLFW.glfwTerminate();
	}
}