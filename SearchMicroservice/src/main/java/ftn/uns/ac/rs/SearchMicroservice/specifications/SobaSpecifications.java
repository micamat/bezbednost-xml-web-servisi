package ftn.uns.ac.rs.SearchMicroservice.specifications;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ftn.uns.ac.rs.SearchMicroservice.model.Soba;

public class SobaSpecifications {
	public static Specification<Soba> findByTipSmestaj(String tip, Long idSmestaj){
		return new Specification<Soba>() {
			final Collection<Predicate> predicates = new ArrayList<Predicate>();
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Soba> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if(tip != null) {
					final Predicate tipPredicate = criteriaBuilder.like(root.join("tipSobe").get("naziv"), "%"+tip+"%");
					predicates.add(tipPredicate);
				}
				if(idSmestaj != null) {
					final Predicate smestajPredicate = criteriaBuilder.equal(root.join("smestaj").get("id"), idSmestaj);
					predicates.add(smestajPredicate);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
