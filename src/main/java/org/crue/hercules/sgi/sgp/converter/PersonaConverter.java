package org.crue.hercules.sgi.sgp.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sgp.dto.PersonaFormlyInput;
import org.crue.hercules.sgi.sgp.dto.PersonaFormlyOutput;
import org.crue.hercules.sgi.sgp.dto.PersonaOutput;
import org.crue.hercules.sgi.sgp.model.Persona;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonaConverter {

  private final ModelMapper modelMapper;
  private final EmailsConverter emailsConverter;

  @PostConstruct
  public void mapperConfig() {

    Condition<String, String> stringNotEmpty = context -> context.getSource() != null
        && !"".equals(context.getSource().trim());

    modelMapper.typeMap(Persona.class, PersonaOutput.class)
        .addMappings(mapper -> mapper.using(emailsConverter).map(Persona::getEmail, PersonaOutput::setEmails))
        .addMappings(mapper -> mapper.when(stringNotEmpty).map(Persona::getEmpresaRef, PersonaOutput::setEmpresaRef))
        .addMappings(
            mapper -> mapper.when(stringNotEmpty).map(Persona::getEntidadPropiaRef, PersonaOutput::setEntidadPropiaRef))
        .addMappings(mapper -> mapper.map(src -> true, PersonaOutput::setActivo));

    modelMapper.typeMap(Persona.class, PersonaFormlyOutput.class)
        .addMappings(mapper -> mapper.using(emailsConverter).map(Persona::getEmail, PersonaFormlyOutput::setEmails));

  }

  public PersonaOutput convert(Persona entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, PersonaOutput.class);
  }

  public Page<PersonaOutput> convert(Page<Persona> page) {
    return page.map(this::convert);
  }

  public PersonaFormlyOutput convertToPersonaFormlyOutput(Persona entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, PersonaFormlyOutput.class);
  }

  public Persona convert(PersonaFormlyInput input) {
    if (input == null) {
      return null;
    }

    return modelMapper.map(input, Persona.class);
  }

}
