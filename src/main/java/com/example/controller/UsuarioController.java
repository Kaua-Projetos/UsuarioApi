package com.example.controller;

import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequestMapping("usuario")
@RestController
public class UsuarioController {

    @Autowired
    UsuarioRepository repository;
    @Autowired
    PasswordEncoder encoder;

    @GetMapping
    public List<Usuario> obterUsuarios(){

        return repository.findAll();
    }
    @PostMapping
    public Usuario salvarUsuario(@RequestBody Usuario usuario){

        usuario.setSenha(encoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }
    @DeleteMapping("{id}")
    public void deletarUsuario(@PathVariable("id") Long id){

        Optional<Usuario> verificarUsuario = repository.findById(id);
        if (verificarUsuario.isPresent()){
            System.out.println("Usuario deletado com sucesso!");
            repository.deleteById(Long.valueOf(id));
        }else {
            System.out.println("Este id de usuario não existe");
        }
    }
    @PutMapping("{id}")
    public void atualizarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuarioAtualizado){
        Optional<Usuario> verificarUsuario = repository.findById(id);

        if (verificarUsuario.isPresent()){
            Usuario usuario = verificarUsuario.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setCpf(usuarioAtualizado.getCpf());

            repository.save(usuario);
        }else {
            System.out.println("Não foi possivel salvar o usuario");
        }




    }

}
