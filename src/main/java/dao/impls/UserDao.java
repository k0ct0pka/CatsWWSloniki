package dao.impls;

import com.zaxxer.hikari.HikariDataSource;
import dao.BaseDao;
import dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.slf4j.Logger;
import org.springframework.stereotype.Repository;

import static src.main.tables.User.USER;

@Repository
@RequiredArgsConstructor
public class UserDao implements BaseDao<UserDto> {
    private final DSLContext dsl;
    private Logger log;

    @Override
    public void save(UserDto user) {
        dsl.insertInto(USER)
                .columns(USER.NAME, USER.EMAIL, USER.PASSWORD)
                .values(user.getName(), user.getEmail(), user.getPassword())
                .execute();
        log.info("user {} inserted", user);
    }

    @Override
    public void update(UserDto userDto) {
        dsl.update(USER)
                .set(USER.NAME, userDto.getName())
                .set(USER.EMAIL, userDto.getEmail())
                .set(USER.PASSWORD, userDto.getPassword())
                .where(USER.ID.eq(userDto.getId()))
                .execute();
        log.info("user {} updated became {}", userDto.getId(), userDto);
    }

    @Override
    public void delete(UserDto userDto) {
        dsl.delete(USER)
                .where(USER.ID.eq(userDto.getId()))
                .execute();
        log.info("user {} deleted", userDto.getId());
    }
    public UserDto findByEmail(String email) {
        UserDto user;
        try{
             user = dsl.select(USER.ID, USER.NAME, USER.EMAIL, USER.PASSWORD)
                     .from(USER)
                     .where(USER.EMAIL.eq(email)).fetchOne()
                     .into(UserDto.class);
        } catch (NullPointerException e){
            log.info("user {} not found", email);
            throw new RuntimeException("user " + email + " not found");
        }
        return user;
    }
}
