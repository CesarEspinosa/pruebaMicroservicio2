package org.springframework.prueba.beans;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.prueba.model.Usuario;
import org.springframework.prueba.service.impl.UsuarioService;

public class UserBean {
	@Autowired
	UsuarioService usuarioService;
	
	public Collection<Usuario> listaUsuarios(){
		return this.usuarioService.listaUsuarios();
	}
}
