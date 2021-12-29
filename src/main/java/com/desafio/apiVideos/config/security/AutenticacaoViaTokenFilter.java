package com.desafio.apiVideos.config.security;

import com.desafio.apiVideos.Model.Usuario;
import com.desafio.apiVideos.repository.UsuarioRepository;
import com.desafio.apiVideos.service.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@AllArgsConstructor
public class AutenticacaoViaTokenFilter  extends OncePerRequestFilter {

    private UsuarioRepository usuarioRepository;

    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String token = pegarToken(request);
        boolean validacao = tokenService.validaToken(token);//Faz a validação do token
        if(validacao){
            autenticaCliente(token);
        }
        filterChain.doFilter(request, response);
    }

    private void autenticaCliente(String token) {
        Long idUsuario = tokenService.getIdUsuario(token);
        Usuario usuario = usuarioRepository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken autentication = new UsernamePasswordAuthenticationToken(
                usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(autentication);
    }

    //Utiliza a request para pegar o somente o token do header da requisição
    private String pegarToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if(token == null || token.isEmpty() || !token.startsWith("Bearer ")){
            return null;
        }
        return token.substring(7, token.length());
    }
}
