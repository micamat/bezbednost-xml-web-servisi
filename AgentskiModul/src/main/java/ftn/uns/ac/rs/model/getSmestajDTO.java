package ftn.uns.ac.rs.model;

public class getSmestajDTO {
	 	protected Long id;
	    protected String naziv;
	    protected String opis;
	    protected String drzava;
	    protected String grad;
	    protected String ulica;
	    protected String broj;
	    protected long idLokacija;
	    protected String TipSmestaja;
	    protected String KategorijaSmestaja;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getNaziv() {
			return naziv;
		}
		public void setNaziv(String naziv) {
			this.naziv = naziv;
		}
		public String getOpis() {
			return opis;
		}
		public void setOpis(String opis) {
			this.opis = opis;
		}
		public String getDrzava() {
			return drzava;
		}
		public void setDrzava(String drzava) {
			this.drzava = drzava;
		}
		public String getGrad() {
			return grad;
		}
		public void setGrad(String grad) {
			this.grad = grad;
		}
		public String getUlica() {
			return ulica;
		}
		public void setUlica(String ulica) {
			this.ulica = ulica;
		}
		public String getBroj() {
			return broj;
		}
		public void setBroj(String broj) {
			this.broj = broj;
		}
		public String getTipSmestaja() {
			return TipSmestaja;
		}
		public void setTipSmestaja(String tipSmestaja) {
			TipSmestaja = tipSmestaja;
		}
		public String getKategorijaSmestaja() {
			return KategorijaSmestaja;
		}
		public void setKategorijaSmestaja(String kategorijaSmestaja) {
			KategorijaSmestaja = kategorijaSmestaja;
		}
		public long getIdLokacija() {
			return idLokacija;
		}
		public void setIdLokacija(long idLokacija) {
			this.idLokacija = idLokacija;
		}
	    
	    
}
