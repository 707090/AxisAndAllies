package validation;

import java.util.Collection;

public interface Validator<T> {

	public Collection<Throwable> validate(T type);
	
}
