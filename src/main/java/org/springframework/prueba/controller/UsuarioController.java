package org.springframework.prueba.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.prueba.model.Usuario;
import org.springframework.prueba.service.impl.UsuarioService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
/**
 * Controller de usuarios del microservicio
 * Contiene las 4 funcionalidades básicas de
 * un servicio RESTful (GET, POST, PUT, DELETE). 
 * @author César
 *
 */
@RestController
@RequestMapping("/admon/usuarios")
public class UsuarioController {
	@Autowired
	UsuarioService usuarioService; 
	
	@Autowired
	UsuarioController(UsuarioService usuarioService){
		this.usuarioService = usuarioService;
	}
	/**
	 * Método que regresa la información de un usuario en particular
	 * @param usuarioId id del usuario a buscar, 
	 * 		  se recibe como parte de la URL 
	 * @return Objeto usuario en formato JSON.
	 */
	@RequestMapping( method=RequestMethod.GET, value="/resumen/{usuarioId}")
	public Usuario buscar(@PathVariable int usuarioId) {
		return this.usuarioService.buscarUsuario(usuarioId);
	}
	/**
	 * Método que regresa la información de todos los 
	 * usuarios que existan en la Base de Datos
	 * @return Lista de Usuarios en formato JSON
	 */
	@RequestMapping(method=RequestMethod.GET, value="/resumen")
	public Collection<Usuario> listaUsuarios(){
		return this.usuarioService.listaUsuarios();
	}
	/**
	 * Método que regresa 
	 * @param usuario
	 * @return
	 */
	@RequestMapping(method=RequestMethod.POST, value="/alta")
	public ResponseEntity<Usuario> add(@RequestBody Usuario usuario){
		
		Usuario respuesta = usuarioService.alta(usuario);
		if(respuesta.getId() >= 1) {
			return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/modificar")
	public ResponseEntity<Usuario> modificar(@RequestBody Usuario usuario){
		
		Usuario respuesta = usuarioService.modificar(usuario); 
		if(respuesta.getId() >= 1) {
			return new ResponseEntity<>(respuesta, HttpStatus.ACCEPTED); 
		}else {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/eliminar/{usuarioId}")
	public ResponseEntity<String> eliminar(@PathVariable int usuarioId){
		int r = usuarioService.eliminar(usuarioId);
		if(r == 1) {
			return new ResponseEntity<>("Proceso realizado correctamente", 
					HttpStatus.OK); 
		}else {
			return new ResponseEntity<>("No se realizó la eliminación", 
					HttpStatus.ACCEPTED);
		}
		
	}
}
