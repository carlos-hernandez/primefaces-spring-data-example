package net.chernandez.example.business.util.mapper;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;

@Component("mapperFacade")
public class SpringConfigurableMapper extends ConfigurableMapper {
    /**
     * This is necessary to override the default auto-initialization of the parent class
     */
    public SpringConfigurableMapper() {
        super(false);
    }

    /**
     * Only after the bean is initialized we can scan the Spring context to configure Orika.
     */
    @PostConstruct
    protected void initSpringMappingConfig() {
        init();
    }

    @Override
    protected void configure(MapperFactory factory) {
        super.configure(factory);
        addSpringMappers(factory);
        addSpringConverter(factory);
    }

    private void addSpringMappers(final MapperFactory factory) {
        Map<String, Mapper> mappers = applicationContext.getBeansOfType(Mapper.class);
        mappers.values().forEach(factory::registerMapper);
    }

    private void addSpringConverter(final MapperFactory factory) {
        Map<String, Converter> converters = applicationContext.getBeansOfType(Converter.class);
        for (Converter converter : converters.values()) {
            factory.getConverterFactory().registerConverter(converter);
        }
    }

    @Resource
    private ApplicationContext applicationContext;
}
