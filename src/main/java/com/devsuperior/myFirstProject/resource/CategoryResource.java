package com.devsuperior.myFirstProject.resource;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.myFirstProject.entities.Category;
import com.devsuperior.myFirstProject.repository.CategoryRepository;
 
@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryRepository categoryRrepository;

	@GetMapping
	public ResponseEntity<List<Category>>findAll()
	{
		/*List<Category> lista = new ArrayList<>();
		lista.add(new Category(1L,"Eletronics"));
		lista.add(new Category(2L,"Eletronics"));
*/
		List<Category> lista = categoryRrepository.findAll();
		
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value ="save/{id}/{nome}")
	public ResponseEntity<List<Category>> save(@PathVariable Long id, @PathVariable String nome )
	{
		Category categoria = new Category(id,nome);
		categoryRrepository.save(categoria );
		
		List<Category> lista = categoryRrepository.findAll();
		return ResponseEntity.ok().body(lista);
		
	}

	@GetMapping(value ="/{id}")
	public ResponseEntity<Category>findById(@PathVariable Long id)
	{
		/*List<Category> lista = new ArrayList<>();
		lista.add(new Category(1L,"Eletronics"));
		lista.add(new Category(2L,"Eletronics"));

		Category cat =null;// new Category();
		for (Category category : lista) {
			if(category.getId() == id) {
				cat = new Category(category.getId(),category.getName());
			}
		}
		*/
		Category cat = categoryRrepository.findById(id);

		if (cat!=null) {
			return ResponseEntity.ok().body(cat);
		}
		else {
			return ResponseEntity.notFound().build();
		}
			
		
	}
}
