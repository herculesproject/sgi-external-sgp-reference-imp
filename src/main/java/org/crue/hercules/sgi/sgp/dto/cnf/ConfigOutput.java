package org.crue.hercules.sgi.sgp.dto.cnf;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ConfigOutput implements Serializable {
  private String id;
  private String nombre;
  private String description;
  private String value;
}
