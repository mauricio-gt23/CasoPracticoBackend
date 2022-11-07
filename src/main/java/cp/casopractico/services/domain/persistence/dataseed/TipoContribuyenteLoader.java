package cp.casopractico.services.domain.persistence.dataseed;

import cp.casopractico.services.domain.model.TipoContribuyente;
import cp.casopractico.services.domain.persistence.TipoContribuyenteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TipoContribuyenteLoader {

    private final TipoContribuyenteRepository tipoContribuyenteRepository;

    public TipoContribuyenteLoader(TipoContribuyenteRepository tipoContribuyenteRepository) {
        this.tipoContribuyenteRepository = tipoContribuyenteRepository;
    }

    public void loadTaxpayerTypeData() {
        if (tipoContribuyenteRepository.count() == 0) {
            TipoContribuyente tipoContribuyente1 = new TipoContribuyente("Natural Sin Negocio", true);
            TipoContribuyente tipoContribuyente2 = new TipoContribuyente("Juridica", true);
            TipoContribuyente tipoContribuyente3 = new TipoContribuyente("Natural Con Negocio", true);
            TipoContribuyente tipoContribuyente4 = new TipoContribuyente("No Domiciliado", true);

            tipoContribuyenteRepository.save(tipoContribuyente1);
            tipoContribuyenteRepository.save(tipoContribuyente2);
            tipoContribuyenteRepository.save(tipoContribuyente3);
            tipoContribuyenteRepository.save(tipoContribuyente4);
        }
    }
}
