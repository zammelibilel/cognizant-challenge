package com.cognizant.game.repository;

import org.hibernate.boot.MetadataBuilder;
import org.hibernate.boot.spi.MetadataBuilderContributor;
import org.hibernate.dialect.function.StandardSQLFunction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author zammelib
 *
 */
@Configuration
public class SqlFunctionsMetadataBuilderContributor implements MetadataBuilderContributor {

	@Override
	public void contribute(MetadataBuilder metadataBuilder) {
		metadataBuilder.applySqlFunction("group_concat",
				new StandardSQLFunction("group_concat", StandardBasicTypes.STRING));
	}
}
