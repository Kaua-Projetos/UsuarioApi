package com.example.service;

import com.example.dto.UsuarioDTO;
import com.example.model.Usuario;
import com.example.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {
    private UsuarioRepository usuarioRepository;
    private PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder encoder) {
        this.usuarioRepository = usuarioRepository;
        this.encoder = encoder;
    }

    public void salvar(UsuarioDTO usuariodto) {
        Usuario usuario = new Usuario();
        usuario.setNome(usuariodto.nome());
        usuario.setEmail(usuariodto.email());
        usuario.setSenha(encoder.encode(usuariodto.senha()));
        usuario.setCpf(usuariodto.cpf());

        this.usuarioRepository.save(usuario);
    }

    public Usuario obterPorEmail(@RequestBody Usuario usuario) {
        return this.usuarioRepository.save(usuario);
    }

}

