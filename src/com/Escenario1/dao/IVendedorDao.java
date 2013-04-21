package com.Escenario1.dao;

import com.Escenario1.bo.VendedorBo;
import com.Escenario1.dto.Vendedor;

public interface IVendedorDao extends IGenericDao<Vendedor,Long>{
	
	VendedorBo login(String nombreUsuario, String clave) throws Exception;

}
