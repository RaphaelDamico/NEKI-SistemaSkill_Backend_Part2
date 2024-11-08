package br.com.neki.sistema_skill_refactored.core.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

@GroupSequence({Default.class, UsernameGroup.class, PasswordGroup.class})
public interface ValidationSequence {

}
