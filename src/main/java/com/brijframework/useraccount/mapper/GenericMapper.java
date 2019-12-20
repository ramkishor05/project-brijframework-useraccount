package com.brijframework.useraccount.mapper;

import java.util.List;

public interface GenericMapper<E, D> {

	D mapToDTO(E eoRole);

	E mapToDAO(D eoRoleDTO);
	
	List<E> mapToDAO(List<D> findAll);
	
	List<D> mapToDTO(List<E> eoRoleDTO);
}
