package ${packageName}.config;

@Component
public class Config implements InitializingBean{

	<#list properties as field>
	/** ${field.comment} */
	@Value("${r"${"}${field.colName}${r"}"}")
	private String ${field.colNameCamelCase};
	/** ${field.comment} */
	public static String ${field.colNameFinal};
	</#list>
	@Override
	public void afterPropertiesSet() throws Exception {
		<#list properties as field>
		/** ${field.comment} */
		${field.colNameFinal} = ${field.colNameCamelCase};
		</#list>
	}
}