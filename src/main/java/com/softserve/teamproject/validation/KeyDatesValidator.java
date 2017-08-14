package com.softserve.teamproject.validation;

import com.softserve.teamproject.dto.KeyDateDto;
import com.softserve.teamproject.entity.EventType;
import com.softserve.teamproject.entity.Group;
import com.softserve.teamproject.entity.Template;
import com.softserve.teamproject.entity.User;
import com.softserve.teamproject.repository.UserRepository;
import com.softserve.teamproject.service.MessageByLocaleService;
import com.softserve.teamproject.service.SecurityService;
import java.time.LocalDate;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * Class made to generate valid key dates according to generation strategy and validate incoming
 * events
 */
@Service
public class KeyDatesValidator implements
    ConstraintValidator<KeyDates, KeyDateDto> {

  private UserRepository userRepository;
  private SecurityService securityService;
  private Group group;
  private MessageByLocaleService messageByLocaleService;
  private Map<EventType, LocalDate> validationTemplate = new HashMap<>();
  @Value("${user.teacher}")
  private String teacher;
  @Value("${user.coordinator}")
  private String coordinator;

  @Autowired
  public void setMessageByLocaleService(
      MessageByLocaleService messageByLocaleService) {
    this.messageByLocaleService = messageByLocaleService;
  }

  @Autowired
  public void setUserRepository(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Autowired
  public void setSecurityService(SecurityService securityService) {
    this.securityService = securityService;
  }

  @Override
  public void initialize(KeyDates constraintAnnotation) {
  }

  @Override
  public boolean isValid(KeyDateDto value, ConstraintValidatorContext context) {
    try {
      validateKeyDate(value);
      return true;
    } catch (IllegalArgumentException e) {
      setNewMessage(e.getMessage(), context);
      return false;
    }
  }

  /**
   * Generate dates, which are pointing to demo week
   *
   * @param group the group to generate dates for
   */
  private void generateDates(Group group) {
    if (group.getStartDate() == null) {
      throw new IllegalArgumentException(
          messageByLocaleService.getMessage("illegalArgs.keyDate.generateDate.start"));
    }
    LocalDate startDate = group.getStartDate();
    List<Template> templates = group.getSpecialization().getStrategy().getTemplates();
    for (Template template : templates) {
      LocalDate newDate = null;
      if (template.getDuration() == 0) {
        newDate = group.getFinishDate();
      } else {
        newDate = startDate.plusDays(template.getDuration());
      }

      validationTemplate.put(template.getEventType(), newDate);
      startDate = newDate;
    }
  }

  /**
   * Validates input event according to <code>validDates</code> parameter
   *
   * @param event event to validate
   * @throws IllegalArgumentException if event type is incorrect or date is invalid
   */
  private void validateKeyDate(KeyDateDto event) {
    updateTemplate(event);
    TemporalField temporalField = WeekFields.of(Locale.US).dayOfWeek();
    if (event.getEventType() == null || !validationTemplate.containsKey(event.getEventType())) {
      throw new IllegalArgumentException(
          messageByLocaleService.getMessage("illegalArgs.keyDate.validate.eventType"));
    }
    LocalDate startOfWeek = validationTemplate.get(event.getEventType()).with(temporalField, 1);
    LocalDate endOfWeek = startOfWeek.with(temporalField, 7);
    LocalDate eventDate = event.getDate();
    if (!(eventDate.isAfter(startOfWeek) && eventDate.isBefore(endOfWeek))) {
      throw new IllegalArgumentException(
          messageByLocaleService.getMessage("illegalArgs.keyDate.validate.date"));
    }
  }

  private void updateTemplate(KeyDateDto event) {
    if (event.getGroup() == null) {
      throw new IllegalArgumentException(
          messageByLocaleService.getMessage("illegalArgs.keyDate.event.group"));
    }
    if (group == null || !event.getGroup().equals(group)) {
      group = event.getGroup();
      checkAuth();
      generateDates(event.getGroup());
    }
  }

  private void setNewMessage(String message, ConstraintValidatorContext context) {
    context.disableDefaultConstraintViolation();
    context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
  }

  private void checkAuth() {
    User currentUser = userRepository.getUserByNickName(securityService.findLoggedInUsername());
    if ((currentUser.getRole().getName().equals(coordinator)
        && currentUser.getLocation() != group.getLocation())
        || (currentUser.getRole().getName().equals(teacher)
        && !group.getTeachers().contains(currentUser))) {
      throw new IllegalArgumentException(
          messageByLocaleService.getMessage("auth.error.access.denied"));
    }
  }
}
