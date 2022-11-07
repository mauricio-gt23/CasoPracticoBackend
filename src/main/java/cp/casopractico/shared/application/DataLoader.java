package cp.casopractico.shared.application;

import cp.casopractico.security.domain.persistence.dataseed.RolLoader;
import cp.casopractico.security.domain.persistence.dataseed.UserLoader;
import cp.casopractico.services.domain.persistence.dataseed.EntidadLoader;
import cp.casopractico.services.domain.persistence.dataseed.TipoContribuyenteLoader;
import cp.casopractico.services.domain.persistence.dataseed.TipoDocumentoLoader;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final EntidadLoader entidadLoader;
    private final TipoContribuyenteLoader tipoContribuyenteLoader;
    private final TipoDocumentoLoader tipoDocumentoLoader;
    private final UserLoader userLoader;
    private final RolLoader rolLoader;

    public DataLoader(EntidadLoader entidadLoader, TipoContribuyenteLoader tipoContribuyenteLoader, TipoDocumentoLoader tipoDocumentoLoader, UserLoader userLoader, RolLoader rolLoader) {
        this.entidadLoader = entidadLoader;
        this.tipoContribuyenteLoader = tipoContribuyenteLoader;
        this.tipoDocumentoLoader = tipoDocumentoLoader;
        this.userLoader = userLoader;
        this.rolLoader = rolLoader;
    }

    @Override
    public void run(String... args) throws Exception {
        rolLoader.loadRoleData();
        userLoader.loadUserData();
        tipoContribuyenteLoader.loadTaxpayerTypeData();
        tipoDocumentoLoader.loadDocumentTypeData();
        entidadLoader.loadEntityData();
    }
}
