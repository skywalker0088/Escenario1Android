package com.Escenario1.dao;

import com.Escenario1.dto.Administrador;

public interface IAdministradorDao extends IGenericDao<Administrador,Long> {
	Administrador login(String nombreUsuario, String clave) throws Exception;
}
