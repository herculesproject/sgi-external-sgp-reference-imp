package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.sgp.model.Persona;

/**
 * PersonaNotFoundException
 */
public class PersonaNotFoundException extends SgpNotFoundException {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public PersonaNotFoundException(String id) {
    super(id, Persona.class);
  }

}
