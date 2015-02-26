package net.chernandez.example.controller.common.datamodel;

import net.chernandez.example.business.common.ISubsidiaryBO;
import net.chernandez.example.spi.dto.SubsidiaryDTO;
import net.chernandez.example.spi.search.SubsidiarySearchTerms;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Component
public class SubsidiaryDataModel extends CustomPagingModel<SubsidiaryDTO> {

    @Resource
    private ISubsidiaryBO subsidiaryBO;

    @Override
    protected String getId(SubsidiaryDTO subsidiary) {
        return subsidiary.getId().toString();
    }

    @Override
    protected int countData(Map<String, String> filters) {
        SubsidiarySearchTerms searchTerms = createSearchTerms(filters);
        return (int) subsidiaryBO.countSubsidiaries(searchTerms);
    }

    @Override
    protected List<SubsidiaryDTO> fetchData(Pageable pageable, Map<String, String> filters) {
        SubsidiarySearchTerms searchTerms = createSearchTerms(filters);
        return subsidiaryBO.findSubsidiaries(pageable, searchTerms);
    }

    private SubsidiarySearchTerms createSearchTerms(Map<String, String> filters) {
        SubsidiarySearchTerms searchTerms = new SubsidiarySearchTerms();
        searchTerms.setActive(true);
        searchTerms.setName(filters.get("name"));
        searchTerms.setDescription(filters.get("description"));
        return searchTerms;
    }
}
