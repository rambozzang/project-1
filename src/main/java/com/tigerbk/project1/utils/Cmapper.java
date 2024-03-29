package com.tigerbk.project1.utils;


import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.convention.NameTokenizers;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class Cmapper {
    private final ModelMapper  mp = new ModelMapper();
    @Bean
    public ModelMapper standardMapper(){
        mp.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE)
                .setSkipNullEnabled(true)
                .setFieldMatchingEnabled(true)
                .setSourceNameTokenizer(NameTokenizers.UNDERSCORE)
                .setDestinationNameTokenizer(NameTokenizers.UNDERSCORE)
                .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
        return mp;
    }

    public <D> D run(Object source, Class<D> destinationType) {
        return standardMapper().map(source, destinationType);
    }
}


