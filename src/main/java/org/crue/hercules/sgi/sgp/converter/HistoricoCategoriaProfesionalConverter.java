package org.crue.hercules.sgi.sgp.converter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.crue.hercules.sgi.sgp.dto.HistoricoCategoriaProfesionalOutput;
import org.crue.hercules.sgi.sgp.model.HistoricoCategoriaProfesional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class HistoricoCategoriaProfesionalConverter {

  private final ModelMapper modelMapper;

  public HistoricoCategoriaProfesionalOutput convert(HistoricoCategoriaProfesional entity) {
    if (entity == null) {
      return null;
    }

    return modelMapper.map(entity, HistoricoCategoriaProfesionalOutput.class);
  }

  public List<HistoricoCategoriaProfesionalOutput> convert(List<HistoricoCategoriaProfesional> listEntities) {
    if (listEntities == null) {
      return new ArrayList<>();
    }

    return listEntities.stream().map(this::convert).collect(Collectors.toList());
  }

}
