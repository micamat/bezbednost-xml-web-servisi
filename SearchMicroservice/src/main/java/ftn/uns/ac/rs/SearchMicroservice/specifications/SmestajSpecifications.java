package ftn.uns.ac.rs.SearchMicroservice.specifications;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ftn.uns.ac.rs.SearchMicroservice.model.Smestaj;

public class SmestajSpecifications {
	
	public static Specification<Smestaj> findByLokacijaTipKategorija(String lokacija, String tip, String kategorija){
		return new Specification<Smestaj>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Smestaj> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				final Collection<Predicate> predicates = new ArrayList<Predicate>();
				if(lokacija != null) {
					final Predicate drzavaPredicate = criteriaBuilder.like(root.join("lokacija").get("drzava"), "%"+lokacija+"%");
					predicates.add(drzavaPredicate);
					
					final Predicate gradPredicate = criteriaBuilder.like(root.join("lokacija").get("grad"), "%"+lokacija+"%");
					predicates.add(gradPredicate);
					
					final Predicate ulicaPredicate = criteriaBuilder.like(root.join("lokacija").get("ulica"), "%"+lokacija+"%");
					predicates.add(ulicaPredicate);
				}
				if(tip != null) {
					final Predicate tipPredicate = criteriaBuilder.like(root.join("tipSmestaja").get("naziv"), "%"+tip+"%");
					predicates.add(tipPredicate);
				}
				if(kategorija != null) {
					final Predicate katPredicate = criteriaBuilder.like(root.join("kategorijaSmestaja").get("naziv"), "%"+kategorija+"%");
					predicates.add(katPredicate);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	};
}
