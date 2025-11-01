package org.acme.model.DTO;
//so vai usar alguns
public class ConsultaDTO extends FuncionarioDTO  {
    private int id_paciente;
    private int id_funcionario;
    private String data_consulta;
    private String horas_consultas;
    private String informacao_consulta;

    public ConsultaDTO() {}

    public int getId_paciente() {
        return id_paciente;
    }

    public void setId_paciente(int id_paciente) {
        this.id_paciente = id_paciente;
    }

    public int getId_funcionario() {
        return id_funcionario;
    }

    public void setId_funcionario(int id_funcionario) {
        this.id_funcionario = id_funcionario;
    }

    public String getData_consulta() {
        return data_consulta;
    }

    public void setData_consulta(String data_consulta) {
        this.data_consulta = data_consulta;
    }

    public String getHoras_consultas() {
        return horas_consultas;
    }

    public void setHoras_consultas(String horas_consultas) {
        this.horas_consultas = horas_consultas;
    }

    public String getInformacao_consulta() {
        return informacao_consulta;
    }

    public void setInformacao_consulta(String informacao_consulta) {
        this.informacao_consulta = informacao_consulta;
    }
}
