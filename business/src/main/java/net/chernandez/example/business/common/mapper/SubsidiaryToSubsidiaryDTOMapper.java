package net.chernandez.example.business.common.mapper;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import net.chernandez.example.model.common.Subsidiary;
import net.chernandez.example.spi.dto.SubsidiaryDTO;
import org.springframework.stereotype.Component;

@Component
public class SubsidiaryToSubsidiaryDTOMapper extends CustomMapper<Subsidiary, SubsidiaryDTO> {

    @Override
    public void mapAtoB(Subsidiary subsidiary, SubsidiaryDTO subsidiaryDTO, MappingContext context) {
        subsidiaryDTO.setId(subsidiary.getId());
        subsidiaryDTO.setName(subsidiary.getName());
        subsidiaryDTO.setDescription(subsidiary.getDescription());
        subsidiaryDTO.setActive(subsidiary.isActive());
    }

    @Override
    public void mapBtoA(SubsidiaryDTO subsidiaryDTO, Subsidiary subsidiary, MappingContext context) {
        subsidiary.setId(subsidiaryDTO.getId());
        subsidiary.setName(subsidiaryDTO.getName());
        subsidiary.setDescription(subsidiaryDTO.getDescription());
        subsidiary.setActive(subsidiaryDTO.isActive());
    }
}
