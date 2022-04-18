package com.example.demo;

import com.example.demo.model.Rodas;
import com.example.demo.repository.RodasRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/")
	public String index(){
		return "Olá Mundo!";
	}



	/*@Bean
	CommandLineRunner initDataBase(RodasRepository rodasRepository){
		return args -> {
			rodasRepository.deleteAll();

			Rodas r = new Rodas();
			//Id criado automaticamente
			r.setMarca("Volcan");
			r.setMaterial("Aço Fundido");

			Rodas r2 = new Rodas();
			//Id criado automaticamente
			r2.setMarca("Teste2 Marca");
			r2.setMaterial("Teste 3 Material");

			Rodas r3 = new Rodas();
			//Id criado automaticamente
			r3.setMarca("Teste 3 Marca");
			r3.setMaterial("Teste 3 Material");

			rodasRepository.save(r);
			rodasRepository.save(r2);
			rodasRepository.save(r3);
		};


	}*/
}
