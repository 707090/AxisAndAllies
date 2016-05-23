package model.transform;

public interface Mapper<T, R> {

	public R mapType(T type);
	public T unmapType(R type);
	
}
