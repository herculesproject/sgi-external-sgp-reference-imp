package org.crue.hercules.sgi.sgp.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sgp.util.Constantes;
import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ListStringToStringConverter extends AbstractConverter<List<String>, String> {

  @Override
  protected String convert(List<String> source) {
    if (source == null) {
      return "";
    }
    return source.stream()
        .collect(Collectors.joining(Constantes.LIST_STRING_SEPARATOR));
  }

}
