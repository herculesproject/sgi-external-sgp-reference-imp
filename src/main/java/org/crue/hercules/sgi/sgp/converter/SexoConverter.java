package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.SexoOutput;
import org.crue.hercules.sgi.sgp.model.Sexo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SexoConverter {

  private final ModelMapper modelMapper;

  public SexoOutput convert(Sexo entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, SexoOutput.class);
  }

  public Page<SexoOutput> convert(Page<Sexo> page) {
    return page.map(this::convert);
  }

}
