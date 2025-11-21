package org.crue.hercules.sgi.sgp.converter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.AbstractConverter;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StringToListStringsConverter extends AbstractConverter<String, List<String>> {

  @Override
  protected List<String> convert(String source) {
    if (source == null) {
      return Collections.emptyList();
    }
    return Arrays.asList(source.split(";"))
        .stream()
        .filter(e -> !e.trim().isEmpty())
        .collect(Collectors.toList());
  }

}
