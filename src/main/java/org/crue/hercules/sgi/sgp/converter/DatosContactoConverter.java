package org.crue.hercules.sgi.sgp.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sgp.dto.DatosContactoOutput;
import org.crue.hercules.sgi.sgp.model.DatosContacto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DatosContactoConverter {

  private final ModelMapper modelMapper;
  private final EmailsConverter emailsConverter;
  private final StringToListStringsConverter stringToListStringsConverter;

  @PostConstruct
  public void mapperConfig() {

    modelMapper.typeMap(DatosContacto.class, DatosContactoOutput.class)
        .addMappings(
            mapper -> mapper.using(emailsConverter).map(DatosContacto::getEmails, DatosContactoOutput::setEmails))
        .addMappings(
            mapper -> mapper.using(stringToListStringsConverter)
                .map(DatosContacto::getMoviles, DatosContactoOutput::setMoviles))
        .addMappings(
            mapper -> mapper.using(stringToListStringsConverter)
                .map(DatosContacto::getTelefonos, DatosContactoOutput::setTelefonos));
  }

  public DatosContactoOutput convert(DatosContacto entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, DatosContactoOutput.class);
  }

}
