package br.com.fatec.aulas.api.service;

import java.util.List;

import br.com.fatec.aulas.api.entity.IdentifiedEntity;
import br.com.fatec.aulas.api.entity.Servico;

public interface EntityDAO<EntityType extends IdentifiedEntity> {
	
	public EntityType save(EntityType entityType);

	public EntityType update(EntityType entityType);

	public void remove(EntityType entityType);

	public List<EntityType> findAll();

	public EntityType findById(Long id);
	
}
