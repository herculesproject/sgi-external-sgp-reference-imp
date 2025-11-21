package org.crue.hercules.sgi.sgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.crue.hercules.sgi.sgp.enums.TipoColectivo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = ColectivoTipoColectivo.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ColectivoTipoColectivo extends BaseEntity {

  protected static final String TABLE_NAME = "colectivo_tipocolectivo";

  private static final String COLECTIVO_ID_COLUMN_NAME = "colectivo_id";
  private static final String TIPO_COLECTIVO_COLUMN_NAME = "tipo_colectivo";

  @Id
  @Column(name = COLECTIVO_ID_COLUMN_NAME)
  private String colectivoId;

  @Id
  @Column(name = TIPO_COLECTIVO_COLUMN_NAME)
  @Enumerated(EnumType.STRING)
  private TipoColectivo tipoColectivo;

}
