package br.com.denis.projeto1;

import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import br.com.denis.projeto1.rh.dominio.Pessoa;
import br.com.denis.projeto1.rh.dominio.PessoaRepositorio;

@Component
public class BancoDadosInicial implements CommandLineRunner {
	
	@Autowired
	private PessoaRepositorio pessoaRepo;	

	@Override
	@Transactional
	public void run(String... args) throws Exception {
		
		Pessoa p1 = new Pessoa("João Marinho");
		p1.setDataNascimento(LocalDate.of(1990, 07, 25));
		p1.setCpf("123.456.789-01");
		p1.setEmail("joaomarinho@mail.com");
		p1.setTelefone("12345-6789");
			
		Pessoa p2 = new Pessoa("Maisa Toledo");
		p2.setDataNascimento(LocalDate.of(1987, 03, 13));
		p2.setCpf("098-765-432-10");
		p2.setEmail("mariatoledo@mail.com");
		p2.setTelefone("09876-4321");
		
		Pessoa p3 = new Pessoa("Orlando Bras");
		p3.setDataNascimento(LocalDate.of(1992, 01, 30));
		p3.setCpf("019-283-746-51");
		p3.setEmail("orlandobras@mail.com");
		p3.setTelefone("01928-3746");
		
		pessoaRepo.save(p1);
		pessoaRepo.save(p2);
		pessoaRepo.save(p3);		
	}
}
