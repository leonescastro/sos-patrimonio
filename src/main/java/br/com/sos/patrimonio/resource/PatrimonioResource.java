package br.com.sos.patrimonio.resource;

import br.com.sos.patrimonio.dto.PatrimonioDto;
import br.com.sos.patrimonio.model.Marca;
import br.com.sos.patrimonio.model.Patrimonio;
import br.com.sos.patrimonio.repository.MarcaRepository;
import br.com.sos.patrimonio.repository.PatrimonioRepository;
import org.apache.commons.lang.StringUtils;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("patrimonios")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PatrimonioResource {

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    PatrimonioRepository patrimonioRepository;

    @GET
    @Path("")
    @Tag(name = "patrimonio")
    @Operation(description = "Recupera todas as marcas")
    public List<Patrimonio> listAll() {
        return patrimonioRepository.findOrdered();
    }

    @GET
    @Path("{id}")
    @Tag(name = "patrimonio")
    @Operation(description = "Recupera uma marca pelo ID")
    public Patrimonio findById(@PathParam("id") Long id) {
        return patrimonioRepository.findById(id);
    }

    @DELETE
    @Path("{id}")
    @Tag(name = "patrimonio")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Exclui um patrimônio")
    public void update(@PathParam("id") Long id) {
        patrimonioRepository.deleteById(id);
    }

    @PUT
    @Path("{id}")
    @Tag(name = "patrimonio")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Altera os dados de um patrimônio")
    public Patrimonio update(@PathParam("id") Long id, PatrimonioDto dto) {
        if (StringUtils.isEmpty(dto.getNome())) {
            throw new WebApplicationException("O nome do patrimônio não foi informado.", 422);
        }

        Patrimonio patrimonio = patrimonioRepository.findById(id);

        if (patrimonio == null) {
            throw new WebApplicationException(String.format("Patrimônio com o id %s não encontrada.", id), 404);
        }

        patrimonio.setNome(dto.getNome());
        patrimonio.getMarca().setId(dto.getMarcaId());

        return patrimonio;
    }

    @POST
    @Path("")
    @Tag(name = "patrimonio")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Inseri um novo patrimônio")
    public void save(PatrimonioDto dto) {

        if (StringUtils.isEmpty(dto.getNome())) {
            throw new WebApplicationException("O nome do patrimônio não foi informado.", 422);
        }

        Patrimonio patrimonio = patrimonioRepository.find("nome", dto.getNome()).firstResult();

        if (patrimonio != null) {
            throw new WebApplicationException(String.format("Existe um patrimônio cadastrado com este nome. (%s)", dto.getNome()), 422);
        }

        if (dto.getMarcaId() == null || dto.getMarcaId().intValue() == 0) {
            throw new WebApplicationException("O id da marca não foi informado.", 422);
        }

        Marca marca = marcaRepository.findById(dto.getMarcaId());

        if (marca == null) {
            throw new WebApplicationException(String.format("A marcada informada não existe. (%s)", dto.getMarcaId()), 422);
        }

        patrimonioRepository.persist(Patrimonio.builder()
                .nome(dto.getNome())
                .marca(Marca.builder().id(dto.getMarcaId()).build())
                .build());
    }

}
