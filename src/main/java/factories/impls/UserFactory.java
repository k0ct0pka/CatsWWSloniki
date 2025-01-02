package factories.impls;

import dao.BaseDao;
import dto.UserDto;
import factories.BaseFactory;

import java.util.Map;

public class UserFactory implements BaseFactory<UserDto> {
    public UserDto build(Map<String, String> parameters) {
        return UserDto.builder()
                .id(parameters.get("id")==null?null:Integer.parseInt(parameters.get("id")))
                .name(parameters.get("name"))
                .email(parameters.get("email"))
                .password(parameters.get("password"))
                .build();
    }
}
