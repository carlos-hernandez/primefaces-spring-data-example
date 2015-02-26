package net.chernandez.example.business.common.predicates;

import com.mysema.query.types.Predicate;
import net.chernandez.example.model.common.QSubsidiary;

public class SubsidiaryPredicates {

    public static Predicate nameContains(final String searchTerm) {
        QSubsidiary qSubsidiary = QSubsidiary.subsidiary;
        return qSubsidiary.name.containsIgnoreCase(searchTerm);
    }

    public static Predicate descriptionContains(final String searchTerm) {
        QSubsidiary qSubsidiary = QSubsidiary.subsidiary;
        return qSubsidiary.description.containsIgnoreCase(searchTerm);
    }

    public static Predicate isActive(boolean active) {
        QSubsidiary qSubsidiary = QSubsidiary.subsidiary;
        return qSubsidiary.active.eq(active);
    }
}
