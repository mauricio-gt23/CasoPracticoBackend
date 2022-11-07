package cp.casopractico.services.domain.persistence.dataseed;

import cp.casopractico.services.domain.model.Entidad;
import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.services.domain.model.TipoDocumento;
import cp.casopractico.services.domain.persistence.EntidadRepository;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import cp.casopractico.services.domain.persistence.TipoDocumentoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EntidadLoader {

    private final EntidadRepository entidadRepository;
    private final TipoContribuyenteRepository tipoContribuyenteRepository;
    private final TipoDocumentoRepository tipoDocumentoRepository;

    public EntidadLoader(EntidadRepository entidadRepository, TipoContribuyenteRepository tipoContribuyenteRepository, TipoDocumentoRepository tipoDocumentoRepository) {
        this.entidadRepository = entidadRepository;
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
        this.tipoDocumentoRepository = tipoDocumentoRepository;
    }
    public void loadEntityData() {
        if (entidadRepository.count() == 0) {

            TipoDocumento tipoDocumento3 = tipoDocumentoRepository.getReferenceById(3L);
            TipoDocumento tipoDocumento6 = tipoDocumentoRepository.getReferenceById(6L);

            TipoContribuyente tipoContribuyente1 = tipoContribuyenteRepository.getReferenceById(1L);
            TipoContribuyente tipoContribuyente2 = tipoContribuyenteRepository.getReferenceById(2L);
            TipoContribuyente tipoContribuyente3 = tipoContribuyenteRepository.getReferenceById(3L);
            TipoContribuyente tipoContribuyente4 = tipoContribuyenteRepository.getReferenceById(4L);

            Entidad entidad1 = new Entidad("20505327552", "SYL S.A.C", "SYL CARGO NOMBRE COMERCIAL", "Jr. Comandante Jimenez Nro. 166 Int. a (entre Cuadra 7 y 8 Javier Padro Oeste)", "79845612",
                            true, tipoDocumento3, tipoContribuyente1);
            Entidad entidad2 = new Entidad("20543844838", "PUNTUAL EXPRESS S.A.C.", "", "MZA. F LOTE. 29 AS.RSD.MONTECARLO II LIMA - LIMA - SAN MARTIN DE PORRE", "",
                    true, tipoDocumento3, tipoContribuyente1);
            Entidad entidad3 = new Entidad("10410192999", "ALVAREZ MACHUCA RENZO GUSTAVO", "", "AV. LOS ALISOS MZA. G LOTE. 05 ASC. LA ALBORADA DE OQUENDO III ETAPA (CRUCE PTE OQUENDO CON AV.NESTOR GAMBETTA) PROV. CONST. DEL CALLAO - PROV. CONST. DEL CALLAO - CALLAO", "",
                    true, tipoDocumento3, tipoContribuyente3);
            Entidad entidad4 = new Entidad("20600131037", "CARNICOS MAFER S.A.C.", "", "CAL.EL UNIVERSO NRO. 327 URB. LA CAMPIÑA ZONA CINCO (ALTURA ", "",
                    true, tipoDocumento3, tipoContribuyente2);
            Entidad entidad5 = new Entidad("2055652821", "SUMAQUINARIA S.A.C.", "", "AV. M.SUCRE NRO. 455 DPTO. 603 LIMA - LIMA - MAGDALENA DEL MAR", "",
                    true, tipoDocumento3, tipoContribuyente2);
            Entidad entidad6 = new Entidad("20545412528", "OASIS FOODS S.A.C.", "", "CAL. FRANCISCO MASIAS NRO. 370 URB. SAN EUGENIO (PISO 7) LIM", "",
                    true, tipoDocumento3, tipoContribuyente2);
            Entidad entidad7 = new Entidad("20510620195", "INVERSIONES PRO3 SAC", "", "AV. AUTOPIDTA RAMIRO PRIALE LOTE. 02 A.V. PROP HUERTOS DE HU", "",
                    true, tipoDocumento3, tipoContribuyente2);
            Entidad entidad8 = new Entidad("20498383361", "REPUESTOS DAVID DIESEL E.I.R.L.", "", "CAR.VIA EVITAMIENTO MZA. 857 LOTE. 7 SEC. IRRIGACION EL CURAL 1 AREQUIPA - AREQUIPA - CERRO COLORADO", "",
                    true, tipoDocumento3, tipoContribuyente2);
            Entidad entidad9 = new Entidad("CNAH00003", "ANHUI HAYVO PROTECTIVE PRODUCT MANUFACTURING CO.,LTD", "", "173 FENGLE AVENUE,ECNOMIC DEVELOPMENT ZONE,QUANJIAO COUNTY", "",
                    true, tipoDocumento6, tipoContribuyente4);

            entidadRepository.save(entidad1);
            entidadRepository.save(entidad2);
            entidadRepository.save(entidad3);
            entidadRepository.save(entidad4);
            entidadRepository.save(entidad5);
            entidadRepository.save(entidad6);
            entidadRepository.save(entidad7);
            entidadRepository.save(entidad8);
            entidadRepository.save(entidad9);
        }
    }
}
