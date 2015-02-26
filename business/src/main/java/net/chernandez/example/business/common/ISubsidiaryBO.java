package net.chernandez.example.business.common;

import net.chernandez.example.spi.dto.SubsidiaryDTO;
import net.chernandez.example.spi.search.SubsidiarySearchTerms;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ISubsidiaryBO {

    long countSubsidiaries(SubsidiarySearchTerms searchTerms);

    List<SubsidiaryDTO> findSubsidiaries(Pageable pageable, SubsidiarySearchTerms searchTerms);
}
