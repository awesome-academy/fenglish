package vn.framgia.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneValidator implements ConstraintValidator<PhoneConstraint, String> {
	
	@Override
	public void initialize(PhoneConstraint constraintAnnotation) {
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String contactField, ConstraintValidatorContext context) {
		return contactField != null && contactField.matches("^(01[2689]|09)[0-9]{8}$")
				&& contactField.length() > 9 && contactField.length() < 12;
	}

}
