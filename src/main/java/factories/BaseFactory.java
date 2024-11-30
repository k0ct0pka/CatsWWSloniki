package factories;

import java.util.Map;

public interface BaseFactory<T>{
    T build(Map<String, String> params);
}
