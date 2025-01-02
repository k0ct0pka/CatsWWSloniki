package dto;

import dao.impls.UserDao;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
public class CatDto {
    Integer id;
    String name;
    String breed;
    String image;
    UserDto user;
}
