package com.crm.mgr.specification;
;
import com.crm.mgr.entity.TaskEntity;
import org.springframework.data.jpa.domain.Specification;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class TaskSpecification implements Specification<TaskEntity> {
    private List<SearchCriteria> searchCriteria;

    public TaskSpecification() {
        this.searchCriteria = new ArrayList<>();
    }

    public void add(SearchCriteria criteria) {
        searchCriteria.add(criteria);
    }

    @Override
    public Predicate toPredicate(Root<TaskEntity> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        List<Predicate> predicates = new ArrayList<>();
        for (SearchCriteria criteria : searchCriteria) {
            switch(criteria.getOperation()) {
                case GREATER_THAN -> predicates.add(builder.greaterThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN -> predicates.add(builder.lessThan(root.get(criteria.getKey()), criteria.getValue().toString()));
                case GREATER_THAN_EQUAL -> predicates.add(builder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case LESS_THAN_EQUAL -> predicates.add(builder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString()));
                case NOT_EQUAL -> predicates.add(builder.notEqual(root.get(criteria.getKey()), criteria.getValue()));
                case EQUAL -> predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
                case MATCH -> predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase() + "%"));
                case MATCH_END -> predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), criteria.getValue().toString().toLowerCase() + "%"));
                case MATCH_START -> predicates.add(builder.like(builder.lower(root.get(criteria.getKey())), "%" + criteria.getValue().toString().toLowerCase()));
                case IN -> predicates.add(builder.in(root.get(criteria.getKey())).value(criteria.getValue()));
                case NOT_IN -> predicates.add(builder.not(root.get(criteria.getKey())).in(criteria.getValue()));
            }
        }
        return builder.and(predicates.toArray(new Predicate[0]));
    }
}
