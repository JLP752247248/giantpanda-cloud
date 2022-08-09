import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


/**
 * @author [ your email ]
 * @date 2022-08-09 11:00:58
 * Created by CodeGen .
 */
@RestController
@RequestMapping
public class __model__s {

    @Autowired
    private __model__WriteService __model__WriteService;

    @Autowired
    private __model__ReadService __model__ReadService;

    /**
     * 查询
     *
     * @param id
     * @return
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public __model__ find__model__(@PathVariable Long id) {
        return __model__ReadService.findById(id);
    }

    /**
     * 创建
     *
     * @param __model__ * @return
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public Long create__model__(@RequestBody __model__ __model__) {
        return __model__WriteService.create(__model__);
    }
}