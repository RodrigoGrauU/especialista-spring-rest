package com.algaworks.algafood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.algaworks.algafood.api.model.EnderecoModel;
import com.algaworks.algafood.domain.model.Endereco;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		var modelMapper = new ModelMapper();
		
//		modelMapper.createTypeMap(Restaurante.class, RestauranteModel.class)
//		.addMapping(Restaurante::getTaxaFrete, RestauranteModel::setPrecoFrete);
		
		var enderecoToEnderecoModelTypeMapper =  modelMapper.createTypeMap(Endereco.class, EnderecoModel.class);
		enderecoToEnderecoModelTypeMapper.<String>addMapping(src -> src.getCidade().getEstado().getNome(), 
				(dest, value) -> dest.getCidade().setEstado(value));
		
		return modelMapper;
	}
}
