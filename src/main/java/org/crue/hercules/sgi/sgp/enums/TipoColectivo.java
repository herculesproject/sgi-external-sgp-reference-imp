package org.crue.hercules.sgi.sgp.enums;

public enum TipoColectivo {
  /** COM */
  DESTINATARIO_COMUNICADO("DESTINATARIO_COMUNICADO"),

  /** CSP */
  /** Persona autorizada grupo */
  PERSONA_AUTORIZADA_GRUPO("PERSONA_AUTORIZADA_GRUPO"),
  /** Responsable economico */
  RESPONSABLE_ECONOMICO_CSP("RESPONSABLE_ECONOMICO_CSP"),
  /** Responsable proyecto externo */
  RESPONSABLE_PROYECTO_EXTERNO("RESPONSABLE_PROYECTO_EXTERNO"),
  /** Solicitante */
  SOLICITANTE_CSP("SOLICITANTE_CSP"),
  /** Tutor */
  TUTOR_CSP("TUTOR_CSP"),

  /** EER */
  /** Miembro equipo empresa explotacion resultados */
  MIEMBRO_EQUIPO_EMPRESA_EXPLOTACION_RESULTADOS("MIEMBRO_EQUIPO_EMPRESA_EXPLOTACION_RESULTADOS"),

  /** ETI */
  /** Equipo trabajo */
  EQUIPO_TRABAJO_ETICA("EQUIPO_TRABAJO_ETICA"),
  /** Evaluador */
  EVALUADOR_ETICA("EVALUADOR_ETICA"),
  /** Solicitante */
  SOLICITANTE_ETICA("SOLICITANTE_ETICA"),

  /** PII */
  /** Autor invencion */
  AUTOR_INVENCION("AUTOR_INVENCION"),

  /** PRC */
  /** Autor */
  AUTOR_PRC("AUTOR_PRC");

  private String code;

  private TipoColectivo(String code) {
    this.code = code;
  }

  public String getCode() {
    return code;
  }

  public static TipoColectivo fromCode(String code) {
    for (TipoColectivo property : TipoColectivo.values()) {
      if (property.code.equals(code)) {
        return property;
      }
    }
    return null;
  }

}
