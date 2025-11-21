package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.DatosAcademicosOutput;
import org.crue.hercules.sgi.sgp.model.DatosAcademicos;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatosAcademicosConverter {

  private final ModelMapper modelMapper;

  public DatosAcademicosOutput convert(DatosAcademicos entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, DatosAcademicosOutput.class);
  }

}
