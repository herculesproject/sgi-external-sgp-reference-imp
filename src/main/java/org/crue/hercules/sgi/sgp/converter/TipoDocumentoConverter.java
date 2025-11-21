package org.crue.hercules.sgi.sgp.converter;

import org.crue.hercules.sgi.sgp.dto.TipoDocumentoOutput;
import org.crue.hercules.sgi.sgp.model.TipoDocumento;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TipoDocumentoConverter {

  private final ModelMapper modelMapper;

  public TipoDocumentoOutput convert(TipoDocumento entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, TipoDocumentoOutput.class);
  }

  public Page<TipoDocumentoOutput> convert(Page<TipoDocumento> page) {
    return page.map(this::convert);
  }

}
