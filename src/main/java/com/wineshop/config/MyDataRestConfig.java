package com.wineshop.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.wineshop.model.Region;
import com.wineshop.model.Variety;
import com.wineshop.model.Wine;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	private EntityManager entityManager;

	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		entityManager = theEntityManager;
	}

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		exposeIds(config);
	}

	private void exposeIds(RepositoryRestConfiguration config) {
		Class[] domainTypes = {Wine.class, Region.class};
		config.exposeIdsFor(domainTypes);
		
//		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
//		List<Class> entityClasses = new ArrayList<>();
//		// - get the entity types for the entities
//		for (EntityType tempEntityType : entities) {
//			entityClasses.add(tempEntityType.getJavaType());
//			Class[] domainTypes = entityClasses.toArray(new Class[0]);
//			config.exposeIdsFor(domainTypes);
//		}

	}

}
