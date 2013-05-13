package com.Escenario1.dao;

import com.Escenario1.dto.Administrador;
import com.Escenario1.dto.Ventas;

public interface IVentasDao extends IGenericDao<Ventas,Long>{
	int ultimovalor(int index) throws Exception;	
}
