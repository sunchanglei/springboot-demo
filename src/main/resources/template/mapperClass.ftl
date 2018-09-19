package ${packageName}.mapper;

@Mapper
public interface ${className?cap_first}Mapper extends BaseMapper<${className?cap_first}Bo>{

    public List<${className?cap_first}Bo> listByParam(@Param("param") String param);

}