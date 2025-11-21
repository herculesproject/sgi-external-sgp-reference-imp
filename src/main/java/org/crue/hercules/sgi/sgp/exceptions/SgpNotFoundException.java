package org.crue.hercules.sgi.sgp.exceptions;

import org.crue.hercules.sgi.framework.exception.NotFoundException;
import org.crue.hercules.sgi.framework.problem.message.ProblemMessage;
import org.crue.hercules.sgi.framework.spring.context.support.ApplicationContextSupport;

/**
 * SgpNotFoundException
 */
public class SgpNotFoundException extends NotFoundException {

  private static final String PROBLEM_MESSAGE_PARAMETER_ENTITY = "entity";
  private static final String MESSAGE_KEY_ID = "id";

  /**
   * Serial version
   */
  private static final long serialVersionUID = 1L;

  public SgpNotFoundException(String message) {
    super(message);
  }

  public SgpNotFoundException(Long id, Class<?> clazz) {
    super(ProblemMessage.builder().key(SgpNotFoundException.class)
        .parameter(PROBLEM_MESSAGE_PARAMETER_ENTITY, ApplicationContextSupport.getMessage(clazz))
        .parameter(MESSAGE_KEY_ID, id).build());
  }

  public SgpNotFoundException(String id, Class<?> clazz) {
    super(ProblemMessage.builder().key(SgpNotFoundException.class)
        .parameter(PROBLEM_MESSAGE_PARAMETER_ENTITY, ApplicationContextSupport.getMessage(clazz))
        .parameter(MESSAGE_KEY_ID, id).build());
  }

}
