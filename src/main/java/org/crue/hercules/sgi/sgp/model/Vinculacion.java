package org.crue.hercules.sgi.sgp.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = Vinculacion.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vinculacion extends BaseEntity {

  protected static final String TABLE_NAME = "vinculacion";

  private static final String PERSONA_ID_COLUMN_NAME = "persona_id";
  private static final String CATEGORIA_PROFESIONAL_COLUMN_NAME = "categoria_profesional_id";
  private static final String FECHA_OBTENCION_CATEGORIA_COLUMN_NAME = "fecha_obtencion_categoria";
  private static final String DEPARTAMENTO_REF_COLUMN_NAME = "departamento_ref";
  private static final String CENTRO_REF_COLUMN_NAME = "centro_ref";
  private static final String AREA_CONOCIMIENTO_REF_COLUMN_NAME = "area_conocimiento_ref";
  private static final String EMPRESA_REF_COLUMN_NAME = "empresa_ref";
  private static final String PERSONAL_PROPIO_COLUMN_NAME = "personal_propio";
  private static final String ENTIDAD_PROPIA_REF_COLUMN_NAME = "entidad_propia_ref";

  /** Persona Id */
  @Id
  @Column(name = PERSONA_ID_COLUMN_NAME)
  private String personaId;

  /** Categoria profesional */
  @ManyToOne
  @JoinColumn(name = CATEGORIA_PROFESIONAL_COLUMN_NAME)
  private CategoriaProfesional categoriaProfesional;

  /** Fecha obtencion categoria */
  @Column(name = FECHA_OBTENCION_CATEGORIA_COLUMN_NAME)
  private LocalDate fechaObtencionCategoria;

  /** Departamento Ref */
  @Column(name = DEPARTAMENTO_REF_COLUMN_NAME)
  private String departamentoRef;

  /** Centro Ref */
  @Column(name = CENTRO_REF_COLUMN_NAME)
  private String centroRef;

  /** Area de conocimiento Ref */
  @Column(name = AREA_CONOCIMIENTO_REF_COLUMN_NAME)
  private String areaConocimientoRef;

  /** Empresa Ref */
  @Column(name = EMPRESA_REF_COLUMN_NAME)
  private String empresaRef;

  /** Personal propio */
  @Column(name = PERSONAL_PROPIO_COLUMN_NAME)
  private Boolean personalPropio;

  /** Entidad propia Ref */
  @Column(name = ENTIDAD_PROPIA_REF_COLUMN_NAME)
  private String entidadPropiaRef;

}
