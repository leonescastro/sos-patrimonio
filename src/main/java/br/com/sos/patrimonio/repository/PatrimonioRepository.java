package br.com.sos.patrimonio.repository;

import br.com.sos.patrimonio.dto.PatrimonioDto;
import br.com.sos.patrimonio.model.Marca;
import br.com.sos.patrimonio.model.Patrimonio;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class PatrimonioRepository implements PanacheRepository<Patrimonio> {

    public List<Patrimonio> findOrdered() {
        return listAll(Sort.by("nome"));
    }

}
