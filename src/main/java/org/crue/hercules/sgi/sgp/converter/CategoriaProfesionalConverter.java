package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.CategoriaProfesionalOutput;
import org.crue.hercules.sgi.sgp.model.CategoriaProfesional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CategoriaProfesionalConverter {

  private final ModelMapper modelMapper;

  public CategoriaProfesionalOutput convert(CategoriaProfesional entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, CategoriaProfesionalOutput.class);
  }

  public Page<CategoriaProfesionalOutput> convert(Page<CategoriaProfesional> page) {
    return page.map(this::convert);
  }

}
