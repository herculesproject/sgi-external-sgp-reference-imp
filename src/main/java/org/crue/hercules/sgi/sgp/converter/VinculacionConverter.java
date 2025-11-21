package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.VinculacionOutput;
import org.crue.hercules.sgi.sgp.model.Vinculacion;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VinculacionConverter {

  private final ModelMapper modelMapper;

  public VinculacionOutput convert(Vinculacion entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, VinculacionOutput.class);
  }

}
