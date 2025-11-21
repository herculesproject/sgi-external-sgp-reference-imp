package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.sgp.model.CategoriaProfesional;

/**
 * CategoriaProfesionalNotFoundException
 */
public class CategoriaProfesionalNotFoundException extends SgpNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public CategoriaProfesionalNotFoundException(String id) {
    super(id, CategoriaProfesional.class);
  }

}
