package br.com.sos.patrimonio.infra.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

/**
 * Classe para representar a estrutura da mensagem de erro conforme descrito no padrao de erros, aonde:
 *
 * Obrigatorios
 * code               : Contém o código do erro. Deve ser numérico e conter no máximo 3 dígitos
 * source             : Nome do módulo ou da classe ou, quando operação IIB, número sequencial do erro
 * message            : Mensagem de erro para o usuário
 *
 * Opcionais
 * userHelp           : Orientação sobre como o usuário pode resolver o problema
 * developerMessage   : Mensagem técnica para o desenvolvedor
 * moreInfo           : Complemento do erro para o desenvolvedor
 *  
 * sequential         : Sequencial do erro. Utilizado para compatibilidade com o Curio v. 0.2.x
 *
 * https://fontes.intranet.bb.com.br/dev/publico/padroes/blob/master/endpoints/errors-endpoint.md
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(name="Erro", description="Informações sobre o erro que ocorreu.")
public class Erro {

    private String code;
    private String message;
}
