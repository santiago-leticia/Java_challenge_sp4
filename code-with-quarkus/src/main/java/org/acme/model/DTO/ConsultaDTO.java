package org.acme.model.DTO;
//so vai usar alguns
public class ConsultaDTO extends FuncionarioDTO {
    private String data_consulta;
    private String informacao_consulta;

    public ConsultaDTO() {}

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getInformacao_consulta() {
        return informacao_consulta;
    }

    public void setInformacao_consulta(String informacao_consulta) {
        this.informacao_consulta = informacao_consulta;
    }
}
