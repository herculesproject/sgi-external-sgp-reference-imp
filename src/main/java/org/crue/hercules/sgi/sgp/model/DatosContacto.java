package org.crue.hercules.sgi.sgp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@Table(name = DatosContacto.TABLE_NAME)
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DatosContacto extends BaseEntity {

  protected static final String TABLE_NAME = "datos_contacto";

  private static final String PERSONA_ID_COLUMN_NAME = "persona_id";
  private static final String PAIS_CONTACTO_REF_COLUMN_NAME = "pais_contacto_ref";
  private static final String COMUNIDAD_CONTACTO_REF_COLUMN_NAME = "comunidad_contacto_ref";
  private static final String PROVINCIA_CONTACTO_REF_COLUMN_NAME = "provincia_contacto_ref";
  private static final String CIUDAD_CONTACTO_COLUMN_NAME = "ciudad_contacto";
  private static final String DIRECCION_CONTACTO_COLUMN_NAME = "direccion_contacto";
  private static final String CODIGO_POSTAL_CONTACTO_COLUMN_NAME = "codigo_postal_contacto";
  private static final String EMAILS_COLUMN_NAME = "emails";
  private static final String TELEFONOS_COLUMN_NAME = "telefonos";
  private static final String MOVILES_COLUMN_NAME = "moviles";

  /** Persona Id */
  @Id
  @Column(name = PERSONA_ID_COLUMN_NAME)
  private String personaId;

  /** Pais contacto */
  @Column(name = PAIS_CONTACTO_REF_COLUMN_NAME)
  private String paisContactoRef;

  /** Comunidad autonoma contacto */
  @Column(name = COMUNIDAD_CONTACTO_REF_COLUMN_NAME)
  private String comAutonomaContactoRef;

  /** Provincia contacto */
  @Column(name = PROVINCIA_CONTACTO_REF_COLUMN_NAME)
  private String provinciaContactoRef;

  /** Ciudad contacto */
  @Column(name = CIUDAD_CONTACTO_COLUMN_NAME)
  private String ciudadContacto;

  /** Direccion contacto */
  @Column(name = DIRECCION_CONTACTO_COLUMN_NAME)
  private String direccionContacto;

  /** Codigo postal contacto */
  @Column(name = CODIGO_POSTAL_CONTACTO_COLUMN_NAME)
  private String codigoPostalContacto;

  /** Emails */
  @Column(name = EMAILS_COLUMN_NAME)
  private String emails;

  /** Telefonos fijos */
  @Column(name = TELEFONOS_COLUMN_NAME)
  private String telefonos;

  /** Telefonos moviles */
  @Column(name = MOVILES_COLUMN_NAME)
  private String moviles;

}
