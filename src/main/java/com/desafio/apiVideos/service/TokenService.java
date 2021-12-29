package com.desafio.apiVideos.service;

import com.desafio.apiVideos.Model.Usuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenService {

    @Value("${apiVideos.jwt.expiration}") //Pega um valor do application propeties
    private String expiration;

    @Value("${apiVideos.jwt.secret}")
    private String secret;

    public String gerarToken(Authentication authentication) {
        Usuario usuario = (Usuario) authentication.getPrincipal();
        Date hoje = new Date();

        Date validade = new Date(hoje.getTime() + Long.parseLong(expiration));

        return Jwts.builder()
                .setIssuer("Api de videos")//Quem gerou o token
                .setSubject(usuario.getId().toString())//De quem é o token
                .setIssuedAt(hoje)//Quando foi consedido
                .setExpiration(validade)//seta a data de validade do token
                .signWith(SignatureAlgorithm.HS256, secret)//
                .compact();
    }

    public boolean validaToken(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        return Long.parseLong(Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject());
    }
}
