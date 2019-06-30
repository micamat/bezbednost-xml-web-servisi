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
	
	public static Specification<Smestaj> findByLokacijaTipKategorija(String drzava, String grad, String ulica, String tip, String kategorija, String kapacitet){
		return new Specification<Smestaj>() {
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<Smestaj> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				final Collection<Predicate> predicates = new ArrayList<Predicate>();
				if(drzava != "") {
					final Predicate drzavaPredicate = criteriaBuilder.like(root.join("lokacija").get("drzava"), "%"+drzava+"%");
					predicates.add(drzavaPredicate);
				}
				if(grad != "") {
					final Predicate gradPredicate = criteriaBuilder.like(root.join("lokacija").get("grad"), "%"+grad+"%");
					predicates.add(gradPredicate);
				}
				if(ulica != "") {
					final Predicate ulicaPredicate = criteriaBuilder.like(root.join("lokacija").get("ulica"), "%"+ulica+"%");
					predicates.add(ulicaPredicate);
				}
				if(tip != "") {
					final Predicate tipPredicate = criteriaBuilder.like(root.join("tipSmestaja").get("naziv"), "%"+tip+"%");
					predicates.add(tipPredicate);
				}
				if(kategorija != "") {
					final Predicate katPredicate = criteriaBuilder.like(root.join("kategorijaSmestaja").get("naziv"), "%"+kategorija+"%");
					predicates.add(katPredicate);
				}
				if(kapacitet != "") {
					final Predicate kapPredicate = criteriaBuilder.greaterThanOrEqualTo(root.get("kapacitet"), Integer.parseInt(kapacitet));
					predicates.add(kapPredicate);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	};
}
