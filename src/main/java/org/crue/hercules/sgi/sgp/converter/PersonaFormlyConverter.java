package org.crue.hercules.sgi.sgp.converter;

import javax.annotation.PostConstruct;

import org.crue.hercules.sgi.sgp.dto.PersonaFormlyInput;
import org.crue.hercules.sgi.sgp.dto.PersonaFormlyOutput;
import org.crue.hercules.sgi.sgp.model.PersonaFormlyRequest;
import org.crue.hercules.sgi.sgp.model.PersonaFormlyResponse;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PersonaFormlyConverter {

  private final ModelMapper modelMapper;
  private final EmailsConverter emailsConverter;
  private final ListStringToStringConverter listStringToStringConverter;

  @PostConstruct
  public void mapperConfig() {
    Condition<String, String> stringNotEmpty = context -> context.getSource() != null
        && !"".equals(context.getSource().trim());

    modelMapper.typeMap(PersonaFormlyResponse.class, PersonaFormlyOutput.class)
        .addMappings(
            mapper -> mapper.using(emailsConverter).map(PersonaFormlyResponse::getEmails,
                PersonaFormlyOutput::setEmails));

    modelMapper.typeMap(PersonaFormlyInput.class, PersonaFormlyRequest.class)
        .addMappings(
            mapper -> mapper.when(stringNotEmpty).map(PersonaFormlyInput::getPersonaId,
                PersonaFormlyRequest::setId))
        .addMappings(mapper -> mapper.using(listStringToStringConverter)
            .map(PersonaFormlyInput::getEmails, PersonaFormlyRequest::setEmails))
        .addMappings(mapper -> mapper.using(listStringToStringConverter)
            .map(PersonaFormlyInput::getMoviles, PersonaFormlyRequest::setMoviles))
        .addMappings(mapper -> mapper.using(listStringToStringConverter)
            .map(PersonaFormlyInput::getTelefonos, PersonaFormlyRequest::setTelefonos));
  }

  public PersonaFormlyOutput convert(PersonaFormlyResponse entity) {
    return entity == null ? null : modelMapper.map(entity, PersonaFormlyOutput.class);
  }

  public PersonaFormlyRequest convert(PersonaFormlyInput input) {
    return input == null ? null : modelMapper.map(input, PersonaFormlyRequest.class);
  }

}
