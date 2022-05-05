package com.generation.games;

import com.generation.games.model.Categoria;
import com.generation.games.model.Produto;
import com.generation.games.repository.CategoriaRepository;
import com.generation.games.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GamesApplication {

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public static void main(String[] args) {
		SpringApplication.run(GamesApplication.class, args);

	}

	@Bean
	CommandLineRunner initDatabase(ProdutoRepository produtoRepository, CategoriaRepository categoriaRepository) {

		return args -> {

			Categoria categoria1 = categoriaRepository.save(new Categoria("Open World"));
			Categoria categoria2 = categoriaRepository.save(new Categoria("Puzzle"));
			Categoria categoria3 = categoriaRepository.save(new Categoria("Maze"));
			//Categoria categoria4 = categoriaRepository.save(new Categoria("Maze")); // Testando Unique

			produtoRepository.save(new Produto("Skyrim", 100.00, 5, categoria1));
			produtoRepository.save(new Produto("Portal", 50.00, 3, categoria2));
			produtoRepository.save(new Produto("Pac-Man", 15.00, 23, categoria3));
			produtoRepository.save(new Produto("Witcher 3", 75.00, 16, categoria1));
			produtoRepository.save(new Produto("Bomberman", 5.00, 28, categoria2));
			produtoRepository.save(new Produto("GTA V", 60.00, 56, categoria1));


		};
	}
}
