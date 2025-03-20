package com.example.controller;

import com.example.model.Livro;
import com.example.model.Usuario;
import com.example.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("livro")
@CrossOrigin(origins = {"http://localhost:4200", "https://usuarioapi-young-brook-4416.fly.dev"})
public class LivroController {

    @Autowired
    LivroRepository repository;

    @GetMapping
    public List<Livro> obterLivros(){
        return repository.findAll();
    }

    @PostMapping
    public Livro salvarLivro(@RequestBody Livro livro){
       return repository.save(livro);
    }

    @DeleteMapping("{id}")
    public void deletarLivro(@PathVariable("id") Long id){

        Optional<Livro> verificarLivro = repository.findById(id);
        if (verificarLivro.isPresent()){
            System.out.println("Livro deletado com sucesso!");
            repository.deleteById(Long.valueOf(id));
        }else {
            System.out.println("Este id de livro não existe");
        }
    }
    @PutMapping("{id}")
    public void atualizarLivro(@PathVariable("id") Long id, @RequestBody Livro livroAtualizado) {
        Optional<Livro> verificarLivro = repository.findById(id);

        if (verificarLivro.isPresent()) {
            Livro livro = verificarLivro.get();
            livro.setAutor(livroAtualizado.getAutor());
            livro.setTitulo(livroAtualizado.getTitulo());
            livro.setDescricao(livroAtualizado.getDescricao());
            livro.setPaginas(livroAtualizado.getPaginas());
            livro.setDataPublicacao(livroAtualizado.getDataPublicacao());

            repository.save(livro);
        } else {
            System.out.println("Não foi possivel salvar o usuario");
        }
    }
}
