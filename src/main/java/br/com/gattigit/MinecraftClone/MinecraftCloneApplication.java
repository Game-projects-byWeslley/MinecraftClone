package br.com.gattigit.MinecraftClone;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.gattigit.MinecraftClone.render.Render;

@SpringBootApplication
public class MinecraftCloneApplication implements CommandLineRunner{

	private Render render = new Render(600,600);

	public static void main(String[] args) {
		SpringApplication.run(MinecraftCloneApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		render.generateWindow();
		System.out.println("Window");
	}

}
