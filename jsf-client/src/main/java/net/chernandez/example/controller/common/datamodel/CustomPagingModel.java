package net.chernandez.example.controller.common.datamodel;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public abstract class CustomPagingModel<T> extends LazyDataModel<T> {
    private List<T> dataSource;
    private int pageSize;
    private int rowIndex;
    private int rowCount;

    public CustomPagingModel() {
        super();
    }

    protected abstract String getId(T entity);

    protected abstract int countData(Map<String, String> filters);

    protected abstract List<T> fetchData(Pageable pageable, Map<String, String> filters);

    @Override
    public List<T> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, String> filters) {
        Pageable page = new PageRequest(first / pageSize, pageSize);

        setRowCount(countData(filters));
        return fetchData(page, filters);
    }

    @Override
    public boolean isRowAvailable() {
        if (dataSource == null) {
            return false;
        }

        int index = rowIndex % pageSize;
        return index >= 0 && index < dataSource.size();
    }

    @Override
    public Object getRowKey(T entity) {
        return getId(entity);
    }

    @Override
    public T getRowData() {
        if (dataSource == null) {
            return null;
        }

        int index = rowIndex % pageSize;
        if (index > dataSource.size()) {
            return null;
        }

        return dataSource.get(index);
    }

    @Override
    public T getRowData(String rowKey) {
        if (dataSource == null) {
            return null;
        }

        for (T entity : dataSource) {
            if (getId(entity).equals(rowKey)) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public int getPageSize() {
        return pageSize;
    }

    @Override
    public int getRowIndex() {
        return this.rowIndex;
    }

    @Override
    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    @Override
    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    @Override
    public int getRowCount() {
        return this.rowCount;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void setWrappedData(Object list) {
        this.dataSource = (List<T>) list;
    }

    @Override
    public Object getWrappedData() {
        return dataSource;
    }
}