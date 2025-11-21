package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.sgp.model.NivelAcademico;

/**
 * NivelAcademicoNotFoundException
 */
public class NivelAcademicoNotFoundException extends SgpNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public NivelAcademicoNotFoundException(String id) {
    super(id, NivelAcademico.class);
  }

}
