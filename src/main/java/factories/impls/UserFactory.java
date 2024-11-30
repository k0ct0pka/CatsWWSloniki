package factories.impls;

import dao.BaseDao;
import dto.UserDto;
import factories.BaseFactory;

import java.util.Map;

public class UserFactory implements BaseFactory<UserDto> {
    public UserDto build(Map<String, String> parameters) {
        return UserDto.builder()
                .name(parameters.get("name"))
                .email(parameters.get("email"))
                .password(parameters.get("password"))
                .build();
    }
}
