package net.chernandez.example.repositories.common;

import net.chernandez.example.model.common.Subsidiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

public interface SubsidiaryRepository
        extends JpaRepository<Subsidiary, Integer>, QueryDslPredicateExecutor<Subsidiary> {
}
