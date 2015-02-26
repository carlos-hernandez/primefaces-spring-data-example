package net.chernandez.example.controller.common;

import net.chernandez.example.spi.dto.SubsidiaryDTO;
import org.primefaces.model.LazyDataModel;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class SubsidiaryController {

    @ManagedProperty(value = "#{subsidiaryDataModel}")
    private LazyDataModel<SubsidiaryDTO> dataModel;

    public LazyDataModel<SubsidiaryDTO> getDataModel() {
        return dataModel;
    }

    public void setDataModel(LazyDataModel<SubsidiaryDTO> dataModel) {
        this.dataModel = dataModel;
    }
}
