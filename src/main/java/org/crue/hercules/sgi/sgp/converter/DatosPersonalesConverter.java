package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.DatosPersonalesOutput;
import org.crue.hercules.sgi.sgp.model.DatosPersonales;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatosPersonalesConverter {

  private final ModelMapper modelMapper;

  public DatosPersonalesOutput convert(DatosPersonales entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, DatosPersonalesOutput.class);
  }

}
