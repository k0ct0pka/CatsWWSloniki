package factories.impls;

import dto.CatDto;
import dto.UserDto;
import factories.BaseFactory;

import java.util.Map;

public class CatFactory implements BaseFactory<CatDto> {
    @Override
    public CatDto build(Map<String, String> params) {
        return CatDto.builder()
                .id(params.get("id")==null?null:Integer.parseInt(params.get("id")))
                .name(params.get("name"))
                .breed(params.get("breed"))
                .build();
    }
}
