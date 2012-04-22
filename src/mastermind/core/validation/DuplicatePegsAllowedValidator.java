package mastermind.core.validation;

import mastermind.core.Code;
import mastermind.core.ColorPeg;

/**
 * 
 * @author Andrew Church
 * Class that implements ICodeValidator to validate codes using basic logic
 *
 */
public class DuplicatePegsAllowedValidator implements ICodeValidator {

	@Override
	public Boolean validate(Code c) 
	{
		return !c.isEmpty();
	}

}
