package ftn.uns.ac.rs.model;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 3.3.2
 * 2019-06-08T10:32:41.331+02:00
 * Generated source version: 3.3.2
 *
 */
@WebService(targetNamespace = "http://rs.ac.uns.ftn/Model", name = "ProducerPort")
@XmlSeeAlso({ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ProducerPort {

    @WebMethod(operationName = "GetAllStatusSobe")
    @WebResult(name = "GetAllStatusSobeResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllStatusSobeResponse")
    public GetAllStatusSobeResponse getAllStatusSobe(

        @WebParam(partName = "GetAllStatusSobeRequest", name = "GetAllStatusSobeRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllStatusSobeRequest getAllStatusSobeRequest
    );

    @WebMethod(operationName = "CreateCenovnik")
    @WebResult(name = "CreateCenovnikResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateCenovnikResponse")
    public CreateCenovnikResponse createCenovnik(

        @WebParam(partName = "CreateCenovnikRequest", name = "CreateCenovnikRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateCenovnikRequest createCenovnikRequest
    );

    @WebMethod(operationName = "GetByIdSmestaj")
    @WebResult(name = "GetByIdSmestajResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdSmestajResponse")
    public GetByIdSmestajResponse getByIdSmestaj(

        @WebParam(partName = "GetByIdSmestajRequest", name = "GetByIdSmestajRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdSmestajRequest getByIdSmestajRequest
    );

    @WebMethod(operationName = "CreateTipSobe")
    @WebResult(name = "CreateTipSobeResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateTipSobeResponse")
    public CreateTipSobeResponse createTipSobe(

        @WebParam(partName = "CreateTipSobeRequest", name = "CreateTipSobeRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateTipSobeRequest createTipSobeRequest
    );

    @WebMethod(operationName = "GetAllLokacija")
    @WebResult(name = "GetAllLokacijaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllLokacijaResponse")
    public GetAllLokacijaResponse getAllLokacija(

        @WebParam(partName = "GetAllLokacijaRequest", name = "GetAllLokacijaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllLokacijaRequest getAllLokacijaRequest
    );

    @WebMethod(operationName = "GetByIdZauzece")
    @WebResult(name = "GetByIdZauzeceResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdZauzeceResponse")
    public GetByIdZauzeceResponse getByIdZauzece(

        @WebParam(partName = "GetByIdZauzeceRequest", name = "GetByIdZauzeceRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdZauzeceRequest getByIdZauzeceRequest
    );

    @WebMethod(operationName = "GetAllCenovnik")
    @WebResult(name = "GetAllCenovnikResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllCenovnikResponse")
    public GetAllCenovnikResponse getAllCenovnik(

        @WebParam(partName = "GetAllCenovnikRequest", name = "GetAllCenovnikRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllCenovnikRequest getAllCenovnikRequest
    );

    @WebMethod(operationName = "GetByIdUsluga")
    @WebResult(name = "GetByIdUslugaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdUslugaResponse")
    public GetByIdUslugaResponse getByIdUsluga(

        @WebParam(partName = "GetByIdUslugaRequest", name = "GetByIdUslugaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdUslugaRequest getByIdUslugaRequest
    );

    @WebMethod(operationName = "GetAllKategorijaSmestaja")
    @WebResult(name = "GetAllKategorijaSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllKategorijaSmestajaResponse")
    public GetAllKategorijaSmestajaResponse getAllKategorijaSmestaja(

        @WebParam(partName = "GetAllKategorijaSmestajaRequest", name = "GetAllKategorijaSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllKategorijaSmestajaRequest getAllKategorijaSmestajaRequest
    );

    @WebMethod(operationName = "GetAllSoba")
    @WebResult(name = "GetAllSobaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllSobaResponse")
    public GetAllSobaResponse getAllSoba(

        @WebParam(partName = "GetAllSobaRequest", name = "GetAllSobaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllSobaRequest getAllSobaRequest
    );

    @WebMethod(operationName = "CreateLokacija")
    @WebResult(name = "CreateLokacijaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateLokacijaResponse")
    public CreateLokacijaResponse createLokacija(

        @WebParam(partName = "CreateLokacijaRequest", name = "CreateLokacijaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateLokacijaRequest createLokacijaRequest
    );

    @WebMethod(operationName = "GetByIdKategorijaSmestaja")
    @WebResult(name = "GetByIdKategorijaSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdKategorijaSmestajaResponse")
    public GetByIdKategorijaSmestajaResponse getByIdKategorijaSmestaja(

        @WebParam(partName = "GetByIdKategorijaSmestajaRequest", name = "GetByIdKategorijaSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdKategorijaSmestajaRequest getByIdKategorijaSmestajaRequest
    );

    @WebMethod(operationName = "GetAllZauzece")
    @WebResult(name = "GetAllZauzeceResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllZauzeceResponse")
    public GetAllZauzeceResponse getAllZauzece(

        @WebParam(partName = "GetAllZauzeceRequest", name = "GetAllZauzeceRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllZauzeceRequest getAllZauzeceRequest
    );

    @WebMethod(operationName = "GetByIdCenovnik")
    @WebResult(name = "GetByIdCenovnikResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdCenovnikResponse")
    public GetByIdCenovnikResponse getByIdCenovnik(

        @WebParam(partName = "GetByIdCenovnikRequest", name = "GetByIdCenovnikRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdCenovnikRequest getByIdCenovnikRequest
    );

    @WebMethod(operationName = "GetByIdTipSmestaja")
    @WebResult(name = "GetByIdTipSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdTipSmestajaResponse")
    public GetByIdTipSmestajaResponse getByIdTipSmestaja(

        @WebParam(partName = "GetByIdTipSmestajaRequest", name = "GetByIdTipSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdTipSmestajaRequest getByIdTipSmestajaRequest
    );


    @WebMethod(operationName = "GetAllTipSobe")
    @WebResult(name = "GetAllTipSobeResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllTipSobeResponse")
    public GetAllTipSobeResponse getAllTipSobe(

        @WebParam(partName = "GetAllTipSobeRequest", name = "GetAllTipSobeRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllTipSobeRequest getAllTipSobeRequest
    );

    @WebMethod(operationName = "CreateKoordinate")
    @WebResult(name = "CreateKoordinateResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateKoordinateResponse")
    public CreateKoordinateResponse createKoordinate(

        @WebParam(partName = "CreateKoordinateRequest", name = "CreateKoordinateRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateKoordinateRequest createKoordinateRequest
    );

    @WebMethod(operationName = "CreateZauzece")
    @WebResult(name = "CreateZauzeceResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateZauzeceResponse")
    public CreateZauzeceResponse createZauzece(

        @WebParam(partName = "CreateZauzeceRequest", name = "CreateZauzeceRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateZauzeceRequest createZauzeceRequest
    );

    @WebMethod(operationName = "GetByIdKoordinate")
    @WebResult(name = "GetByIdKoordinateResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdKoordinateResponse")
    public GetByIdKoordinateResponse getByIdKoordinate(

        @WebParam(partName = "GetByIdKoordinateRequest", name = "GetByIdKoordinateRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdKoordinateRequest getByIdKoordinateRequest
    );

    @WebMethod(operationName = "CreateUsluga")
    @WebResult(name = "CreateUslugaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateUslugaResponse")
    public CreateUslugaResponse createUsluga(

        @WebParam(partName = "CreateUslugaRequest", name = "CreateUslugaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateUslugaRequest createUslugaRequest
    );

    @WebMethod(operationName = "GetByIdLokacija")
    @WebResult(name = "GetByIdLokacijaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdLokacijaResponse")
    public GetByIdLokacijaResponse getByIdLokacija(

        @WebParam(partName = "GetByIdLokacijaRequest", name = "GetByIdLokacijaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdLokacijaRequest getByIdLokacijaRequest
    );

    @WebMethod(operationName = "CreateTipSmestaja")
    @WebResult(name = "CreateTipSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateTipSmestajaResponse")
    public CreateTipSmestajaResponse createTipSmestaja(

        @WebParam(partName = "CreateTipSmestajaRequest", name = "CreateTipSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateTipSmestajaRequest createTipSmestajaRequest
    );
    
    @WebMethod(operationName = "CreateKategorijaSmestaja")
    @WebResult(name = "CreateKategorijaSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateKategorijaSmestajaResponse")
    public CreateKategorijaSmestajaResponse createKategorijaSmestaja(

        @WebParam(partName = "CreateKategorijaSmestajaRequest", name = "CreateKategorijaSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateKategorijaSmestajaRequest createKategorijaSmestajaRequest
    );

    @WebMethod(operationName = "CreateSmestaj")
    @WebResult(name = "CreateSmestajResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateSmestajResponse")
    public CreateSmestajResponse createSmestaj(

        @WebParam(partName = "CreateSmestajRequest", name = "CreateSmestajRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateSmestajRequest createSmestajRequest
    );

    @WebMethod(operationName = "GetByIdSoba")
    @WebResult(name = "GetByIdSobaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdSobaResponse")
    public GetByIdSobaResponse getByIdSoba(

        @WebParam(partName = "GetByIdSobaRequest", name = "GetByIdSobaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdSobaRequest getByIdSobaRequest
    );

    @WebMethod(operationName = "GetAllTipSmestaja")
    @WebResult(name = "GetAllTipSmestajaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllTipSmestajaResponse")
    public GetAllTipSmestajaResponse getAllTipSmestaja(

        @WebParam(partName = "GetAllTipSmestajaRequest", name = "GetAllTipSmestajaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllTipSmestajaRequest getAllTipSmestajaRequest
    );

    @WebMethod(operationName = "CreateSoba")
    @WebResult(name = "CreateSobaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateSobaResponse")
    public CreateSobaResponse createSoba(

        @WebParam(partName = "CreateSobaRequest", name = "CreateSobaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateSobaRequest createSobaRequest
    );

    @WebMethod(operationName = "GetAllKoordinate")
    @WebResult(name = "GetAllKoordinateResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllKoordinateResponse")
    public GetAllKoordinateResponse getAllKoordinate(

        @WebParam(partName = "GetAllKoordinateRequest", name = "GetAllKoordinateRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllKoordinateRequest getAllKoordinateRequest
    );

    @WebMethod(operationName = "CreateStatusSobe")
    @WebResult(name = "CreateStatusSobeResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "CreateStatusSobeResponse")
    public CreateStatusSobeResponse createStatusSobe(

        @WebParam(partName = "CreateStatusSobeRequest", name = "CreateStatusSobeRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        CreateStatusSobeRequest createStatusSobeRequest
    );

    @WebMethod(operationName = "GetAllSmestaj")
    @WebResult(name = "GetAllSmestajResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllSmestajResponse")
    public GetAllSmestajResponse getAllSmestaj(

        @WebParam(partName = "GetAllSmestajRequest", name = "GetAllSmestajRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllSmestajRequest getAllSmestajRequest
    );

    @WebMethod(operationName = "GetAllUsluga")
    @WebResult(name = "GetAllUslugaResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetAllUslugaResponse")
    public GetAllUslugaResponse getAllUsluga(

        @WebParam(partName = "GetAllUslugaRequest", name = "GetAllUslugaRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetAllUslugaRequest getAllUslugaRequest
    );

    @WebMethod(operationName = "GetByIdTipSobe")
    @WebResult(name = "GetByIdTipSobeResponse", targetNamespace = "http://rs.ac.uns.ftn/Model", partName = "GetByIdTipSobeResponse")
    public GetByIdTipSobeResponse getByIdTipSobe(

        @WebParam(partName = "GetByIdTipSobeRequest", name = "GetByIdTipSobeRequest", targetNamespace = "http://rs.ac.uns.ftn/Model")
        GetByIdTipSobeRequest getByIdTipSobeRequest
    );
}
