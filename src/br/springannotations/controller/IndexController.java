package br.springannotations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import br.springannotations.model.Index;
import br.springannotations.repository.IndexRepository;

@Controller
public class IndexController {
	
	@Autowired
	private IndexRepository indexRepository;

	@RequestMapping({ "", "/" })
	public ModelAndView index() {
		
		indexRepository.salvar(new Index("Hello, Spring!!"));
		Index index = indexRepository.find(1l);
		return new ModelAndView("index", "index", index );
	}

}
