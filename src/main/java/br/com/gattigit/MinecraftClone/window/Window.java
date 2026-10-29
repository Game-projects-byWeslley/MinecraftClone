package br.com.gattigit.MinecraftClone.window;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL46;

import br.com.gattigit.MinecraftClone.principal.Principal;
import br.com.gattigit.MinecraftClone.render.Render;

public class Window{
	private int height, width;

	private Double timer = 0.0;
	
	private int lps = 0;//loops per second
	
	private Render render;

	private Principal principal;

	public Window(int height, int width, Render render){
		this.height = height;
		this.width = width;
		this.render = render;
		this.principal = new Principal(render);
	}

	public void generateWindow(){
		//try init
		if(!GLFW.glfwInit()){throw new RuntimeException("GLFW nao conseguiu inicializar.");}
		
		//try win
		long win = GLFW.glfwCreateWindow(width, height, "Minecraft Clone", 0L, 0L);
		if(win == 0L) throw new RuntimeException("\nErro na inicialização da TELA\n");

		//context e openGL
		GLFW.glfwMakeContextCurrent(win);
		GL.createCapabilities();
		GL46.glViewport(0, 0, width, height);

		principal.work();
		render.init();


		//loop run
		while (!GLFW.glfwWindowShouldClose(win)) {
			//limpa a tela
			GL11.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
			//lps
			lps++;
			if(GLFW.glfwGetTime() >= timer + 1.0){
				timer = GLFW.glfwGetTime();
				System.out.println("Timer: "+ timer.intValue()+" | lps: "+lps);
				lps = 0;
			}
			
			/*Object Renderer*/
			render.draw();
			
			/*check for end loop */
			GLFW.glfwSwapBuffers(win);
			GLFW.glfwPollEvents();
		}


		//Finaliza a window
		GLFW.glfwDestroyWindow(win);
		GLFW.glfwTerminate();
	}
}