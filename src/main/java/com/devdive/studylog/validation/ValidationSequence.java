package com.devdive.studylog.validation;

import jakarta.validation.GroupSequence;
import jakarta.validation.groups.Default;

import static com.devdive.studylog.validation.ValidationGroups.*;

@GroupSequence({First.class, Second.class, Third.class, Default.class})
public class ValidationSequence {
}
