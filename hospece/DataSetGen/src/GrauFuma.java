public enum GrauFuma {    
	NAO_FUMA("nothing"), CASUALMENTE("little"), MODERADO("moderate"), DEPENDENTE("much");
     
    private final String valor;
    GrauFuma(String valorOp){
        valor = valorOp;
    }
    public String getValor(){
        return valor;
    }
}