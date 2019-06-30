package ftn.uns.ac.rs.SearchMicroservice.specifications;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import ftn.uns.ac.rs.SearchMicroservice.model.SobneUsluge;

public class SobneUslugeSpecifications {
	public static Specification<SobneUsluge> findBySobaUsluga(Integer idSoba, String usluga){
		return new Specification<SobneUsluge>() {
			final Collection<Predicate> predicates = new ArrayList<Predicate>();
			private static final long serialVersionUID = 1L;
			@Override
			public Predicate toPredicate(Root<SobneUsluge> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
				if(idSoba != null) {
					final Predicate sobaPredicate = criteriaBuilder.equal(root.join("soba").get("id"), idSoba);
					predicates.add(sobaPredicate);
				}
				if(usluga != "") {
					final Predicate uslugaPredicate = criteriaBuilder.equal(root.join("usluga").get("naziv"), usluga);
					predicates.add(uslugaPredicate);
				}
				return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
