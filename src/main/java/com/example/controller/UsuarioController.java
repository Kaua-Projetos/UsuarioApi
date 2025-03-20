package com.example.controller;

import com.example.dto.SenhaDTO;
import com.example.dto.UsuarioDTO;
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
@CrossOrigin(origins = {"http://localhost:4200", "https://usuarioapi-young-brook-4416.fly.dev"})
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
    public Usuario salvarUsuario(@RequestBody UsuarioDTO usuarioDTO, String email) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuarioDTO.nome());
        usuario.setEmail(usuarioDTO.email());
        usuario.setSenha(encoder.encode(usuarioDTO.senha()));
        usuario.setCpf(usuarioDTO.cpf());

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
    public void atualizarUsuario(@PathVariable("id") Long id, @RequestBody UsuarioDTO usuariodto){
        Optional<Usuario> verificarUsuario = repository.findById(id);

        if (verificarUsuario.isPresent()){
            Usuario usuario = verificarUsuario.get();
            usuario.setNome(usuariodto.nome());
            usuario.setEmail(usuariodto.email());
            usuario.setSenha(usuariodto.senha());
            usuario.setCpf(usuariodto.cpf());
            repository.save(usuario);
        }else {
            System.out.println("Não foi possivel salvar o usuario");
        }
    }

    @PutMapping("/email/{email}")
    public void atualizarSenha(@PathVariable("email") String email, @RequestBody SenhaDTO senhaDTO){
        Optional<Usuario> verificarUsuarioEmail = repository.findByEmail(email);

        if (verificarUsuarioEmail.isPresent()){
            Usuario usuario = verificarUsuarioEmail.get();

            usuario.setSenha(encoder.encode(senhaDTO.senha()));
            repository.save(usuario);
        }else{
            System.out.println("Email não encontrado");
        }
    }

}
