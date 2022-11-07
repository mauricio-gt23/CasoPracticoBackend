package cp.casopractico.services.domain.persistence.dataseed;

import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipoDocumentoLoader {

    private final TipoDocumentoRepository tipoDocumentoRepository;

    public TipoDocumentoLoader(TipoDocumentoRepository tipoDocumentoRepository) {
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }

    public void loadDocumentTypeData() {
        if (tipoDocumentoRepository.count() == 0) {
            TipoDocumento tipoDocumento1 = new TipoDocumento("4", "CARNET DE EXTRANJERIA", "CARNET DE EXTRANJERIA", true);
            TipoDocumento tipoDocumento2 = new TipoDocumento("7", "PASAPORTE", "PASAPORTE", true);
            TipoDocumento tipoDocumento3 = new TipoDocumento("11", "PARTIDA DE NACIMIENTO - IDENTIDAD", "PARTIDA DE NACIMIENTO - IDENTIDAD", true);
            TipoDocumento tipoDocumento4 = new TipoDocumento("99", "OTROS", "OTROS", true);
            TipoDocumento tipoDocumento5 = new TipoDocumento("6", "RUC", "REGISTRO UNICO DEL CONTRIBUYENTE", true);
            TipoDocumento tipoDocumento6 = new TipoDocumento("1", "DNI", "DOCUMENTO NACIONAL DE IDENTIDAD", true);
            tipoDocumentoRepository.save(tipoDocumento1);
            tipoDocumentoRepository.save(tipoDocumento2);
            tipoDocumentoRepository.save(tipoDocumento3);
            tipoDocumentoRepository.save(tipoDocumento4);
            tipoDocumentoRepository.save(tipoDocumento5);
            tipoDocumentoRepository.save(tipoDocumento6);
        }
    }
}

