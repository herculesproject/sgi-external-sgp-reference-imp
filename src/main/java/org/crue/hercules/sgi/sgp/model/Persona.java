package org.crue.hercules.sgi.sgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = Persona.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Persona extends BaseEntity {

  protected static final String TABLE_NAME = "persona";

  private static final String ID_COLUMN_NAME = "id";
  private static final String NOMBRE_COLUMN_NAME = "nombre";
  private static final String APELLIDOS_COLUMN_NAME = "apellidos";
  private static final String SEXO_ID_COLUMN_NAME = "sexo_id";
  private static final String NUMERO_DOCUMENTO_COLUMN_NAME = "numero_documento";
  private static final String TIPO_DOCUMENTO_ID_COLUMN_NAME = "tipo_documento_id";
  private static final String EMPRESA_REF_COLUMN_NAME = "empresa_ref";
  private static final String ENTIDAD_PROPIA_REF_COLUMN_NAME = "empresa_propia_ref";
  private static final String PERSONAL_PROPIO_COLUMN_NAME = "personal_propio";
  private static final String COLECTIVO_COLUMN_NAME = "colectivo_id";
  private static final String EMAILS_COLUMN_NAME = "emails";

  /** Id */
  @Id
  @Column(name = ID_COLUMN_NAME, nullable = false)
  @NotBlank
  private String id;

  /** Nombre */
  @Column(name = NOMBRE_COLUMN_NAME)
  @NotBlank
  private String nombre;

  /** Apellidos */
  @Column(name = APELLIDOS_COLUMN_NAME)
  @NotBlank
  private String apellidos;

  /** Sexo */
  @ManyToOne
  @JoinColumn(name = SEXO_ID_COLUMN_NAME, nullable = true)
  private Sexo sexo;

  /** Numero de documento */
  @Column(name = NUMERO_DOCUMENTO_COLUMN_NAME)
  @NotBlank
  private String numeroDocumento;

  /** Tipo de documento */
  @ManyToOne
  @JoinColumn(name = TIPO_DOCUMENTO_ID_COLUMN_NAME, nullable = false)
  @NotNull
  private TipoDocumento tipoDocumento;

  /** Empresa Ref */
  @Column(name = EMPRESA_REF_COLUMN_NAME)
  private String empresaRef;

  /** Empresa propia Ref */
  @Column(name = ENTIDAD_PROPIA_REF_COLUMN_NAME)
  private String entidadPropiaRef;

  /** Personal propio */
  @Column(name = PERSONAL_PROPIO_COLUMN_NAME)
  private boolean personalPropio;

  /** Colectivo */
  @Column(name = COLECTIVO_COLUMN_NAME)
  @NotNull
  private String colectivoId;

  /** Emails */
  @Column(name = EMAILS_COLUMN_NAME)
  @NotBlank
  private String email;

}
