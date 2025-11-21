package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.NivelAcademicoOutput;
import org.crue.hercules.sgi.sgp.model.NivelAcademico;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NivelAcademicoConverter {

  private final ModelMapper modelMapper;

  public NivelAcademicoOutput convert(NivelAcademico entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, NivelAcademicoOutput.class);
  }

  public Page<NivelAcademicoOutput> convert(Page<NivelAcademico> page) {
    return page.map(this::convert);
  }

}
