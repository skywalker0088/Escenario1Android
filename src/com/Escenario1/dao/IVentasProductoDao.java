package com.Escenario1.dao;

import java.util.List;

import com.Escenario1.dto.Vendedor;
import com.Escenario1.dto.VentasProducto;

public interface IVentasProductoDao extends IGenericDao<VentasProducto,Long> {
	
	List<VentasProducto> restriveallforventa(int idventa) throws Exception;
}
