package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.ColectivoOutput;
import org.crue.hercules.sgi.sgp.model.Colectivo;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ColectivoConverter {

  private final ModelMapper modelMapper;

  public ColectivoOutput convert(Colectivo entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, ColectivoOutput.class);
  }

  public Page<ColectivoOutput> convert(Page<Colectivo> page) {
    return page.map(this::convert);
  }

}
