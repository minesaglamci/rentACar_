package rentacar.core.utilities.results;

import org.springframework.data.domain.Sort.Order;
import org.springframework.data.util.Streamable;

public class DataResult<T> extends Result {
	T data;
	
	public DataResult(T data, boolean success, String message) {
		super(success, message);
		this.data = data;
	}
	
	public DataResult(T data, boolean success) {
		super(success);
		this.data = data;
	}
	
	public T getData() {
		return this.data;
	}

	public Object stream() {
		// TODO Auto-generated method stub
		return null;
	}

}
