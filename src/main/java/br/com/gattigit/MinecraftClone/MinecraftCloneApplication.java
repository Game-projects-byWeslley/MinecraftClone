package br.com.gattigit.MinecraftClone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gattigit.MinecraftClone.render.Render;
import br.com.gattigit.MinecraftClone.window.Window;

@SpringBootApplication
public class MinecraftCloneApplication implements CommandLineRunner{
	
	Render render = new Render();
	private Window window = new Window(600,600, render);

	public static void main(String[] args) {
		SpringApplication.run(MinecraftCloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		window.generateWindow();
		System.out.println("Window");
	}

}
