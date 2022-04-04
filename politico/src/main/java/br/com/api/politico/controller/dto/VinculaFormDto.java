package br.com.api.politico.controller.dto;

public class VinculaFormDto {

    private Long idAssociado;
    private Long idPartido;

    public Long getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Long idAssociado) {
        this.idAssociado = idAssociado;
    }

    public Long getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Long idPartido) {
        this.idPartido = idPartido;
    }

    public VinculaFormDto(Long idAssociado, Long idPartido) {
        this.idAssociado = idAssociado;
        this.idPartido = idPartido;
    }

    public VinculaFormDto() {

    }
}
