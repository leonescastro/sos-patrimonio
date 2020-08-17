package br.com.sos.patrimonio.resource;

import br.com.sos.patrimonio.dto.MarcaDto;
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

@Path("marcas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MarcaResource {

    @Inject
    MarcaRepository marcaRepository;

    @Inject
    PatrimonioRepository patrimonioRepository;

    @GET
    @Path("")
    @Tag(name = "marca")
    @Operation(description = "Recupera todas as marcas")
    public List<Marca> listAll() {
        return marcaRepository.findOrdered();
    }

    @GET
    @Path("{id}")
    @Tag(name = "marca")
    @Operation(description = "Recupera uma marca pelo ID")
    public Marca findById(@PathParam("id") Long id) {
        return marcaRepository.findById(id);
    }

    @GET
    @Tag(name = "marca")
    @Path("{id}/patrimonios")
    @Operation(description = "Recupera os patrimônios de uma marca")
    public List<Patrimonio> findByIdMarca(@PathParam("id") Long id) {
        return patrimonioRepository.find("marca_id", id).list();
    }

    @DELETE
    @Path("{id}")
    @Tag(name = "marca")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Exclui uma marca")
    public void delete(@PathParam("id") Long id) {
        Marca marca = marcaRepository.findById(id);

        if (marca == null) {
            throw new WebApplicationException(String.format("Marca com o id %s não encontrada.", id), 404);
        }

        marcaRepository.delete(marca);
    }

    @PUT
    @Path("{id}")
    @Tag(name = "marca")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Altera os dados de uma marca")
    public void update(@PathParam("id") Long id, MarcaDto dto) {
        if (StringUtils.isEmpty(dto.getNome())) {
            throw new WebApplicationException("O nome da marca não foi informada.", 422);
        }

        Marca marca = marcaRepository.find("nome", dto.getNome()).firstResult();

        if (marca != null) {
            throw new WebApplicationException(String.format("Existe uma marca cadastrada para este nome. (%s)", dto.getNome()), 422);
        }

        marca = marcaRepository.findById(id);

        if (marca == null) {
            throw new WebApplicationException(String.format("Marca com o id %s não encontrada.", id), 404);
        }

        marca.setNome(dto.getNome());
    }

    @POST
    @Path("")
    @Tag(name = "marca")
    @Transactional(rollbackOn = Exception.class)
    @Operation(description = "Inseri um nova marca")
    public void save(MarcaDto dto) {
        if (StringUtils.isEmpty(dto.getNome())) {
            throw new WebApplicationException("O nome da marca não foi informada.", 422);
        }

        Marca marca = marcaRepository.find("nome", dto.getNome()).firstResult();

        if (marca != null) {
            throw new WebApplicationException(String.format("Existe uma marca cadastrada para este nome. (%s)", dto.getNome()), 422);
        }

        marcaRepository.persist(Marca.builder().nome(dto.getNome()).build());
    }

}
