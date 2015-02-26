package net.chernandez.example.business.common.impl;

import com.mysema.query.BooleanBuilder;
import com.mysema.query.types.Predicate;
import ma.glasnost.orika.MapperFacade;
import net.chernandez.example.business.common.ISubsidiaryBO;
import net.chernandez.example.model.common.Subsidiary;
import net.chernandez.example.repositories.common.SubsidiaryRepository;
import net.chernandez.example.spi.dto.SubsidiaryDTO;
import net.chernandez.example.spi.search.SubsidiarySearchTerms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

import static net.chernandez.example.business.common.predicates.SubsidiaryPredicates.*;
import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.trimToEmpty;

@Service
public class SubsidiaryBO implements ISubsidiaryBO {

    @Resource
    private SubsidiaryRepository subsidiaryRepository;

    @Resource
    private MapperFacade mapper;

    @Override
    public long countSubsidiaries(SubsidiarySearchTerms searchTerms) {
        Predicate search = constructSearchQuery(searchTerms);
        return subsidiaryRepository.count(search);
    }

    @Override
    public List<SubsidiaryDTO> findSubsidiaries(Pageable pageable, SubsidiarySearchTerms searchTerms) {
        Predicate search = constructSearchQuery(searchTerms);
        Page<Subsidiary> page = subsidiaryRepository.findAll(search, pageable);
        return generateDTOs(page.getContent());
    }

    private Predicate constructSearchQuery(SubsidiarySearchTerms searchTerms) {
        BooleanBuilder mainQuery = new BooleanBuilder();
        mainQuery.and(isActive(searchTerms.isActive()));

        String name = searchTerms.getName();
        if (isNotBlank(name)) {
            mainQuery.and(nameContains(trimToEmpty(name)));
        }

        String description = searchTerms.getDescription();
        if (isNotBlank(description)) {
            mainQuery.and(descriptionContains(description));
        }

        return mainQuery;
    }

    private List<SubsidiaryDTO> generateDTOs(List<Subsidiary> subsidiaries) {
        List<SubsidiaryDTO> dtos = new ArrayList<>();
        for (Subsidiary subsidiary : subsidiaries) {
            dtos.add(mapper.map(subsidiary, SubsidiaryDTO.class));
        }

        return dtos;
    }
}
