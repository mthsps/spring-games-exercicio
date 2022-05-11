package com.generation.games.service;

import com.generation.games.model.Usuario;
import com.generation.games.model.UsuarioLogin;
import com.generation.games.repository.UsuarioRepository;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> register(Usuario usuario) {

        if (usuarioRepository.findByUsuario(usuario.getUsuario()).isPresent()) {
            return Optional.empty();
        }

        usuario.setSenha(encryptPassword(usuario.getSenha()));
        return Optional.of(usuarioRepository.save(usuario));

    }


    public Optional<UsuarioLogin> authenticateUser (Optional<UsuarioLogin> usuarioLogin) {

        Optional<Usuario> usuario = usuarioRepository.findByUsuario(usuarioLogin.get().getUsuario());

        if (usuario.isPresent()) {
           if (passwordMatcher(usuarioLogin.get().getSenha(), usuario.get().getSenha())) {
               usuarioLogin.get().setId(usuario.get().getId());
               usuarioLogin.get().setNome(usuario.get().getNome());
               usuarioLogin.get().setImagemUrl(usuario.get().getImagemUrl());
               usuarioLogin.get().setToken(generateToken(usuarioLogin.get().getUsuario(), usuarioLogin.get().getSenha()));
               usuarioLogin.get().setSenha(usuario.get().getSenha());

               return usuarioLogin;
           }
        }

        return Optional.empty();
    }

    private String encryptPassword(String senha) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.encode(senha);
    }
    private boolean passwordMatcher(String typedPassword, String savedPassword) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return encoder.matches(typedPassword, savedPassword);
    }

    private String generateToken(String usuario, String senha) {

        String token = usuario + ":" + senha;
        byte[] tokenBase64 = Base64.encodeBase64(token.getBytes(StandardCharsets.US_ASCII));
        return "Basic " + new String(tokenBase64);

    }


}
