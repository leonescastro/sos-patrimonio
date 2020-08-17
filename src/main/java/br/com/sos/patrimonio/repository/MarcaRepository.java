package br.com.sos.patrimonio.repository;

import br.com.sos.patrimonio.model.Marca;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MarcaRepository implements PanacheRepository<Marca> {

    public List<Marca> findOrdered() {
        return listAll(Sort.by("nome"));
    }
}
