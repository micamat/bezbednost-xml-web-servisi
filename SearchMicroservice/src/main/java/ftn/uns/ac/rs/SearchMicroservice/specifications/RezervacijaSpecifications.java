package ftn.uns.ac.rs.SearchMicroservice.specifications;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ftn.uns.ac.rs.SearchMicroservice.model.Rezervacija;

public class RezervacijaSpecifications {
	public static Specification<Rezervacija> findByDate(Date begin, Date end){
		return new Specification<Rezervacija>() {
			final Collection<Predicate> predicates = new ArrayList<Predicate>();
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Rezervacija> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if(begin != null) {
					final Predicate beginPred = criteriaBuilder.lessThan(root.<Date>get("datumOd"), begin);
					predicates.add(beginPred);
				}
				if(end != null) {
					final Predicate endPred = criteriaBuilder.greaterThan(root.get("datumDo").as(Date.class), end);
					predicates.add(endPred);
				}
				final Predicate slobodnaPred = criteriaBuilder.equal(root.join("statusSobe").get("naziv"), "slobodna");
				predicates.add(slobodnaPred);
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
			
		};
	}
}
