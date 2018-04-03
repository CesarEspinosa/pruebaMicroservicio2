package org.springframework.prueba.controller;

import java.util.Collection;


import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.prueba.beans.UserBean;
import org.springframework.stereotype.Component;
@Component
public class Routes extends RouteBuilder{
	
	@Override
	public void configure() throws Exception{
		rest("/prueba/todosUsuarios").description("Servicio REST de usuarios")
		
			
			.get("/resumen").description("Devuelve todos los usuarios")
			.produces("application/json")
			.outType(Collection.class)
			.route().bean(UserBean.class, "listaUsuarios").end();
		
		restConfiguration().component("jetty")
			.bindingMode(RestBindingMode.json)
			.component("servlet")
			.dataFormatProperty("prettyPrint","true")
			.port(8080);
	}

}
