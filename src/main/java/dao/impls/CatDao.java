package dao.impls;

import dao.BaseDao;
import dto.*;
import org.jooq.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import src.main.tables.Cats;
import src.main.tables.User;
import src.main.tables.records.CatsRecord;
import src.main.tables.records.UserRecord;

import java.util.*;

import static org.jooq.impl.DSL.multiset;
import static org.jooq.impl.DSL.selectFrom;
import static src.main.tables.Cats.CATS;
import static src.main.tables.User.USER;

@Repository
public class CatDao implements BaseDao<CatDto> {
    @Autowired
    private DSLContext dsl;
    private final Logger log = LoggerFactory.getLogger(UserDao.class);
    public List<CatDto> findAll() {

        Result<Record6<Integer, String, String, Integer, String, String>> fetch = dsl.select(CATS.ID, CATS.NAME, CATS.BREED, USER.ID, USER.NAME, USER.EMAIL)
                .from(CATS)
                .leftJoin(USER)
                .on(CATS.USER_ID.eq(USER.ID))
                .limit(100)
                .fetch();
        List<CatDto> cats = mapIntoDto(fetch);
        log.info("Found total {} cats", cats.size());
        return cats;
    }

    private List<CatDto> mapIntoDto(Result<Record6<Integer, String, String, Integer, String, String>> fetch) {
        List<CatDto> cats = new ArrayList<>();
        for (Record6<Integer, String, String, Integer, String, String> result : fetch) {
            cats.add(CatDto.builder()
                    .id(result.getValue(CATS.ID))
                    .name(result.getValue(CATS.NAME))
                    .breed(result.getValue(CATS.BREED))
                    .user(
                            UserDto.builder()
                            .id(result.getValue(USER.ID))
                            .name(result.getValue(USER.NAME))
                            .email(result.getValue(USER.EMAIL))
                            .build()
                    )
                    .build());

        }
        return cats;
    }

    @Override
    public Integer save(CatDto catDto) {
        Integer id = dsl.insertInto(CATS).columns(CATS.NAME, CATS.BREED, CATS.USER_ID)
                .values(catDto.getName(), catDto.getBreed(), catDto.getUser().getId())
                .returningResult(CATS.ID)
                .fetchOne()
                .into(Integer.class);
        log.info("Inserted {} cat with id {}", catDto.getName(),id);
        return id;
    }

    @Override
    public void update(CatDto catDto) {
        dsl.update(CATS)
                .set(CATS.NAME, catDto.getName())
                .set(CATS.BREED, catDto.getBreed())
                .where(CATS.ID.eq(catDto.getId()))
                .execute();
    }

    @Override
    public void delete(Integer id) {
        dsl.delete(CATS)
                .where(CATS.ID.eq(id))
                .execute();
        log.info("Deleted cat with id {}",id);
    }

    public List<CatDto> findByUserId(Integer userId) {

        Result<Record6<Integer, String, String, Integer, String, String>> fetch = dsl.select(CATS.ID, CATS.NAME, CATS.BREED, USER.ID, USER.NAME, USER.EMAIL)
                .from(CATS)
                .innerJoin(USER)
                .on(CATS.USER_ID.eq(USER.ID))
                .and(CATS.USER_ID.eq(userId))
                .fetch();
        List<CatDto> cats = mapIntoDto(fetch);
        log.info("Found {} cats for user with id {}", cats.size(), userId);
        return cats;
    }
}
