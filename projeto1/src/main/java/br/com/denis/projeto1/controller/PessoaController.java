package br.com.denis.projeto1.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import br.com.denis.projeto1.rh.dominio.Pessoa;
import br.com.denis.projeto1.rh.dominio.PessoaRepositorio;

@Controller
public class PessoaController {

	private PessoaRepositorio pessoaRepo;

	public PessoaController(PessoaRepositorio pessoaRepo) {
		this.pessoaRepo = pessoaRepo;
	}

	@GetMapping("/rh/pessoas")
	public String listarPessoas(Model model) {
		model.addAttribute("listaPessoas", pessoaRepo.findAll());

		return "rh/pessoas/index";
	}

	@GetMapping("/rh/pessoas/nova")
	public String novaPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		
		return "rh/pessoas/form";
	}

	@PostMapping("/rh/pessoas/salvar")
	public String salvarPessoa(@ModelAttribute("pessoa") Pessoa pessoa) {
		pessoaRepo.save(pessoa);
		
		return "redirect:/rh/pessoas";
	}
	
	@GetMapping("/rh/pessoas/{id}")
	public String alterarPessoa(@PathVariable("id") Long id, Model model) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		
		if (pessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		
		model.addAttribute("pessoa", pessoa.get());
		
		return "rh/pessoas/form";
	}
	
	@GetMapping("/rh/pessoas/excluir/{id}")
	public String excluirPessoa(@PathVariable("id") Long id, Model model) {
		Optional<Pessoa> pessoa = pessoaRepo.findById(id);
		
		if (pessoa.isEmpty()) {
			throw new IllegalArgumentException("Pessoa inválida");
		}
		
		pessoaRepo.delete(pessoa.get());
		
		return "redirect:/rh/pessoas";
	}	
}
