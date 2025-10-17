package org.acme.model.DTO;
//so vai usar alguns
public class ConsultaDTO extends FuncionarioDTO {
    private String nome_paciente;
    private String funcionario_resposanvel;
    private String data_consulta;
    private String informacao_consulta;

    public ConsultaDTO() {}

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getFuncionario_resposanvel() {
        return funcionario_resposanvel;
    }

    public void setFuncionario_resposanvel(String funcionario_resposanvel) {
        this.funcionario_resposanvel = funcionario_resposanvel;
    }

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
