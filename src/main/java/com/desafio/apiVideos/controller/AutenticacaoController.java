package com.desafio.apiVideos.controller;

import com.desafio.apiVideos.Model.Usuario;
import com.desafio.apiVideos.dto.TokenDto;
import com.desafio.apiVideos.form.LoginForm;
import com.desafio.apiVideos.repository.UsuarioRepository;
import com.desafio.apiVideos.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<TokenDto> autentica(@RequestBody @Validated LoginForm form){
        UsernamePasswordAuthenticationToken dadosLogin = form.converter();

        try{
            Authentication authentication = authManager.authenticate(dadosLogin);
            String token = tokenService.gerarToken(authentication);
            return ResponseEntity.ok(new TokenDto(token, "Bearer"));
        }catch (ArithmeticException e){
            return ResponseEntity.badRequest().build();
        }

    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario){
        //colocar criptografia dentro de um metodo da classe de usuario repository
        usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
        if(!usuarioRepository.findByEmail(usuario.getEmail()).isPresent()){
            return ResponseEntity.ok(usuarioRepository.save(usuario));
        }
        return ResponseEntity.badRequest().build();
    }

}
